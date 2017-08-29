package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

/**
 * To get the possibility to register and un-register to generators
 * we use the <i>Observer Pattern</i>. Each time an observer registers
 * to an pre-defined generator the generator will be registered and
 * listed in the UI. In other words, a generator is as soon available
 * as an observer registers to it. The same holds, when an observer
 * removes itself or un-registers.
 * </p>
 * Usually <i>observers</i> are used to get notified when a change in
 * the subject happens. But we use the notification to list registered
 * generators in the UI, rather than getting notified about structural
 * changes.
 * </p>
 * The image below show registered pre-defined generators.
 * </p>
 * <img src="fillType_generator.jpg" alt="fillType_generator.jpg" />
 * </p>
 * As the image shows, if an observer registers itself to an available subject (generator),
 * the subject gets listed in the list. And vice versa when un-registered.
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
 * @since Apr/30/09
 */
public interface ObservableGenerator
{
	/**
	 * When an observer registers, it's just added to the end
	 * of the observer list.
	 * 
	 * @param observer
	 * 		an object to notify when a change occurs.
	 */
	public void registerObserverGenerator(ObserverGenerator observer);
	
	/**
	 * Likewise, when an observer wants to un-register
	 * we just take it off the list.
	 * 
	 * @param observer
	 * 		an object which doesn't want any notifications anymore.
	 */
	public void removeObserverGenerator(ObserverGenerator observer);
	
	/**
	 * Here's the fun part; this is where we tell
	 * all observers about the state.
	 * Because they are all <i>Observers</i>, we
	 * know how to notify them.
	 * </p>
	 * This observable is different from common usage,
	 * we notify about operations to use, rather updating
	 * data structure.
	 * 
	 * @param add
	 * 		here is the operation. There is one of two operations
	 * 		possible.
	 * 		<ul>
	 * 			<li>add</li> This operation is explicit, if
	 * 			the flag is set to <i>true</i>.
	 * 			This registers the observable to a list holding
	 * 			available generators.
	 * 			<li>remove</li> This operation is implicit, 
	 * 			if the flag is set to <i>false</i>.
	 * 			This is the vice versa function. It removes the
	 * 			subject from the list of available generators.
	 * 		</ul>
	 */
	public void notifyObserverGenerator(boolean add);
}