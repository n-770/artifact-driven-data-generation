package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.awt.Color;
import java.awt.Font;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

import org.uncommons.maths.random.DiscreteUniformGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.DummyFile;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.Facet;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.GeneratedArtefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Name;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.PhysicalDummyFile;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.XMLTestDataCollection;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.DialogFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.GenerateData;

/**
 * This class has the job of generating test data to the artefacts
 * and their facets. The factes have had been specified before the
 * this process started. If no settings had made on the facets the
 * process of generating test data would not have started.
 * </p>
 * This process may take some time in generating the data and there're
 * a lot of I/O bound tasks involved, too. So it is separated from
 * the EDT to the background. To give feedback, though, a status dialog
 * is used. The dialog itself is set to the foreground and is modal,
 * so no other task is permitted while doing the job. This is because
 * with this approach no changes to the artefacts and facets are guaranteed. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/12/09
 */
public class GenerateTestData extends SwingWorker<Void, Void>
{
	/**
	 * dialog displaying the progress and additional & helpful
	 * information. It is also needed to cancel the test data
	 * generation.
	 */
	private GenerateData dialog = null;
	
	/**
	 * this is the path to where we store the generated
	 * test data sets.
	 */
	private URI path_testdata = null;
	
	/**
	 * the file name
	 */
	private String fileName = null;
	
	/**
	 * the file extension
	 */
	private String fileExtension = null;
	
	/**
	 * creates an extra thread for the dialog.
	 */
	private DialogRunnable dialogRun = null;
	
	/**
	 * here is the 2nd progress listener.
	 * This one is on the main view.
	 */
	private ProgressListener progressListener = null;
	
	/**
	 * constructor is passed the dialog displaying the
	 * informations about the test data generation progress.
	 * The dialog is helpful of getting feedback from the system
	 * and enables the user to send a cancel request to the worker.
	 * 
	 * @param path
	 * 		the path to save the generated test data
	 * @param fileName
	 * 		the file name
	 * @param ext
	 * 		the file extension
	 */
	public GenerateTestData(URI path, String fileName, String ext)
	{
		this.path_testdata = path;
		this.fileName = fileName;
		this.fileExtension = ext;
		
		this.setUpDialog();
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		List<GeneratedArtefact> testDataCollection = null;
		
		// find all concrete artefacts
		List<MetaClass> cArtefacts = this.findConcreteArtefacts(ControllerFactory.getController().getArtefacts());
		
		int n = this.findQuantity(cArtefacts);
		
		// init progress bars
		this.initProgress(false, 0, n);
		
		// keeps track of generated data so far
		int generatedDataInTotal = 0;
		
		// create collection
		testDataCollection = this.generateCollection(cArtefacts, generatedDataInTotal);
		
		// testing & debugging
//		this.print(testDataCollection);
		
		// create XML
		boolean xml = this.createXMLTestDataCollection(testDataCollection);
		
		// create physical dummy file
		if(xml)
		{
			this.createPhysicalDummy(testDataCollection);
		}
		
		return null;
	}
	
	@Override
	protected void done()
	{
		if(this.isDone())
		{
			this.dialog.setOKButtonEnabled();
		}
	}
	
	/**
	 * creates an instance of the status dialog and
	 * sets up the needed settings to run properly.
	 * There's a reference of this worker needed to
	 * get the ability to cancel the job.
	 * Furthermore, the dialog is in an extra thread,
	 * to dialog and worker do not affect each other.
	 */
	private void setUpDialog()
	{
		this.dialog = DialogFactory.createGenerateDataDialog(
				DataGenerator.getApplication().getMainFrame());
		
		this.dialog.setWorkerReference(this);
		
		this.dialogRun = new DialogRunnable(this.dialog);
		
		new Thread(this.dialogRun).start();
	}
	
	/**
	 * sets the value of progress to all
	 * used progress bars.
	 * 
	 * @param n
	 * 		progress
	 */
	private void setProgressValue(int n)
	{
		this.dialog.setValueProgressBar(n);
		
		this.progressListener.setProgressValue(n);
	}
	
