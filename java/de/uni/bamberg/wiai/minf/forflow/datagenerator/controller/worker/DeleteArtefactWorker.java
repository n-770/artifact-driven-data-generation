package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;

/**
 * The process of deleting an artefact is done in a background thread,
 * to avoid annoying blocking of the EDT. Because it can take some time
 * to update the model layer and reflect the new data structure.
 * </p>
 * When the task is done the model layer notifies it's observers, that
 * it has changed. And the UI reloads it's component and displays the 
 * new structure.
 * </p>
 * <font size=6><b>Worker Thread</b></font></br>
 * Remember, worker threads perform long running calculations or I/O bound tasks,
 * communicating with databases, accessing web, reading and writing files.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/12/09
 */
public class DeleteArtefactWorker extends SwingWorker<Void, Void>
{
	/**
	 * specifies the tree data
	 */
	private DefaultTreeModel model = null;
	
	/**
	 * specifies the node to get rid off
	 */
	private DefaultMutableTreeNode noteToDelete = null;
	
	/**
	 * reference to the corresponding model layer data.
	 * It depends on the selected tree.
	 */
	private Artefact artefactCollection = null;
	
	/**
	 * when this flag is set to <i>true</i> the whole
	 * artefact collection will be deleted.
	 * So better be careful with that.
	 */
	private boolean isAbstractTerm = false;
	
	/**
	 * Constructor is passed the selected tree in UI
	 * and the node to delete.
	 * Because deleting a node can cause the deletion
	 * of other nodes, too. It is done in background.
	 * 
	 * @param model
	 * 		defines the tree model component in UI.
	 * @param noteToDelete
	 * 		the artefact to delete from the model layer.
	 * 		Because in UI a tree structure is used to display,
	 * 		it is called a node.
	 * @param isAbstractTerm
	 * 		if this flag is set to <i>true</i>, the whole linked collection
	 * 		is going to be deleted!!!
	 */
	public DeleteArtefactWorker(DefaultTreeModel model,
								DefaultMutableTreeNode noteToDelete,
								boolean isAbstractTerm)
	{
		this.model = model;
		this.noteToDelete = noteToDelete;
		this.isAbstractTerm = isAbstractTerm;
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		// find artefact type
		ArtefactType type = this.findArtefactType(this.noteToDelete);
		
		// get artefact collection
		this.artefactCollection = this.getArtefactCollcetion(type);
		
		if(!this.isAbstractTerm)
		{
			MetaClass toDelete = this.artefactCollection.getArtefact(this.noteToDelete.toString());
			
			this.delete(this.artefactCollection.getArtefacts(), type, toDelete);
		}
		
		else
		{
			List<DefaultMutableTreeNode> list = this.getNodes(this.noteToDelete);
			
			for(int i=0; i<list.size(); i++)
			{
				MetaClass toDelete = this.artefactCollection.getArtefact(list.get(i).toString());
				
				this.deleteAll(this.artefactCollection.getArtefacts(), toDelete);
			}
		}
		
		return null;
	}
	
	/**
	 * fetches the current selected artefact collection by artefact type.
	 * It is loaded from the model layer via the controller.
	 * 
	 * @param type
	 * 		defines the artefact type to load.
	 * @return
	 * 		artefact collection
	 */
	private Artefact getArtefactCollcetion(ArtefactType type)
	{
		Artefact res = null;
		
		for(Iterator<Artefact> i=ControllerFactory.getController().iteratorArtefacts(); i.hasNext();)
		{
			Artefact artefact = i.next();
			
			// got it!
			if(artefact.getArtefactType().getName().equals(type.getName()))
			{
				res = artefact;
			}
		}
		
		return res;
	}
	
