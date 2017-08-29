package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;

/**
 * This abstract class represents the type of filling any {@link MetaAttribute}
 * with valuable data. Because there are a bunch of possibilities to
 * do the job properly a strategy pattern is used at the basic level.
 * </p>
 * Strategy pattern defines a family of algorithms, encapsulates each one, and
 * makes them interchangeable. Strategy lets the algorithm vary independently
 * from clients that use it.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public class FillType
{
	/**
	 * reference to filling behaviour.
	 */
	private FillBehaviour fillBehaviour = null;
	
	/**
	 * sole default constructor
	 */
	public FillType()
	{
	}
	
	/**
	 * sets the filling algorithm.
	 * This part is interchangeable and is independent
	 * from the client. This makes it flexible enough
	 * to change the strategy at runtime.
	 * 
	 * @param fb
	 * 		algorithm to handle filling
	 */
	public void setFillBehaviour(FillBehaviour fb)
	{
		this.fillBehaviour = fb;
	}
	
	/**
	 * gets the filling strategy.
	 * Depending on the strategy filling could be
	 * done several ways. It's also possible to
	 * change the strategy at runtime. This happens,
	 * when the tester decides to use a different
	 * algorithm to handle the filling.
	 * 
	 * @return
	 * 		filling behaviour
	 */
	public FillBehaviour getFillBehaviour()
	{
		return this.fillBehaviour;
	}
	
	/**
	 * this method is a pass through and is
	 * for convenience only. You could instead
	 * get the fill strategy and get the value
	 * by calling <i>getValueAt(n)</i> manually.
	 * 
	 * @param n
	 * 		the value at index n to fetch
	 * @return
	 * 		the value as a string object or null
	 */
	public String getValueAt(int n)
	{
		return this.fillBehaviour.getValueAt(n);
	}
	
	/**
	 * this method is a pass through to
	 * the strategy and returns this value.
	 * It is just for convenience. You could
	 * also return the strategy by {@link #getFillBehaviour()}
	 * and then call <i>getN()</i>.
	 * 
	 * @see
	 * 		FillBehaviour#getN()
	 * @return
	 * 		total amount of available values
	 */
	public int getN()
	{
		if(this.fillBehaviour != null)
		{
			return this.fillBehaviour.getN();
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 * gets the name of the strategy currently
	 * selected. This is for displaying purposes
	 * only.
	 * 
	 * @return
	 * 		name of current strategy.
	 */
	public String getName()
	{
		if(this.fillBehaviour != null)
		{
			return this.fillBehaviour.getName();
		}
		else
		{
			return "";
		}
	}
}