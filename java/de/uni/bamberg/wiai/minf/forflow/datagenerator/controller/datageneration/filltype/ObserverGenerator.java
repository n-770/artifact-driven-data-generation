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
public interface ObserverGenerator
{
	/**
	 * With this method observers get the latest
	 * updates about its observed subject.
	 * This kind of observer is different from the common usage.
	 * When an update occurs, we do not update the data structure,
	 * but we do either add or remove the generator from a list.
	 * 
	 * @param add
	 * 		there're to operations possible: </br>
	 * 		<ul>
	 * 			<li>add</li> if flag set to <i>true</i>
	 * 			<li>remove</li> if flag set to <i>false</i>.
	 * 			This operation is implicit.
	 * 		</ul>
	 * @param generator
	 * 		delivers informations about which generator
	 * 		has notified an update.
	 */
	public void updateGenerator(boolean add, Generator generator);
}