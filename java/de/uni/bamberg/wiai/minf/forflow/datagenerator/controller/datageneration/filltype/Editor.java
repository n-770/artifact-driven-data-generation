package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The editor filling type is a fast and handy option
 * when no flat file exits, no interval can be defined
 * and no pre-defined generator fits.
 * The editor offers the option to create a list of values
 * in the system itself, rather to switch the application
 * an create one.
 * It's more of convenience. It's also possible to save the 
 * entered text and link it with the external file option.
 * </p>
 * <font size=6><b>Example</b></font></br>
 * Here is an example how to enter the values for an attribute
 * (facet). Each line holds one value, it doesn't matter how
 * long the line is or how it is written.
 * </p> 
 * <img src="fillType_editor.jpg" alt"fillType_editor.jpg" />
 * </p>
 * Notice, the class implements {@link FillBehaviour} interface.
 * It makes this kind of strategy observable.
 * </p>
 * <font size=6><b>Observable</b></font></br>
 * Observable means in this context, whenever an observer 
 * registers to or un-registers from this class, the subject
 * itself notifies the UI to register this class in a specific
 * list of generators.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/29/09
 */
public class Editor implements FillBehaviour
{
	/**
	 * the name of this kind of filling.
	 * Remember, it's only for displaying purposes.
	 */
	private final String name = "Editor";
	
	/**
	 * holds the list of values typed in by
	 * this option.
	 */
	private List<String> values = null;
	
	/**
	 * used when incrementing values is enabled
	 */
	private List<Value> valuesIncrement = null;
	
	/**
	 * sometimes it can be useful to increment
	 * the value in the editor. There is no need
	 * to type in a whole bunch of different values
	 * when one incremented value is appropriate.
	 */
	private boolean incrementValue = false;
	
	/**
	 * the lower bound value
	 */
	private int lowerBound = 0;
	
	/**
	 * the upper bound value
	 */
	private int upperBound = 0;
	
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
	 * sole default constructor
	 */
	public Editor()
	{
	}
	
	/**
	 * sets the values which has been typed
	 * in by the editor.
	 * Notice, this method awaits the complete list
	 * at once. Calls calulateN() to get the latest
	 * update on it.
	 * 
	 * @param values
	 * 		the list of possible values
	 */
	public void setValues(List<String> values)
	{
		this.values = values;
		
		this.N = this.calculateN();
		this.lowerBound = 0;
		this.upperBound = (this.getN() - 1);
		
		this.createIncrementList(values);
		
		this.notifyOberverFillBehaviour();
	}
	
	/**
	 * sometimes it is useful to increment one
	 * value rather than typing in a whole bunch
	 * of different ones.
	 * </p>
	 * <font size=6><b>When to use?</b></font></br>
	 * This is especially true, when file names are generated.
	 * In the file system are usually files containing the same file
	 * name but have different version numbers, as 
	 * an example. This is simulated with the increment option.
	 * </p>
	 * <font size=5><b>Example</b></font></br>
	 * Here is a quick example on how the values in the editor
	 * are used. Suppose you've typed in something like this:</br>
	 * <font color=green>Presentation_</font> </br>
	 * <font color=green>Order</font> </br>
	 * <font color=green>Contract with Certkiller.com </font></br>
	 * </p>
	 * Furthermore, we assume the increment function has been enabled.
	 * The generated values for the 1st increment and the last will be:</br>
	 * <font color=red>Presentation_</font><b>0</b></br>
	 * <font color=red>Order</font><b>0</b></br>
	 * <font color=red>Contract with Certkiller.com </font><b>0</b></br>
	 * ... </br>
	 * <font color=red>Presentation_</font><b>n</b></br>
	 * <font color=red>Order</font><b>n</b></br>
	 * <font color=red>Contract with Certkiller.com </font><b>n</b></br>
	 * </p>
	 * There is still one thing to mention. Only those values will be
	 * incremented which are actually taken for generation.
	 * 
	 * <font size=6><b>When available?</b></font></br>
	 * The option is only available when the editor 
	 * is used as the fill behaviour. If you save the
	 * typed in values as an <i>value file</i> and
	 * then link it with <i>external file</i> the
	 * option will not be available anymore.
	 * 
	 * @param increment
	 * 		enable function incrementing values?
	 */
	public void isValueIncremented(boolean increment)
	{
		this.incrementValue = increment;
		
		this.createIncrementList(this.getValues());
	}
	
