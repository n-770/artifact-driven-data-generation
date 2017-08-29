package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * The job of this background task is to fetch all factes belonging
 * to an artefact. The job is initiated by the tester when he or she
 * clicks in the tree component to an available node. That node becomes
 * the selected node and is passed down here to the controller layer.
 * The first thing to do is to transform the selected node to an proper
 * artefact type and get its corresponding facets. When this is done
 * properly the result is returned to the UI and displayed in a table
 * fasioned way.
 * </p>
 * <font size=6><b>Worker Thread</b></font></br>
 * As with tedious work, the system does this in background. There are
 * more reasons to consider. Either the EDT gets stuck computing the results,
 * or the user gets annoyed by not responding GUI elements.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/21/09
 */
public class FacetUpdater extends SwingWorker<MetaClass, Void>
{
	/**
	 * here is the selected node from the tree component
	 */
	private DefaultMutableTreeNode selectedNode = null;
	
	/**
	 * reference to main view where the facets are displayed.
	 */
	private DataGeneratorView view = null;
	
	/**
	 * path to selected node.
	 */
	private TreePath path = null;
	
	/**
	 * constructor is passed the name of the selected node.
	 * The node itself comes from the tree component in
	 * the UI. The job here is to get all facets to that artefact
	 * and display it in a table fashion.
	 * 
	 * @param path
	 * 		represents the path to a node
	 * @param selectedNode
	 * 		selected node to get the latest update on facets.
	 * @param view
	 * 		reference to main view, where the facets are displayed
	 * 		in its proper fashion.
	 */
	public FacetUpdater(TreePath path, DefaultMutableTreeNode selectedNode, DataGeneratorView view)
	{
		this.path = path;
		this.selectedNode = selectedNode;
		this.view = view;
	}
	
	@Override
	protected MetaClass doInBackground() throws Exception
	{
		// transform selected node
		ArtefactType type = this.findArtefactType(this.path, this.selectedNode);
		
		MetaClass mClass = ControllerFactory.getController().getMetaClass(this.selectedNode.toString(), type);
		
		return mClass;
	}
	
	@Override
	protected void done()
	{
		if(this.isDone())
		{
			try
			{
				MetaClass mClass = this.get();
				
				if(mClass != null)
				{
					this.view.setTableData(mClass.getName(), mClass.getAttributes());
					
					this.view.setQuantity(mClass.getQuantity(), mClass.isConcreteSpec());
				}
			}
			catch(ExecutionException ee)
			{
				ErrorMessage.getInstance().printMessage(ee,
														this.getClass().getName(),
														"ExecutionException",
														Level.FINEST);
			}
			catch(InterruptedException ie)
			{
				ErrorMessage.getInstance().printMessage(ie,
														this.getClass().getName(),
														"InterruptedException",
														Level.FINEST);
			}
		}
	}
	
	/**
	 * to delete the selected node from the actual model layer,
	 * we need to know which artefact type is selected.
	 * In other words, we need to know which path has to be
	 * removed. This is done with this method.
	 * 
	 * @param path
	 * 		represents the path to a node
	 * @param node
	 * 		selected node in tree
	 * @return
	 * 		type of artefact to remove from.
	 */
	private ArtefactType findArtefactType(TreePath path, DefaultMutableTreeNode node)
	{
		ArtefactType type = null;
		
		for(int i=0; i<path.getPathCount(); i++)
		{
			TreeNode treeNode = (TreeNode) path.getPathComponent(i);
			
			if(ControllerFactory.getController().hasArtefactType(treeNode.toString()))
			{
				type = ControllerFactory.getController().getArtefactType(treeNode.toString());
			}
		}
		
		return type;
	}
}