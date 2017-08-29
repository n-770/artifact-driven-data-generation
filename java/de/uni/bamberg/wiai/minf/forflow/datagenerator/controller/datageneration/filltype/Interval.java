package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Interval implements {@link FillBehaviour}
 * </p>
 * Of course, it should be possible to define an interval
 * as a filling behaviour for an given attribute.
 * An interval is a set of numbers with the property that any
 * number that lies between two numbers in the set is also
 * included into it. For example, the set of all numbers
 * x satisfying <code>0 &le; x &le; 20</code> is an interval
 * which contains <i>0</i> and <i>20</i>, as well as all numbers
 * between them.
 * </p>
 * An interval can be described as:
 * <ul>
 * 		<li>integer</li>
 * </ul>
 * </p>
 * <b>Endpoints (upper & lower bound)</b></br>
 * The interval of numbers between <i>x</i> and <i>y</i>, including
 * <i>x</i> and <i>y</i> is denoted <code>[x, y]</code>. The two
 * numbers are called the <i>endpoints</i> of the interval.
 * To distinguish between between the upper and lower endpoint, we
 * call them <i>upper bound</i> and <i>lower bound</i>.
 * </p>
 * <b>Notation of intervals</b></br>
 * To indicate that one of the endpoints is to be excluded from the set,
 * we use the following notation:
 * </p>
 * <pre>
 * <code>
 * ]x, y[ = {z | x < z < y}
 * [x, y[ = {z | x &le; z < y}
 * ]x, y] = {z | x < z &le; y}
 * [x, y] = {z | x &le; z &le; y} 
 * </code>
 * </pre>
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
 * @since Apr/29/09
 */
public class Interval implements FillBehaviour
{
	/**
	 * the name of this filling behaviour.
	 * It is for displaying concerns only.
	 */
	private final String name = "Interval";
	
	/**
	 * the lower endpoint of the interval
	 */
	private int lowerBound = -1;
	
	/**
	 * the upper endpoint of the interval
	 */
	private int upperBound = -1;
	
	/**
	 * sets the interval steps
	 */
	private int intervalSteps = 0;
	
	/**
	 * sets the interval type. That could be an
	 * integer or a double.
	 */
	private IntervalType intervalType = null;
	
	/**
	 * holds the total values for an filling behaviour.
	 * In this case its generators. A pre-defined
	 * generator is linked to an so-called value file.
	 * The value file is an plain text file with a single
	 * value holding in each line. The total amount
	 * of those values makes <i>N</i>.
	 * </p>
	 * <b>N = &Sigma;n_i</b>
	 */
	private int N = 0;
	
	/**
	 * holds the observers for fill behaviour.
	 */
	private List<ObserverFillBehaviour> observers = null;
	
	/**
	 * default constructor set the interval to integer values.
	 */
	public Interval()
	{
		this.intervalType = IntervalType.INTEGER;
	}
	
	/**
	 * sets the interval of a given attribute (facet).
	 * The interval is verified and the success of
	 * this process is returned to the invoker method
	 * as feedback.
	 * </p>
	 * Example:
	 * </p>
	 * Suppose you wanna have an interval between one
	 * and fourteen, inclusive endpoints. All valid intervals
	 * to achieve this are:
	 * </p>
	 * <code>
	 * ]a, b[ &rarr; ]0, 15[ </br>
	 * [a, b[ &rarr; [1, 15[ </br>
	 * ]a, b] &rarr; ]0, 14] </br>
	 * [a, b] &rarr; [1, 14] </br>
	 * </code>
	 * 
	 * @param interval
	 * 		the interval is given as a string, 'cos
	 * 		we need the brackets to indicate the case.
	 * @return
	 * 		<i>true</i>, if a valid interval has been parsed,
	 * 		otherwise <i>false</i>
	 */
	public boolean setInterval(String interval)
	{
		boolean valid = false;
		
		// parse input
		int[] res = this.parseInterval(interval);
		
		if(res != null)
		{
			// validate
			if(this.isValid(res))
			{
				valid = true;
				
				this.lowerBound = res[0];
				this.upperBound = res[1];
				
				this.N = this.calculateN(this.lowerBound, this.upperBound);
				
				this.notifyOberverFillBehaviour();
			}
		}
		
		return valid;
	}
	
	/**
	 * returns the current interval for an attribute
	 * as an array of length two.
	 * You can access the endpoints as follows:</br>
	 * int[0] = lowerBound (inclusive)
	 * int[1] = upperBound (inclusive)
	 * 
	 * @return
	 * 		array of length two containing upper & lower
	 * 		endpoints.
	 */
	public final int[] getEndpointsOfInterval()
	{
		int[] interval = new int[2];
		
		interval[0] = this.lowerBound;
		interval[1] = this.upperBound;
		
		return interval;
	}
	
	/**
	 * gets the lower bound endpoint.
	 * This number is inclusive.
	 * 
	 * @return
	 * 		lower bound
	 */
	public final int getLowerBound()
	{
		return this.lowerBound;
	}
	
	/**
	 * gets the upper bound endpoint.
	 * This number is inclusive.
	 * 
	 * @return
	 * 		upper bound
	 */
	public final int getUpperBound()
	{
		return this.upperBound;
	}
	
	/**
	 * sets the interval stepping by the given
	 * parameter
	 * 
	 * @param steps
	 * 		sets the stepping
	 */
	public void setIntervalSteps(int steps)
	{
		this.intervalSteps = steps;
	}
	
	/**
	 * gets the interval stepping
	 * 
	 * @return
	 * 		interval stepping
	 */
	public final int getIntervalSteps()
	{
		return this.intervalSteps;
	}
	
	/**
	 * sets the interval type. An interval is either
	 * in integer values or double values.
	 * 
	 * @param intervalType
	 * 		integer or double values
	 */
	public void setIntervalType(IntervalType intervalType)
	{
		this.intervalType = intervalType;
	}
	
	/**
	 * gets the interval type. That could be either
	 * an integer or double type.
	 * 
	 * @return
	 * 		interval type
	 */
	public final IntervalType getIntervalTyp()
	{
		return this.intervalType;
	}
	
	/**
	 * this method is not used here and therefore
	 * throws an {@link UnsupportedOperationException}.
	 * This method is not applicable for intervals so
	 * an alternative method does the job.
	 * 
	 * @see
	 * 		#calculateN(int, int)
	 * @return
	 * 		N = &Sigma;n_i
	 */
	@Override
	public final int calculateN(URI file)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final int getN()
	{
		return this.N;
	}
	
	@Override
	public final String getValueAt(int n)
	{
		String value = null;
		
		// [lb, ub]
		if((n >= this.getLowerBound()) && (n <= this.getUpperBound()))
		{
			// iterate through interval
			for(int i=this.getLowerBound(); i<=this.getUpperBound(); i++)
			{
				// found
				if(i == n)
				{
					value = String.valueOf(i);
				}
			}
		}
		
		return value;
	}
	
	@Override
	public final String getName()
	{
		return this.name;
	}
	
	@Override
	public void registerObserverFillBehaviour(ObserverFillBehaviour observer)
	{
		if(this.observers == null)
		{
			this.observers = new ArrayList<ObserverFillBehaviour>();
		}
		
		this.observers.add(observer);
		
		this.notifyOberverFillBehaviour();
	}
	
	@Override
	public void removeObserverFillBehaviour(ObserverFillBehaviour observer)
	{
		if(this.observers != null)
		{
			int n = this.observers.indexOf(observer);
			
			if(n >= 0)
			{
				this.observers.remove(n);
			}
		}
	}
	
	@Override
	public void notifyOberverFillBehaviour()
	{
		if(this.observers != null)
		{
			for(Iterator<ObserverFillBehaviour> i=this.observers.iterator(); i.hasNext();)
			{
				ObserverFillBehaviour ob = i.next();
				
				ob.updateFillBehaviour(this.getLowerBound(), this.getUpperBound(), this.getN());
			}
		}
	}
	
	@Override
	public final String toString()
	{
		return (this.lowerBound +", " +this.upperBound);
	}
	
	/**
	 * parses the typed in interval and breaks the 
	 * string into several chunks.
	 * The break up is done with regular expressions
	 * to save parsing time. After the split up there
	 * are two sets of information. The first contains
	 * the brackets and the second the digits.
	 * </p>
	 * The value of upper and lower endpoints depend
	 * on the specific bracket, 'cos <i>inclusive</i>
	 * or <i>exclusive</i> cases must be considered.
	 * </p>
	 * The parsing succeeds, if only valid brackets,
	 * integers and no word characters are used.
	 * The found interval is then returned to the invoker
	 * in an array of length two.
	 * </br>
	 * <code>
	 * int[0] = lower bound </br>
	 * int[1] = upper bound
	 * </code>
	 * 
	 * @param interval
	 * 		the typed in interval of user
	 * @return
	 * 		if parsing has been succeeded, an array of
	 * 		length two will be returned.
	 */
	private int[] parseInterval(String interval)
	{
		int[] parsedInterval = new int[2];
		
		boolean upgradeLower = false;
		boolean downgradeUpper = false;
		
		Pattern patternBrackets = Pattern.compile("\\d");
		Pattern patternDigits = Pattern.compile("\\W");
		
		// [, ]
		String[] itemsBrackets = patternBrackets.split(interval);
		
		// 0 15
		String[] itemsDigits = patternDigits.split(interval);
		
		// brackets
		for(int i=0; i<itemsBrackets.length; i++)
		{
			String s = itemsBrackets[i];
			
			// bracket at beginning
			if((s.equals("[")) && (i==0))
			{
				upgradeLower = false;
			}
			else if((s.equals("]")) && (i==0))
			{
				upgradeLower = true;
			}
			
			// bracket at end
			else if((s.equals("[")) && (i+1==itemsBrackets.length))
			{
				downgradeUpper = true;
			}
			else if((s.equals("]")) && (i+1==itemsBrackets.length))
			{
				downgradeUpper = false;
			}
		}
		
		boolean isLowerSet = false;
		boolean isUpperSet = false;
		
		// digits
		for(int i=0; i<itemsDigits.length; i++)
		{
			try
			{
				if(!itemsDigits[i].isEmpty())
				{
					// lower bound
					if(!isLowerSet)
					{
						isLowerSet = true;
						
						int digit = Integer.parseInt(itemsDigits[i]);
						
						if(upgradeLower)
						{
							parsedInterval[0] = (digit +1);
						}
						else
						{
							parsedInterval[0] = digit;
						}
					}
					
					// upper bound
					else if(!isUpperSet && isLowerSet)
					{
						isUpperSet = true;
						
						int digit = Integer.parseInt(itemsDigits[i]);
						
						if(downgradeUpper)
						{
							parsedInterval[1] = (digit -1);
						}
						else
						{
							parsedInterval[1] = digit;
						}
					}
				}
			}
			catch(NumberFormatException nfe)
			{
				parsedInterval = null;
			}
		}
		
		return parsedInterval;
	}
	
	/**
	 * validates the parsed interval.
	 * The interval is said to be valid, if and
	 * only if it passes this method with success.
	 * For example, an interval starts always with
	 * the lower bound and ends with the upper bound.
	 * If lower endpoint is equal or greater than upper
	 * endpoint the interval fails the test.
	 * </p>
	 * <i>True</i> is returned in case the interval
	 * passes the test, otherwise <i>false</i> will be
	 * returned.
	 * 
	 * @param interval
	 * 		interval typed in by user has to be checked,
	 * 		before it is used.
	 * @return
	 * 		<i>true</i>, if interval is said to be valid.
	 */
	private boolean isValid(int[] interval)
	{
		boolean valid = false;
		
		if(interval[0] < interval[1])
		{
			valid = true;
		}
		
		return valid;
	}
	
	/**
	 * <i>N</i> is the total amount of possible values
	 * for this interval. We get <i>N</i> by subtracting
	 * the lower bound off of upper bound.
	 * </p>
	 * <i>N = upper - lower</i>
	 * 
	 * @see
	 * 		#calculateN(URI)
	 * @param lowerBound
	 * 		the lower endpoint of the interval
	 * @param upperBound
	 * 		the upper endpoint of the interval
	 * @return
	 * 		N = &Sigma;n_i
	 */
	private final int calculateN(int lowerBound, int upperBound)
	{
		return (this.upperBound - this.lowerBound);
	}
}