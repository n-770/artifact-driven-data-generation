package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.NormalDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ObserverDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.PRNG;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.UniformDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Address;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Country;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Dates;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Email;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.FirstName;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Generator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.LastName;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Name;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.ObserverGenerator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Sex;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.ObserverUnit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.ObserverUnitCustom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.Ecore;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.XMLStartup;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.XMLStartupTag;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.Model;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.ModelFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.DataType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ObserverArtefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * The <i>ControllerImpl</i> class implements the {@link Controller}
 * and provides accessors to manipulate the underlying data model.
 * This class is the only one which has a reference to the model
 * and works as the intermediate for the view.
 * </p>
 * <font size=6><b>Singelton Pattern</b></font></br>
 * Notice, that it is only permitted to have one instance of the
 * controller class. As a result of this, it is implemented as
 * <i>Singleton</i> pattern.
 * This is helpy for the view, 'cos the whole view package must have
 * access to it. And with this pattern one doesn't have to worry about
 * handing down references to the controller layer.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since 03/24/09
 */
public class ControllerImpl implements Controller
{
	/**
	 * <b>Controller</b> has a reference to model.
	 * In fact, it is the only class which as direct access
	 * to it.
	 */
	private Model model = null;
	
	/**
	 * caches the path to XML startup file.
	 */
	private java.net.URI pathXML = null;
	
	/**
	 * holds the path to XML Schema Description (XSD)
	 * of XML startup file
	 */
	private java.net.URI pathXSD = null;
	
	/**
	 * reference to XML handler
	 */
	private XMLStartup xml = null;
	
	/**
	 * flag indicates whether to log or not.
	 * The value comes at boot time from the
	 * startup XML file and at runtime from
	 * UI. 
	 */
	private Boolean logging = null;
	
	/**
	 * to be able to generate dates between
	 * an interval an lower bound is needed.
	 * The lower bound has to be set at any time.
	 */
	private Date date_lowerBound = null;
	
	/**
	 * to be able to generate dates between
	 * an interval of dates an upper bound is needed.
	 * This value could be defined but isn't needed to be.
	 * </p>
	 * In fact, if the date is not set the sytem takes
	 * the current year as the default behaviour.
	 */
	private Date date_upperBound = null;
	
	/**
	 * here is the pseudo random number generator stored.
	 * Remember, is is possible to define one in setup.xml.
	 * If no one is defined the default will be MersenneTwister.
	 */
	private PRNG prng = null;
	
	/**
	 * default constructor of {@link Controller} class.
	 * It has restricted visibility.
	 * Please do not use it for object creation.
	 * Instead us the provided factory, which does the job.
	 * </p>
	 * The constructor creates an instance of it's model.
	 */
	protected ControllerImpl()
	{
		this.model = ModelFactory.create();
	}
	
	/**
	 * this method is only used in the startup process.
	 * Because at this stage we don't know whether logging
	 * is gonna be enabled or not. If the flag is not set
	 * (null) system will load the startup file.
	 * 
	 * @return
	 * 		boolean object or null
	 */
	public Boolean getLogging()
	{
		return this.logging;
	}
	
	/**
	 * gets the lower bound of the date interval.
	 * 
	 * @return
	 * 		lower bound
	 */
	public Date getDateLowerBound()
	{
		return this.date_lowerBound;
	}
	
	/**
	 * gets the upper bound of the date interval.
	 * 
	 * @return
	 * 		upper bound
	 */
	public Date getDateUpperBound()
	{
		return this.date_upperBound;
	}
	
	@Override
	public EPackage loadEcoreModel(URI uri)
	{
		Ecore ecore = new Ecore();

		return ecore.load(uri);
	}

	@Override
	public EPackage loadEcoreModel(String uri)
	{
		return this.loadEcoreModel(URI.createFileURI(uri));
	}
	
	@Override
	public void setEcoreModel(EPackage ePackage)
	{
		this.model.setResource(ePackage);
	}
	
	@Override
	public EPackage getEcoreModel()
	{
		return this.model.getResource();
	}
	
	@Override
	public void printEcoreModel(EPackage ePackage)
	{
		for(Iterator<EClassifier> i=ePackage.getEClassifiers().iterator(); i.hasNext();)
		{
			EClassifier classifier = i.next();
			
			// class
			if(classifier instanceof EClass)
			{
				EClass eClass =  (EClass) classifier;
				
				System.out.println(eClass.getName());
				System.out.println(this.generateLine(eClass.getName()));
				
				for(Iterator<EClass> l=eClass.getESuperTypes().iterator(); l.hasNext();)
				{
					EClass eSuperClass = l.next();
					
					System.out.println("base class: " +eSuperClass.getName());
				}
				
				// attributes
				for(Iterator<EAttribute> j=eClass.getEAttributes().iterator(); j.hasNext();)
				{
					EAttribute attribute = j.next();

					System.out.println("facet: " +attribute.getName() +" " +attribute.getEType().getName());
				}
				
				// reference
				for(Iterator<EReference> k=eClass.getEReferences().iterator(); k.hasNext();)
				{
					EReference reference = k.next();

					System.out.println("refernce: " +reference.getName());
				}
				
				System.out.println();
			}
			
			// enum
			else if(classifier instanceof EEnum)
			{
				EEnum eEnum = (EEnum) classifier;
				
				System.out.println("enum: " +eEnum.getName());

				for(Iterator<EEnumLiteral> j=eEnum.getELiterals().iterator(); j.hasNext();)
				{
					EEnumLiteral literal = j.next();

					System.out.println("literal: " +literal.getName());
				}
			}
			
			// datatype
			else if(classifier instanceof EDataType)
			{
				EDataType dataType = (EDataType) classifier;

				System.out.println("dataType: " +dataType.getInstanceClassName());
			}
		}
	}
	
