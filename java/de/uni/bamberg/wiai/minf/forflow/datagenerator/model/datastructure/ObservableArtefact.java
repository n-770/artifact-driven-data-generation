package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

/**
 * To be able to update the view whenever a change in the model layer occurs,
 * we use the <i>Observer Pattern</i> to get along with it.
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
 * @since Mar/24/09
 */
public interface ObservableArtefact
{
	/**
	 * When an observer registers, we just add it to the 
	 * end of an observer list.
	 * 
	 * @param observer
	 * 		an object to notify when an change occurs.
	 * @param type
	 * 		type of artefact to observe
	 */
	public void registerObserverArtefact(ObserverArtefact observer, ArtefactType type);
	
	/**
	 * Likewise, when an observer wants to un-register,
	 * we just take it off the list.
	 * 
	 * @param observer
	 * 		an object which doesn't want any notifications anymore.
	 * @param type
	 * 		the type of artefact to remove observer
	 */
	public void removeObserverArtefact(ObserverArtefact observer, ArtefactType type);
	
	/**
	 * Here's the fun part; this is where we tell
	 * all observers about the state.
	 * Because they are all <i>Observers</i>, we
	 * know they all implement update(), so we
	 * know how to notify them.
	 */
	public void notifyObserverArtefact();
}