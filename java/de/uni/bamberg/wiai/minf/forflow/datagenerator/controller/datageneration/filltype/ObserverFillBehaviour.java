package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

/**
 * This interface allows observers to get notified when its subject,
 * in that case {@link FillBehaviour}, changes state.
 * That might happen when an user changes the strategy of fill behaviour
 * or changes values within. Some components depend on those values and
 * need to get notified. We do this by sending out an notification for
 * all observers.
 * </p>
 * <font size=6><b>Observer Pattern</b></font></br>
 * Observer defines a one-to-many dependency between objects, so that when
 * one object changes state, all its dependents are notified and updated
 * automatically. 
 * </p>
 * This defines the subject to observe. It's possible that more objects (observers) observe
 * a subject (observable). But there's always only one subject to observe.
 * That's the one-to-many dependency. Many observers, but one subject.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/08/09
 */
public interface ObserverFillBehaviour
{
	/**
	 * With this method observers get the latest update
	 * about its observed subject.
	 * 
	 * @param lowerBound
	 * 		the lower endpoint
	 * @param upperBound
	 * 		the upper endpoint 
	 * @param N
	 * 		the total amount of values in fill behaviour
	 */
	public void updateFillBehaviour(int lowerBound, int upperBound, int N);
}