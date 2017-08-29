package de.uni.bamberg.wiai.minf.forflow.datagenerator.model;


/**
 * <b>ModelFactory</b> class uses the <i>Factory</i> pattern for
 * creating a model object.
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
 * life: creating the corresponding {@link Model} for the controller layer.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public final class ModelFactory
{
	/**
	 * sole default constructor not visible to outside world
	 */
	private ModelFactory()
	{
	}
	
	/**
	 * creates a model object for the controller layer.
	 * The controller is the one and only class which has
	 * direct access to it.
	 * 
	 * @return
	 * 		{@link Model}
	 */
	public static Model create()
	{
		Model model = new ModelImpl();
		
		return model;
	}
}