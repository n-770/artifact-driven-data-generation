package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.dependency;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;

/**
 * <b>DependencyImpl</b> implements <i>Dependency</i> as it's interface.
 * </p>
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
public class DependencyImpl implements Dependency
{
	/**
	 * it's the class where to look for the below attribute
	 */
	private MetaClass mClass = null;
	
	/**
	 * the attribute to which another attribute depends on
	 */
	private MetaAttribute mAttribute = null;
	
	/**
	 * sole default constructor
	 */
	public DependencyImpl()
	{
	}
	
	/**
	 * constructor is passed the class and the attribute
	 * we another attribute depends on.
	 * 
	 * @param mClass
	 * 		tells, where the attribute comes from
	 * @param mAttriube
	 * 		specifies the attribute to which another attribute depends on
	 */
	public DependencyImpl(MetaClass mClass, MetaAttribute mAttriube)
	{
		this.mClass = mClass;
		this.mAttribute = mAttriube;
	}
	
	@Override
	public void setMClass(MetaClass mClass)
	{
		this.mClass = mClass;
	}
	
	@Override
	public MetaClass getMClass()
	{
		return this.mClass;
	}
	
	@Override
	public void setMAttribute(MetaAttribute mAttribute)
	{
		this.mAttribute = mAttribute;
	}
	
	@Override
	public MetaAttribute getMAttribute()
	{
		return this.mAttribute;
	}
}