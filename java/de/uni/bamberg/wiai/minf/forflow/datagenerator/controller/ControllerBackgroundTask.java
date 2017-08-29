package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Custom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.XMLStartup;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.ArtefactUpdater;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.DeleteArtefactWorker;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.FacetUpdater;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.GenerateTestData;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.LoadMultiplicityValues;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.LoadNullValueWorker;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.LoadEMFModel;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.LoadValueFile;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.LogWorker;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.ProbabilityDensityFunctionUpdater;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.SaveValueFile;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.SetMultiplicityValues;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.SetNullValuesWorker;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.UpdateQuantity;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.UpdateUnit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.UpdateUnitCustom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker.XMLUpdater;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.FacetSimplified;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorActions;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.Multiplicity;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.NullValues;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.graph.Graph;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * Long running computations or I/O bound task should never run on EDT.
 * </p>
 * <fong size=6><b>Run on background</b></font></br>
 * <i>ControllerBackgroundTask</i> class holds every single task
 * run in background on this system. This has the effect, that the
 * job of long running task is done in background and disburding
 * the EDT (Event Dispatching Thread) of being block itself.
 * The UI is still responsible to user interaction and avoids
 * sluggish UIs.
 * </p>
 * <fong size=6><b>Who does it concern?</b></font></br>
 * Remember, layers above the controller layer are not allowed to do
 * that by itself. They've to ask the controller to do their job.
 * </p>
 * <font color=red>Objects within controller layer do not have to ask for this!</font>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/10/09
 */
public class ControllerBackgroundTask
{
	/**
	 * sole default constructor only visible to same package
	 */
	protected ControllerBackgroundTask()
	{
	}
	
	/**
	 * The job of this method is to load an EMF Ecore model file.
	 * Actually, this file describes an UML diagram and therefore
	 * is an meta-model of that diagram.
	 * Without such a file load into the system it's not possible
	 * to do any test data generation. Because only at runtime,
	 * we build an in-memory model of an UML diagram and link
	 * it with constraints.
	 * </p>
	 * Long running processes are swapped to background tasks.
	 * It creates a Swing Worker Thread to do the job in background.
	 * 
	 * @see
	 * 		LoadEMFModel
	 */
	public void loadEMFModel(DataGeneratorActions dga, String path)
	{
		// Swing worker Thread does the main task
		LoadEMFModel worker = new LoadEMFModel(dga, path);
		
		worker.execute();
	}
	
	/**
	 * method does run an update on the product artefacts.
	 * It doesn't matter where or which component displays the
	 * elements of this branch of artefacts, because the model
	 * layer and especially artefacts are observable.
	 * And whenever the data structure changes it notifies its
	 * observers to update.
	 * </p>
	 * The update process is run in background to avoid annoying
	 * blocking of the EDT.
	 * @see 
	 * 		ArtefactUpdater
	 * @param artefacts
	 * 		list of artefacts to display in UI
	 * @param treeModel
	 * 		model to represent the artefacts
	 * @param tree
	 * 		Tree component in UI
	 * @param type
	 * 		defines which type of artefacts to update
	 */
	public void updateObservers(List<MetaClass> artefacts, 
									   DefaultTreeModel treeModel,
									   JTree tree,
									   ArtefactType type)
	{
		ArtefactUpdater worker = new ArtefactUpdater(artefacts, treeModel, tree, type);
		
		worker.execute();
	}
	
	/**
	 * manages the deletion of an artefact. This is done in background,
	 * 'cos it causes to update the model layer to reflect the new data structure.
	 * </p>
	 * Notice, the method is invoked by the view, when an user decides to delete
	 * an artefact. The deletion <i>must</i> be done explicit and if it
	 * is passed on to this layer, we know there is no way back!
	 * 
	 * @param model
	 * 		identifies the tree component where the deleting occured
	 * @param nodeToDelete
	 * 		identifies the desired node to kick out
	 * @param isAbstractTerm
	 * 		flag indicates, whether the whole linked collection will be
	 * 		deleted.
	 */
	public void deleteArtefact(DefaultTreeModel model,
							   DefaultMutableTreeNode nodeToDelete,
							   boolean isAbstractTerm)
	{
		DeleteArtefactWorker worker = new DeleteArtefactWorker(model, nodeToDelete, isAbstractTerm);
		
		worker.execute();
	}
	
	/**
	 * Creates an background thread for doing this job.
	 * The worker iterates through all elements in artefacts
	 * and gathers its facets. Then the result is passed
	 * to the {@link NullValues} dialog and displayed.
	 * When displayed it is ready for further manipulation of the data.
	 * 
	 * @param nullValuesDialog
	 * 		dialog defining null values for facets.
	 */
	public void openNullValuesDialog(NullValues nullValuesDialog)
	{
		LoadNullValueWorker worker = new LoadNullValueWorker(nullValuesDialog);
		
		worker.execute();
	}
	
