package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.Iterator;

import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;

/**
 * This background worker has the job of updating the quantity
 * in the corresponding artefact, when the user types in an amount
 * in the text field.
 * </p>
 * The amount is the number of test data sets driven by the generation
 * process. This is an very important part and without any number there
 * won't be any test data generated.
 * </p>
 * Notice, the system doesn't ask explicit for a number. Instead, if nothing
 * has typed in, it assumes nothing should be generated to that artefact.
 * This artefact will be skipped and the next artefact is examined.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/22/09
 */
public class UpdateQuantity extends SwingWorker<Boolean, Void>
{
	/**
	 * amount of how many test data is gonna be generated
	 */
	private int quantity = 0;
	
	/**
	 * a path in tree component of UI.
	 */
	private TreePath path = null;
	
	/**
	 * the selected node. Thats where the quantity is updated
	 * actually.
	 */
	private DefaultMutableTreeNode selectedNode = null;
	
	/**
	 * constructor assigns a quantity, the path of a tree component and
	 * last the selected node itself.
	 * 
	 * @param quantity
	 * 		determines, how many test data sets to generate for selected node
	 * @param path
	 * 		the path of the tree component, where the selected node resides
	 * @param selectedNode
	 * 		the update is done to this node in the model layer.
	 */
	public UpdateQuantity(int quantity, TreePath path, DefaultMutableTreeNode selectedNode)
	{
		this.quantity = quantity;
		this.path = path;
		this.selectedNode = selectedNode;
	}
	
	@Override
	protected Boolean doInBackground() throws Exception
	{
		boolean successfull = false;
		
		ArtefactType type = this.findArtefactType(this.path);
		
		for(Iterator<Artefact> i=ControllerFactory.getController().iteratorArtefacts(); i.hasNext();)
		{
			Artefact artefact = i.next();
			
			// got collection
			if(artefact.getArtefactType().getName().equals(type.getName()))
			{
				for(Iterator<MetaClass> j=artefact.iterator(); j.hasNext();)
				{
					MetaClass mClass = j.next();
					
					// found class to update
					if(mClass.getName().equals(this.selectedNode.toString()))
					{
						// last check
						if(mClass.isConcreteSpec())
						{
							mClass.setQuantity(this.quantity);
							
							successfull = true;
						}
					}
				}
			}
		}
		
		return successfull;
	}
	
	/**
	 * method extracts the elements of the path and figures
	 * out the artefact type. The type is needed to find
	 * the collection to update.
	 * 
	 * @param path
	 * 		path of the tree component
	 * @return
	 * 		type of artefact to look for the update
	 */
	private ArtefactType findArtefactType(TreePath path)
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