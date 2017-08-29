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
public interface ObservableDistribution
{
	/**
	 * When an observer registers, it's just added to the end
	 * of the obersver list.
	 * 
	 * @param observer
	 * 		an object to notify when a change occurs.
	 * 		In terms of probability distributions, a change means
	 * 		either its created or eliminated.
	 */
	public void registerObserverDistribution(ObserverDistribution observer);
	
	/**
	 * Likewise, when an observer wants to un-register
	 * we just take it off the list.
	 * 
	 * @param observer
	 * 		an object which doesn't want any notifications about
	 * 		its subject anymore.
	 */
	public void removeObserverDistribution(ObserverDistribution observer);
	
	/**
	 * Here's the fun part; this is where we tell
	 * all observers about the state.
	 * Because they are all <i>Observers</i>, we know
	 * how to notify them.
	 * </p>
	 * Note, this observable is different in same kind.
	 * In other words, we use the observable to notify
	 * about its availability and not about changes in the structure.
	 * 
	 * @param add
	 * 		two distinct operations are available and possible.
	 * 		<ul>
	 * 			<li>add</li>
	 * 				This operation is straightforward and the subject
	 * 				indicates about that, if the flag is set to <i>true</i>.
	 * 			<li>remove</li>
	 * 				It is also possible that a subject notifies about removing
	 * 				it from the view layer. This only happens when the controller
	 * 				layer eliminates the probability distribution from the
	 * 				model layer. The probability distribution sends an notification
	 * 				to all its observers that is is gonna be removed.
	 * 				The view layer in turn can take proper actions and react to that
	 * 				with removing the corresponding probability distribution from
	 * 				the UI list. So users can't get in trouble selecting not available
	 * 				probability distributions. Here not available means in terms of
	 * 				the model layer, of course. 
	 * 		</ul>
	 */
	public void notifyObserverDistribution(boolean add);
}