	/**
	 * to delete the selected node from the actual model layer,
	 * we need to know which artefact type is selected.
	 * In other words, we need to know which path has to be
	 * removed. This is done with this method.
	 * 
	 * @param node
	 * 		selected node in tree
	 * @return
	 * 		type of artefact to remove from.
	 */
	private ArtefactType findArtefactType(DefaultMutableTreeNode node)
	{
		ArtefactType type = null;
		
		TreeNode[] path = node.getPath();
		
		for(int i=0; i<path.length; i++)
		{
			TreeNode treeNode = path[i];
			
			if(ControllerFactory.getController().hasArtefactType(treeNode.toString()))
			{
				type = ControllerFactory.getController().getArtefactType(treeNode.toString());
			}
		}
		
		return type;
	}
	
	/**
	 * deletes a class out of the specific data structure.
	 * If necessary the method is called recursive to fulfill
	 * its intended behaviour.
	 * 
	 * @param artefacts
	 * 		the artefact collection holding the actuall data.
	 * 		This is in model layer.
	 * @param type
	 * 		the artefact type defines which collection to use.
	 * @param toDelete
	 * 		the element to remove from the model layer.
	 */
	@SuppressWarnings("unchecked")
	private void delete(List<MetaClass> artefacts, ArtefactType type, MetaClass toDelete)
	{
		// leaf = concrete term
		if((toDelete.isConcreteSpec()) && (toDelete.hasSuperClass()))
		{
			// delete leaf only
			if(this.hasMoreSubclasses(artefacts, toDelete.getSuperClass()))
			{
				this.delete(toDelete);
			}
			else
			{
				this.delete(toDelete);
				
				this.delete(this.artefactCollection.getArtefacts(), type, toDelete.getSuperClass());
			}
		}
		
		else if((toDelete.isConcreteSpec()) && (!toDelete.hasSuperClass()))
		{
			this.delete(toDelete);
		}
		
		// intermediate
		else if((!toDelete.isConcreteSpec()) && (toDelete.hasSuperClass()))
		{
			// delete only this intermediate
			if(this.hasMoreSubclasses(artefacts, toDelete.getSuperClass()))
			{
				// delete its sub-classes and itself
				if(this.hasMoreSubclasses(artefacts, toDelete))
				{
					List<MetaClass> subClasses = this.getSubClasses(artefacts, toDelete);
					
					for(int i=0; i<subClasses.size(); i++)
					{
						this.delete(this.artefactCollection.getArtefacts(), type, subClasses.get(i));
					}
					
					this.delete(toDelete);
				}
				
				// delete only itself
				else
				{
					this.delete(toDelete);
				}
			}
			
			// delete also its base
			else
			{
				if(this.hasMoreSubclasses(artefacts, toDelete))
				{
					List<MetaClass> subClasses = this.getSubClasses(artefacts, toDelete);
					
					for(int i=0; i<subClasses.size(); i++)
					{
						this.delete(artefacts, type, subClasses.get(i));
					}
					
					this.delete(toDelete);
				}
				else
				{
					this.delete(toDelete);
					
					this.delete(this.artefactCollection.getArtefacts(), type, toDelete.getSuperClass());
				}
			}
		}
		
		// root
		else if((!toDelete.isConcreteSpec()) && (!toDelete.hasSuperClass()))
		{
			if(this.hasMoreSubclasses(artefacts, toDelete))
			{
				this.deleteAll(artefacts, toDelete);
			}
			else
			{
				this.delete(toDelete);
			}
			
			// root has been deleted, delete also abstractRoot
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.model.getRoot();
			
			for(Enumeration<DefaultMutableTreeNode> i=root.children(); i.hasMoreElements();)
			{
				DefaultMutableTreeNode abstractRoot = i.nextElement();
				
				if(abstractRoot.toString().equals(type.getName()))
				{
					this.model.removeNodeFromParent(abstractRoot);
				}
			}
		}
	}
	
