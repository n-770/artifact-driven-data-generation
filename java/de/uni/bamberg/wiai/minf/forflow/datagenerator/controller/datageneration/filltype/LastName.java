package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.ValueRetriever;

/**
 * This generator is responsible to generate surnames
 * for an attribute. Unlike {@link Name} generator, this
 * one has only one job. The class inherits behaviour of
 * abstract class {@link Generator} and notifies about
 * changes in observing or removing from the UI. 
 * </p>
 * <font size=6><b>Observable</b></font></br>
 * Notice, the class implements {@link FillBehaviour} interface.
 * It makes this kind of strategy observable.
 * Observable means in this context, whenever an observer 
 * registers to or un-registers from this class, the subject
 * itself notifies the UI to register this class in a specific
 * list of generators.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/30/09
 */
public class LastName extends Generator
{
	/**
	 * holds observers to this generator
	 */
	private List<ObserverGenerator> observers = null; 
	
	/**
	 * default constructor creates a meaningful name
	 * for this kind of generator to identify.
	 */
	public LastName()
	{
		super("Last Name");
		
		this.setPathToFileByLanguage();
	}
	
	/**
	 * <font size=6><b>Clone</b></font></br>
	 * constructor is only visible to package.
	 * The object to clone is passed in and it's
	 * only used for that purpose. 
	 * 
	 * @param lastName
	 * 		the object to clone.
	 */
	protected LastName(LastName lastName)
	{
		super(lastName);
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
		return new LastName(this);
	}

	@Override
	protected String getFile()
	{
		return Resources.NAMES;
	}
}