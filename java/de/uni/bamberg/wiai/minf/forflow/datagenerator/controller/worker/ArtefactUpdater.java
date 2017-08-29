package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;

/**
 * Updates the artefacts data structure of any artefact in a separate thread,
 * disburden the EDT from being blocked during that work.
 * The class uses a Swing Worker thread to accomplish this task without
 * annoying the user.
 * </p>
 * When the task is done successfully, it updates the artefact tree
 * with the latest changes.
 * </p>
 * <font size=6><b>Worker Thread</b></font></br>
 * Worker threads perform long running calculations or I/O bound tasks. And they're
 * used for tasks such as communicating with databases, accessing the web, and
 * reading or writing files.
 * </p>
 * To understand the used terms in the documentation the terms are explainted here
 * in short:</br>
 * <ul>
 * 		<li><b>artefact</b></li>
 * 			<ul>
 * 				<li>
 * 					This terms has to points of view.</br>
 * 					On a higher level point of view, it is something that is abstract.
 * 					The term is used to indicate something abstract and
 * 					it is mandatory to sub-class it.</br>
 * 					On a low level an artefact is something that is concrete and to
 * 					which test data has to be generated. Even an object is at a lower level
 * 					it is still an artefact.
 * 				</li>
 * 			</ul>
 * 		<li><b>abstract term</b></li>
 * 			<ul>
 * 				<li>
 * 					An abstract term is an artefact and is still at a higher level.
 * 					If you compare it with a tree, an abstract term is either a root
 * 					or it's a sub-class of a root or even a sub-class of an another
 * 					abstract term.</p>
 * 					They always have itself one or more sub-classes and are never
 * 					concrete terms.
 * 				</li>
 * 			</ul>
 * 		<li><b>concrete term (concrete specification)</b></li>
 * 			<ul>
 * 				<li>
 * 					It's always a sub-class of an abstract term and has itself
 * 					never ever any sub-classes. In terms of a tree, a concrete term or
 * 					specification is a leaf. And test data is always generated to
 * 					a those terms. But they do inherit facets (attributes) of their
 * 					parents, that are abstract terms.</p>
 * 					To mess it up completely, it is again an artefact, but at low level.
 * 				</li>
 * 			</ul>
 * </ul>
 * </p>
 * A visual example will help to nail it down. If you look at the picture properly,
 * you'll notice that the higher levels in general are in <i>italic</i>.
 * The meaning is borrowed from UML and emphasizes the abstract term.
 * As a result <i>Artefact</i>, <i>Product</i> and even <i>Document</i> are
 * artefacts, but they're also abstract terms.
 * </p>
 * At the lower level, there are terms listed, which are not in <i>italic</i> or
 * anything else. At this level only concrete terms or specifications are seeded.
 * Those terms are both artefacts and concrete terms.
 * <pre>
 *                              <i>Artefact</i>
 *                              /       \
 *                        <i>Product</i>       <i>Document</i>
 *                       /      \       /      \
 *                     Screw   Nut    Bill    Scope of statement
 * </pre>
 * 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/10/09
 */
public class ArtefactUpdater extends SwingWorker<JTree, Void>
{
	/**
	 * list of artefacts to update
	 */
	private List<MetaClass> artefacts = null;
	
	/**
	 * tree model holding artefacts
	 */
	private DefaultTreeModel treeModel = null;
	
	/**
	 * tree component in UI.
	 */
	private JTree tree = null;
	
	/**
	 * type of artefacts to update
	 */
	private ArtefactType type = null;
	
	/**
	 * the node to update with latest data
	 */
	private DefaultMutableTreeNode toUpdate = null;
	
	/**
	 * constructor assigns a list of artefacts which are updated in
	 * the tree of products
	 * 
	 * @param artefacts
	 * 		list of artefacts to update at UI.
	 * @param treeModel
	 * 		tree model that holds artefacts.
	 * @param tree
	 * 		tree component in UI.
	 * @param type
	 * 		defines which artefacts to update
	 */
	public ArtefactUpdater(List<MetaClass> artefacts,
						   DefaultTreeModel treeModel,
						   JTree tree,
						   ArtefactType type)
	{
		this.artefacts = artefacts;
		this.treeModel = treeModel;
		this.tree = tree;
		this.type = type;
	}

	@Override
	protected JTree doInBackground() throws Exception
	{
		// get root 'abstract', it's fix and not changeable! 
		DefaultMutableTreeNode root_fix = (DefaultMutableTreeNode) this.treeModel.getRoot();
		
		// abstract term, delete it and build it up with new data
		this.toUpdate = this.getRoot(this.treeModel, root_fix, this.type);
		
		this.toUpdate.removeAllChildren();
		
		MetaClass root = this.findRoot(this.artefacts.get(0));
		
		this.toUpdate.add(new DefaultMutableTreeNode(root.getName()));
		
		this.buildUpTree(this.treeModel, this.artefacts, root);
		
		this.treeModel.reload();
		
		this.expandAllNodes(this.tree);
		
		return this.tree;
	}
	
	@Override
	protected void finalize() throws Throwable
	{
		if(this.isCancelled())
		{
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
			
			treeModel.setRoot(null);
			treeModel.setRoot(root);
		}
	}
	
