package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

import java.util.List;

/**
 * To be able to update the view whenever a change in the model layer occurs,
 * we use the <i>Observer Pattern</i> to get along with it.
 * </p>
 * <font size=6><b>Observer Pattern</b></font></br>
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
 * @since Mar/24/09
 */
public interface ObserverArtefact
{
	/**
	 * With this method observers get the latest updates
	 * about its observed subject.
	 * 
	 * @param artefacts
	 * 		only artefact collection defined in type will be updated.
	 * @param type
	 * 		tells, which type of artefacts has changed recently.
	 */
	public void updateArtefact(List<MetaClass> artefacts, ArtefactType type);
}