	/**
	 * initializes the used progress bars.
	 * One progress bar is in the status dialog
	 * and one in the status bar.
	 * 
	 * @param indeterminate
	 * 		visualize as indeterminate?
	 * @param min
	 * 		the minimum value
	 * @param max
	 * 		the maximum value possible
	 */
	private void initProgress(boolean indeterminate, int min, int max)
	{
		this.dialog.initProgressBar(min, max, indeterminate);
		
		this.progressListener = new ProgressListener(DataGeneratorView.getProgressBar(),
													 indeterminate,
													 max);
	}
	
	/**
	 * calculates the total quantity of data sets
	 * to generate. Remember, test data can only generated
	 * to concrete artefacts, but not to abstract ones.
	 * 
	 * @param artefacts
	 * 		the artefacts data structure to iterate through
	 * @return
	 * 		the total amount of concrete artefacts to which
	 * 		test data will be generated
	 */
	private List<MetaClass> findConcreteArtefacts(List<Artefact> artefacts)
	{
		List<MetaClass> cArtefacts = null;
		
		for(Iterator<Artefact> i=artefacts.iterator(); i.hasNext();)
		{
			Artefact artefact = i.next();
			
			for(Iterator<MetaClass> j=artefact.iterator(); j.hasNext();)
			{
				MetaClass mClass = j.next();
				
				if(mClass.isConcreteSpec())
				{
					if(cArtefacts == null)
					{
						cArtefacts = new ArrayList<MetaClass>();
					}
					
					cArtefacts.add(mClass);
				}
			}
		}
		
		return cArtefacts;
	}
	
	/**
	 * summarizes the amount of test data to generate.
	 * 
	 * @param artefacts
	 * 		list of artefacts
	 * @return
	 * 		amount of test data sets
	 */
	private int findQuantity(List<MetaClass> artefacts)
	{
		int n = 0;
		
		for(Iterator<MetaClass> i=artefacts.iterator(); i.hasNext();)
		{
			MetaClass mClass = i.next();
			
			n += mClass.getQuantity();
		}
		
		return n;
	}
	
	/**
	 * sets the name of the current artefact to which
	 * test data is gonna be generated into the status dialog.
	 * The colour is set to red and the font is serif with
	 * size of 14.
	 * 
	 * @param name
	 * 		the name of the artefact
	 */
	private void setHeadline(String name)
	{
		this.dialog.addStatusInformation(name,
										 Color.RED,
										 new Font(Font.SERIF, Font.PLAIN, 14));
	}
	
	/**
	 * this method does the main job of creating test data sets.
	 * All sets are bundled to one test data collection. As a result
	 * each test data generation process has it's own collection.
	 * For each concrete terms (artefacts) test data is generated only.
	 * It looks up the quantity of each concrete term and determines the
	 * related artefacts (abstract artefacts). This gives a set of artefacts
	 * to which test data is gonna be generated and furthermore all relevant
	 * facets are within those artefacts.
	 *  
	 * @param concreteArtefacts
	 * 		list of concrete artefacts. Only to these types test data is
	 * 		gonna be generated!
	 * @return
	 * 		generated collection of test data
	 */
	private List<GeneratedArtefact> generateCollection(List<MetaClass> concreteArtefacts, int generatedDataInTotal)
	{
		List<GeneratedArtefact> testDataCollection = new ArrayList<GeneratedArtefact>();
		
		// for each artefact generate the quantity of test data specified
		for(int i=0; i<concreteArtefacts.size(); i++)
		{
			MetaClass concreteTerm = concreteArtefacts.get(i);
			
			// headline
			this.setHeadline(concreteTerm.getName());
			
			// we would skip the term if no quantity set
			if(concreteTerm.getQuantity() > 0)
			{
				List<GeneratedArtefact> generatedArtefacts = this.generateDataTo(concreteTerm,
																		  		 generatedDataInTotal);
				
				for(Iterator<GeneratedArtefact> j=generatedArtefacts.iterator(); j.hasNext();)
				{
					testDataCollection.add(j.next());
				}
			}
		}
		
		return testDataCollection;
	}
	
