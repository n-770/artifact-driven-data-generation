package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

import javax.swing.SwingWorker;

import org.eclipse.emf.ecore.EPackage;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorActions;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * <b>ModelLoadWorker</b> class for doing the main tasks separated from the
 * EDT. Worker threads perform long running calculations or input/output
 * (I/O) bound tasks. Worker threads are used for tasks such as
 * communicating with databases, accessing web resources, and reading
 * or writing large files.
 * </p>
 * The framework automatically runs it as a background thread. When the task
 * completes successfully, it updates the UI with a dialog.
 * </p>
 * This worker thread loads <i>Ecore</i> meta models into the system.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/06/09
 */
public class LoadEMFModel extends SwingWorker<List<MetaClass>, Void>
{
	/**
	 * current working directory, like UNIX <i>pwd</i> command
	 */
	private String path = null;
	
	/**
	 * reference to action class
	 */
	private DataGeneratorActions actions = null;
	
	/**
	 * creates a progress listener
	 */
	private ProgressListener progress = null;
	
	/**
	 * possible artefact types. These ones are
	 * passed to the dialog before its displayed.
	 */
	private ArtefactType[] artefactTypes = null;
	
	/**
	 * constructor passed on the path to a meta model file.
	 * 
	 * @param dga
	 * 		reference to {@link DataGeneratorActions}
	 * @param path
	 * 		path to the selected file
	 */
	public LoadEMFModel(DataGeneratorActions dga, String path)
	{
		this.actions = dga;
		this.path = path;
		
		int size = ControllerFactory.getController().getArtefactTypes().size();
		
		this.artefactTypes = new ArtefactType[size];
		
		this.progress = new ProgressListener(DataGeneratorView.getProgressBar(), true, 100);
	}
	
	/**
	 * method does the main task loading the <i>Ecore</i> file and building
	 * the data structure out of it.
	 * The time for that task depends on the size of the <i>Ecore</i> model.
	 * The more classes and attributes are there, the more time is needed
	 * to parse it and build up the data structure.
	 */
	@Override
	protected List<MetaClass> doInBackground() throws Exception
	{
		this.actions.getChooseArtefactsDialog().resetLists();
		
		// load EMF's Ecore Meta-model
		EPackage ePackage = ControllerFactory.getController().loadEcoreModel(this.path);
		
		ControllerFactory.getController().setEcoreModel(ePackage);
		
		// update artefact types
		for(int i=0; i<ControllerFactory.getController().getArtefactTypes().size(); i++)
		{
			ArtefactType type = ControllerFactory.getController().getArtefactTypes().get(i);
			
			this.artefactTypes[i] = type;
		}
		
		return ControllerFactory.getController().getArtefacts(ePackage);
	}
	
	@Override
	protected void done()
	{
		if(this.isDone())
		{
			try
			{
				// update artefacts
				for(Iterator<MetaClass> i=this.get().iterator(); i.hasNext();)
				{
					MetaClass mc = i.next();
					
					this.actions.getChooseArtefactsDialog().addElementToAvailableArtefacts(mc);
				}
				
				this.actions.getChooseArtefactsDialog().setArtefactType(this.artefactTypes);
			}
			catch(InterruptedException ie)
			{
				ErrorMessage.getInstance().printMessage(ie,
														this.getClass().getName(),
														"InterruptedException",
														Level.FINEST);
			}
			catch(ExecutionException ee)
			{
				ErrorMessage.getInstance().printMessage(ee,
														this.getClass().getName(),
														"ExecutionExecption",
														Level.FINEST);
			}
			
			this.actions.openChooseArtefactsDialog();
		}
		
		if(this.isCancelled())
		{
			return;
		}
		
		this.progress.reset();
		this.progress = null;
	}
	
	@Override
	protected void finalize() throws Throwable
	{
	}
}