	@Override
	public void loadStartupFile(java.net.URI xml, java.net.URI xsd)
	{
		this.xml = new XMLStartup();
		
		try
		{
			this.xml.loadStartUp(xml, xsd);
			
			this.pathXML = xml;
			this.pathXSD = xsd;
		}
		catch(ValidationException ve)
		{
			String message = "A validation execption has been occured!\n\n"
				+"This happens usually when the startup file is\n"
				+"invalid or corrupted or other bad things have had happened.\n"
				+"Therefore, the system is going to be shut down!\n";
			
			ErrorMessage.getInstance().printMessage(ve, message, "ValidationException");
			
			DataGenerator.getApplication().exit();
		}
	}
	
	@Override
	public List<Artefact> getArtefacts()
	{
		return this.model.getArtefacts();
	}
	
	@Override
	public Artefact getArtefact(ArtefactType type)
	{
		Artefact artefact = null;
		
		for(Iterator<Artefact> i=this.iteratorArtefacts(); i.hasNext();)
		{
			Artefact arte = i.next();
			
			// got it
			if(arte.getArtefactType().getName().equals(type.getName()))
			{
				artefact = arte;
			}
		}
		
		return artefact;
	}
	
	@Override
	public MetaClass getMetaClass(String name, ArtefactType type)
	{
		MetaClass res = null;
		
		if(this.getArtefacts() != null)
		{
			for(Iterator<Artefact> i=this.iteratorArtefacts(); i.hasNext();)
			{
				Artefact artefact = i.next();
				
				if(artefact.getArtefactType().getName().equals(type.getName()))
				{
					for(Iterator<MetaClass> j=artefact.iterator(); j.hasNext();)
					{
						MetaClass mClass = j.next();
						
						if(mClass.getName().equals(name))
						{
							res = mClass;
						}
					}
				}
			}
		}
		
		return res;
	}
	
	@Override
	public Iterator<Artefact> iteratorArtefacts()
	{
		return this.model.iteratorArtefact();
	}
	
	@Override
	public void addArtefact(Artefact artefact)
	{
		this.model.addArtefact(artefact);
	}
	
	@Override
	public boolean addToArtefact(MetaClass mClass)
	{
		boolean successful = false;
		boolean alreadyExits = false;
		
		if(this.getArtefacts() != null)
		{
			for(Iterator<Artefact> i=this.iteratorArtefacts(); i.hasNext();)
			{
				Artefact artefact = i.next();
				
				// put it in here
				if(artefact.getArtefactType().getName().equals(mClass.getArtefactType().getName()))
				{
					artefact.addArtefact(mClass);
					
					successful = true;
					alreadyExits = true;
				}
			}
		}
		
		// create new one
		if(!alreadyExits)
		{
			Artefact artefact = ArtefactFactory.createArtefact();
			
			artefact.setArtefactType(mClass.getArtefactType());
			artefact.addArtefact(mClass);
			
			this.addArtefact(artefact);
			
			successful = true;
			alreadyExits = true;
		}
		
		return successful;
	}
	
	@Override
	public List<MetaClass> getArtefacts(EPackage ePackage)
	{
		List<MetaClass> availableArtefacts = new ArrayList<MetaClass>();
		
		for(Iterator<EClassifier> i=ePackage.getEClassifiers().iterator(); i.hasNext();)
		{
			EClassifier eClassifier = i.next();
			
			// class
			if(eClassifier instanceof EClass)
			{
				EClass eClass = (EClass) eClassifier;
				
				MetaClass mClass = ArtefactFactory.createMetaClass();
				
				mClass.setName(eClass.getName());
				
				availableArtefacts.add(mClass);
			}
		}
		
		return availableArtefacts;
	}
	