	/**
	 * If this method is called a root object had been selected.
	 * That means, all classes are removed from the model layer 
	 * for this specific artefact type.
	 * 
	 * @param artefacts
	 * 		data structure to remove elements
	 * @param baseClass
	 * 		a class with potential sub-classes
	 */
	private void deleteAll(List<MetaClass> artefacts, MetaClass baseClass)
	{
		// abstract term
		if(!baseClass.isConcreteSpec())
		{
			// call recursive
			if(this.hasMoreSubclasses(artefacts, baseClass))
			{
				List<MetaClass> subClasses = this.getSubClasses(artefacts, baseClass);
				
				for(int i=0; i<subClasses.size(); i++)
				{
					this.deleteAll(artefacts, subClasses.get(i));
				}
				
				this.delete(baseClass);
			}
			
			// delete
			else
			{
				this.delete(baseClass);
			}
		}
		
		// concrete term
		else
		{
			this.delete(baseClass);
		}
	}
	
	/**
	 * deletes a class out of the data structure in model layer
	 * for once and all. And it deletes the corresponding node
	 * in the tree model, too. So both are always synched.
	 * 
	 * @param mClass
	 * 		the class to delete
	 */
	private void delete(MetaClass mClass)
	{
		// delete from model layer
		this.artefactCollection.deleteArtefact(mClass);
		
		// delete from tree model
		this.deleteNodeFromModel(mClass, this.model);
	}
	
	/**
	 * Method checks whether the base class of a sub-class has
	 * still more than one sub-class.
	 * When nodes are deleted form the model layer, it could happen
	 * that there is no sub-class left.
	 * In this case the base class has to be deleted, too.
	 * </p>
	 * A few examples might help to get the picture:</br>
	 * <ul>
	 * 	<li>red indicates the node to delete</li>
	 * 	<li>roots are abstract terms and must be sub-classed</li>
	 * 	<li>intermediate nodes are abstract terms and must be sub-classed</li>
	 * 	<li>leafs are concrete terms or specifications.
	 * 		Always keep in mind, it to have a view at it as a tester!
	 * 	</li>
	 * </ul>
	 * 
	 * </p><p>
	 * 	<b>Example 1: Leaf</b></br>
	 * 	<img src="leaf_example1.jpg" alt="leaf_example1.jpg" width="25%" height="25%"/></br>
	 * 	There are three nodes in the data structure and the leaf is going to
	 * 	be deleted. Because its base class has still a sub-class it is non-critical.
	 * </p></p>
	 * 
	 * </p><p>
	 * 	<b>Example 2: Leaf</b></br>
	 * 	<img src="leaf_example2.jpg" alt="leaf_example2.jpg" width="25%" height="25%"/></br>
	 * 	The leaf node is gonna be deleted. But this time its base class has no
	 * 	other sub-classes left. That means a base class without any concrete sub-class
	 * 	is gonna be deleted, too.
	 * </p></p>
	 * 
	 * </p><p>
	 * 	<b>Example 3: Intermediate</b></br>
	 * 	<img src="intermediate_example1.jpg" alt="intermediate_example1.jpg" width="25%" height="25%"/></br>
	 * 	Here is a intermediate node marked to delete. Because it abstract all of it's
	 * 	sub-classes - which are concrete - are deleted, too. The base class has still
	 * 	a sub-class and remains.
	 * </p></p>
	 * 
	 * </p><p>
	 * 	<b>Example 4: Intermediate</b></br>
	 * 	<img src="intermediate_example2.jpg" alt="intermediate_example2.jpg" width="25%" height="25%"/></br>
	 * 	Again the intermediate node is marked to delete. But this time it's critical,
	 * 	because its base class has no other sub-classes to hold.
	 * 	As mentions before, root nodes are abstract like intermediate, it can not
	 * 	exist without sub-classing. As a result of that, it must be deleted, too.
	 * </p></p>
	 * 
	 * </p><p>
	 * 	<b>Example 5: Root</b></br>
	 * 	<img src="root_example1.jpg" alt="root_example1.jpg" width="25%" height="25%"/></br>
	 * 	The root is gonna be deleted and as an abstract node all of its sub-classes
	 * 	are gonna be deleted.
	 * </p></p>
	 * 
	 * @param mClass
	 * 		class has to hold more than one sub-class otherwise
	 * 		it is also deleted.
	 * @return
	 * 		true, if the base class of a class has still more sub-classes.
	 */
	private boolean hasMoreSubclasses(List<MetaClass> artefacts, MetaClass mClass)
	{
		boolean hasMore = false;
		
		// there has to be at least one more, but the node to delete
		if(this.countSubClasses(artefacts, mClass) > 1)
		{
			hasMore = true;
		}
		
		return hasMore;
	}
	