	/**
	 * to meta class passed to this method test data is gonna
	 * be generated. It finds the related artefacts and builds
	 * a test data set with the settings previously done.
	 * To give also feedback to the user informations are
	 * passed to the status dialog. 
	 * 
	 * @param mClass
	 * 		the meta class (artefact) to generate data to
	 * @param generatedDataInTotal
	 * 		this is an counter of the generated data so far 
	 * @return
	 * 		test data set to passed artefact
	 */
	private List<GeneratedArtefact> generateDataTo(MetaClass mClass, int generatedDataInTotal)
	{
		List<GeneratedArtefact> collection = new ArrayList<GeneratedArtefact>();
		 
		int trackTermQuantity = 0;
		
		// find all artefacts needed for this term (parent facets are important!)
		List<MetaClass> relatedArtefacts = this.getRelatedArtefacts(mClass);
		
		// generate as many test data sets as defined
		for(int i=0; i<mClass.getQuantity(); i++)
		{
			GeneratedArtefact generatedArtefact = new GeneratedArtefact(mClass.getName());
			
			generatedArtefact.setArtefactType(mClass.getArtefactType());
			
			// give feedback
			generatedDataInTotal++;
			this.updateProgressLabel(i, generatedDataInTotal, mClass.getQuantity());
			
			// iterate through related artefact term
			for(Iterator<MetaClass> j=relatedArtefacts.iterator(); j.hasNext();)
			{
				MetaClass relatedTerm = j.next();
				
				GeneratedArtefact tmp = this.generateArtefact(relatedTerm, trackTermQuantity, mClass.getQuantity());
				
				if(tmp != null)
				{
					for(Iterator<Facet> k=tmp.iterator(); k.hasNext();)
					{
						generatedArtefact.addFacet(k.next());
					}
				}
			}
			
			// create & set path to dummy file
			generatedArtefact.setDummyFile(this.createAndSetPath(generatedArtefact, this.path_testdata));
			
			collection.add(generatedArtefact);
			
			trackTermQuantity++;
			
			this.setProgressValue(generatedDataInTotal);
		}
		
		return collection;
	}
	
	/**
	 * this method is called by {@link #generateDataTo(MetaClass, int)}
	 * and does part of the job in generating test data. But instead of
	 * generating the whole set at once if accomplish this sequentially.
	 * To the passed in meta class test data is gonna be generated. There'
	 * a counter parameter too. It gives information about how many data sets
	 * to artefact 'mClass' has been generated.
	 * 
	 * @param mClass
	 * 		the meta class (artefact) to generated data to
	 * @param trackTermQuantity
	 * 		keeps track of how much has been generated
	 * @param quantity
	 * 		the total quantity to generate
	 * @return
	 * 		one generated data subset
	 */
	private GeneratedArtefact generateArtefact(MetaClass mClass, int trackTermQuantity, int quantity)
	{
		GeneratedArtefact generatedArtefact = new GeneratedArtefact(mClass.getName());
		
		// iterate through facets
		for(Iterator<MetaAttribute> i=mClass.iterator(); i.hasNext();)
		{
			MetaAttribute mAttribute = i.next();
			
			Facet facet = this.generateFacet(mAttribute, trackTermQuantity, quantity);
			
			if(facet != null)
			{
				generatedArtefact.addFacet(facet);
			}
		}
		
		return generatedArtefact;
	}
	