	/**
	 * sometimes it is useful to increment one
	 * value rather than typing in a whole bunch
	 * of different ones.
	 * </p>
	 * <font size=6><b>When to use?</b></font></br>
	 * This is especially true, when file names are generated.
	 * In the file system are usually files containing the same file
	 * name but have different version numbers, as 
	 * an example. This is simulated with the increment option.
	 * </p>
	 * <font size=5><b>Example</b></font></br>
	 * Here is a quick example on how the values in the editor
	 * are used. Suppose you've typed in something like this:</br>
	 * <font color=green>Presentation_</font> </br>
	 * <font color=green>Order</font> </br>
	 * <font color=green>Contract with Certkiller.com </font></br>
	 * </p>
	 * Furthermore, we assume the increment function has been enabled.
	 * The generated values for the 1st increment and the last will be:</br>
	 * <font color=red>Presentation_</font><b>0</b></br>
	 * <font color=red>Order</font><b>0</b></br>
	 * <font color=red>Contract with Certkiller.com </font><b>0</b></br>
	 * ... </br>
	 * <font color=red>Presentation_</font><b>n</b></br>
	 * <font color=red>Order</font><b>n</b></br>
	 * <font color=red>Contract with Certkiller.com </font><b>n</b></br>
	 * </p>
	 * There is still one thing to mention. Only those values will be
	 * incremented which are actually taken for generation.
	 * 
	 * <font size=6><b>When available?</b></font></br>
	 * The option is only available when the editor 
	 * is used as the fill behaviour. If you save the
	 * typed in values as an <i>value file</i> and
	 * then link it with <i>external file</i> the
	 * option will not be available anymore.
	 * 
	 * @return
	 * 		<i>true</i> when function is enabled otherwise <i>false</i>
	 */
	public final boolean isValueIncremented()
	{
		return this.incrementValue;
	}
	
	@Override
	public final String getValueAt(int n)
	{
		String value = null;
		
		if((n >= this.getLowerBound()) && (n <= this.getUpperBound()))
		{
			for(int i=0; i<this.values.size(); i++)
			{
				// found
				if(i == n)
				{
					if(this.isValueIncremented())
					{
						StringBuffer sb = new StringBuffer();
						
						// val_0, val_1, ...
						sb.append(this.valuesIncrement.get(i).getValue());
						sb.append(this.valuesIncrement.get(i).getIncrement());
						
						value = sb.toString();
					}
					else
					{
						value = this.values.get(i);
					}
				}
			}
		}
		
		return value;
	}
	
	/**
	 * gets the list of values at once.
	 * Notice, if the list hasn't been initialized, yet,
	 * then <i>null</i> will be returned.
	 * 
	 * @return
	 * 		list of typed in values
	 */
	public final List<String> getValues()
	{
		return this.values;
	}
	
	/**
	 * this method is not used here and
	 * therefore throws an {@link UnsupportedOperationException}.
	 * There exits an alternative method doing the job for editors.
	 * 
	 * @see
	 * 		#calculateN()
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
	
	/**
	 * gets the lower bound
	 * 
	 * @return
	 * 		lower endpoint
	 */
	public final int getLowerBound()
	{
		return this.lowerBound;
	}
	
	/**
	 * gets the upper endpoint
	 * 
	 * @return
	 * 		upper bound
	 */
	public final int getUpperBound()
	{
		return this.upperBound;
	}
	
	/**
	 * Returns an iterator over the elements
	 * in this list in proper sequence. 
	 * 
	 * @return
	 * 		iterator for values
	 */
	public Iterator<String> iterator()
	{
		return this.values.iterator();
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
	
	/**
	 * This method is the substitution of {@link #calculateN(URI)}.
	 * iterates through the list hold in memory
	 * and increments <i>N</i>.
	 * 
	 * @see
	 * 		#calculateN(URI)
	 * @return
	 * 		N = &Sigma;n_i
	 */
	private int calculateN()
	{
		int n = 0;
		
		for(Iterator<String> i=iterator(); i.hasNext();)
		{
			i.next();
			
			n++;
		}
		
		return n;
	}
	
	/**
	 * creates a separate list to keep track of the incremented
	 * values
	 * 
	 * @param values
	 * 		the typed in values from the editor. The values
	 * 		are passed down from the view layer
	 */
	private void createIncrementList(List<String> values)
	{
		if((values != null) && (this.incrementValue))
		{
			this.valuesIncrement = new ArrayList<Value>();
			
			for(Iterator<String> i=values.iterator(); i.hasNext();)
			{
				this.valuesIncrement.add(new Value(i.next()));
			}
		}
		else
		{
			this.valuesIncrement = null;
		}
	}
	
	/**
	 * A class holding a single value and its incremented counter value.
	 * This is only needed when the increment feature is enabled.
	 * 
	 * @author Michael Munz
	 * @version 0.1
	 * @since May/13/09
	 */
	private class Value
	{
		/**
		 * the typed in value
		 */
		private String value = null;
		
		/**
		 * the counter when values
		 * are incremented
		 */
		private int counter = 0;
		
		/**
		 * sole default constructor
		 */
		protected Value()
		{
		}
		
		/**
		 * constructor is passed the value.
		 * The counter is not needed to hand down,
		 * because it is set initially to zero.
		 * 
		 * @param value
		 * 		a single value from the editor
		 */
		protected Value(String value)
		{
			this.value = value;
		}
		
		/**
		 * sets the value
		 * 
		 * @param value
		 * 		value from editor
		 */
		protected void setValue(String value)
		{
			this.value = value;
		}
		
		/**
		 * gets the value
		 * 
		 * @return
		 * 		value
		 */
		protected final String getValue()
		{
			return this.value;
		}
		
		/**
		 * here is the fun part. This method
		 * increments the counter by one each
		 * time the value is returned.
		 * When a value is returned we assume
		 * it is used for test data generation.
		 */
		protected final int getIncrement()
		{
			int n = this.counter;
			
			this.counter++;
			
			return n;
		}
	}
}