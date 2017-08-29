package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerImpl;

/**
 * This class is responsible for creating dates.
 * The class inherits behaviour from the abstract class {@link Generator}.
 * Generating dates is helpful when the tester is faced with
 * the task to generate a file with a <i>time stamp</i>.
 * Let's assume we've to create a file called <i>sales quantity</i>.
 * It has of course an attribute <i>date modified</i> and <i>date created</i>.
 * The class is able to generate to these attributes (facets) dates.  
 * </p>
 * <font size=6><b>Observable</b></font></br>
 * It is observable like all other fill behaviours.
 * Observable means in this context, whenever an observer 
 * registers to or un-registers from this class, the subject
 * itself notifies the UI to register this class in a specific
 * list of generators.
 * </p>
 * <font size=6><b>Naming convention</b></font></br>
 * To avoid irritations and conflicts with Java.util.Date this
 * class is slightly different named.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public class Dates extends Generator
{
	/**
	 * date is in msec, but we want days
	 * 
	 * msec / 1000 = sec / 60 = min / 60 = h / 24 = days   
	 */
	private final float days = (1000 * 60 * 60 * 24);
	
	/**
	 * a month are approximate 30 days
	 */
	private final float month = (this.days * 30.4167f);
	
	/**
	 * 12 month make a year
	 */
	private final float years = (this.month * 12);
	
	/**
	 * this is the lower bound of the date interval
	 */
	private Date date_min = null;
	
	/**
	 * here is the upper bound of the interval
	 */
	private Date date_max = null;
	
	/**
	 * specifies the level of details. In other words,
	 * it set the available values
	 */
	private DateInterval levelOfDetail = null;
	
	/**
	 * here are the observers of this class
	 */
	private List<ObserverGenerator> observers = null;
	
	/**
	 * default constructor set the level of detail to <i>day</i>
	 */
	public Dates()
	{
		super("Date");
		
		this.setDefaultLevelOfDetail();
		
		ControllerImpl controller = (ControllerImpl) ControllerFactory.getController();
		
		this.date_min = controller.getDateLowerBound();
		this.date_max = controller.getDateUpperBound();
	}
	
	/**
	 * <font size=6><b>Clone</b></font></br>
	 * the constructor is only accessable in same package
	 * and is intended to make a copy of this class.
	 * 
	 * @param date
	 * 		object to clone
	 */
	protected Dates(Dates date)
	{
		super(date);
		
		this.date_min = date.getLowerDate();
		this.date_max = date.getUpperDate();
		this.levelOfDetail = date.getLevelOfDetail();
	}
	
	/**
	 * sets the lower bound of the date interval.
	 * 
	 * @param lower
	 * 		lower bound
	 */
	protected void setLowerDate(Date lower)
	{
		this.date_min = lower;
	}
	
	/**
	 * gets the lower bound of the date interval
	 * 
	 * @return
	 * 		lower bound
	 */
	public final Date getLowerDate()
	{
		return this.date_min;
	}
	
	/**
	 * sets the upper bound of the date interval.
	 * 
	 * @param upper
	 * 		upper bound
	 */
	protected void setUpperDate(Date upper)
	{
		this.date_max = upper;
	}
	
	/**
	 * gets the upper bound of the date interval.
	 * 
	 * @return
	 * 		upper bound
	 */
	public final Date getUpperDate()
	{
		return this.date_max;
	}
	
	/**
	 * sets the level of detail.
	 * 
	 * @param level
	 * 		year, month, day are possible
	 */
	public void setLevelOfDetail(DateInterval level)
	{
		if(level != null)
		{
			this.levelOfDetail = level;
		}
		else
		{
			this.setDefaultLevelOfDetail();
		}
		
		if((this.getLowerDate() != null) && (this.getUpperDate() != null))
		{
			this.setN(this.calcN(this.getLowerDate(), this.getUpperDate(), this.getLevelOfDetail()));
			
			this.setLowerBound(0);
			this.setUpperBound(getN() - 1);
		}
	}
	
	/**
	 * level of detail specifies the amount of values
	 * available for generating dates.
	 * 
	 * @return
	 * 		year, month or day
	 */
	public final DateInterval getLevelOfDetail()
	{
		return this.levelOfDetail;
	}
	
	@Override
	public final String getValueAt(int n)
	{
		String value = null;
		
		this.setDefaultLevelOfDetail();
		
		// in range?
		if((n >= 0) && (n < this.getN()))
		{
			Date tmp = new Date(this.calcMiliSec(n, this.getLevelOfDetail()));
			
			value = this.formatDate(tmp, this.getLevelOfDetail());
		}
		
		return value;
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
		return new Dates(this);
	}
	
	/**
	 * this class doesn't specify any value file so
	 * it's not gonna be used. It throws an {@link UnsupportedOperationException}.
	 */
	@Override
	protected final String getFile()
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * checks whether the date interval has been set,
	 * otherwise the default value is set. That is <i>day</i>
	 */
	private void setDefaultLevelOfDetail()
	{
		if(this.levelOfDetail == null)
		{
			this.setLevelOfDetail(DateInterval.DAY);
		}
	}
	
	/**
	 * calculates the available days in the range
	 * of the interval of upper - lower bound.
	 * The level of detail restricts the possible values
	 * for an date interval depending on the context.
	 * 
	 * @see
	 * 		DateInterval
	 * @param lower
	 * 		the lower bound of interval
	 * @param upper
	 * 		the upper bound of interval
	 * @param level
	 * 		specifies the level of detail.
	 */
	private int calcN(Date lower, Date upper, DateInterval level)
	{
		float res = 0;
		
		// we calculate here 'msec' of [lb, up]
		long difference = (this.getUpperDate().getTime() - this.getLowerDate().getTime());
		
		// days
		if(level.equals(DateInterval.DAY))
		{
			res = (difference / this.days);
		}
		
		// month
		else if(level.equals(DateInterval.MONTH))
		{
			res = (difference / this.month);
		}
		
		// year
		else if(level.equals(DateInterval.YEAR))
		{
			res = (difference / this.years);
		}
		
		return (int) Math.ceil(res);
	}
	
	/**
	 * calculates the milliseconds of a value at position n.
	 * The date interval goes from the lower bound to the upper bound.
	 * </p>
	 * <font size=6><b>Example</b></font></br>
	 * Suppose you've got the interval [2007, 2009] and
	 * the level of detail is highest, that is <i>days</i>.
	 * Then you get the following: </br>
	 * i=0 Jan/01/2007
	 * i=1 Jan/02/2007
	 * i=2 
	 * i=3 
	 * i=4 
	 * i=5 Jan/06/2007
	 * ...
	 * i=1095 Dec/31/2009
	 * 
	 * @param n
	 * 		the value at the index n
	 * @param level
	 * 		the level of detail restricts the interval to
	 * 		possible values.
	 * @return
	 * 		the date at index n
	 */
	private long calcMiliSec(int n, DateInterval level)
	{
		long res = 0;
		
		// day
		if(level.equals(DateInterval.DAY))
		{
			res = (long) Math.ceil((this.getLowerDate().getTime() + (n * this.days)));
		}
		
		// month
		else if(level.equals(DateInterval.MONTH))
		{
			res = (long) Math.ceil((this.getLowerDate().getTime() + (n * this.month)));
		}
		
		// year
		else if(level.equals(DateInterval.YEAR))
		{
			res = (long) Math.ceil((this.getLowerDate().getTime() + (n * this.years)));
		}
		
		return res;
	}
	
	/**
	 * this class formats the date so that the result is a pretty
	 * printed string. This is done with the help of java.text.SimpleDateFormat.
	 * It is local-sensitive. If it is instantiated without a <i>local</i>
	 * parameter, it will format the date according to the default <i>Local</i>.
	 * 
	 * @param date
	 * 		the date to format pretty printed
	 * @param levelOfDetail
	 * 		the current level of detail
	 * @return
	 * 		pretty formatted date
	 */
	private String formatDate(Date date, DateInterval levelOfDetail)
	{
		String formattedDate = null;
		
		SimpleDateFormat formatter = null;
		
		Locale local = this.getSelectedLocal();
		
		if(levelOfDetail.equals(DateInterval.YEAR))
		{
			formatter = new SimpleDateFormat("yyyy", local);
			
			formattedDate = formatter.format(date);
		}
		else if(levelOfDetail.equals(DateInterval.MONTH))
		{
			formatter = new SimpleDateFormat("MMM yyyy", local);
			
			formattedDate = formatter.format(date);
		}
		else if(levelOfDetail.equals(DateInterval.DAY))
		{
			formatter = new SimpleDateFormat("d MMM yyyy", local);
			
			formattedDate = formatter.format(date);
		}
		
		return formattedDate;
	}
	
	/**
	 * figures out the current local.
	 * 
	 * @return
	 * 		current local
	 */
	private Locale getSelectedLocal()
	{
		Locale local = null;
		
		if(this.getLanguage().equals(LanguageType.US))
		{
			local = Locale.US;
		}
		else if(this.getLanguage().equals(LanguageType.DE))
		{		
			local = Locale.GERMANY;
		}
		
		return local;
	}
}