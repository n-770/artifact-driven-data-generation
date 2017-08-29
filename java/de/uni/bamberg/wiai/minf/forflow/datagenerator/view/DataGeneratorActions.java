package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Custom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.FileExt;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.ChooseArtefacts;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.DialogFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.Multiplicity;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.About;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog.NullValues;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.DataGenerator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.Message;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.util.Splitter;

/**
 * Here is where the actions caused by the UI main frame are handled.
 * The actions origins are {@link DataGeneratorView}, but they
 * are processed further here. The actions itself may passed on
 * to the controller.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/06/09
 */
public class DataGeneratorActions
{
	/**
	 * about dialog shows the usual stuff.
	 */
	private About dialog_about = null;

	/**
	 * reference to {@link NullValues} dialog.
	 * When the corresponding action takes place, the dialog
	 * is shown to the user.
	 */
	private NullValues dialog_nullValues = null;
	
	/**
	 * reference to {@link Multiplicity} dialog.
	 * Opens up, when the corresponding action is invoked.
	 */
	private Multiplicity dialog_multiplicityValues = null;
	
	/**
	 * {@link ChooseArtefacts} dialog is invoked, when
	 * an EMF Ecore Model is successfully loaded into the system.
	 * </p>
	 * The job of the dialog is to let the user specify the artefacts
	 * to which test data is going to be generated.
	 */
	private ChooseArtefacts dialog_chooseArtefacts = null;
	
	/**
	 * reference to {@link DataGeneratorView}.
	 */
	private DataGeneratorView view = null;
	
	/**
	 * constructor is passed the reference of the view and
	 * creates its needed components.
	 */
	public DataGeneratorActions(DataGeneratorView view)
	{
		this.view = view;
		
		this.createComponents();
	}
	
	/**
	 * responsible for object creation.
	 * That is needed, because other components should be
	 * able to set and get data of those.
	 * </p>
	 * The objects are dialogs the view layer needs for
	 * some operations and functionality.
	 * The dialogs created are:
	 * <ul>
	 * 		<li>about dialog</li>
	 * 		<li>choose artefacts dialog</li>
	 * 		<li>multiplicity values dialog</li>
	 * 		<li>null values dialog</li>
	 * </ul>
	 */
	private void createComponents()
	{
		JFrame mainFrame = DataGenerator.getApplication().getMainFrame();
		
		this.dialog_about = DialogFactory.createAboutDialog(mainFrame);
		this.dialog_chooseArtefacts = DialogFactory.createChooseArtefactsDialog(mainFrame, this.view);
		this.dialog_multiplicityValues = DialogFactory.createMultiplicityDialog(mainFrame);
		this.dialog_nullValues = DialogFactory.createNullValueDialog(mainFrame);
	}
	
	/**
	 * passes the call to open the about dialog
	 * to the application framework.
	 */
	protected void openAboutDialog()
	{
		DataGenerator.getApplication().show(this.dialog_about);
	}
	
