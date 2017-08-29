package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.ValueRetriever;

/**
 * Address class is responsible for generating meaningful
 * addresses. The class inherits behaviour from abstract class
 * {@link Generator} and is, of course, observable.
 * </p>
 * <font size=6><b>Observable</b></font></br>
 * Observable means in this context, whenever an observer 
 * registers to or un-registers from this class, the subject
 * itself notifies the UI to register this class in a specific
 * list of generators.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/30/09
 */
public class Address extends Generator
{
	/**
	 * holds the observers for this generator
	 */
	private List<ObserverGenerator> observers = null;
	
	/**
	 * default constructor assigns a name for
	 * this kind of generator to identify easily.
	 */
	public Address()
	{
		super("Address");
		
		this.setPathToFileByLanguage();
	}
	
	/**
	 * <font size=6><b>Clone</b></font></br>
	 * the constructor is only accessable in same package
	 * and is intended to make a copy of this class.
	 * 
	 * @param address
	 * 		object to clone
	 */
	protected Address(Address address)
	{
		super(address);
	}
	
	@Override
	public String getValueAt(int n)
	{
		this.setPathToFileByLanguage();
		
		// is in interval?
		if((n >= 0) && (n < this.getN()))
		{
			return ValueRetriever.getValueAt(n, getPathToFile());
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void registerObserverGenerator(ObserverGenerator observer)
	{
		if(this.observers == null)
		{
			this.observers = new ArrayList<ObserverGenerator>();
		}
		
		this.observers.add(observer);
		
		this.notifyObserverGenerator(true);
	}
	
	@Override
	public void removeObserverGenerator(ObserverGenerator observer)
	{
		if(this.observers != null)
		{
			int n = this.observers.indexOf(observer);
			
			if(n >= 0)
			{
				this.observers.remove(n);
				
				this.notifyObserverGenerator(false);
			}
		}
	}
	
	@Override
	public void notifyObserverGenerator(boolean add)
	{
		for(Iterator<ObserverGenerator> i=this.observers.iterator(); i.hasNext();)
		{
			ObserverGenerator ob = i.next();
			
			ob.updateGenerator(add, this);
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new Address(this);
	}
	
	@Override
	protected String getFile()
	{
		return Resources.ADDRESSES;
	}
}