package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

/**
 * This is a factory for all different types (strategies) of 
 * weight & measurements. You may use this approach rather than
 * to create its own objects.
 * Objects of different layers have to do this. 
 * </p>
 * <font size=6><b>Factory Pattern</b></font></br>
 * Factories are used to encapsulate object creation from the use.
 * In other words, object creation is always done in the layer 
 * objects reside. After the creation process it is passed to where
 * it is used. In this implementation it's the controller layer.
 * This layer is above the model layer so it has to ask the factory
 * for doing the job. In that case it doesn't matter that controller
 * layer does the request. Look at the factory as an access point
 * to a different layer.
 * Factories handle the details of object creation and has one job in
 * life: creating the objects of this package for layer above of this.
 * 
 * @author Michel Munz
 * @version 0.1
 * @since Apr/20/09
 */
public final class UnitFactory
{
	/**
	 * sole default constructor
	 */
	private UnitFactory()
	{
	}
	
	/**
	 * creates an currency object and passes
	 * the instance on to the invoker 
	 * 
	 * @return
	 * 		currency
	 */
	public static Currency createCurrency()
	{
		return new Currency();
	}
	
	/**
	 * creates a custom unit object and
	 * passes the instance to the invoker.
	 * 
	 * @return
	 * 		custom
	 */
	public static Custom createCustom()
	{
		return new Custom();
	}
	
	/**
	 * creates a length object and passes
	 * the instance to the invoker.
	 * 
	 * @return
	 * 		length
	 */
	public static Length createLength()
	{
		return new Length();
	}
	
	/**
	 * creates a mass object and passes
	 * the instance on to the invoker
	 * 
	 * @return
	 * 		mass
	 */
	public static Mass createMass()
	{
		return new Mass();
	}
	
	/**
	 * creates a math object and passes
	 * the instance on to the invoker.
	 * 
	 * @return
	 * 		math
	 */
	public static Math createMath()
	{
		return new Math();
	}
	
	/**
	 * creates an miscellaneous (misc) object
	 * and passes the instance to the invoker.
	 * 
	 * @return
	 * 		miscellaneous
	 */
	public static Miscellaneous createMisc()
	{
		return new Miscellaneous();
	}
	
	/**
	 * creates an temperature object and passes
	 * the instance on to the destination.
	 * 
	 * @return
	 * 		temperature
	 */
	public static Temperature createTempeature()
	{
		return new Temperature();
	}
	
	/**
	 * creates an time object and passes
	 * the instance on to its destination
	 * 
	 * @return
	 * 		time
	 */
	public static Time createTime()
	{
		return new Time();
	}
	
	/**
	 * creates an unit object and passes the
	 * instance to its destination.
	 * 
	 * @return
	 * 		unit
	 */
	public static Unit createUnit()
	{
		return new Unit();
	}
}