	/**
	 * each artefact to genrate data to consists of facets.
	 * The handling of these facets is done at this method.
	 * To each meta class its facets are considered and test
	 * data is gonna be generated. The method awaits the meta
	 * class its attributes and the counter of generated data.
	 * Notice, the attributes are explicitly passed!
	 * </p>
	 * Data will be generated if the settings in the
	 * attributes do allow this. To put it more clearly,
	 * it checks the settings of <i>nullValueAllowed</i>,
	 * <i>multipleValues</i> and so on to generate test data.
	 * It combines the settings to make the most of it. 
	 * 
	 * @param mAttribute
	 * 		the attributes to which this method generates data.
	 * 		These fields will be the facets in the test data collection
	 * @param trackTermQuantity
	 * 		tracks down how many data sets to <i>mClass</i> has been generated.
	 * @param quantity
	 * 		the total amount of data to generate
	 * @return
	 * 		a facet (a facet is a attribute to which test data is generated)
	 */
	private Facet generateFacet(MetaAttribute mAttribute, int trackTermQuantity, int quantity)
	{
		Facet facet = new Facet(mAttribute.getName());
		
		boolean isNullValue = false;
		int howManyValues = 0;
		
		// null value
		if(mAttribute.isNullValueAllowed())
		{
			// yes
			if(this.stillNullValuesAllowed(trackTermQuantity, quantity, mAttribute.getPercentOfNullVallues()))
			{
				isNullValue = true;
			}
		}
		
		if(!isNullValue)
		{
			// multiple values
			howManyValues = this.multipleValues(mAttribute);
			
			// fill up with data
			List<String> values = this.getValues(mAttribute, howManyValues);
			
			if((values != null) && (values.size() > 0))
			{
				facet.setValues(values);
			}
			
			if(mAttribute.getUnit() != null)
			{
				facet.setUnit(mAttribute.getUnit());
			}
		}
		
		return facet;
	}
	
	/**
	 * checks if multiple values are allowed. This means,
	 * has the tester allowed to generated to an attribute
	 * more than one value? If the answer is yes, the total
	 * amount as an integer is returned, otherwise the lower bound
	 * gets returned as default value, that is <i>1</i> value.
	 * </p>
	 * The method makes use of the uniform probability distribution
	 * to generate more than one value. If the function is enabled,
	 * it uses the parameter <i>a</i> and <i>b</i> to generated
	 * how many values actually to generate to this. With this approach
	 * it's possible to have values in the interval [a, b].
	 * </p>
	 * <font size=6><b>Uniform distribution</b></font></br>
	 * X ~ U(a, b)</br>
	 * where a is the minimum and b is the maximum value.
	 * </p>
	 * Now suppose the function generating multiple values for an
	 * attribute has been enabled. The lower bound in an attribute
	 * is always 1 and is used as <i>a</i>. The upper bound is the
	 * maximum values to generate, this is <i>b</i>. Each values
	 * in the range [a, b] are equally likely. And with the help
	 * of the uniform distribution there is a value generated in
	 * the range of [a, b] describing how many values to generate
	 * actually for this attribute.
	 * 
	 * @param mAttribute
	 * 		has tester set to generate multiple values?
	 * @return
	 * 		amount of values to generate
	 */
	private int multipleValues(MetaAttribute mAttribute)
	{
		int howManyValues = 0;
		
		if(mAttribute.isMultiplicityAllowed())
		{
			// X ~ U(a, b)
			DiscreteUniformGenerator uniform = new DiscreteUniformGenerator(mAttribute.getLowerBound(),
																			mAttribute.getUpperBound(),
																			new MersenneTwisterRNG());
			
			howManyValues = uniform.nextValue().intValue();
		}
		else
		{
			// at least '1'
			howManyValues = mAttribute.getLowerBound();
		}
		
		return howManyValues;
	}
	