	/**
	 * method is invoked from the {@link DataGeneratorView}
	 * and opens up a file chooser dialog where the tester
	 * has the opportunity to browse for a proper file to
	 * load into the system.
	 * 
	 * @param workingDir
	 * 		its the latest <i>dir</i> from which a file has been
	 * 		deserialzed.
	 */
	protected void loadModel(URI workingDir)
	{
		JFileChooser fileChooser = null;
		
		// user's home dir
		if(workingDir == null)
		{
			fileChooser = new JFileChooser();
		}
		
		// jump to current working dir
		else
		{
			fileChooser = new JFileChooser(workingDir.getPath());
		}
		
		// default filter
		FileNameExtensionFilter dfnef = null;
		
		// set up file filters (desc, ext)
		for(int i=0; i<FileExt.FILE_EXT_EMF.length; i++)
		{
			FileNameExtensionFilter fnef = new FileNameExtensionFilter(
					FileExt.FILE_DESC_EMF[i],
					FileExt.FILE_EXT_EMF[i]);
			
			fileChooser.addChoosableFileFilter(fnef);
			
			if(i == 0)
			{
				dfnef = fnef;
			}
		}
		
		fileChooser.setFileFilter(dfnef);
		
		// open dialog
		int returnVal = fileChooser.showOpenDialog(
				DataGenerator.getApplication().getMainFrame());
		
		// open button
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			this.view.workingDir = Splitter.splitPathAndFileName(
					fileChooser.getSelectedFile().toURI());
			
			try
			{
				ControllerFactory.getControllerBackgroundTask().loadEMFModel(
						this,
						fileChooser.getSelectedFile().getCanonicalPath());
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		
		// cancel button
		else
		{
			return;
		}
		
		return;
	}
	
	/**
	 * passes the call to open null value dialog
	 * to the controller layer where it is processed
	 * further in background.
	 */
	protected void openNullValuesDialog()
	{
    	ControllerFactory.getControllerBackgroundTask().openNullValuesDialog(this.dialog_nullValues);
	}
	
	/**
	 * passes the call to open the multiplicity dialog
	 * to the controller layer. At controller layer it
	 * is job is done in background to get rid off of
	 * annoying EDT blocking behaviour.
	 */
	protected void openMultiplicityValuesDialog()
	{
		ControllerFactory.getControllerBackgroundTask().openMultiplicityDialog(this.dialog_multiplicityValues);
	}
	
	/**
	 * offers the opportunity to select those artefacts of an EMF Ecore Model,
	 * to which test data should going to be generated.
	 * The dialog opens up only, if the parsing of the Model was successful.
	 */
	public void openChooseArtefactsDialog()
	{
		DataGenerator.getApplication().show(this.dialog_chooseArtefacts);
	}
	
	/**
	 * method is invoked by main view and passes its
	 * call to the controller layer.
	 * The controller load the corresponding facets to
	 * an artefact in background to disburden the EDT.
	 * When done, the found facets are displayed in a
	 * table fashioned way.
	 * 
	 * @param path
	 * 		the whole path to a node
	 * @param selectedNode
	 * 		the name of the artefact to update.
	 * 		At that point it is still a tree node of the UI.
	 * 		It is converted to an actual artefact when the
	 * 		call arrives its destination.
	 * 		Any job other than passing it through could be too
	 * 		time consuming to the EDT.
	 */
	protected void loadFacets(TreePath path, DefaultMutableTreeNode selectedNode)
	{
		ControllerFactory.getControllerBackgroundTask().loadFacets(path,
																   selectedNode,
																   this.view);
	}
	
	/**
	 * this method passes the add facet call to
	 * the controller layer. Controller knows all
	 * the necessary steps, conditions and constraints
	 * to fulfill this call.
	 */
	protected void addFacet()
    {
    	// TODO add facet
		Message.showMessageDialog(
    			"I'm sorry!"
    			+"\n"
    			+"Adding facets has been postponed to a later version",
    			"TODO",
    			JOptionPane.INFORMATION_MESSAGE);
    }
	
	/**
	 * this method hands the call down to controller layer
	 * where a facet is going to be removed. Remember, only
	 * controller layer has the access to model layer to
	 * make the proper changes in the data structure.
	 */
	protected void deleteFacet()
    {
    	// TODO delete facet
    }
    
    /**
     * to create a new artefact significant changes
     * in the model layer must be done. This is
     * only allowed to controller layer. Therefore,
     * we pass this call on to controller.
     */
	protected void createNewArtefact()
    {
    	// TODO create a new artefact
		Message.showMessageDialog(
    			"I'm sorry!"
    			+"\n"
    			+"Creating a new artefact is postponed to a later version",
    			"TODO",
    			JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * method passes it on to Controller layer where all the
     * work is done. Controller decides whether it runs this in
     * background and does the updates of the model, because the
     * layer knows all the steps a deletion causes.
     * 
     * @see
     * 		DataGeneratorView#deleteArtefact()
     * @param model
     * 		tree where a selected element (node) is gonna be dismissed
     * @param nodeToDelete
     * 		the node to kick
     * @param isAbstractTerm
     * 		flag, indicates whether a whole collection, it is linked to,
     * 		will be deleted.
     */
	protected void deleteArtefact(DefaultTreeModel model, 
								  DefaultMutableTreeNode nodeToDelete,
								  boolean isAbstractTerm)
    {
		ControllerFactory.getControllerBackgroundTask().deleteArtefact(model,
																	   nodeToDelete,
																	   isAbstractTerm);
    }
    
    /**
     * this method is all about generating test data
     * to artefacts. To actually generate data, there
     * is much more work to do in background and 
     * only controller has the rights to do so.
     * As a matter of fact, this method is passed on, too.
     * 
     * @param workingDir
     * 		current working directory. We jump right there
     */
	protected void generateData(URI workingDir)
    {
		JFileChooser fileChooser = null;
		
		// user's home dir
		if(workingDir == null)
		{
			fileChooser = new JFileChooser();
		}
		
		// jump to current dir
		else
		{
			fileChooser = new JFileChooser(workingDir.getPath());
		}
		
		// default filter
		FileNameExtensionFilter dfnef = null;
		
		// set up file filter (desc, ext)
		for(int i=0; i<FileExt.FILE_EXT_XML.length; i++)
		{
			FileNameExtensionFilter fnef = new FileNameExtensionFilter(
					FileExt.FILE_DESC_XML[i],
					FileExt.FILE_EXT_XML[i]);
			
			fileChooser.addChoosableFileFilter(fnef);
			
			if(i == 0)
			{
				dfnef = fnef;
			}
		}
		
		// save dialog
		int n = fileChooser.showSaveDialog(DataGenerator.getApplication().getMainFrame());
		
		// save button
		if(n == JFileChooser.APPROVE_OPTION)
		{
			// name.ext
			File selectedFile = fileChooser.getSelectedFile();
			
			// ext
			String ext = Splitter.hasFileExtension(selectedFile) ? Splitter.getFileExtension(selectedFile) : dfnef.getExtensions()[0];
			
			// dir
			this.view.workingDir = Splitter.splitPathAndFileName(selectedFile.toURI());
			
			// name
			String name = Splitter.getFileName(selectedFile);
			
			ControllerFactory.getControllerBackgroundTask().generateTestData(
					this.view.workingDir,
					name,
					ext);
		}
		
		// cancel
		else
		{
			return;
		}
		
		return;
    }
    
    /**
     * this method passes on the save project call.
     * To save a current project session is a great help
     * to every tester. This method allows to mirror 
     * current conditions to any persistent storage.
     */
	protected void saveProject()
    {
    	// TODO save project
		Message.showMessageDialog(
    			"I'm sorry!"
    			+"\n"
    			+"Save a current session has been postponed."
    			+"\n"
    			+"This feature is coming in a later version.",
    			"TODO",
    			JOptionPane.INFORMATION_MESSAGE);
    }
	
	/**
	 * this method is called from within {@link DataGeneratorView}
	 * whenever an update has occurred.
	 * 
	 * @param artefacts
	 * 		the current list of artefacts to display in UI
	 * @param treeModel
	 * 		the component that displays the data
	 * @param tree
	 * 		tree component in UI
	 * @param type
	 * 		defines which type of artfacts to update
	 */
	protected void updateArtefact(List<MetaClass> artefacts, 
								  DefaultTreeModel treeModel,
								  JTree tree,
								  ArtefactType type)
	{
		ControllerFactory.getControllerBackgroundTask().updateObservers(artefacts,
																		treeModel,
																		tree,
																		type);
	}
	
	/**
	 * method passes the parameters on to the controller layer.
	 * It's a pass-through method.
	 * 
	 * @param quantity
	 * 		amount of test data to generate
	 * @param path
	 * 		the path where the selected node resides
	 * @param node
	 * 		the selected tree node
	 */
	protected void updateQuantity(int quantity, TreePath path, DefaultMutableTreeNode node)
	{
		ControllerFactory.getControllerBackgroundTask().updateQuantity(quantity, path, node);
	}
	
	/**
	 * method will be invoked, when an unit in model layer has changed state.
	 * It's a pass-through method from main view and passes on to controller layer.
	 * 
	 * @param units
	 * 		the data of the unit that has changed structure.
	 * 		This is the latest data.
	 * @param name
	 * 		the name of the unit that has had notified.
	 * @param tree
	 * 		the tree component
	 * @param model
	 * 		the tree model holding the data
	 */
	protected void updateUnit(List<String> units, String name, JTree tree, DefaultTreeModel model)
	{
		ControllerFactory.getControllerBackgroundTask().updateUnit(units, name, tree, model);
	}
	
	/**
	 * it's invoked when an unit in model layer has send out a notification to
	 * its observers to get the latest update.
	 * An update occurs, when the state changes.
	 * 
	 * @param units
	 * 		the latest data of an unit that has had notified to update
	 * @param name
	 * 		the name of the unit type
	 * @param tree
	 * 		the tree component in the UI.
	 * @param model
	 * 		the corresponding tree model holding the data to display.
	 */
	protected void updateUnitCustom(List<Custom> units, String name, JTree tree, DefaultTreeModel model)
	{
		ControllerFactory.getControllerBackgroundTask().updateUnitCustom(units, name, tree, model);
	}
	
	/**
     * used by:
     * <font size=3>
     * FillType.External file
     * </font></p>
     * Seeding an attribute (facet) with data by linking to
     * an external file. The file lies outside the system.
     * With this method the tester has the opportunity to
     * browses to the desired file and registers the path.
     * With the given path the system knows where to find the file
     * and how to use it.
     * 
     *  @param workingDir
     *  		the latest <i>dir</i> where a file has been loaded.
     *  @return
     *  		path of selected file
     */
	protected URI fillType_ExternalFile_SelectFile(URI workingDir)
	{
		JFileChooser fileChooser = null;
		
		// user's home dir
		if(workingDir == null)
		{
			fileChooser = new JFileChooser();
		}
		
		// jump to current dir
		else
		{
			fileChooser = new JFileChooser(workingDir.getPath());
		}
		
		// default filer
		FileNameExtensionFilter dfnef = null;
		
		// set up file filter (desc + ext)
		for(int i=0; i<FileExt.FILE_EXT_TXT.length; i++)
		{
			FileNameExtensionFilter fnef = new FileNameExtensionFilter(
					FileExt.FILE_DESC_TXT[i],
					FileExt.FILE_EXT_TXT[i]);
			
			fileChooser.addChoosableFileFilter(fnef);
			
			if(i == 0)
			{
				dfnef = fnef;
			}
		}
		
		fileChooser.setFileFilter(dfnef);
		
		// open dialog
		int val = fileChooser.showOpenDialog(
				DataGenerator.getApplication().getMainFrame());
		
		// open button
		if(val == JFileChooser.APPROVE_OPTION)
		{
			workingDir = fileChooser.getSelectedFile().toURI();
		}
		
		// cancel button
		else
		{
			return null;
		}
		
		return workingDir;
	}
	
	/**
	 * used by:
     * <font size=3>
     * FillType.Editor
     * </font></p>
	 * With the editor the tester has the opportunity
     * to create a list with values used by the filling
     * algorithm. If the list is complex, for convenience,
     * a method to save the typed in text is appropriate.
     * So the list can be reused and is serialized.
     * </p>
     * Method opens up a file browser to save 
     * the file anywhere.
     * 
     * @param workingDir
     * 		the latest <i>dir</i> which has been used.
     * 		We use it to jump to it right there.
     * @param values
     * 		the list with values which had been typed in
	 */
	protected void fillType_Editor_Save(URI workingDir, List<String> values)
	{
		JFileChooser fileChooser = null;
		
		// user's home dir
		if(workingDir == null)
		{
			fileChooser = new JFileChooser();
		}
		
		// jumpt to current dir
		else
		{
			fileChooser = new JFileChooser(workingDir.getPath());
		}
		
		// default filter
		FileNameExtensionFilter dfnef = null;
		
		// set up file filter (dec + ext)
		for(int i=0; i<FileExt.FILE_EXT_TXT.length; i++)
		{
			FileNameExtensionFilter fnef = new FileNameExtensionFilter(
					FileExt.FILE_DESC_TXT[i],
					FileExt.FILE_EXT_TXT[i]);
			
			fileChooser.addChoosableFileFilter(fnef);
			
			if(i == 0)
			{
				dfnef = fnef;
			}
		}
		
		fileChooser.setFileFilter(dfnef);
		
		// save dialog
		int val = fileChooser.showSaveDialog(
				DataGenerator.getApplication().getMainFrame());
		
		// save button
		if(val == JFileChooser.APPROVE_OPTION)
		{
			this.view.workingDir = Splitter.splitPathAndFileName(
					fileChooser.getSelectedFile().toURI());
			
			ControllerFactory.getControllerBackgroundTask().saveValueFile(
					this.view.workingDir,
					values,
					fileChooser.getSelectedFile().getName());
			
		}
		
		// cancel
		else
		{
			return;
		}
	}
	
	/**
	 * gets a call by {@link DataGeneratorView#fillTypeEditorLoad()} and passes
	 * it on to the background task. The result is then passed backwards to the
	 * invoker.
	 * 
	 * @param dir
	 * 		the directory
	 * @return
	 * 		a list of values
	 */
	protected List<String> fillType_Editor_Load(URI dir)
	{
		List<String> valueFiles = null;
		
		JFileChooser fileChooser = null;
		
		// user's home dir
		if(dir == null)
		{
			fileChooser = new JFileChooser();
		}
		
		// jump to current working dir
		else
		{
			fileChooser = new JFileChooser(dir.getPath());
		}
		
		// default filter
		FileNameExtensionFilter dfnef = null;
		
		// set up file filter (desc, ext)
		for(int i=0; i<FileExt.FILE_EXT_TXT.length; i++)
		{
			FileNameExtensionFilter fnef = new FileNameExtensionFilter(
					FileExt.FILE_DESC_TXT[i],
					FileExt.FILE_EXT_TXT[i]);
			
			fileChooser.addChoosableFileFilter(fnef);
			
			if(i == 0)
			{
				dfnef = fnef;
			}
		}
		
		fileChooser.setFileFilter(dfnef);
		
		// open dialog
		int n = fileChooser.showOpenDialog(DataGenerator.getApplication().getMainFrame());
		
		// open button
		if(n == JFileChooser.APPROVE_OPTION)
		{
			this.view.workingDir = Splitter.splitPathAndFileName(
					fileChooser.getSelectedFile().toURI());
			
			valueFiles = ControllerFactory.getControllerBackgroundTask().loadValueFile(
					fileChooser.getSelectedFile().toURI());
		}
		
		// cancel button
		else
		{
			return null;
		}
		
		return valueFiles;
	}
	
	/**
	 * gets the {@link ChooseArtefacts} dialog.
	 * This method is used to fill the components
	 * with data before the dialog is shown to the user.
	 * 
	 * @return
	 * 		choose artefacts dialog
	 */
	public ChooseArtefacts getChooseArtefactsDialog()
	{
		return this.dialog_chooseArtefacts;
	}
	
	/**
	 * gets the {@link NullValues} dialog.
	 * This is, because components are filled with data
	 * before shown to users.
	 * 
	 * @return
	 * 		null value dialog
	 */
	public NullValues getNullValuesDialog()
	{
		return this.dialog_nullValues;
	}
	
	/**
	 * gets the {@link Multiplicity} dialog.
	 * Because we fill the components with data
	 * before it is shown to users.
	 * 
	 * @return
	 * 		multiplicity dialog
	 */
	public Multiplicity getMultiplicityDialog()
	{
		return this.dialog_multiplicityValues;
	}
	
	/**
	 * returns the reference of the main view.
	 * 
	 * @return
	 * 		ref. to main frame.
	 */
	public DataGeneratorView getView()
	{
		return this.view;
	}
}