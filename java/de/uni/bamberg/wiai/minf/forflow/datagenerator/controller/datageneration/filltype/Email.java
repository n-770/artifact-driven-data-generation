package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.ValueRetriever;

/**
 * This class is responsible for generating email
 * addresses. The class inherits its behaviour from
 * the base-class of {@link Generator}.
 * Reember, to achieve realistic test data all email
 * addresses are life data. They have been collected
 * by the help of public accessable email address databases.
 * </p>
 * <font size=6><b>Observable</b></font></br>
 * It is observable like all other fill behaviours.
 * Observable means in this context, whenever an observer 
 * registers to or un-registers from this class, the subject
 * itself notifies the UI to register this class in a specific
 * list of generators.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/11/09
 */
public class Email extends Generator
{
	/**
	 * holds the observers of this class
	 */
	private List<ObserverGenerator> observers = null;
	
	/**
	 * default constructor
	 */
	public Email()
	{
		super("Email");
		
		this.setPathToFileByLanguage();
	}
	
	/**
	 * this constructor is passed an email object.
	 * The constructor is intended to be used only
	 * for making an copy of this class.
	 * It is accessable throughout the package.
	 * 
	 * @param email
	 * 		object to clone
	 */
	protected Email(Email email)
	{
		super(email);
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
		return new Email(this);
	}
	
	@Override
	protected String getFile()
	{
		return Resources.EMAILS;
	}
}