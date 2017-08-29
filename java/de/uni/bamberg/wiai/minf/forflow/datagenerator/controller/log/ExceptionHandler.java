package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.log;

import javax.swing.SwingUtilities;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * Since Java 5 exists a new way to catch unexpected exceptions and
 * this class is a customized handler for that purpose.
 * </p>
 * Any exception not catched is handled here and a message dialog
 * will pop up to give detailed information about what has actually happened.
 * With a flag you've got the opportunity to enable or disable logging
 * mechanism. That is, the logging logs the exceptions to a <i>log</i> file. 
 * To use this handler it must be registered properly. You can do that
 * in more than one way. Well, actually there're two. You can register
 * an handler on a local thread and is only used there or you register
 * on the EDT which makes it available globally.
 * </p>
 * <font size=6><b>Registration</b></font></br>
 * Here is an example how you register to EDT (global).
 * </p> 
 * <code>
 * 		public static void main(String[] args)</br>
 * 		{</br>
 * 			<font color="red">Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler())</font></br>
 * 		}</br>
 * </code>
 * </p>
 * You have also the option to register on a single thread (local)
 * </p>
 * <code>
 * 		<font color="red">Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler())</font>
 * </code>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/05/09
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler
{
	/**
	 * flag, setting the log mechanism.
	 * The default value is <i>true</i>
	 */
	private boolean logging = true;
	
	/**
	 * sole default constructor.
	 * If this constructor is taken, the logging
	 * mechanism is enabled by default.
	 */
	public ExceptionHandler()
	{
		ErrorMessage.getInstance(this.logging);
	}
	
	/**
	 * constructor is passed the flag for setting
	 * the logging mechanism. The default is set
	 * to <i>true</i>.
	 * 
	 * @param log
	 * 		logging mechanism enabled or disabled?
	 */
	public ExceptionHandler(boolean log)
	{
		this.logging = log;
		
		ErrorMessage.getInstance(log);
	}
	
	@Override
	public void uncaughtException(final Thread t, final Throwable e)
	{
		if(SwingUtilities.isEventDispatchThread())
		{
			this.showException(t, e);
		}
		else
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					showException(t, e);
				}
			});
		}
	}
	
	/**
	 * prepares the exception that has had thrown and
	 * then shows it in a error dialog in the most
	 * detailed way possible.
	 * If logging has been enabled, the <i>log</i>
	 * mechanism is caused to run too.
	 * 
	 * @param t
	 * 		either the EDT or any other thread, like
	 * 		worker threads.
	 * @param e
	 * 		superclass of all errors and exceptions
	 */
	private void showException(Thread t, Throwable e)
	{
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<e.getStackTrace().length; i++)
		{
			StackTraceElement ste = e.getStackTrace()[i];
			
			sb.append(ste.toString() +"\n");
		}
		
		ErrorMessage.getInstance().printMessage(t, e, sb.toString());
		
		if(this.logging)
		{
			this.logException(t, e);
		}
	}
	
	/**
	 * is called when logging mechanism are enabled.
	 * This leads to an creation of a <i>log</i> file.
	 * Because it uses serialization and I/O bound tasks
	 * we do this in background on a worker thread.
	 * 
	 * @param t
	 * 		either the EDT or any other thread, like
	 * 		worker threads.
	 * @param e
	 * 		superclass of all errors and exceptions
	 */
	private void logException(Thread t, Throwable e)
	{
		ErrorMessage.getInstance().printMessage(e, "ExceptionHandler");
	}
}