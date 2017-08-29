package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

/**
 * To be able to update the weight and measurements whenever a change
 * in the data structure happens, we use <i>Observer Pattern</i> to get
 * the desired effect.
 * </p>
 * <font size=6><b>Observer Pattern</b></font></br>
 * This helps to get an update only, when things change and we get rid off
 * of annoying pings.
 * </p>
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
 * @since Apr/24/09
 */
public interface ObservableUnitCustom
{
	/**
	 * When an observer registers, we just add it to the 
	 * end of an observer list.
	 * 
	 * @param observer
	 * 		an object to notify when an change occurs.
	 * @param name
	 * 		the name of an unit to register to.
	 */
	public void registerObserverUnitCustom(ObserverUnitCustom observer, String name);
	
	/**
	 * Likewise, when an observer wants to un-register,
	 * we just take it off the list.
	 * 
	 * @param observer
	 * 		an object which doesn't want any notifications anymore.
	 * @param name
	 * 		the name of an unit to remove observer.
	 */
	public void removeObserverUnitCustom(ObserverUnitCustom observer, String name);
	
	/**
	 * Here's the fun part; this is where we tell
	 * all observers about the state.
	 * Because they are all <i>Observers</i>, we
	 * know they all implement update(), so we
	 * know how to notify them.
	 */
	public void notifyObserverUnitCustom();
}