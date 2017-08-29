package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.ValueRetriever;

/**
 * This generator has the job of generating first names.
 * It is possible to choose between different language dependent
 * first names. The class inherits behaviour from {@link Generator}.
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
public class FirstName extends Generator
{
	/**
	 * specifies to which sex type a prename
	 * is generated: <i>male</i> or <i>female</i>
	 */
	private SexType sex = null;
	
	/**
	 * holds the observers for this generator
	 */
	private List<ObserverGenerator> observers = null;
	
	/**
	 * default constructor assigns a wordy name
	 * for this kind of generator and specifies.
	 */
	public FirstName()
	{
		super("First Name");
		
		this.setDefaultSex();
		
		this.setPathToFileByLanguage();
	}
	
	/**
	 * <font size=6><b>Clone</b></font></br>
	 * constructor is only visible to package.
	 * And is used to make a copy of this class.
	 * 
	 * @param firstName
	 * 		object to clone
	 */
	protected FirstName(FirstName firstName)
	{
		super(firstName);
		
		this.sex = firstName.getSex();
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
	 * to generate accurate prenames, the
	 * generator needs to know which sex
	 * type it is.
	 * It doesn't make sense to generate a prename
	 * for girls if the sex type is <i>male</i>
	 * and vice versa.
	 * 
	 * @param sex
	 * 		which sex type is it? <i>male</i> or <i>female</i>
	 */
	public void setSex(SexType sex)
	{
		this.sex = sex;
		
		this.notifyOberverFillBehaviour();
	}
	
	/**
	 * gets the sex type for this generator.
	 * It's important to distinguish between 
	 * <i>female</i> and <i>male</i> types,
	 * because it doesn't make sense to generate
	 * a prename for girls when it's a <i>male</i>.
	 * 
	 * @return
	 * 		sex: <i>female</i> or <i>male</i>
	 */
	public final SexType getSex()
	{
		return this.sex;
	}
	
	@Override
	public void registerObserverGenerator(ObserverGenerator observer)
	{
		if(this.observers == null)
		{
			this.observers = new ArrayList<ObserverGenerator>();
		}
		
		this.observers.add(observer);
		
		// register notification
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
				
				// remove notification
				this.notifyObserverGenerator(false);
			}
		}
	}
	
	@Override
	public void notifyObserverGenerator(boolean add)
	{
		if(this.observers != null)
		{
			for(Iterator<ObserverGenerator> i=this.observers.iterator(); i.hasNext();)
			{
				ObserverGenerator ob = i.next();
				
				ob.updateGenerator(add, this);
			}
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new FirstName(this);
	}

	@Override
	protected final String getFile()
	{
		String preName = null;
		
		this.setDefaultSex();
		
		if(this.getSex().equals(SexType.FEMALE))
		{
			preName = Resources.PRENAME_GIRLS;
		}
		else if(this.getSex().equals(SexType.MALE))
		{
			preName = Resources.PRENAME_BOYS;
		}
		
		return preName;
	}
	
	/**
	 * checks, if the sex type has been initialized.
	 * Otherwise it restores the default value, that
	 * is <i>female</i>.
	 */
	private void setDefaultSex()
	{
		if(this.getSex() == null)
		{
			this.sex = SexType.FEMALE;
		}
	}
}