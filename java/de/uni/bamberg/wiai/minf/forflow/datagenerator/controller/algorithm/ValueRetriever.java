package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

import javax.swing.SwingWorker;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerBackgroundTask;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * This class is responsible to get the correct value of a linked value file.
 * The job is accomplished in background, because it is one of the I/O bound
 * tasks.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/12/09
 */
public final class ValueRetriever
{
	/**
	 * holds the reference of itself
	 */
	private static ValueRetriever valueRetriever = null;
	
	/**
	 * sole default constructor not visible to outside world
	 */
	private ValueRetriever()
	{
	}
	
	/**
	 * creates only one single object of this class.
	 * It is based on the <i>Singleton Pattern</i>
	 * and 1st checks whether there is still one instance
	 * created. If there's no instance created yet it will
	 * create on and then return the reference.
	 * 
	 * @return
	 * 		value retriever reference
	 */
	private static ValueRetriever getInstance()
	{
		if(valueRetriever == null)
		{
			valueRetriever = new ValueRetriever();
		}
		
		return valueRetriever;
	}
	
	/**
	 * the method tries to retrieve the value at the
	 * given position <i>i</i>. <i>i</i> is an index and has to be
	 * between the lower and upper bound of the file.
	 * The lower bound of a value file is the first value,
	 * that is '1' and the last value 'n'.
	 * </p>
	 * Because retrieving a value from a file could be
	 * a tedious work, this class makes use of a background
	 * thread. The thread does the work in background separated
	 * from the EDT and returns the value at the time the
	 * job is finished successfully.
	 * 
	 * @param i
	 * 		the index defines which value to retrieve
	 * @param file
	 * 		the file to search for the value at position i
	 * @return
	 * 		value at position i as string object
	 */
	public static String getValueAt(int i, URI file)
	{
		String value = null;
		
		Retrieve retrieve = getInstance().new Retrieve(i, file);
		
		retrieve.execute();
		
		try
		{
			value = retrieve.get();
		}
		catch(ExecutionException ee)
		{
			ErrorMessage.getInstance().printMessage(ee, "ExecutionException", Level.FINEST);
		}
		catch(InterruptedException ie)
		{
			ErrorMessage.getInstance().printMessage(ie, "InterruptedException", Level.FINEST);
		}
		
		return value;
	}
	
	/**
	 * {@link ValueRetriever} makes use of an inner private class
	 * to do the calculation of <i>N</i> with the help of an 
	 * swing worker thread. The background thread is necessary,
	 * because there are I/O processes involved in retrieving
	 * the value.
	 * </p>
	 * Within the controller layer objects do not have to use
	 * the {@link ControllerBackgroundTask} as layers above
	 * controller layer.
	 * 
	 * @author Michael Munz
	 * @version 0.1
	 * @since May/12/09
	 */
	private class Retrieve extends SwingWorker<String, Void>
	{
		/**
		 * the index to get the value at
		 */
		private int n = 0;
		
		/**
		 * the file to get the value from as URI object
		 */
		private URI file = null;
		
		/**
		 * constructor is passed the index and the file.
		 * The index tells which value to retrieve while
		 * the file tells which value file to search through.
		 * 
		 * @param n
		 * 		the value to look for at position n
		 * @param file
		 * 		the file to search for value n
		 */
		public Retrieve(int n, URI file)
		{
			this.n = n;
			this.file = file;
		}
		
		@Override
		protected String doInBackground() throws Exception
		{
			// 1st value is at index 0
			int n = 0;
			
			String value = null;
			
			// open file for reading
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(this.file.getPath()));
				
				String tmp = null;
				
				do
				{
					tmp = reader.readLine();
					
					if(n == this.n)
					{
						value = tmp;
					}
					
					n++;
				}
				while(tmp != null);
				
				reader.close();
			}
			catch(FileNotFoundException fnfe)
			{
				ErrorMessage.getInstance().printMessage(fnfe, "FileNotFoundException", Level.FINEST);
			}
			catch(IOException ioe)
			{
				ErrorMessage.getInstance().printMessage(ioe, "IOException", Level.FINEST);
			}
			
			return value;
		}
	}
}