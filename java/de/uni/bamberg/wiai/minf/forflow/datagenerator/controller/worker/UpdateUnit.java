package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;

/**
 * This worker has the job of updating the Unit tree component.
 * If you wanna know more about what an unit might be, have a look
 * at {@link Unit}.
 * </p>
 * The update is done in background to avoid getting stuck in the EDT.
 * With the unit data structure being observed by the view {@link DataGeneratorView}
 * as the observer we get always the latest updates on time and without
 * ever pinging the model layer. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/24/09
 */
public class UpdateUnit extends SwingWorker<JTree, Void>
{
	/**
	 * here is the latest data to update in UI.
	 */
	private List<String> data = null;
	
	/**
	 * that's the name of the unit that has notified.
	 */
	private String name = null;
	
	/**
	 * here is the tree component of the UI.
	 */
	private JTree tree = null;
	
	/**
	 * the corresponding tree model of tree component.
	 */
	private DefaultTreeModel model = null;
	
	/**
	 * constructor is passed the latest data of an unit.
	 * The unit is identified by name. Also handed down are
	 * tree component and its tree model. Those elements are needed
	 * to reflect the update accordingly. 
	 * 
	 * @param data
	 * 		the latest data of an unit element.
	 * @param name
	 * 		the unit element is identified here.
	 * 		That's the name of the element that has send notification.
	 * @param tree
	 * 		the tree component. It's from the UI.
	 * @param model
	 * 		the tree model resides in tree.
	 */
	public UpdateUnit(List<String> data, String name, JTree tree, DefaultTreeModel model)
	{
		this.data = data;
		this.name = name;
		this.tree = tree;
		this.model = model;
	}
	
	@Override
	protected synchronized JTree doInBackground() throws Exception
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.model.getRoot();
		
		if(this.existsUnitTypeAlready(this.name, this.model, root))
		{
			DefaultMutableTreeNode toDelete = this.findUnit(this.name, this.model, root);
			
			this.remove(root, toDelete);
			
			this.add(this.data, this.model, root, this.name);
		}
		else
		{
			this.add(this.data, this.model, root, this.name);
		}
		
		if(this.model != null)
		{
			this.model.reload();
		}
		
		return this.tree;
	}
	
	@Override
	protected synchronized void done()
	{
		if(this.isDone())
		{
			try
			{
				if(this.get() != null)
				{
					this.expandAllNodes(this.get());
				}
			}
			catch(InterruptedException ie)
			{
			}
			catch(ExecutionException ee)
			{
			}
		}
	}
	
	/**
	 * checks, whether an unit item is already within the tree component.
	 * 
	 * @return
	 * 		true, if already in it.
	 */
	private boolean existsUnitTypeAlready(String name, TreeModel model, DefaultMutableTreeNode parent)
	{
		boolean exists = false;
		
		for(int i=0; i<model.getChildCount(parent); i++)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) model.getChild(parent, i);
			
			if(node.toString().equals(parent.toString()))
			{
				exists = true;
			}
		}
		
		return exists;
	}
	
	/**
	 * tries to get the tree node to an given name.
	 * If found, it is returned otherwise null.
	 * 
	 * @param name
	 * 		the node to fetch as string name
	 * @param model
	 * 		the model to look for the node
	 * @param parent
	 * 		defines the root node
	 * @return
	 * 		node or null
	 */
	private DefaultMutableTreeNode findUnit(String name, TreeModel model, DefaultMutableTreeNode parent)
	{
		DefaultMutableTreeNode node = null;
		
		for(int i=0; i<model.getChildCount(parent); i++)
		{
			DefaultMutableTreeNode candid = (DefaultMutableTreeNode) model.getChild(parent, i);
			
			if(candid.toString().equals(name))
			{
				node = candid;
			}
		}
		
		return node;
	}
	
	/**
	 * adds the latest data items to the corresponding
	 * node element.
	 * 
	 * @param data
	 * 		the latest data of an unit
	 * @param model
	 * 		the model to update
	 * @param root
	 * 		the root node. That's the fix and unchangable unit node.
	 * @param name
	 * 		the node where the data has to be updated.
	 */
	private void add(List<String> data, TreeModel model, DefaultMutableTreeNode root, String name)
	{
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(name);
		
		for(Iterator<String> i=data.iterator(); i.hasNext();)
		{
			String toAdd = i.next();
			
			newNode.add(new DefaultMutableTreeNode(toAdd));
		}
		
		root.add(newNode);
	}
	
	/**
	 * removes the node <i>toDelete</i> from the root.
	 * Actually, this is a very simple job. If a node
	 * is marked to be deleted, then this node is removed
	 * from the root and all its children, too.
	 * 
	 * @param root
	 * 		the root node
	 * @param toDelete
	 * 		the node to delete
	 */
	private void remove(DefaultMutableTreeNode root, DefaultMutableTreeNode toDelete)
	{
		root.remove(toDelete);
	}
	
	/**
	 * expands all nodes in the tree before it is shown to the UI.
	 * This has the effect that all of it's nodes have been expanded
	 * when it comes to an update. So the tester doesn't have to
	 * do this annoying task.
	 * 
	 * @param tree
	 * 		it's the start point
	 */
	private void expandAllNodes(JTree tree)
	{
		if(tree != null)
		{
			for(int i=0; i<tree.getRowCount(); i++)
			{
				tree.expandRow(i);
			}
		}
	}
}