	/**
	 * This method is only invoked, when the above method
	 * {@link #openNullValuesDialog(NullValues)} has been invoked
	 * earlier.
	 * </p>
	 * It sets the null value allowed flags to each facet. This
	 * is needed for test data generation, because there might be
	 * facets which allow this setting.
	 * 
	 * @param dialog
	 * 		reference to dialog.
	 * 		Needed to close when the job is done.
	 * @param tableData
	 * 		data of the table model
	 */
	public void saveNullValues(NullValues dialog, List<FacetSimplified> tableData)
	{
		SetNullValuesWorker worker = new SetNullValuesWorker(dialog, tableData);
		
		worker.execute();
	}
	
	/**
	 * fetches all attributes and build an temporarily data structure
	 * to display the current situation.
	 * This is, as always, done in background. First the table data
	 * is gathered and set and then displayed.
	 * 
	 * @param dialog
	 * 		multiplicity dialog called when done.
	 */
	public void openMultiplicityDialog(Multiplicity dialog)
	{
		LoadMultiplicityValues worker = new LoadMultiplicityValues(dialog);
		
		worker.execute();
	}
	
	/**
	 * once the multiplicity dialog has been displayed and the settings
	 * are accepted, they are going to be saved in the data structure in
	 * model layer. And this is the method doing the job. Of course, in background.
	 * 
	 * @param dialog
	 * 		reference to multiplicity dialog, to handle closing.
	 * @param tableData
	 * 		the actual data to set
	 */
	public void saveMultiplicityValues(Multiplicity dialog, List<FacetSimplified> tableData)
	{
		SetMultiplicityValues worker = new SetMultiplicityValues(dialog, tableData);
		
		worker.execute();
	}
	
	/**
	 * runs an update on the XML startup file to reflect the user actions.
	 * The method is usually invoked, when a change in the data structure
	 * is proposed. Adding or deleting artefact types leads to a change in
	 * the structure for example.
	 * </p>
	 * Notice, only relevant elements to the file are synched, not everything. 
	 * 
	 * @param xml
	 * 		path to XML startup file
	 * @param xsd
	 * 		path to XML's XSD file
	 * @param xmlHandler
	 * 		class which handles the action to reflect the change in the file
	 * @param adding
	 * 		indicates whether to add or to remove something from the XML file
	 * @param tagElement
	 * 		to get a clue where to make the change in the XML file structure.
	 * 		XMLType refers to a XMl element tag used within.
	 * @param content
	 * 		here's the object when it comes to add something new to the file.
	 * 		Together with XMLType controller knows which class type it is, when
	 * 		casting is done.
	 * 		</p>
	 * 		If <i>adding</i> is false, content parameter holds the content to delete
	 * 		from the file.  
	 */
	public void updateXMLFile(URI xml, URI xsd, XMLStartup xmlHandler, boolean adding, String tagElement, Object content)
	{
		XMLUpdater worker = new XMLUpdater(xml, xsd, xmlHandler, adding, tagElement, content);
		
		worker.execute();
	}
	
	/**
	 * with this method the user gets the ability to get facets
	 * to an artefact by just clicking to the corresponding node
	 * at the tree in the GUI. Everything needed to get the job
	 * properly done is done by the controller in background.
	 * </p>
	 * With this approach life is easy.
	 * 
	 * @param path
	 * 		represents the path to a selected node
	 * @param selectedNode
	 * 		the artefact to load its facets. When the background job
	 * 		is created the tree node of the UI is transformed to an
	 * 		actual artefact object.
	 * @param view
	 * 		reference to main view.
	 * 		This is needed later to set the computed results
	 */
	public void loadFacets(TreePath path,
						   DefaultMutableTreeNode selectedNode,
						   DataGeneratorView view)
	{
		FacetUpdater worker = new FacetUpdater(path, selectedNode, view);
		
		worker.execute();
	}
	
	/**
	 * runs an update on the quantity. This parameter specifies
	 * how many test data sets to generate. This information
	 * is very important later in the generation process.
	 * Without any specification on quantity the whole process
	 * won't start.
	 * 
	 * @param quantity
	 * 		how many test data to generate?
	 * @param path
	 * 		the path where the selected node resides
	 * @param selectedNode
	 * 		the name is very wordy! It's the selected node in the path
	 */
	public void updateQuantity(int quantity, TreePath path, DefaultMutableTreeNode selectedNode)
	{
		UpdateQuantity worker = new UpdateQuantity(quantity, path, selectedNode);
		
		worker.execute();
	}
	
