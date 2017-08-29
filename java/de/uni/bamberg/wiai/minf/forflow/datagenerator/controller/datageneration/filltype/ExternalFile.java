package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.ValueCounter;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.ValueRetriever;

/**
 * This class implements {@link FillBehaviour} interface.
 * <b>ExternalFile</b> is one of more strategies to <i>fill</i>
 * an attribute (facet) with data. Attributes are seeded with data
 * of an external file. An external file is a file outside this system.
 * In other words, the file hasn't been shipped with this distribution.
 * </p>
 * External files are useful to achieve more realistic test data generation.
 * This is because the system will use a file of the customer, rather than
 * using it's own files. With this approach the customer has a powerful
 * option to customize attributes to its own needs.  
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
 * @since Apr/20/09
 */
public class ExternalFile implements FillBehaviour
{
	/**
	 * the name of the filling type is gonna be
	 * used in the table
	 */
	private final String name = "external File";
	
	/**
	 * the path to an external file
	 */
	private URI path = null; 
	
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
	public ExternalFile()
	{
	}
	
	/**
	 * sets the path to an external file
	 * the customer has specified.
	 * The file is gonna used to generated data.
	 * 
	 * @param file
	 * 		path to external file as an URI
	 */
	public void setPathToFile(URI file)
	{
		this.path = file;
		
		this.N = this.calculateN(file);
		this.lowerBound = 0;
		this.upperBound = (this.getN() - 1);
		
		this.notifyOberverFillBehaviour();
	}
	
	/**
	 * gets the path to the external file which
	 * has been previously defined.
	 * 
	 * @return
	 * 		path to file as an URI
	 */
	public final URI getPathToFile()
	{
		return this.path;
	}
	
	@Override
	public final int calculateN(URI file)
	{
		return ValueCounter.calculateN(file);
	}
	
	@Override
	public final int getN()
	{
		return this.N;
	}
	
	@Override
	public final String getValueAt(int n)
	{
		if((n >= 0) && (n < this.getN()))
		{
			return ValueRetriever.getValueAt(n, this.getPathToFile());
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public String getName()
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
				
				ob.updateFillBehaviour(this.lowerBound, this.upperBound, this.getN());
			}
		}
	}
}