	/**
	 * counts the amount of existing sub-classes and returns
	 * that number.
	 * 
	 * @param artefacts
	 * 		the data to search for
	 * @param mClass
	 * 		the class which must have sub-classes
	 * @return
	 * 		number of sub-classes
	 */
	private int countSubClasses(List<MetaClass> artefacts, MetaClass mClass)
	{
		int n = 0;
		
		for(Iterator<MetaClass> i=artefacts.iterator(); i.hasNext();)
		{
			MetaClass anyClass = i.next();
			
			if(anyClass.hasSuperClass())
			{
				if(anyClass.getSuperClass().getName().equals(mClass.getName()))
				{
					n++;
				}
			}
		}
		
		return n;
	}
	
	/**
	 * catches all sub-classes of a base class and returns
	 * the result in a list. That's necessary, because a class
	 * can have more than one sub-class. But the other way round
	 * is not possible. A sub-class can have only one base class.
	 * 
	 * @param artefacts
	 * 		data of artefacts to search for
	 * @param mClass
	 * 		the class for which to find sub-classes
	 * @return
	 * 		list of sub-classes or null
	 */
	private List<MetaClass> getSubClasses(List<MetaClass> artefacts, MetaClass mClass)
	{
		List<MetaClass> subClasses = null;
		
		for(Iterator<MetaClass> i=artefacts.iterator(); i.hasNext();)
		{
			MetaClass subClass = i.next();
			
			if(subClass.hasSuperClass())
			{
				if(subClass.getSuperClass().getName().equals(mClass.getName()))
				{
					if(subClasses == null)
					{
						subClasses = new ArrayList<MetaClass>();
					}
					
					subClasses.add(subClass);
				}
			}
		}
		
		return subClasses;
	}
	
	/**
	 * summarizes all sub-nodes of the current selected abstract term root.
	 * This root is not in the model layer structure. So it has to be deleted
	 * separate. But its sub-classes are stored there and will be deleted
	 * recursive.
	 * 
	 * @param toDel
	 * 		a node to delete
	 * @return
	 * 		list of sub-nodes to delete recursive, because there could be
	 * 		more than one
	 */
	@SuppressWarnings("unchecked")
	private List<DefaultMutableTreeNode> getNodes(DefaultMutableTreeNode toDel)
	{
		List<DefaultMutableTreeNode> list = null;
		
		for(Enumeration<DefaultMutableTreeNode> i=toDel.children(); i.hasMoreElements();)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) i.nextElement();
			
			if(list == null)
			{
				list = new ArrayList<DefaultMutableTreeNode>();
			}
			
			list.add(node);
		}
		
		return list;
	}
	
	/**
	 * deletes the node not only from the model layer but also from the
	 * tree model component.
	 * 
	 * @param mClass
	 * 		the meta class to delete
	 * @param model
	 * 		tree model component
	 */
	@SuppressWarnings("unchecked")
	private void deleteNodeFromModel(MetaClass mClass, DefaultTreeModel model)
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		
		for(Enumeration<DefaultMutableTreeNode> i=root.breadthFirstEnumeration(); i.hasMoreElements();)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) i.nextElement();
			
			if(node.toString().equals(mClass.getName()))
			{
				model.removeNodeFromParent(node);
			}
		}
	}
	
	/**
	 * for debugging purposes only
	 * 
	 * @param model
	 * 		the model to print out
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void print(DefaultTreeModel model)
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		
		for(Enumeration<DefaultMutableTreeNode> i=root.breadthFirstEnumeration(); i.hasMoreElements();)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) i.nextElement();
			
			System.out.println(node.toString());
		}
	}
}