	@Override
	public void createArtefacts(List<MetaClass> selection, DataGeneratorView view)
	{
		// iterate through selection
		for(Iterator<MetaClass> i=selection.iterator(); i.hasNext();)
		{
			MetaClass artefact = i.next();
			
			/* 
			 * the cases below, has to be seen like the structure in a UML model.
			 * 
			 * 		root = is the top-level class in an UML.
			 * 			   That means, it has itself no base class
			 * 
			 * 		node = is the intermediate class in an UML.
			 * 			   It has a base class and a sub-class.
			 * 
			 * 		leaf = is a concrete specification 
			 */
			
			// case: root
			if(this.isType(UMLType.ROOT, artefact))
			{
				List<MetaClass> concSpec = this.buildUpDataStructureRoot(this.getEcoreModel(),
																		 artefact);
				
				MetaClass mClass = null;
				
				for(int j=0; j<concSpec.size(); j++)
				{
					concSpec.get(j).setArtefactType(artefact.getArtefactType());
					
					mClass = this.buildUpDataStructureLeaf(this.getEcoreModel(),
															 concSpec.get(j));
					
					// update model
					this.addToArtefact(mClass);
				}
			}
			
			// case: node
			else if(this.isType(UMLType.NODE, artefact))
			{
				List<MetaClass> concSpec = this.buildUpDataStructureRoot(this.getEcoreModel(),
																		 artefact);
				
				MetaClass mClass = null;
				
				for(int j=0; j<concSpec.size(); j++)
				{
					concSpec.get(j).setArtefactType(artefact.getArtefactType());
					
					mClass = this.buildUpDataStructureLeaf(this.getEcoreModel(),
															 concSpec.get(j));
					
					// update model
					this.addToArtefact(mClass);
				}
			}
			
			// case: leaf
			else if(this.isType(UMLType.LEAF, artefact))
			{
				artefact.setToConcreteSpec();
				
				MetaClass mClass = this.buildUpDataStructureLeaf(getEcoreModel(), artefact);
				
				// update model
				this.addToArtefact(mClass);
			}
		}
		
//		this.model.print();
		
		// register observer to all selected types
		this.registerObserverToAllSelectedArtefactTypes(view);
	}
	
	@Override
	public void registerObserverArtefact(ObserverArtefact observer, ArtefactType type)
	{
		if(this.getArtefacts() != null)
		{
			for(Iterator<Artefact> i=this.getArtefacts().iterator(); i.hasNext();)
			{
				Artefact artefact = i.next();
				
				if(artefact.getArtefactType().getName().equals(type.getName()))
				{
					artefact.registerObserverArtefact(observer, type);
				}
			}
		}
	}
	
	@Override
	public void removeObserverArtefact(ObserverArtefact observer, ArtefactType type)
	{
		if(this.getArtefacts() != null)
		{
			for(Iterator<Artefact> i=this.getArtefacts().iterator(); i.hasNext();)
			{
				Artefact artefact = i.next();
				
				if(artefact.getArtefactType().getName().equals(type.getName()))
				{
					artefact.removeObserverArtefact(observer, type);
				}
			}
		}
	}
	
