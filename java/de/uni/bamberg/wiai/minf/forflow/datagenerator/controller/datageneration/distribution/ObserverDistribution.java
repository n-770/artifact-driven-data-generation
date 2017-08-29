package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution;

/**
 * This is an observer interface for observers to observe
 * the probability distribution subject. If a new probability
 * distribution is created the subject itself sends a notification
 * to the observer, that is the view layer. The view gets the
 * latest update on that and in turn updates the available list in
 * UI. With this approach the view always represents the model layer.
 * The observer gets notification on two operations: when a new
 * probability distribution has been created and when one is eliminated.
 * To make proper distinction between those two notifications a flag
 * is provided. On <i>true</i> it has been created and notifies about
 * to add it to and on <i>false</i> it notifies about its elimination.
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
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/06/09
 */
public interface ObserverDistribution
{
	/**
	 * The method makes sure the observer gets the latest
	 * updates about probability distributions (subject).
	 * The update doesn't notify about changes in the data structure,
	 * but notifies about removing or adding a probability distribution
	 * to a list of available distributions. The list is usually somewhere
	 * in the UI.
	 * </p>
	 * <font size=6><b>Register</b></font></br>
	 * When a notification is send out to the observer, the flag is
	 * set to <i>true</i>, that means a new subject has been created.
	 * The creating is done in controller layer but the view layer gets
	 * a notification about that.
	 * </p>
	 * <font size=6><b>Un-register</b></font></br>
	 * Un-registering means the subject, that is a probability distribution,
	 * is gonna be eliminated from the model layer. Removing can only do
	 * the controller layer, but to make sure the view gets the latest update
	 * the subject itself sends and notification about the status.
	 * 
	 * @param add
	 * 		flag, indicates the operation to do. That could be to add a
	 * 		probability distribution to or removing one from the list.
	 * @param distribution
	 * 		this is the subject which has send the notification.
	 */
	public void updateProbabilityDistribution(boolean add, ProbabilityDistribution distribution);
}