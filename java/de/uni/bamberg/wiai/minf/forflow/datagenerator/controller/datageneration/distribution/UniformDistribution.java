package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.DiscreteUniformGenerator;

/**
 * <font size=6><b>X ~ U(a, b)</b></font>
 * </p>
 * An uniform probability distribution can be either discrete or
 * continuous. We use the discrete uniform distribution. 
 * </p>
 * <font size=6><b>Probability density</b></font></br>
 * The probability of any outcome k_i is 1/n. All values
 * of a set of possible values are equally. The probability density
 * function f(x) is a constant value.  
 * </p>
 * <font size=6><b>Parameters</b></font></br>
 * The uniform distribution is defined by two parameters, a and b.
 * A tells where the lower bound is and b where the upper bound is.
 * If X follows a uniform distribution then
 * <ul>
 * 		<li>f(x) = 1/(b - a) where a &le; x &le; b</li>
 * 		<li>E(X) = (a + b)/2</li>
 * 		<li>Var(X) = (b - a)&sup2;/12</li>
 * </ul>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public class UniformDistribution extends ProbabilityDistribution
{
	/**
	 * defines whether the distribution is discrete
	 * or continuous
	 */
	private boolean isDiscrete = true;
	
	/**
	 * parameter <i>a</i> = min
	 */
	private int a = 0;
	
	/**
	 * parameter <i>b</i> = max
	 */
	private int b = 0;
	
	/**
	 * here are the observers stored
	 */
	private List<ObserverDistribution> observers = null;
	
	/**
	 * detfault constructor sets the name of this
	 * probability distribution. It's for printing purposes.
	 */
	public UniformDistribution()
	{
		this.name = "Uniform Distribution";
	}
	
	/**
	 * <font size=6><b>Clone Constructor</b></font></br>
	 * This constructor is passed an object to make an copy
	 * of it. Constructor is only visible to package.
	 * 
	 * @param distribution
	 * 		object to clone
	 */
	protected UniformDistribution(UniformDistribution distribution)
	{
		super(distribution);
		
		this.isDiscrete = distribution.isDiscrete();
		this.a = distribution.getA();
		this.b = distribution.getB();
	}
	
	@Override
	public NumberGenerator<Integer> generatorInt(Random random)
	{
		return new DiscreteUniformGenerator(this.a, this.b, random);
	}
	
	/**
	 * this method is not supported by discrete.
	 * It throws an {@link UnsupportedOperationException}.
	 * Use instead {@link #generatorInt(Random)}.
	 */
	@Override
	public NumberGenerator<Double> generatorDouble(Random random)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String getDescription()
	{
		return ("X ~ U(a, b) = U(" +this.a +", " +this.b +")");
	}
	
	/**
	 * sets the parameter <i>a</i> = min
	 * 
	 * @param a
	 * 		the minimum
	 */
	public void setA(int a)
	{
		this.a = a;
	}
	
	/**
	 * gets the parameter <i>a</i> = min
	 * 
	 * @return
	 * 		the minimum
	 */
	public int getA()
	{
		return this.a;
	}
	
	/**
	 * sets the parameter <i>b</i> = max
	 * 
	 * @param b
	 * 		the maximum
	 */
	public void setB(int b)
	{
		this.b = b;
	}
	
	/**
	 * gets the parameter <i>b</i> = max
	 * 
	 * @return
	 * 		the maximum
	 */
	public int getB()
	{
		return this.b;
	}
	
	@Override
	public Map<Double, Double> generateExpectedValues()
	{
		// <X, P(X=x)>
		Map<Double, Double> res = new HashMap<Double, Double>();
		
		// iterate over interval
		for(double i=this.getLowerBound(); i<=this.getUpperBound(); i++)
		{
			double p = this.calcProbability(i);
			
			// key = X, value = P(X=x)
			res.put(i, p);
		}
		
		return res;
	}
	
	/**
	 * Expectation gives a prediction of the results.
	 * The expectation of a variable X is like the mean,
	 * but for probability distributions.
	 * The expectation gives the typical or average
	 * value of a variable but it doesn't tell anything
	 * about how the values are spread out. 
	 * 
	 * @return
	 * 		E(X) = (a + b) / 2
	 */
	@Override
	public int getExpectation()
	{
		return ((this.a + this.b) / 2);
	}
	
	@Override
	public int getStandardDeviation()
	{
		return (int) Math.sqrt(this.getVariance());
	}

	/**
	 * Use variance to measure the spread of values.
	 * In other words, variance tells you about the
	 * spread of the result.
	 * 
	 * @return
	 * 		Var(X) = (b - a)&sup2; / 12
	 */
	@Override
	public double getVariance()
	{
		return (Math.pow(this.b - this.a, 2) / 12);
	}

	@Override
	public boolean isDiscrete()
	{
		return this.isDiscrete;
	}
	
	@Override
	public void registerObserverDistribution(ObserverDistribution observer)
	{
		if(this.observers == null)
		{
			this.observers = new ArrayList<ObserverDistribution>();
		}
		
		this.observers.add(observer);
		
		this.notifyObserverDistribution(true);
	}
	
	@Override
	public void removeObserverDistribution(ObserverDistribution observer)
	{
		if(this.observers != null)
		{
			int n = this.observers.indexOf(observer);
			
			if(n >= 0)
			{
				this.observers.remove(n);
				
				this.notifyObserverDistribution(false);
			}
		}
	}
	
	@Override
	public void notifyObserverDistribution(boolean add)
	{
		for(Iterator<ObserverDistribution> i=this.observers.iterator(); i.hasNext();)
		{
			ObserverDistribution observer = i.next();
			
			observer.updateProbabilityDistribution(add, this);
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new UniformDistribution(this);
	}
	
	/**
	 * calculates the probability at position <i>i</i>
	 * for this probability distribution. It is only
	 * used for expected values and means an idealized
	 * form.
	 * 
	 * @param i
	 * 		index at position <i>i</i> not used here.
	 * @return
	 * 		probability at position <i>i</i>
	 */
	private double calcProbability(double i)
	{
		return ((double) 1 / (double) (this.b - this.a));
	}
}