	/**
	 * this method combines the probability distribution and
	 * the fill type settings. This makes it easy to get the values
	 * generated made by the settings for this attribute.
	 * </p>
	 * First, there is a check if any probability distribution has been
	 * selected and does any fill type exist for this kind of attribute.
	 * When the attribute passes the test the actual values are generated
	 * with the probability distribution and the fill behaviour.
	 * The distribution gives information which values to take from the fill
	 * strategy and the fill strategy provides the values.
	 * 
	 * @param mAttribute
	 * 		attribute holds the settings needed to generated random values.
	 * @param n
	 * 		how many values do we need to generate?
	 * 		The number passed here comes from {@link #multipleValues(MetaAttribute)}.
	 * @return
	 * 		list of generated values with the length of <i>n</i>
	 */
	private List<String> getValues(MetaAttribute mAttribute, int n)
	{
		List<String> res = new ArrayList<String>();
		
		Map<Double, Double> generatedValues = null;
		
		// distribution & fill type set
		if((mAttribute.getDistribution() != null) && (mAttribute.getFillType() != null))
		{
			generatedValues = mAttribute.getDistribution().generateValues(
					n,
					ControllerFactory.getController().getPRNG()); 
			
			for(int i=0; i<generatedValues.size(); i++)
			{
				Double key = Double.valueOf(i);
				Double value = Double.valueOf(generatedValues.get(key));
				
				// name is handled different
				if(mAttribute.getFillType().getFillBehaviour() instanceof Name)
				{
					Name name = (Name) mAttribute.getFillType().getFillBehaviour();
					
					String v = name.getValueAt(value.intValue(),
											   value.intValue());
					
					if(v != null)
					{
						res.add(v);
					}
				}
				
				// other fill behaviour
				else
				{
					String v = mAttribute.getFillType().getValueAt(value.intValue());
					
					if(v != null)
					{
						res.add(v);
					}
				}
			}
		}
		
		return res;
	}
	
	/**
	 * with this method a check is done whether it is allowed
	 * to generate <i>null</i> values to attributes.
	 * If it is still possible to generate <i>null</i> values
	 * <i>true</i> is returned otherwise <i>false</i> 
	 * 
	 * @param trackTermQuantity
	 * 		a counter telling how many data sets has been generated
	 * 		for a meta class.
	 * @param total
	 * 		this is the total number of test data to generate
	 * @param percentNull
	 * 		tells how many sets should be have <i>null</i> values.
	 * @return
	 * 		true if and only if it is still possible to generate <i>null</i>
	 * 		values to an attribute
	 */
	private boolean stillNullValuesAllowed(int trackTermQuantity, int total, int percentNull)
	{
		boolean isAllowed = false;
		if(trackTermQuantity < this.calcPercentNullValues(total, percentNull))
		{
			isAllowed = true;
		}
		
		return isAllowed;
	}
	
	/**
	 * update the label in the status dialog.
	 * 
	 * @param j
	 * 		the current artefact number
	 * @param size
	 * 		the amount of generated test data so far
	 * @param quantity
	 * 		the amount of test data to generate for current artefact
	 */
	private void updateProgressLabel(int j, int size, int quantity)
	{
		this.dialog.setProgressInfo(String.valueOf(size));
		this.dialog.addStatusInformation("Successfully generated: " +(j+1) +" of " +quantity);
	}
	
	/**
	 * this method does an important job. It gathers all nodes
	 * from the leaf to the root. Those nodes are needed because
	 * the concrete term depends on them.
	 * </p>
	 * <font size=6><b>Example</b></font></br>
	 * Suppose you've got a <i>specification sheet</i> to generate
	 * test data to and the hierarchy is as follows:</br>
	 * <pre>
	 * <code>
	 * -document
	 *    - specification sheet
	 *    - scope of statement
	 *    - book
	 *    - contract
	 *       - contract of employment
	 *       - contract of sale
	 * </code>
	 * </pre>
	 * As you can see in the above example, the nodes needed to generate
	 * a <i>specification sheet</i> are the <font color=red>specification sheet</font>
	 * itself and its parent node <font color=red>document</font>. The process
	 * would be recursive, but here the document is the root node, too.
	 * As a result, two nodes in question are returned.
	 * 
	 * @param mClass
	 * 		the concrete artefact in node in question to which related nodes
	 * 		are searched for. The search goes always from the leaf to the root.
	 *  	In other words, it's a button up search.
	 * @return
	 * 		a list of related nodes
	 */
	private List<MetaClass> getRelatedArtefacts(MetaClass mClass)
	{
		List<MetaClass> res = null;
		
		List<MetaClass> nodes = new ArrayList<MetaClass>();;
		
		nodes.add(mClass);
		
		MetaClass mClassTmp = mClass;
		
		while(mClassTmp.hasSuperClass())
		{
			nodes.add(mClassTmp.getSuperClass());
			
			mClassTmp = mClassTmp.getSuperClass();
		}
		
		// node in question has parents
		if(nodes.size() > 0)
		{
			res = new ArrayList<MetaClass>();
			
			for(int i=nodes.size()-1; i>=0; i--)
			{
				res.add(nodes.get(i));
			}
		}
		
		return res;
	}
	
