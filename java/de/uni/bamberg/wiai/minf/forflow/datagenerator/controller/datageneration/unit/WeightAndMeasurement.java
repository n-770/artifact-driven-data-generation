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
 * @since Apr/20/09
 */
public interface WeightAndMeasurement extends ObservableUnit
{
	/**
	 * gets the name of the current selected weight
	 * or measurement.
	 * It's only for displaying purposes used.
	 * 
	 * @return
	 * 		weight or measurement
	 */
	public String getName();
	
	/**
	 * sets the current selected weight or
	 * measurement. It's used to track it down.
	 * 
	 * @param selected
	 * 		what's the selected element?
	 */
	public void setSelected(int selected);
	
	/**
	 * returns the last selected
	 * type of unit.
	 * 
	 * @return
	 * 		name of the last selected element
	 */
	public String getSelected();
	
	/**
	 * sets the complete list at once.
	 * 
	 * @param list
	 * 		list of weight or measurements
	 */
	public void setList(List<String> list);
	
	/**
	 * adds one type of weight or measurement
	 * to an existing list.
	 * 
	 * @param type
	 * 		name of the element to add to list.
	 */
	public void addToList(String type);
	
	/**
	 * tries to remove an element form the list
	 * by a given type name.
	 * If successful, true will be returned.
	 * Otherwise false.
	 * 
	 * @param type
	 * 		element to delete from list.
	 * @return
	 * 		true, if successful
	 */
	public boolean remove(String type);
	
	/**
	 * gets the whole data structure at once.
	 * 
	 * @return
	 * 		complete structure
	 */
	public List<String> getList();
	
	/**
	 * Returns an iterator over the elements
	 * in this list in proper sequence. 
	 * 
	 * @return
	 * 		iterator
	 */
	public Iterator<String> iterator();
}