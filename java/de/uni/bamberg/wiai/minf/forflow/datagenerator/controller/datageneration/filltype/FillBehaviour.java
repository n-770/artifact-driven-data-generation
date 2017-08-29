package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

import java.net.URI;

/**
 * Here is the interface for the filling strategy.
 * Depending on that strategy filling is handled each
 * time differently. This is necessary, because we want
 * to offer the tester different options to handle the
 * filling of attributes with valuable data.
 * </p>
 * Conceivable strategies are:
 * <ul>
 * 		<li>external file</li>
 * 		<li>pre-defined generator</li>
 * 		<li>customized input. That is helpy, when intervals
 * 			preferred instead of file lists or when formulas are typed in.
 * 		</li>
 * </ul>
 * </p>
 * <font size=6><b>Observable</b></font></br>
 * Any fill behaviour is also observable, that means it its based
 * on the <i>Observer Pattern</i>. This is necessary because some
 * parts of the implementation depend on those values. With this kind
 * of pattern dependent objects get always the latest data.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public interface FillBehaviour extends ObservableFillBehaviour
{
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
	public int calculateN(URI file);
	
	/**
	 * each filling behaviour whether it is via a 
	 * value file, an interval or by editor each
	 * of them has a range of values. This range
	 * is referred to as <i>N</i>. <i>N</i> is
	 * the total number of possible or available values
	 * to use for generation.
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
	 * @return
	 * 		number of values <i>N = &Sigma;n_i</i> for this filling behaviour.
	 */
	public int getN();
	
	/**
	 * this method has the job of retrieving the value at
	 * the specified index. Most fill behaviour strategies are based on
	 * value files. These files have a specific amount of possible
	 * values from i = 1, ..., n where i is the index.
	 * When the process of generating test data is activated, to each
	 * facet values of the fill behaviour are used. To get
	 * values in the range of the value file, this method is needed.
	 * By the way, only the strategies itselves have access to 
	 * value files.
	 * 
	 * @param n
	 * 		the index specifies the value at position n to retrieve. 
	 * @return
	 * 		the value at position n or null
	 */
	public String getValueAt(int n);
	
	/**
	 * gets the name of this filling.
	 * It is for displaying purposes.
	 * 
	 * @return
	 * 		a meaningful name
	 */
	public String getName();
}