	/**
	 * Controller itself doesn't notify about changes.
	 * It does the observed subject itself.
	 * So as a matter of fact, this isn't implemented.
	 * </p>
	 * It throws an {@link UnsupportedOperationException}.
	 * 
	 * @see Artefact for implementation details.
	 */
	@Override
	public void notifyObserverArtefact()
	{
		// not used here, instead its initiated by Artefact
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void addArtefactType(ArtefactType type)
	{
		this.model.addArtefactType(type);
		
		ControllerFactory.getControllerBackgroundTask().updateXMLFile(this.pathXML,
																	  this.pathXSD,
																	  this.xml,
																	  true,
																	  XMLStartupTag.ARTEFACT,
																	  type);
	}
	
	@Override
	public void addArtefactType(String name, boolean createDummy)
	{
		ArtefactType type = ArtefactFactory.createArtefactType();
		
		type.setName(name);
		type.isDummyFileAllowed(createDummy);
		
		this.addArtefactType(type);
	}
	
	@Override
	public List<ArtefactType> getArtefactTypes()
	{
		return this.model.getArtefactTypes();
	}
	
	@Override
	public ArtefactType getArtefactType(String name)
	{
		ArtefactType res = null;
		
		for(Iterator<ArtefactType> i=this.iteratorArtefactType(); i.hasNext();)
		{
			ArtefactType type = i.next();
			
			if(type.getName().equals(name))
			{
				res = type;
			}
		}
		
		return res;
	}
	
	@Override
	public boolean removeArtefactType(ArtefactType type)
	{
		boolean successful = false;
		
		int n = this.getArtefactTypes().indexOf(type);
		
		if(n >= 0)
		{
			this.getArtefactTypes().remove(n);
			
			this.removeArtefact(type);
			
			ControllerFactory.getControllerBackgroundTask().updateXMLFile(this.pathXML,
																		  this.pathXSD,
																		  this.xml,
																		  false,
																		  XMLStartupTag.ARTEFACT,
																		  type);
			
			successful = true;
		}
		
		return successful;
	}
	
	@Override
	public boolean removeArtefactType(String name)
	{
		boolean successful = false;
		
		for(int i=0; i<this.getArtefactTypes().size(); i++)
		{
			ArtefactType type = this.getArtefactTypes().get(i);
			
			if(type.getName().equals(name))
			{
				this.getArtefactTypes().remove(i);
				
				this.removeArtefact(type);
				
				ControllerFactory.getControllerBackgroundTask().updateXMLFile(this.pathXML,
																			  this.pathXSD,
																			  this.xml, 
																			  false,
																			  XMLStartupTag.ARTEFACT,
																			  type);
				
				successful = true;
			}
		}
		
		return successful;
	}
	
	@Override
	public boolean hasArtefactType(String name)
	{
		boolean isType = false;
		
		for(Iterator<ArtefactType> i=this.getArtefactTypes().iterator(); i.hasNext();)
		{
			ArtefactType type = i.next();
			
			// found
			if(type.getName().equals(name))
			{
				isType = true;
			}
		}
		
		return isType;
	}
	
	@Override
	public Iterator<ArtefactType> iteratorArtefactType()
	{
		return this.model.iteratorArtefactType();
	}
	
	@Override
	public int getNoOfAttributes()
	{
		return this.model.getNoOfAttributesInTotal();
	}
	
	@Override
	public int getNoOfClasses()
	{
		return this.model.getNoOfClassesInTotal();
	}
	
	@Override
	public void parsedArtefactTypes(List<ArtefactType> artefactTypes)
	{
		// register available artefact types
		for(Iterator<ArtefactType> i=artefactTypes.iterator(); i.hasNext();)
		{
			ArtefactType type = i.next();
			
			this.addArtefactType(type);
		}
	}
	
	@Override
	public void setLogging(boolean logging)
	{
		// not initialized yet
		if(this.logging == null)
		{
			this.logging = Boolean.valueOf(logging);
		}
		
		// setting changed?
		else 
		{
			if(this.logging.booleanValue() != logging)
			{
				this.logging = logging;
				
				// update XML file
				ControllerFactory.getControllerBackgroundTask().updateXMLFile(this.pathXML,
																			  this.pathXSD,
																			  this.xml,
																			  true,
																			  XMLStartupTag.LOGGING,
																			  this.logging);
			}
		}
	}
	
	@Override
	public boolean isLogging()
	{
		return this.logging;
	}
	
	@Override
	public void setPRNG(String prng)
	{
		// default
		if(prng == null)
		{
			this.prng = PRNG.MersenneTwister;
		}
		else
		{
			// AES
			if(prng.equals(PRNG.AESCounter.toString()))
			{
				this.prng = PRNG.AESCounter;
			}
			
			// CellarAutomaton
			else if(prng.equals(PRNG.CelllarAutomaton.toString()))
			{
				this.prng = PRNG.CelllarAutomaton;
			}
			
			// CMWC4096
			else if(prng.equals(PRNG.CMWC4096.toString()))
			{
				this.prng = PRNG.CMWC4096;
			}
			
			// JavaRNG
			else if(prng.equals(PRNG.JavaRNG.toString()))
			{
				this.prng = PRNG.JavaRNG;
			}
			
			// MersenneTwister
			else if(prng.equals(PRNG.MersenneTwister.toString()))
			{
				this.prng = PRNG.MersenneTwister;
			}
			
			// SecureRandom
			else if(prng.equals(PRNG.SecureRandom.toString()))
			{
				this.prng = PRNG.SecureRandom;
			}
			
			// XORShift
			else if(prng.equals(PRNG.XORShift.toString()))
			{
				this.prng = PRNG.XORShift;
			}
			
			// nothing matches -> default
			else
			{
				this.prng = PRNG.MersenneTwister;
			}
		}
	}
	
	@Override
	public void setPRNG(PRNG prng)
	{
		this.prng = prng;
	}
	
	@Override
	public PRNG getPRNG()
	{
		return this.prng;
	}
	
	@Override
	public void setUnits(List<Unit> units)
	{
		this.model.setUnits(units);
	}
	
	@Override
	public void addUnit(Unit unit)
	{
		this.model.addUnit(unit);
	}
	
	@Override
	public List<Unit> getUnits()
	{
		return this.model.getUnits();
	}
	
	@Override
	public Iterator<Unit> iteratorUnit()
	{
		return this.model.iteratorUnits();
	}
	
	@Override
	public void setRawDate(int lower, int upper)
	{
		// year, month, day, h, min, sec
		this.date_lowerBound = new GregorianCalendar(lower, 0, 1).getTime();
		
		// upper not set, we take the current system time
		if(upper == 0)
		{
			int year = Calendar.getInstance().get(Calendar.YEAR);
			
			this.date_upperBound = new GregorianCalendar(year, 11, 31).getTime();
		}
		
		// upper set
		else
		{
			this.date_upperBound = new GregorianCalendar(upper, 11, 31).getTime();
		}
	}
	
	/**
	 * the controller doesn't support that method.
	 * It throws an {@link UnsupportedOperationException}.
	 */
	@Override
	public void notifyObserverUnit()
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void registerObserverUnit(ObserverUnit observer, String name)
	{
		if(this.getUnits() != null)
		{
			for(Iterator<Unit> i=this.iteratorUnit(); i.hasNext();)
			{
				Unit unit = i.next();
				
				// got it
				if(unit.getName().equals(name))
				{
					unit.getWeightAndMeasurement().registerObserverUnit(observer, name);
				}
			}
		}
	}
	
	@Override
	public void removeObserverUnit(ObserverUnit observer, String name)
	{
		if(this.getUnits() != null)
		{
			for(Iterator<Unit> i=this.iteratorUnit(); i.hasNext();)
			{
				Unit unit = i.next();
				
				// got it
				if(unit.getName().equals(name))
				{
					unit.getWeightAndMeasurement().removeObserverUnit(observer, name);
				}
			}
		}
	}
	
	/**
	 * the controller doesn't support that function
	 * and therefore throws an {@link UnsupportedOperationException}.
	 */
	@Override
	public void notifyObserverUnitCustom()
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void registerObserverUnitCustom(ObserverUnitCustom observer, String name)
	{
		if(this.getUnits() != null)
		{
			for(Iterator<Unit> i=this.iteratorUnit(); i.hasNext();)
			{
				Unit unit = i.next();
				
				// got it
				if(unit.getName().equals(name))
				{
					unit.getWeightAndMeasurementCustom().registerObserverUnitCustom(observer, name);
				}
			}
		}
	}
	
	@Override
	public void removeObserverUnitCustom(ObserverUnitCustom observer, String name)
	{
		if(this.getUnits() != null)
		{
			for(Iterator<Unit> i=this.iteratorUnit(); i.hasNext();)
			{
				Unit unit = i.next();
				
				// got it
				if(unit.getName().equals(name))
				{
					unit.getWeightAndMeasurementCustom().removeObserverUnitCustom(observer, name);
				}
			}
		}
	}
	
	@Override
	public void createAndRegisterGenerator(ObserverGenerator observer)
	{
		// create
		Generator name = new Name();
		Generator firstName = new FirstName();
		Generator lastName = new LastName();
		Generator sex = new Sex();
		Generator address = new Address();
		Generator country = new Country();
		Generator date =  new Dates();
		Generator email = new Email();
		
		// register
		name.registerObserverGenerator(observer);
		firstName.registerObserverGenerator(observer);
		lastName.registerObserverGenerator(observer);
		sex.registerObserverGenerator(observer);
		address.registerObserverGenerator(observer);
		country.registerObserverGenerator(observer);
		date.registerObserverGenerator(observer);
		email.registerObserverGenerator(observer);
		
		// pass to model layer
		this.model.addGenerator(name);
		this.model.addGenerator(firstName);
		this.model.addGenerator(lastName);
		this.model.addGenerator(sex);
		this.model.addGenerator(address);
		this.model.addGenerator(country);
		this.model.addGenerator(date);
		this.model.addGenerator(email);
	}
	
	@Override
	public void unregisterGenerator(Generator generator, ObserverGenerator observer)
	{
		generator.removeObserverGenerator(observer);
		
		this.model.removeGenerator(generator);
	}
	
	@Override
	public void createAndRegisterDistribution(ObserverDistribution observer)
	{
		// create
		ProbabilityDistribution normal = new NormalDistribution();
		ProbabilityDistribution uniform = new UniformDistribution();
		
		// register
		normal.registerObserverDistribution(observer);
		uniform.registerObserverDistribution(observer);
		
		// pass to model layer
		this.model.addProbabilityDistribution(normal);
		this.model.addProbabilityDistribution(uniform);
	}
	
	@Override
	public void unregisterDistribution(ProbabilityDistribution distribution, ObserverDistribution observer)
	{
		distribution.removeObserverDistribution(observer);
		
		this.model.removeProbabilityDistribution(distribution);
	}
	
	/**
	 * to build up the data structure top-down the concrete
	 * specifications are highly needed. By concrete specification
	 * we refer to leafs in tree data structures.
	 * The leafs have no sub-classes and are therefore concrete specifications.
	 * Whereas a root or a intermediate node are called abstract term.
	 * 
	 * @param ePackage
	 * 		EMF Ecore model
	 * @param mClass
	 * 		the artefact a user has had chosen is a root class in an UML diagram.
	 * @return
	 * 		list of all concrete specifications for a chosen artefact
	 */
	private List<MetaClass> buildUpDataStructureRoot(EPackage ePackage, MetaClass mClass)
	{
		return this.findSubClasses(this.getEcoreModel(), mClass);
	}
	
	/**
	 * builds the artefact data structure out of the EMF Ecore Model.
	 * This is, because a customized data structure is more adjustable,
	 * extendable. There have to be stored a lot more additional informations
	 * to the artefacts, which is not possible in the original Ecore Model.
	 * 
	 * @param ePackage
	 * 		Ecore Model
	 * @param mClass
	 * 		
	 * @return
	 * 		{@link MetaClass} data structure
	 */
	private MetaClass buildUpDataStructureLeaf(EPackage ePackage, MetaClass mClass)
	{
		// iterate through Ecore Model
		for(Iterator<EClassifier> i=ePackage.getEClassifiers().iterator(); i.hasNext();)
		{
			EClassifier classifier = i.next();
			
			// class
			if(classifier instanceof EClass)
			{
				EClass eClass = (EClass) classifier;
				
				if(eClass.getName().equals(mClass.getName()))
				{
					// has base class
					if(this.hasBaseClass(eClass))
					{
						MetaClass mSuperClass = ArtefactFactory.createMetaClass();
						
						Iterator<EClass> it = eClass.getESuperTypes().iterator();
						
						// here we can assume that the Ecore model has single inheritance
						EClass eSuperClass = it.next();
						
						mSuperClass.setName(eSuperClass.getName());
						mSuperClass.setArtefactType(mClass.getArtefactType());
						
						// not created yet
						if(!this.alreadyCreated(mSuperClass))
						{
							mSuperClass = this.buildUpDataStructureLeaf(this.getEcoreModel(), mSuperClass);
							
							this.addToArtefact(mSuperClass);
						}
						
						// already created
						else
						{
							mSuperClass = this.find(mSuperClass);
						}
						
						mClass.setSuperClass(mSuperClass);
					}
					
					// attributes
					for(Iterator<EAttribute> j=eClass.getEAttributes().iterator(); j.hasNext();)
					{
						MetaAttribute mAttribute = ArtefactFactory.createMetaAttribute();
						
						EAttribute eAttribute = j.next();
						
						mAttribute.setName(eAttribute.getName());
						mAttribute.setDataType(this.getDataType(eAttribute.getEType().getName()));
						mAttribute.setLowerBound(eAttribute.getLowerBound());
						mAttribute.setUpperBound(eAttribute.getUpperBound());
						
						mClass.addAttriubte(mAttribute);
					}
				}
			}
		}
		
		return mClass;
	}
	
	/**
	 * checks, whether a artefact class has been already created.
	 * In that case, we do not create a second instance, but we
	 * use the reference instead.
	 * </p>
	 * The class name is going to be used to accomplish this task.
	 * It can be used, because there can't be two classes with the same
	 * name in a model.
	 * 
	 * @param mClass
	 * 		meta class to check
	 * @return
	 * 		true, if and only if a class has been found with the same name
	 */
	private boolean alreadyCreated(MetaClass mClass)
	{
		boolean exists = false;
		
		if(this.getArtefacts() != null)
		{
			for(Iterator<Artefact> i=this.getArtefacts().iterator(); i.hasNext();)
			{
				Artefact artefact = i.next();
				
				// collection found
				if(artefact.getArtefactType().getName().equals(mClass.getArtefactType().getName()))
				{
					for(Iterator<MetaClass> j=artefact.iterator(); j.hasNext();)
					{
						MetaClass mc = j.next();
						
						// found
						if(mc.getName().equals(mClass.getName()))
						{
							exists = true;
						}
					}
				}
			}
		}
		
		return exists;
	}
	
	/**
	 * checks, whether a meta class is one of three
	 * types. The types are derived from the fact, that
	 * an UML diagram can have three different class types.
	 * </p>
	 * The class types are corresponding to its place in a
	 * UML hierarchy.
	 * </p>
	 * Example:</p>
	 * <pre>
	 *                     <i>Artefact</i>
	 *                       /\
	 *                      /  \
	 *               <i>Product</i>    <i>Document</i>
	 *                  /\         /\
	 *                 /  \       /  \
	 *            Screw    Nut CAD    ScopeOfStatement 
	 * </pre>
	 * If you request <i>Artefact</i> as root, true would be returned.
	 * Buf if you request the same as a leaf or a node, false would be
	 * the answer.
	 * </p>
	 * <ul>
	 * 		UML to Tree
	 * 		<li>root
	 * 			<ul>
	 * 				<li>Artefact</li>
	 * 			</ul>
	 * 		</li>
	 * 		<li>
	 * 			node (intermediate)
	 * 		</li>
	 * 			<ul>
	 * 				<li>Product</li>
	 * 				<li>Document</li>
	 * 			</ul>
	 * 		<li>
	 * 			leaf
	 * 		</li>
	 * 			<ul>
	 * 				<li>Screw</li>
	 * 				<li>Nut</li>
	 * 				<li>CAD</li>
	 * 				<li>ScopeOfStatement</li>
	 * 			</ul>
	 * </ul>
	 * 
	 * @see
	 * 		UMLType
	 * @param type
	 * 		UMLTyp
	 * @param mClass
	 * 		a class to check the type
	 * @return
	 * 		true, if and only if the type requested for a class holds.
	 */
	private boolean isType(UMLType type, MetaClass mClass)
	{
		boolean isType = false;
		
		EPackage ePackage = this.getEcoreModel();
		
		for(Iterator<EClassifier> i=ePackage.getEClassifiers().iterator(); i.hasNext();)
		{
			EClassifier eClassifier = i.next();
			
			// class
			if(eClassifier instanceof EClass)
			{
				EClass eClass = (EClass) eClassifier;
				
				if(eClass.getName().equals(mClass.getName()))
				{
					// is root?
					if(type.equals(UMLType.ROOT))
					{
						// has root itself?
						if(eClass.getESuperTypes().isEmpty())
						{
							isType = true;
						}
					}
					
					// is node?
					else if(type.equals(UMLType.NODE))
					{
						// has base class?
						if(!eClass.getESuperTypes().isEmpty())
						{
							// has a sub-class?
							if(this.hasSubClass(ePackage, eClass))
							{
								isType = true;
							}
						}
					}
					
					// is leaf?
					else if(type.equals(UMLType.LEAF))
					{
						// has base class?
						if(!eClass.getESuperTypes().isEmpty())
						{
							// has no sub-class?
							if(!this.hasSubClass(ePackage, eClass))
							{
								isType = true;
							}
						}
					}
				}
			}
		}
		
		return isType;
	}
	
	/**
	 * checks, whether a class has a base class.
	 * 		
	 * @param eClass
	 * 		class to check
	 * @return
	 * 		true, if a base class exists
	 */
	private boolean hasBaseClass(EClass eClass)
	{
		boolean hasBaseClass = false;
		
		if(!eClass.getESuperTypes().isEmpty())
		{
			hasBaseClass = true;
		}
		
		return hasBaseClass;
	}
	
	/**
	 * iterates through the EMF Ecore model and tries
	 * to find out, if a EClass has a sub-class.
	 * In case it has, then true is returned to the invoker.
	 * Otherwise false will be returned.
	 * 
	 * @param ePackage
	 * 		EMF ECore Model
	 * @param eClass
	 * 		has this class a sub-class?
	 * @return
	 * 		true, if it has. Otherwise false.
	 */
	private boolean hasSubClass(EPackage ePackage, EClass eClass)
	{
		boolean hasSubClass = false;
		
		for(Iterator<EClassifier> i=ePackage.getEClassifiers().iterator(); i.hasNext();)
		{
			EClassifier eClassifier = i.next();
			
			if(eClassifier instanceof EClass)
			{
				EClass eClass2 = (EClass) eClassifier;
				
				if(!eClass2.getESuperTypes().isEmpty())
				{
					for(Iterator<EClass> j=eClass2.getESuperTypes().iterator(); j.hasNext();)
					{
						EClass eSuperClass = j.next();
						
						if(eSuperClass.getName().equals(eClass.getName()))
						{
							hasSubClass = true;
						}
					}
				}
			}
		}
		
		return hasSubClass;
	}
	
	/**
	 * finds for a given Eclass all its sub-classes by iterating
	 * through the EMF Ecore model.
	 * </p>
	 * Since a base class can have more than one sub-class, all
	 * corresponding sub-classes are returned in a list.
	 * </p>
	 * Notice, a base class can have more than one sub-class, but
	 * not the other way round. That would mean multi-inheritance and
	 * and is neither permitted nor supported.
	 * 
	 * @param ePackage
	 * 		the Ecore model
	 * @param baseClass
	 * 		the class from where we start looking for sub-classes
	 * @return
	 * 		list with sub-classes.
	 */
	private List<MetaClass> findSubClasses(EPackage ePackage, MetaClass baseClass)
	{
		List<MetaClass> subClasses = new ArrayList<MetaClass>();
		
		subClasses = this.getSubClasses(ePackage, baseClass, subClasses);
		
		return subClasses;
	}
	
	/**
	 * gets all sub-classes of a base class and returns the result
	 * in a list, because a base class might be sub-classes be more than
	 * one class.
	 * </p>
	 * Notice, that no element is returned, when no sub-classes exist for a class.
	 * 
	 * @param ePackage
	 * 		EMF Ecore Model
	 * @param baseClass
	 * 		defines the basis for the search for sub-classes
	 * @param subClasses
	 * 		the list to fill is provided by the invoker
	 * @return
	 * 		a list of sub-classes.
	 */
	private List<MetaClass> getSubClasses(EPackage ePackage, MetaClass baseClass, List<MetaClass> subClasses)
	{
		for(Iterator<EClassifier> i=ePackage.getEClassifiers().iterator(); i.hasNext();)
		{
			EClassifier eClassifier = i.next();
			
			// class
			if(eClassifier instanceof EClass)
			{
				EClass subClassCandid = (EClass) eClassifier;
				
				if(!subClassCandid.getESuperTypes().isEmpty())
				{
					for(Iterator<EClass> j=subClassCandid.getESuperTypes().iterator(); j.hasNext();)
					{
						EClass potBaseClass = j.next();
						
						// found a sub-class
						if(potBaseClass.getName().equals(baseClass.getName()))
						{
							// abstract term (current)
							if(this.hasSubClass(ePackage, subClassCandid))
							{
								MetaClass subClass = ArtefactFactory.createMetaClass();
								
								subClass.setName(subClassCandid.getName());
								
								this.getSubClasses(ePackage, subClass, subClasses);
							}
							
							// concrete spec.
							else
							{
								if(subClasses == null)
								{
									subClasses = new ArrayList<MetaClass>();
								}
								
								MetaClass concSpec = ArtefactFactory.createMetaClass();
								
								concSpec.setName(subClassCandid.getName());
								concSpec.setToConcreteSpec();
								
								subClasses.add(concSpec);
							}
						}
					}
				}
			}
		}
		
		return subClasses;
	}
	
	/**
	 * tries to find an already created meta class in the
	 * artefacts data structure.
	 * Depending on the artefact type the corresponding structure
	 * is searched by the class name.
	 * </p>
	 * If it already exists such a class its reference is returned.
	 * Otherwise a null reference is given back.
	 * 
	 * @param mClass
	 * 		meta class to search for by name.
	 * @return
	 * 		reference to the class.
	 */
	private MetaClass find(MetaClass mClass)
	{
		MetaClass res = null;
		
		for(Iterator<Artefact> i=this.getArtefacts().iterator(); i.hasNext();)
		{
			Artefact artefact = i.next();
			
			// right collection found
			if(artefact.getArtefactType().getName().equals(mClass.getArtefactType().getName()))
			{
				for(Iterator<MetaClass> j=artefact.iterator(); j.hasNext();)
				{
					MetaClass mc = j.next();
					
					if(mc.getName().equals(mClass.getName()))
					{
						res = mc;
					}
				}
			}
		}
		
		return res;
	}
	
	/**
	 * registers the main view to all selected types of artefacts.
	 * With this we do not observe artefact collections, but pass
	 * through the reference.
	 * It is not possible to register at an earlier stage, 'cos
	 * we've to create the data structure first.
	 * 
	 * @param view
	 * 		the observer of all selected artefact types are mapped
	 * 		to all corresponding artefact collections. 
	 */
	private void registerObserverToAllSelectedArtefactTypes(DataGeneratorView view)
	{
		for(Iterator<Artefact> i=this.iteratorArtefacts(); i.hasNext();)
		{
			Artefact artefact = i.next();
			
			this.registerObserverArtefact(view, artefact.getArtefactType());
		}
	}
	
	/**
	 * generates a separation line with the same length as
	 * its class name.
	 * Used for printing Ecore models more pretty printed.
	 * </p>
	 * It's only for debugging and testing purposes.
	 * 
	 * @param s
	 * 		name of a class
	 * @return
	 * 		line with the same length as class name.
	 */
	private String generateLine(String s)
	{
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<s.length(); i++)
		{
			sb.append("*");
		}
		
		return sb.toString();
	}
	