	/**
	 * calculates the portion with the percent formula.
	 * To get an integer value the result is rounded up.
	 * </p>
	 * <font size=6><b>Percent formula</b></font></br>
	 * P = (p * B) / 100
	 * 
	 * @param B
	 * 		the base value
	 * @param p
	 * 		percentage
	 * @return
	 * 		Portion
	 */
	private int calcPercentNullValues(int B, int p)
	{
		return (int) Math.ceil((p * B) / 100);
	}
	
	/**
	 * creates the path to the dummy file. It consists
	 * of the folder to save and a filename and a file extension.
	 * Notice, only artefacts of type <i>documents</i> are allowed to
	 * have a dummy file. The dummy file consists of facets <i>file name</i>
	 * and <i>file format</i>, also known as extension.
	 * </p>
	 * <font size=6><b>Example</b></font></br>
	 * Here an example to make it more clear. 
	 * Suppose you want to to save the generated test data collection
	 * into the following folder: </br>
	 * <font color=blue>/user/home/testData/collection</font>.
	 * The facet values of <i>fileName</i> and <i>fileFormat</i> are:</br>
	 * fileName = Head first Statistics </br>
	 * fileFormat = pdf </br>
	 * When these parts are combined into a path then the result would be
	 * <font color=green>/user/home/testData/collection/Head first Statistics.pdf</font>
	 * The result of these settings would generated the an artefact that
	 * looks similar to the following generated example.
	 * </p>
	 * <img src="generatedArtefact.jpg" alt="generatedArtefact.jpg"/>
	 * 
	 * @param artefact
	 * 		generated artefact to which a dummy is gonna be created
	 * @param path
	 * 		the path to save the test collection 
	 * @return
	 * 		dummy file with path and corresponding file name & file format
	 */
	private DummyFile createAndSetPath(GeneratedArtefact artefact, URI path)
	{
		DummyFile dummyFile = new DummyFile();
		
		String filePath = path.toString();
		StringBuffer fileName = new StringBuffer();
		StringBuffer fileFormat = new StringBuffer();
		
		if(artefact.getArtefactType().getName().equals("Documents"))
		{
			// iterate through facets
			for(Iterator<Facet> i=artefact.iterator(); i.hasNext();)
			{
				Facet facet = i.next();
				
				// figure out file name
				if((facet.getName().equalsIgnoreCase("fileName"))
						||
				   (facet.getName().equalsIgnoreCase("dateiName")))
				{
					if((facet.getValue() != null) && (!facet.getValue().equals("")))
					{
						fileName.append(facet.getValue());
					}
				}
				
				// figure out file format
				if((facet.getName().equalsIgnoreCase("fileFormat"))
						||
				   (facet.getName().equalsIgnoreCase("fileExtension"))
				   		||
				   (facet.getName().equalsIgnoreCase("dateiFormat"))
				   		||
				   (facet.getName().equalsIgnoreCase("dateiErweiterung")))
				{
					if((facet.getValue() != null) && (!facet.getValue().equals("")))
					{
						fileFormat.append(facet.getValue());
					}
				}
			}
			
			// path
			if((filePath != null) && (filePath.length() > 0))
			{
				// file name
				if((fileName.length() == 0) && (artefact.getName() != null))
				{
					fileName.append(artefact.getName());
				}
				
				if(fileName.length() > 0)
				{
					// file format
					if(fileFormat.length() > 0)
					{
						// common in Windows
						dummyFile.setPath(filePath);
						dummyFile.setFileName(fileName.toString());
						dummyFile.setFileFormat(fileFormat.toString());
					}
					else
					{
						// UNIX like
						dummyFile.setPath(filePath);
						dummyFile.setFileName(fileName.toString());
					}
				}
			}
		}
		
		return dummyFile;
	}
	
