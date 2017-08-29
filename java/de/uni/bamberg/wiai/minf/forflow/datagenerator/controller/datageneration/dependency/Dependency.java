package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.dependency;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;

/**
 * To generate proper test data it may be important to know the dependencies
 * of attributes. This interface tries to fill the gap.
 * If any dependency exists between attributes it's important to compute
 * the attribute to which another attribute depends.
 * </p>
 * In test data generation view, a dependency means, an attribute uses the results
 * of another attribute. That attribute could itself depend on any other attribute
 * or attributes.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public interface Dependency
{
	/**
	 * sets the class where the attribute below hails from.
	 *  
	 * @param mClass
	 * 		a meta class to which the attribute belongs
	 */
	public void setMClass(MetaClass mClass);
	
	/**
	 * gets the meta class.
	 * Remember the attribute and the class
	 * are referenced together here.
	 * 
	 * @return
	 * 		meta class
	 */
	public MetaClass getMClass();
	
	/**
	 * sets the attribute to which another attribute depends.
	 * 
	 * @param mAttribute
	 * 		meta attribute
	 */
	public void setMAttribute(MetaAttribute mAttribute);
	
	/**
	 * gets the attribute.
	 * 
	 * @return
	 * 		meta attribute
	 */
	public MetaAttribute getMAttribute();
}