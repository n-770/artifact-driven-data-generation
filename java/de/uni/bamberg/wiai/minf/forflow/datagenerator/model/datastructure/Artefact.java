package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <b>Artefact</b> is a wrapper for {@link MetaClass} objects.
 * Here are those artefacts gathered at one place to which test
 * data should be generated.
 * Notice, at this point we only support two types of artefacts.
 * That is, <i>products</i> and <i>documents</i>. Furthermore, we
 * hold those sets in disjunct sets.
 * </p>
 * Each artefact is also an observable. Subjects observing artefacts
 * get gets notified by the latest updates. This helps to get synchronized
 * each time an update occurs. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public class Artefact implements ObservableArtefact
{
	/**
	 * holds one or more artefacts of the a type to which 
	 * the system generates test data.
	 */
	private List<MetaClass> artefacts = null;
	
	/**
	 * keeps track of total elements
	 */
	private int noOfClasses = 0;
	
	/**
	 * backtracks the total amount of facets hold in data structure.
	 */
	private int noOfAttributes = 0;
	
	/**
	 * describes the type of artefacts in this collection
	 */
	private ArtefactType artefactType = null;
	
	/**
	 * here are the observers of this artefact collection.
	 */
	private List<ObserverArtefact> observersArtefact = null;
	
	/**
	 * default constructor initializes the lists used
	 * in this class.
	 * Its visibility is restricted to package.
	 */
	protected Artefact()
	{
		this.artefacts = new ArrayList<MetaClass>();
		
		this.observersArtefact = new ArrayList<ObserverArtefact>();
	}
	
	/**
	 * sets the artefacts at once and
	 * notifies the observers about changes.
	 * 
	 * @param artefacts
	 * 		list of products
	 */
	public void setArtefacts(List<MetaClass> artefacts)
	{
		this.artefacts = artefacts;
		
		this.incrementNoOfAttributes(artefacts);
		
		this.notifyObserverArtefact();
	}
	
	/**
	 * adds an artefact to the corresponding list
	 * and notifies observers about the data change.
	 * But before this is done, it has to be checked,
	 * if an element with that name is already in the
	 * collection. It's not allowed to put it in twice.
	 * 
	 * @param artefact
	 * 		consists of a meta class.
	 */
	public void addArtefact(MetaClass artefact)
	{
		if(this.artefacts == null)
		{
			this.artefacts = new ArrayList<MetaClass>();
		}
		
		if(!this.hasArtefact(artefact))
		{
			this.artefacts.add(artefact);
			
			this.incrementNoOfAttributes(artefact);
			
			this.notifyObserverArtefact();
		}
	}
	
	/**
	 * gets an specific meta class of products' artefact list. 
	 * If an artefact could be found with the given name it
	 * is returned, otherwise null.
	 * 
	 * @param name
	 * 		the name of an product artefact
	 * @return
	 * 		meta class with given name, or null
	 */
	public MetaClass getArtefact(String name)
	{
		MetaClass res = null;
		
		for(Iterator<MetaClass> i=this.artefacts.iterator(); i.hasNext();)
		{
			MetaClass mClass = i.next();
			
			if(mClass.getName().equals(name))
			{
				res = mClass;
			}
		}
		
		return res;
	}
	
	/**
	 * gets a specific meta class out of the product artefact
	 * data structure. If the structure is null, <i>null</i> is
	 * returned, otherwise the element at the desired index if
	 * the index is valid.
	 * 
	 * @param index
	 * 		element at desired position
	 * @return
	 * 		meta class or null
	 */
	public MetaClass getArtefact(int index)
	{
		if((this.artefacts != null) && (this.artefacts.size() > index))
		{
			return this.artefacts.get(index);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * checks whether an meta class is already in
	 * the data structure of artefacts.
	 * We do this by the name of a class.
	 * 
	 * @param mClass
	 * 		check meta class against product artfacts 
	 * @return
	 * 		true, if and only if it exists already
	 */
	public final boolean hasArtefact(MetaClass mClass)
	{
		boolean has = false;
		
		for(Iterator<MetaClass> i=this.artefacts.iterator(); i.hasNext();)
		{
			MetaClass mClass2 = i.next();
			
			if(mClass.getName().equals(mClass2.getName()))
			{
				has = true;
			}
		}
		
		return has;
	}
	
	/**
	 * gets the artefact list
	 * 
	 * @return
	 * 		list of products
	 */
	public List<MetaClass> getArtefacts()
	{
		return this.artefacts;
	}
	
	/**
	 * removes an artefact from the data structure.
	 * Because the data has changed after that operation,
	 * a notification is send out. 
	 * 
	 * @param mClass
	 * 		artefact to delete
	 * @return
	 * 		true, if successfully deleted
	 */
	public final boolean deleteArtefact(MetaClass mClass)
	{
		boolean deleted = false;
		
		int n = this.artefacts.indexOf(mClass);
		
		if(n >= 0)
		{
			this.artefacts.remove(n);
			
			deleted = true;
			
			this.decrementNoOfAttributes(mClass);
			
			this.notifyObserverArtefact();
		}
		
		return deleted;
	}
	
	/**
	 * removes an artefact from data structure.
	 * Because the data has changed after that operation,
	 * observers are notified about it.
	 * 
	 * @param name
	 * 		the name of an object to delete
	 * @return
	 * 		true, if successfully deleted
	 */
	public final boolean deleteArtefact(String name)
	{
		boolean deleted = false;
		
		for(int i=0; i<this.artefacts.size(); i++)
		{
			MetaClass mClass = this.artefacts.get(i);
			
			if(mClass.getName().equals(name))
			{
				this.artefacts.remove(i);
				
				deleted = true;
				
				this.decrementNoOfAttributes(mClass);
				
				this.notifyObserverArtefact();
			}
		}
		
		return deleted;
	}
	
	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 
	 * @return
	 * 		iterator bound to product artefacts
	 */
	public final Iterator<MetaClass> iterator()
	{
		return this.artefacts.iterator();
	}
	
	/**
	 * sets the type of artefacts for this collection.
	 * That means, there are only those kinds of types in it.
	 * Each type has it's own collection.
	 * 
	 * @param artefactType
	 * 		type of artefacts
	 */
	public void setArtefactType(ArtefactType artefactType)
	{
		this.artefactType = artefactType;
	}
	
	/**
	 * gets the type of artefacts for this collection.
	 * For each type are one collection.
	 * 
	 * @return
	 * 		artefact type
	 */
	public final ArtefactType getArtefactType()
	{
		return this.artefactType;
	}
	
	/**
	 * returns the total amount of elements.
	 * It's read-only!
	 * 
	 * @return
	 * 		amount of elements
	 */
	public final int noOfClasses()
	{
		return this.noOfClasses;
	}
	
	/**
	 * reflects the total number of attributes.
	 * Summarized from all classes.
	 * 
	 * @return
	 * 		attributes in total
	 */
	public final int noOfAttributes()
	{
		return this.noOfAttributes;
	}
	
	@Override
	public void registerObserverArtefact(ObserverArtefact observer, ArtefactType type)
	{
		if(!this.observersArtefact.contains(observer))
		{
			this.observersArtefact.add(observer);
			
			this.notifyObserverArtefact();
		}
	}
	
	@Override
	public void removeObserverArtefact(ObserverArtefact observer, ArtefactType type)
	{
		int n = this.observersArtefact.indexOf(observer);
		
		if(n >= 0)
		{
			this.observersArtefact.remove(n);
		}
	}
	
	@Override
	public void notifyObserverArtefact()
	{
		this.updateNoOfClasses();
		
		for(int i=0; i<this.observersArtefact.size(); i++)
		{
			ObserverArtefact ob = this.observersArtefact.get(i);
			
			ob.updateArtefact(this.getArtefacts(), this.artefactType);
		}
	}
	
	/**
	 * only for debugging and testing purposes.
	 */
	public void print()
	{
		if(!this.artefacts.isEmpty())
		{
			this.printList(this.artefacts, false);
		}
	}
	
	/**
	 * updates the element counter each time the data structure changes.
	 */
	private void updateNoOfClasses()
	{
		// reset
		this.noOfClasses = 0;
		
		if(this.artefacts != null)
		{
			this.noOfClasses += this.artefacts.size();
		}
	}
	
	/**
	 * updates the counter for the number of attributes.
	 * It increments the counter, when new facets and classes are added.
	 * 
	 * @param artefacts
	 * 		list of artefacts
	 */
	private void incrementNoOfAttributes(List<MetaClass> artefacts)
	{
		for(Iterator<MetaClass> i=artefacts.iterator(); i.hasNext();)
		{ 
			this.incrementNoOfAttributes(i.next());
		}
	}
	
	/**
	 * updates the counter for the number of attributes.
	 * It increments the counter, when new facets and classes are added.
	 * 
	 * @param mClass
	 * 		an element of artefact's data structure.
	 */
	private void incrementNoOfAttributes(MetaClass mClass)
	{
		for(Iterator<MetaAttribute> i=mClass.iterator(); i.hasNext();)
		{
			i.next();
			this.noOfAttributes++;
		}
	}
	
	/**
	 * updates the counter for the number of attributes.
	 * Its decremented, when facets are deleted.
	 * 
	 * @param mClass
	 * 		an artefact class
	 */
	private void decrementNoOfAttributes(MetaClass mClass)
	{
		for(Iterator<MetaAttribute> i=mClass.iterator(); i.hasNext();)
		{
			i.next();
			this.noOfAttributes--;
		}
	}
	
	/**
	 * called by {@link #print()} and prints the passed list.
	 * There're two ways possible in printing, either sequentially
	 * or recursive.
	 * </p>
	 * If you wanna print only the elements in the list once, you
	 * take sequentially.
	 * </p>
	 * But if you wanna print the hierarchy also, you should print
	 * recursive.
	 * In the latter mode, it's highly possible to have more than once
	 * a element printed out. But that does not mean it is in the
	 * list more than once. 
	 * 
	 * @param list
	 * 		prints the elements in the list
	 * @param recursive
	 * 		printing mode recursive or sequentially
	 */
	private void printList(List<MetaClass> list, boolean recursive)
	{
		System.out.println("Artefact: " +this.artefactType.getName());
		System.out.println("Size: " +list.size());
		
		for(Iterator<MetaClass> i=list.iterator(); i.hasNext();)
		{
			MetaClass mClass = i.next();
			
			this.printClass(mClass, recursive);
			
			System.out.println();
		}
		
		System.out.println();
	}
	
	/**
	 * called by {@link #printList(List, boolean)}
	 * 
	 * @param mClass
	 * 		the meta class to print
	 * @param recursive
	 * 		do it recurisive?
	 */
	private void printClass(MetaClass mClass, boolean recursive)
	{
		System.out.println(mClass.getName() +" " +mClass.getArtefactType());
		System.out.println("isConcreteTerm: " +mClass.isConcreteSpec());
		for(Iterator<MetaAttribute> j=mClass.getAttributes().iterator(); j.hasNext();)
		{
			MetaAttribute mAttribute = j.next();
			
			System.out.println(mAttribute.getName() +" " +mAttribute.getDataType());
		}
		
		if(mClass.hasSuperClass())
		{
			System.out.println("base class: " +mClass.getSuperClass().getName());
			
			System.out.println();
			
			if(recursive)
			{
				this.printClass(mClass.getSuperClass(), recursive);
			}
		}
	}
}