	/**
	 * runs an update on the unit tree component.
	 * It holds weight and measurements and are standardized systems.
	 * The update of the latest data is done in background to
	 * disburden EDT.
	 * 
	 * @param data
	 * 		here is the latest data to update in UI.
	 * 		It comes directly form model layer, when data structure changes.
	 * @param name
	 * 		the name of the unit that has send a notification.
	 * @param tree
	 * 		tree component in UI.
	 * @param model
	 * 		the corresponding tree model.
	 * 		Here is the latest data updated.
	 */
	public void updateUnit(List<String> data, String name, JTree tree, DefaultTreeModel model)
	{
		UpdateUnit worker = new UpdateUnit(data, name, tree, model);
		
		worker.execute();
	}
	
	/**
	 * runs an update on the unit tree component.
	 * It holds weight and measurements and are standardized systems.
	 * The update of the latest data is done in background to
	 * disburden EDT.
	 * 
	 * @param data
	 * 		here is the latest data to update in UI.
	 * 		It comes directly form model layer, when data structure changes.
	 * @param name
	 * 		the name of the unit that has send a notification.
	 * @param tree
	 * 		tree component in UI.
	 * @param model
	 * 		the corresponding tree model.
	 * 		Here is the latest data updated.
	 */
	public void updateUnitCustom(List<Custom> data, String name, JTree tree, DefaultTreeModel model)
	{
		UpdateUnitCustom worker = new UpdateUnitCustom(data, name, tree, model);
		
		worker.execute();
	}
	
	
	/**
	 * This method is used to create a plain text file
	 * with possible values for an attribute.
	 * Usually it's invoked by the editor function, when
	 * the user decides to save the typed in values.
	 * For that reason, its serialized and reusable.
	 * </p>
	 * Notice, the editor is optional. When no other function
	 * fits, tester can create a list of possible values
	 * to use for an attribute.
	 * 
	 * @param workingDir
	 * 		the current directory
	 * @param values
	 * 		the list of values for an given attribute.
	 * @param fileName
	 * 		the name of the file to create
	 */
	public void saveValueFile(URI workingDir, List<String> values, String fileName)
	{
		SaveValueFile worker = new SaveValueFile(workingDir, values, fileName);
		
		worker.execute();
	}
	
	/**
	 * This method is used to get a whole value file retrieved.
	 * It is usually called by the editor load method to modify
	 * an existing value file.
	 * 
	 * @param file
	 * 		the name of the value file
	 */
	public List<String> loadValueFile(URI file)
	{
		List<String> valueFile = null;
		
		LoadValueFile worker = new LoadValueFile(file);
		
		worker.execute();
		
		try
		{
			valueFile = worker.get();
		}
		catch(InterruptedException ie)
		{
			ErrorMessage.getInstance().printMessage(ie, "InterruptedException", Level.FINEST);
		}
		catch(ExecutionException ee)
		{
			ErrorMessage.getInstance().printMessage(ee, "ExecutionException", Level.FINEST);
		}
		
		return valueFile;
	}
	
	/**
	 * Logging is done, of course, in background because there are
	 * I/O processes involved. The call is passed to it's destination.
	 * 
	 * @param level
	 * 		defines the logging level. It's a mechanism to make
     * 		logging very flexible and cots very little at runtime
     * 		when logging code is not used, e.g. in production.
	 * @param message
	 * 		as the parameter already says: you've got the opportunity
     * 		to put same custom information here in.
	 * @param e
	 * 		superclass of all errors and exceptions
	 * @param logger
	 * 		the logger object is passed down, too
	 */
	public void log(Level level, String message, Throwable e, Logger logger)
	{
		LogWorker worker = new LogWorker(level, message, e, logger);
		
		worker.execute();
	}
	
	/**
	 * to get a clue of the current probability distribution
	 * it is visualized at a graph panel. The generation of
	 * the values for the distribution and the painting
	 * is done in background.
	 * 
	 * @param distribution
	 * 		probability distribution to visualize its corresponding
	 * 		probability density function.
	 * @param graph
	 * 		that's the component to draw to
	 */
	public void generateProabilityDensityFunction(ProbabilityDistribution distribution, Graph graph)
	{
		ProbabilityDensityFunctionUpdater worker = new ProbabilityDensityFunctionUpdater(
				distribution,
				graph);
		
		worker.execute();
	}
	
	/**
	 * here is the method generating the test data.
	 * The call comes from the view layer. Because there're
	 * a lot of work involved when generating test data the
	 * whole process is run in background. Therefore, a worker
	 * thread does the job.
	 * 
	 * @param path
	 * 		the path to save the generated test data to
	 * @param fileName
	 * 		the file name
	 * @param ext
	 * 		the file extension
	 */
	public void generateTestData(URI path, String fileName, String ext)
	{
		GenerateTestData worker = new GenerateTestData(path, fileName, ext);
		
		worker.execute();
	}
}