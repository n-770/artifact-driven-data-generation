package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller;


/**
 * <i>ControllerFactory</i> class is a factory for creating an
 * instance of {@link Controller}.
 * </p>
 * <font size=6><b>Factory Pattern</b></font></br>
 * Factories handle the details of object creation. This class
 * has only one job in life: creating a controller object.
 * </p>
 * Notice, there're two different instances. The first is the actual
 * controller while the 2nd is used to create worker threads to disburden
 * the EDT. All layers on a higher level as the controller layer have to
 * use the controller and controller background. Objects residing in
 * the controller layer don't need to do this.
 * </p>
 * <font size=6><b>Singelton Pattern</b></font></br>
 * This factory is based upon the <i>Singleton</i> pattern and creates
 * only once the controllers.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public class ControllerFactory
{
	/**
	 * reference to controller class.
	 */
	private static Controller controller = null;
	
	/**
	 * All long processing tasks are handled and controlled
	 * here. They run in the background to disburden the EDT (Event Dispatching Thread).
	 */
	private static ControllerBackgroundTask backgroundTask = null;
	
	/**
	 * creates an instance of the controller main class.
	 * The {@link Controller} is the only class holding a
	 * reference to model layer. It's needed to get access
	 * to data and it knows when actions are allowed or rejected. 
	 * 
	 * @return
	 * 		an instance of controller
	 */
	public static Controller getController()
	{
		if(controller == null)
		{
			controller = new ControllerImpl();
		}
		
		return controller;
	}
	
	/**
	 * gets the reference to the background tasks.
	 * Here run the long running processes in background threads
	 * to disburden the EDT (Event Dispatching Thread).
	 * 
	 * @return
	 * 		controller for background task.
	 */
	public static ControllerBackgroundTask getControllerBackgroundTask()
	{
		if(backgroundTask == null)
		{
			backgroundTask = new ControllerBackgroundTask();
		}
		
		return backgroundTask;
	}
}