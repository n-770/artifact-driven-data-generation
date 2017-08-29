package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

/**
 * This class handles the object creation of the view layer.
 * It has to be used to create objects of this layer when any
 * layer below makes a request. In fact, the only layer permitted
 * to do this is the controller layer. 
 * </p>
 * <font size=6><b>Factory Pattern</b></font></br>
 * Factories are responsible for object creation and know how to create them.
 * <i>ViewFactory</i> is responsible for creating objects which are part of the view layer.
 * It encapsulates the object creation from where it is actually used.
 * Factories are always used when objects of this layer are passed to
 * any other layer. In other words, when objects pass layer boundaries.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/08/09
 */
public final class ViewFactory
{
	/**
	 * default constructor not visible to outside world.
	 */
	private ViewFactory()
	{
	}
	
	/**
	 * create a new action handling object of the main view.
	 * The outsourcing has been done to split up more clearly
	 * the actions from the components.
	 * 
	 * @param view
	 * 		reference of the main view is passed to the constructor
	 * @return
	 * 		action handling class
	 */
	public static DataGeneratorActions createDataGeneratorActions(DataGeneratorView view)
	{
		return new DataGeneratorActions(view);
	}
}