	/**
	 * gets an abstract term we call a root. But do not confuse
	 * it with the actual root of the tree component. This is fix
	 * and can't be changed nor deleted at no means.
	 * The children nodes of this unchangeable root are all artefacts
	 * at a high level and are abstract terms. Because they are going
	 * to be sub-classed we call them a root. It's more or less the
	 * actual starting point.
	 * 
	 * @param model
	 * 		tree model holds the data to display in tree components
	 * @param parent
	 * 		parent node of which we are looking for an abstract term.
	 * 		Usually this is the unchangeable root node at the top.
	 * @param type
	 * 		It's one of the children of parent we are looking for.
	 * @return
	 * 		the node responsible for artefactType type
	 */
	private DefaultMutableTreeNode getRoot(DefaultTreeModel model,
										   DefaultMutableTreeNode parent,
										   ArtefactType type)
	{
		DefaultMutableTreeNode res = null;
		
		for(int i=0; i<model.getChildCount(parent); i++)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) model.getChild(parent, i);
			
			if(node.toString().equals(type.getName()))
			{
				res = node;
			}
		}
		
		return res;
	}
	
	/**
	 * builds up the tree structure of the tree model component.
	 * The method is called recursive to do the job. It starts thereby
	 * with the root node.
	 * 
	 * @param model
	 * 		the tree model component
	 * @param artefacts
	 * 		the artefact list to add
	 * @param base
	 * 		At 1st call it is the root and to all subsequent calls it
	 * 		is the parent 
	 */
	private void buildUpTree(DefaultTreeModel model, List<MetaClass> artefacts, MetaClass base)
	{
		List<MetaClass> subClasses = this.findSubClasses(artefacts, base);
		
		if(subClasses != null)
		{
			for(Iterator<MetaClass> i=subClasses.iterator(); i.hasNext();)
			{
				MetaClass child = i.next();
				
				this.addNodeToModel(model, child, base);
				
				this.buildUpTree(model, artefacts, child);
			}
		}
	}
	
	/**
	 * gathers all subclasses of a base class. It checks, whether
	 * there're nodes in the artefact list which have the base
	 * class as its parent node. All nodes found are then returned.
	 * 
	 * @param artefacts
	 * 		list of artefact to search through
	 * @param base
	 * 		a node to which this method finds it's subclasses
	 * @return
	 * 		list with subclasses or null
	 */
	private List<MetaClass> findSubClasses(List<MetaClass> artefacts, MetaClass base)
	{
		List<MetaClass> res = null;
		
		for(Iterator<MetaClass> i=artefacts.iterator(); i.hasNext();)
		{
			MetaClass mClass = i.next();
			
			if(mClass.hasSuperClass())
			{
				// found a subclass
				if(mClass.getSuperClass().getName().equals(base.getName()))
				{
					if(res == null)
					{
						res = new ArrayList<MetaClass>();
					}
					
					res.add(mClass);
				}
			}
		}
		
		return res;
	}
	
	/**
	 * adds a new node to the tree model. To do this, first
	 * the parent node in the tree model is needed. We do this by
	 * looking for a node with the name of the parent.getName().
	 * If successful the new node is added as a child to the parent.
	 * Another check makes sure only once a node is added.
	 * 
	 * @param model
	 * 		the tree model
	 * @param child
	 * 		the node to add as a child to parent
	 * @param parent
	 * 		the parent is important. It tells where to add the new node.
	 */
	private void addNodeToModel(DefaultTreeModel model, MetaClass child, MetaClass parent)
	{
		DefaultMutableTreeNode parentNode = this.findNode(parent, model);
		
		if(!this.hasNodeInModel(model, child))
		{
			parentNode.add(new DefaultMutableTreeNode(child.getName()));
		}
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
	
	/**
	 * checks, whether there are doubleganger nodes in the tree.
	 * We do not add a node twice.
	 * 
	 * @param model
	 * 		the tree model holding the elements
	 * @param mClass
	 * 		the node name to check whether already in it
	 * @return
	 * 		true, if node found in tree model
	 */
	@SuppressWarnings("unchecked")
	private boolean hasNodeInModel(DefaultTreeModel model, MetaClass mClass)
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		
		boolean alreadyInIt = false;
		
		for(Enumeration<DefaultMutableTreeNode> i=root.breadthFirstEnumeration(); i.hasMoreElements();)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) i.nextElement();
			
			if(node.toString().equals(mClass.getName()))
			{
				alreadyInIt = true;
			}
		}
		
		return alreadyInIt;
	}
	
	/**
	 * tries to find the node in a tree model with the same name as in meta class.
	 * If successful the node from the tree model is returned otherwise null
	 * 
	 * @param mClass
	 * 		the meta class name is used to look for a node with the same name
	 * @param model
	 * 		the tree model to search for
	 * @return
	 * 		the node of the tree model or null
	 */
	@SuppressWarnings("unchecked")
	private DefaultMutableTreeNode findNode(MetaClass mClass, DefaultTreeModel model)
	{
		DefaultMutableTreeNode res = null;
		
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		
		for(Enumeration<DefaultMutableTreeNode> i=root.breadthFirstEnumeration(); i.hasMoreElements();)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) i.nextElement();
			
			// got it
			if(node.toString().equals(mClass.getName()))
			{
				res = node;
			}
		}
		
		return res;
	}
	
	/**
	 * this method has the job of finding the root node
	 * of the artefact list. This is important, because
	 * the tree model is filled up by beginning at the root.
	 * 
	 * @param mClass
	 * 		any meta class to start the search
	 * @return
	 * 		root or null
	 */
	private MetaClass findRoot(MetaClass mClass)
	{
		MetaClass res = null;
		
		if(mClass.hasSuperClass())
		{
			this.findRoot(mClass.getSuperClass());
		}
		else
		{
			res = mClass;
		}
		
		return res;
	}
}