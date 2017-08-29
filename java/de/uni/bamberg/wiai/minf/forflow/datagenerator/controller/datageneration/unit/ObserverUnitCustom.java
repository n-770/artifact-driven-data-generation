package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

import java.util.List;

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
 * This defines the observer side. It's possible that more objects (observers) observe
 * a subject (observable). But there's always only one subject to observe.
 * That's the one-to-many dependency. Many observers, but one subject.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/24/09
 */
public interface ObserverUnitCustom
{
	/**
	 * With this method observers get the latest updates
	 * about its observed subject.
	 * 
	 * @param units
	 * 		tells when observable has send out an notification to update.
	 * @param name
	 * 		the name of the unit that has changed recently.
	 */
	public void updateUnitCustom(List<Custom> units, String name);
}