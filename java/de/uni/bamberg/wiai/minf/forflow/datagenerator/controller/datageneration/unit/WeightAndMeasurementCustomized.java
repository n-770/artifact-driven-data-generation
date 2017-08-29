package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

import java.util.Iterator;
import java.util.List;

/**
 * The intention behind this interface is to model
 * weights and measurement not fitting into one
 * of the other units. It is thought to be a placeholder.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/22/09
 */
public interface WeightAndMeasurementCustomized extends WeightAndMeasurement, ObservableUnitCustom
{
	/**
	 * returns the last selected
	 * type of unit.
	 * 
	 * @return
	 * 		name of the last selected element
	 */
	public Custom getSelectedCustom(int selected);
	
	/**
	 * sets the complete list at once.
	 * 
	 * @param list
	 * 		list of weight or measurements
	 */
	public void setListCustom(List<Custom> list);
	
	/**
	 * adds one type of weight or measurement
	 * to an existing list.
	 * 
	 * @param custom
	 * 		customized unit
	 */
	public void addToListCustom(Custom custom);
	
	/**
	 * tries to delete an element from the list.
	 * As a matter of fact, the operation could fail, so
	 * we give feedback.
	 * </p>
	 * If successful <i>true</i> will be returned otherwise
	 * <i>false</i>. 
	 * 
	 * @param custom
	 * 		the element to delete from the list.
	 * @return
	 * 		true, if successful.
	 */
	public boolean remove(Custom custom);
	
	/**
	 * gets the whole data structure at once.
	 * 
	 * @return
	 * 		complete structure
	 */
	public List<Custom> getListCustom();
	
	/**
	 * Returns an iterator over the elements
	 * in this list in proper sequence. 
	 * 
	 * @return
	 * 		iterator
	 */
	public Iterator<Custom> iteratorCustom();
}