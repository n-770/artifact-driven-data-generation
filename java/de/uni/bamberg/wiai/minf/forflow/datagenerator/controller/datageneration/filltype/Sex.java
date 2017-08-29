package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.ValueRetriever;

/**
 * Sex generator has the job of creating the sex to an attribute.
 * Generated values for gender are <i>female</i> or <i>male</i>.
 * The class inherits behaviour of abstract class {@link Generator}
 * and is observable.
 * </p>
 * <font size=6><b>Observable</b></font></br>
 * Observable means here, the class can either
 * registered to or un-registered from a list in UI.
 * More precisely, if any observer registers to a generator the generator
 * notifies the UI which in turn registers the subject to a list
 * of available generators.
 * 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/30/09
 */
public class Sex extends Generator
{
	/**
	 * holds observers for this kind of subject
	 */
	private List<ObserverGenerator> observers = null;
	
	/**
	 * holds the gender for an attribute
	 */
	private SexType gender = null;
	
	/**
	 * default constructor assigns itself
	 * the name of its subject.
	 */
	public Sex()
	{
		super("Sex");
		
		this.setPathToFileByLanguage();
	}
	
	/**
	 * <font size=2><b>Clone</b></font></br>
	 * this constructor is passed an <b>Sex</b> object
	 * to get an exact copy of that.
	 * As the headline states, it is only used to make
	 * a clone object.
	 * 
	 * @param sex
	 * 		object to clone
	 */
	protected Sex(Sex sex)
	{
		super(sex);
		
		this.gender = sex.getGender();
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
	
	/**
	 * sets the gender <i>female</i> or <i>male</i>
	 * 
	 * @param gender
	 * 		female or male?
	 */
	public void setGender(SexType gender)
	{
		if(gender != null)
		{
			this.gender = gender;
		}
		else
		{
			this.setDefaultSex();
		}
		
		this.notifyOberverFillBehaviour();
	}
	
	/**
	 * gets the gender of this class.
	 * 
	 * @return
	 * 		female or male
	 */
	public final SexType getGender()
	{
		return this.gender;
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
		return new Sex(this);
	}

	@Override
	protected String getFile()
	{
		return Resources.SEX;
	}
	
	/**
	 * sets the default gender if not initialized
	 */
	private void setDefaultSex()
	{
		if(this.getGender() != null)
		{
			this.setGender(SexType.FEMALE);
		}
	}
}