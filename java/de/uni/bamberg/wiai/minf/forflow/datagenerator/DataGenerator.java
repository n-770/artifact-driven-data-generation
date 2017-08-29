package de.uni.bamberg.wiai.minf.forflow.datagenerator;

import java.io.File;
import java.util.EventObject;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.boot.Startup;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.log.ExceptionHandler;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;

/**
 * <b>DataGenerator</b> class represents the entry point.
 * The application is based on several assumptions.
 * </p>
 * <font size=6><b>Application Framework</b></font></br>
 * First, it uses the <i>Application Framework</i>. The primary
 * goal is to provide a kernel for typical Swing applications.
 * The application must therefore subclass either <i>Application</i> class
 * or its <i>SingleFrameApplication</i> subclass. We use the latter for
 * some reasons. The <i>SingleFrameApplication</i> adds a default main frame,
 * retrieves and injects default resources, and uses the <i>ApplicationContext</i>
 * to save and restore session state.
 * </p>
 * <font size=6><b>Model-View-Control</b></font></br>
 * Second, the program takes into account the MVC pattern. That is the Model-View-Control,
 * which is a common pattern to separate the different views.
 * This class creates the data model and the controller before the view is instantiated
 * and shown to the user.
 * </p>
 * <font size=6><b>Application Lifecycles</b></font></br>
 * With the help of the <i>Application Framework</i> the application life cycle can be
 * easily handled. It supports the following life cycles:
 * <ol>
 *      <li>
 *          <b>launch</b> (To call this method is mandatory)
 *      </li>
 *      <li>
 *          <b>initialize</b> (This method is optional)
 *      </li>
 *      <li>
 *          <b>startup</b> (The framework will invoke this method)
 *      </li>
 *      <li>
 *          <b>ready</b> (The framework will invoke this method)
 *      </li>
 *      <li>
 *          <b>exit</b> (To call this method is mandatory)
 *      </li>
 *      <li>
 *          <b>shutdown</b> (This method is optional)
 *      </li>
 * </ol>
 *
 * @author Michael Munz
 * @version 0.1
 * @since Mar/24/09
 */
public class DataGenerator extends SingleFrameApplication
{
    /**
     * The initialize method is called prior to startup method.
     * It's used to perform any initial configuration or setup steps.
     * This is especially true for non-UI related setup that an
     * application may need before displaying the UI.
     *
     * @param arg0
     * 		not processed
     */
    @Override
    protected void initialize(String[] arg0)
    {
        super.initialize(arg0);
        
        /*
         *  development path
         *  
         *  *.log.Log.FOLDER_LOG = "src/resource/Log";
         *  *.fillType.Resources.FOLDER_RESOURCES = "src/resource/Resources";
         *  *.boot.Startup.FOLDER_SETUP = "src/resource/Setup";
         *  *.view.Help.FOLDER_HELP = "src/resource/Help/index.html";
         */
        
        /*
         *  shipping path
         *  
         *  *.log.Log.FOLDER_LOG = "Log";
         *  *.fillType.Resources.FOLDER_RESOURCES = "Resources";
         *  *.boot.Startup.FOLDER_SETUP = "Setup";
         *  *.view.Help.FOLDER_HELP = "Help/index.html";
         */
        
        // this is a critical part. If anything unprepared happens system is going to tear down.
        ControllerFactory.getController().loadStartupFile(new File(Startup.STARTUP_XML).toURI(),
        												  new File(Startup.STARTUP_XSD).toURI());
    }

    /**
     * All framework applications must override this method.
     * It's used to create and display the UI.
     */
    @Override
    protected void startup()
    {
        show(new DataGeneratorView(this));
    }

    /**
     * This method is optional. The framework calls this after
     * all initial UI events related to the UI startup have been
     * processed.
     * Here is any work placed that depends on the UI being ready
     * and visible.
     */
    @Override
    protected void ready()
    {
        super.ready();
    }

    /**
     * The job of this method is to gracefully shutting down
     * the application. It involves to ask any <i>ExitListener</i>
     * object whether exiting is possible. Then alerting those methods
     * which actually shut down.
     *
     * @param eo
     */
    @Override
    public void exit(EventObject eo)
    {
        super.exit(eo);
    }

    /**
     * It's used to perform application-specific cleanup before
     * the application terminates completely, e.g. closing database
     * connections, saving files, or performing any other final task
     * before the application finally quits.
     */
    @Override
    protected void shutdown()
    {
        super.shutdown();
    }
    
    /**
     * A convenient static getter for the application instance.
     *
     * @return
     *      the instance of DataGenerator
     */
    public static DataGenerator getApplication()
    {
        return Application.getInstance(DataGenerator.class);
    }

    /**
     * The main method is the entry point to launch the application.
     * Using the launch method one ensures that the UI starts on the
     * <i>Event Dispatching Thread</i> (EDT). Calling the launch method,
     * the application's life cycle begins.
     *
     * @param args
     *      the arguments are ignored, because they won't be used.
     */
    public static void main(String[] args)
    {
    	Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    	
        launch(DataGenerator.class, args);
    }
}