	/**
	 * this method has the job of creating a XML test data collection
	 * of the generated data sets. To do this <i>JDOM</i> is used and
	 * if the collection has been created with success the status
	 * information dialog is updated and tells infos in <font color=green>green</font>
	 * letters otherwise in <font color=red>red</font>
	 * 
	 * @param testDataCollection
	 * 		that's the generated test data collection to
	 * 		serialize as XML file.
	 * @return
	 * 		true, if successfully XML test collection has been created
	 */
	private boolean createXMLTestDataCollection(List<GeneratedArtefact> testDataCollection)
	{
		XMLTestDataCollection xml = new XMLTestDataCollection();
		
		URI xmlFile = URI.create(this.path_testdata +"/" +this.fileName +"." +this.fileExtension);
		
		boolean success = xml.save(xmlFile, null, testDataCollection);
		
		if(success)
		{
			this.dialog.addStatusInformation(
					"\nXML test data collection successfully created!"
					+"\n"
					+"at "
					+xmlFile.toString(),
					Color.GREEN);
		}
		else
		{
			this.dialog.addStatusInformation(
					"\nUpps, something got wrong trying to create XML test data collection!"
					+"\n"
					+"at "
					+xmlFile.toString(),
					Color.RED);
		}
		
		return success;
	}
	
	/**
	 * this method does the creation of the dummy files.
	 * It is the last process in the test data generation process.
	 * And it is usually called when the XML collection has been
	 * created with success.
	 * </p>
	 * The method uses the path stored in the generated artefacts.
	 * It is not necessary to provide additional information therefore.
	 * 
	 * @param collection
	 * 		test data collection to which we create a dummy file
	 * @return
	 * 		true, if successfully created dummy files
	 */
	private boolean createPhysicalDummy(List<GeneratedArtefact> collection)
	{
		boolean success = true;
		
		PhysicalDummyFile dummy = new PhysicalDummyFile();
		
		for(Iterator<GeneratedArtefact> i=collection.iterator(); i.hasNext();)
		{
			GeneratedArtefact ga = i.next();
			
			if(ga.getArtefactType().isDummyFileAllowed())
			{
				// write dummy file with meta data
				dummy.createDummyFile(ga.getDummyFile().getPathWithFileName(), ga);
			}
		}
		
		if(success)
		{
			this.dialog.addStatusInformation(
					"\n"
					+"Physical dummy files successfully created!"
					+"\n",
					Color.ORANGE);
		}
		else
		{
			this.dialog.addStatusInformation(
					"Uups, something got wrong in creating physical dummy files!"
					+"n",
					Color.RED);
		}
		
		return success;
	}
	
	/**
	 * only for debugging and testing purposes
	 * 
	 * @param collection
	 * 		generated test data collection
	 */
	@SuppressWarnings("unused")
	private void print(List<GeneratedArtefact> collection)
	{
		for(Iterator<GeneratedArtefact> i=collection.iterator(); i.hasNext();)
		{
			System.out.println(i.next());
		}
	}
	
	/**
	 * This class uses the runnable interface and does
	 * only one thing. When created an the thread starts
	 * the dialog shows up. A thread is needed to make
	 * updates on the dialog at the same time the background
	 * thread runs.
	 * </p>
	 * There's still one thing to mention. It is a private
	 * inner class and only accessable to the test data
	 * generation thread.
	 * 
	 * @author Michael Munz
	 * @version 0.1
	 * @since May/13/09
	 */
	private class DialogRunnable implements Runnable
	{
		private GenerateData dialog = null;
		
		public DialogRunnable(GenerateData dialog)
		{
			this.dialog = dialog;
		}
		
		@Override
		public void run()
		{
			this.dialog.setVisible(true);
		}
	}
}