package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingWorker;

/**
 * When the application has been shipped and exceptions or errors
 * occurring an mechanism to log these events is helpful.
 * </p>
 * <font size=6><b>Logging</b></font></br>
 * The Java API provides for these reasons a powerful and
 * highly flexible logging mechanism. It can be configured
 * so that at production time logging has an very little impact
 * on performance. It is only caused, when an event occurs worth
 * to log.
 * </p>
 * <font size=6><b>Worker thread</b></font></br>
 * Logging is an I/O bound task and therefore an background thread
 * is doing the work separated from the EDT.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/08/09
 */
public class LogWorker extends SwingWorker<Void, Void>
{
	/**
	 * defines the logging level. It's a mechanism to make
	 * logging very flexible and costs very little at runtime
	 * when logging code is not used, e.g. in production.
	 */
	private Level level = null;
	
	/**
	 * as the parameter already says: you've got the opportunity
     * to put same custom information here in.
	 */
	private String message = null;
	
	/**
	 * superclass of all errors and exceptions
	 */
	private Throwable throwable = null;
	
	/**
	 * the logger which does the logging
	 */
	private Logger logger = null;
	
	/**
	 * constructor is passed the log level the custom message
	 * to attache and the exception as a throwable object.
	 * </p>
	 * <font size=2><b>Log level</b></font></br>
	 * The log level has several different values in its priority.
	 * So depending on that not all log calls are actually really
	 * logged in a so-called <i>log</i> file.
	 * </p>
	 * <font size=2><b>Custom message</b></font></br>
	 * It is possible to attache a customized message to the log event.
	 * This may be helpful when the log files are evaluated.
	 * </p>
	 * <font size=2><b>Throwable</b></font></br>
	 * A throwable object is the baseclass of a all exceptions
	 * and errors. This object is also attached to the log event,
	 * because it contains the stack trace.
	 * 
	 * @param level
	 * 		defines the logging level. It's a mechanism to make
     * 		logging very flexible and cots very little at runtime
     * 		when logging code is not used, e.g. in production.
	 * @param message
	 * 		as the parameter already says: you've got the opportunity
     * 		to put same custom information here in.
	 * @param e
	 * 		superclass of all errors and exceptions
	 * @param logger
	 * 		the logger object
	 */
	public LogWorker(Level level, String message, Throwable e, Logger logger)
	{
		this.level = level;
		this.message = message;
		this.throwable = e;
		this.logger = logger;
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		this.logger.log(this.level, this.message, this.throwable);
		
		return null;
	}
}