	/**
	 * called from any method that removes artefact types.
	 * To get synched with the number of types and artefacts
	 * this method is invoked.
	 * 
	 * @param type
	 * 		defines which collection to remove from model layer.
	 */
	private void removeArtefact(ArtefactType type)
	{
		if(this.getArtefacts() != null)
		{
			for(int i=0; i<this.getArtefacts().size(); i++)
			{
				Artefact artefact = this.getArtefacts().get(i);
				
				if(artefact.getArtefactType().getName().equals(type.getName()))
				{
					this.getArtefacts().remove(i);
				}
			}
		}
	}
	
	/**
	 * In the EMF Ecore meta model are data types represented
	 * as strings. This method maps those types to a more
	 * type safe data type.
	 * </p>
	 * The following data types are considered
	 * 
	 * @param dataType
	 * 		used in EMF Ecore model
	 * @return
	 * 		a representation for a data type
	 */
	private final DataType getDataType(String dataType)
	{
		DataType dt = null;
		
		// byte
		if((dataType.equals("EByte")) || (dataType.equals("EByteObject")))
		{
			dt = ArtefactFactory.createDataType(DataType.BYTE);
		}
		
		// short
		else if((dataType.equals("EShort")) || (dataType.equals("EShortObject")))
		{
			dt = ArtefactFactory.createDataType(DataType.SHORT);
		}
		
		// int
		else if((dataType.equals("EInt")) || (dataType.equals("EIntegerObject")))
		{
			dt = ArtefactFactory.createDataType(DataType.INT);
		}
		
		// long
		else if((dataType.equals("ELong")) || (dataType.equals("ELongObject")))
		{
			dt = ArtefactFactory.createDataType(DataType.LONG);
		}
		
		// float
		else if((dataType.equals("EFloat")) || (dataType.equals("EFloatObject")))
		{
			dt = ArtefactFactory.createDataType(DataType.FLOAT);
		}
		
		// double
		else if((dataType.equals("EDouble")) || (dataType.equals("EDoubleObject")))
		{
			dt = ArtefactFactory.createDataType(DataType.DOUBLE);
		}
		
		
		// boolean
		else if((dataType.equals("EBoolean")) || (dataType.equals("EBooleanObject")))
		{
			dt = ArtefactFactory.createDataType(DataType.BOOLEAN);
		}
		
		// char
		else if((dataType.equals("EChar")) || (dataType.equals("ECharacterObject")))
		{
			dt = ArtefactFactory.createDataType(DataType.CHAR);
		}
		
		// string
		else if(dataType.equals("EString"))
		{
			dt = ArtefactFactory.createDataType(DataType.STRING);
		}
		
		// default
		else
		{
			dt = ArtefactFactory.createDataType(DataType.STRING);
		}
		
		return dt;
	}
}