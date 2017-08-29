package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

/**
 * This class is a factory for all different types (strategies)
 * of fill behaviour. You may use this for object creation rather
 * creating it by yourself. Objects in layer other than that have
 * to use this.
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
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public final class FillBehaviourFactory
{
	/**
	 * sole default constructor is not visible to
	 * outside world.
	 */
	private FillBehaviourFactory()
	{
	}
	
	/**
	 * creates the address object and passes 
	 * it on to the invoker
	 * 
	 * @return
	 * 		address
	 */
	public static Address createAddress()
	{
		return new Address();
	}
	
	/**
	 * creates a new country object and
	 * the instance is passed to the invoker.
	 * 
	 * @return
	 * 		country
	 */
	public static Country createCountry()
	{
		return new Country();
	}
	
	/**
	 * creates a new date object. The instance
	 * is passed on to the invoker.
	 * 
	 * @return
	 * 		date
	 */
	public static Dates createDate()
	{
		return new Dates();
	}
	
	/**
	 * creates the editor object and passes
	 * the instance on to the invoker.
	 * 
	 * @return
	 * 		editor
	 */
	public static Editor createEditor()
	{
		return new Editor();
	}
	
	/**
	 * creates a new email object. After
	 * object creation it is passed to
	 * the invoker.
	 * 
	 * @return
	 * 		email
	 */
	public static Email createEmail()
	{
		return new Email();
	}
	
	/**
	 * creates a new external file object and passes
	 * the instance on to the invoker.
	 * 
	 * @return
	 * 		external file
	 */
	public static ExternalFile createExternalFile()
	{
		return new ExternalFile();
	}
	
	/**
	 * creates the fill type and passes the instance
	 * to the invoker
	 * 
	 * @return
	 * 		fill type
	 */
	public static FillType createFillType()
	{
		return new FillType();
	}
	
	/**
	 * creates the first name object and passes
	 * the instance on to the invoker.
	 * 
	 * @return
	 * 		first name
	 */
	public static FirstName createFirstName()
	{
		return new FirstName();
	}
	
	/**
	 * creates the interval fill behviour
	 * and passes the instance on to the invoker.
	 * 
	 * @return
	 * 		interval
	 */
	public static Interval createInterval()
	{
		return new Interval();
	}
	
	/**
	 * creates the last name fill behaviour
	 * and passes the instance on to the invoker.
	 * 
	 * @return
	 * 		last name
	 */
	public static LastName createLastName()
	{
		return new LastName();
	}
	
	/**
	 * creates the name fill behaviour and
	 * passes the instance on to the invoker.
	 * 
	 * @return
	 * 		name
	 */
	public static Name createName()
	{
		return new Name();
	}
	
	/**
	 * creates the sex object instance and
	 * passes it on to the invoker.
	 * 
	 * @return
	 * 		gender
	 */
	public static Sex createSex()
	{
		return new Sex();
	}
}