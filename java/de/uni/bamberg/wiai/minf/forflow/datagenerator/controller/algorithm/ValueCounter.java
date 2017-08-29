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
 * This class has the job of calculating the available values
 * of a <i>value file</i>. A value file is a simple flat file
 * containing a single value in each line. In other words,
 * each line is considered as a single value.
 * </p>
 * <fong size=6><b>Values</b></font></br>
 * As mentions, each line in a value file represents a value.
 * But it doesn't mean a value is only a single word. Rather it
 * can be a whole sentence. As long as it is in a line its one
 * value. Of course, the values depend on the context. So as
 * an example, suppose you wanna generate data to facet called
 * <i>name</i>. You would use a flat file (value file) <i>names.txt</i>.
 * </p>
 * Each line has a value and depending on the context those values
 * are surnames.
 * </p>
 * <fong size=6><b>File structure</b></font></br>
 * To generate names for facet <i>name</i>'s it's necessary
 * to know how many values actually in the <i>value file</i>.
 * Because those files are intended to be modified and extended
 * it doesn't make sense to put in (hard code) the total amount.
 * </p>
 * Hard coding leads to two backdraws. First, it is unpractical,
 * because each time the file has been modified outside the system
 * the user is forced to do that manually. The files have been designed
 * as flat files to get modified and extended easily!
 * Second, the total amount is error-prone and might not represent
 * the actual amount. And further it can easily modified in bad faith.
 * </p>
 * <fong size=6><b>Example</b></font></br>
 * <i>names.txt</i></br>
 * i=1: McLeod </br>
 * i=2: Simpson </br>
 * i=3: Portman </br>
 * ... </br>
 * i=n: Moore </br>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/08/09
 */
public final class ValueCounter
{
	/**
	 * reference of itself
	 */
	private static ValueCounter vc = null;
	
	/**
	 * default constructor not visible to outside world.
	 */
	private ValueCounter()
	{
	}
	
	/**
	 * creates only one single object of this class
	 * based on <i>Singleton</i> Pattern.
	 * First checks, whether an instance already has been
	 * created, does that if necessary and returns the
	 * reference.
	 * 
	 * @return
	 * 		static object of this class 
	 */
	private static ValueCounter getIntance()
	{
		if(vc == null)
		{
			vc = new ValueCounter();
		}
		
		return vc;
	}
	
	/**
	 * the job of this method is to calculate
	 * the total values, that is <b>N = &Sigma;n_i</b>.
	 * That is necessary to put each value a probability
	 * P(X=x) where x = n_i. That is for each value
	 * we give the probability.
	 * </p>
	 * To each filling behaviour we have to do this.
	 * For a deeper understanding here an example why
	 * this is necessary.
	 * </p>
	 * <font size=6><b>Example</b></font></br>
	 * Imagine having a value file called <i>names.txt</i>.
	 * The file has in total about 300 names, so as a
	 * result <b>N = 300</b>;
	 * Each of those values <b>n = 1, ..., N</b> gets a probability
	 * value later when it comes to probability distributions.
	 * A particular probability density function is laid over
	 * the values.
	 * </p>
	 * <font size=6><b>Probability density function</b></font></br>
	 * As an example we assume the Uniform probability distribution
	 * is used. The probability density function is for all values
	 * constant. In other words, each values is equal likely. 
	 * <i>P(X=x) = 1/N</i>.
	 * <pre>
	 * f(x)
	 *  |
	 *  |
	 *  |------------ (1/N)
	 *  |
	 *  |
	 *  |___________|______
	 *              N    values
	 * </pre>
	 * 
	 * @param file
	 * 		the path to the file to calc the values
	 * @return
	 * 		N = &Sigma;n_i
	 */
	public static final int calculateN(URI file)
	{
		// N = &Sigma;n_i where i = 1, ..., n
		int N = 0;
		
		/*
		 *  using getInstance() here has the effect
		 *  that we don't need to call:
		 *    'ValueCounter.getInstance.calculateN(file)'
		 *  
		 *  Just call:
		 *    'ValueCounter.calculateN(file)'
		 */
		CalcN calcN = getIntance().new CalcN(file);
		
		calcN.execute();
		
		try
		{
			N = calcN.get().intValue();
		}
		catch(ExecutionException ee)
		{
			ErrorMessage.getInstance().printMessage(ee, "ExecutionException", Level.FINEST);
		}
		catch(InterruptedException ie)
		{
			ErrorMessage.getInstance().printMessage(ie, "InterruptedException", Level.FINEST);
		}
		
		return N;
	}
	
	/**
	 * the job of this method is to calculate
	 * the total values, that is <b>N = &Sigma;n_i</b>.
	 * That is necessary to put each value a probability
	 * P(X=x) where x = n_i. That is for each value
	 * we give the probability.
	 * </p>
	 * To each filling behaviour we have to do this.
	 * For a deeper understanding here an example why
	 * this is necessary.
	 * </p>
	 * <font size=6><b>Example</b></font></br>
	 * Imagine having a value file called <i>names.txt</i>.
	 * The file has in total about 300 names, so as a
	 * result <b>N = 300</b>;
	 * Each of those values <b>n = 1, ..., N</b> gets a probability
	 * value later when it comes to probability distributions.
	 * A particular probability density function is laid over
	 * the values.
	 * </p>
	 * <font size=6><b>Probability density function</b></font></br>
	 * As an example we assume the Uniform probability distribution
	 * is used. The probability density function is for all values
	 * constant. In other words, each values is equal likely. 
	 * <i>P(X=x) = 1/N</i>.
	 * <pre>
	 * f(x)
	 *  |
	 *  |
	 *  |------------ (1/N)
	 *  |
	 *  |
	 *  |___________|______
	 *              N    values
	 * </pre>
	 * 
	 * @param file
	 * 		the path to the file to calc the values		
	 * @return
	 * 		int[lower bound, upper bound, N]
	 */
	public static int[] getN(URI file)
	{
		int[] res = new int[3];
		
		int N = calculateN(file);
		
		res[0] = 0;
		res[1] = (N - 1);
		res[2] = N;
		
		return res;
	}
	
	/**
	 * {@link ValueCounter} makes use of an inner private class
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
	 * @since May/08/09
	 */
	private class CalcN extends SwingWorker<Integer, Void>
	{
		/**
		 * the file to calc the <i>N</i>.
		 * <i>N</i> is the total number of values
		 * found in a <i>value file</i>.
		 * </p>
		 * More formal:</br>
		 * N = &Sigma;n_i   where i = 1, ..., n
		 */
		private URI file = null;
		
		/**
		 * constructor is passed the file to iterate
		 * through its values.
		 * 
		 * @param file
		 * 		path to file to count the values per set
		 */
		public CalcN(URI file)
		{
			this.file = file;
		}
		
		@Override
		protected Integer doInBackground() throws Exception
		{
			// N = &Sigma;n_i   where i = 1, ..., n
			Integer N = 0;
			
			// open file for reading
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(this.file.getPath()));
				
				while(reader.readLine() != null)
				{
					N++;
				}
			}
			catch(FileNotFoundException fnfe)
			{
				ErrorMessage.getInstance().printMessage(fnfe, "FileNotFoundException");
			}
			catch(IOException ioe)
			{
				ErrorMessage.getInstance().printMessage(ioe, "IOException");
			}
			
			return N;
		}
	}
}