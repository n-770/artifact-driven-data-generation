package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.GaussianGenerator;

/**
 * <font size=3><b>X ~ N(&mu;, &sigma;&sup2;)</b></font></p>
 * This is one of the most important distributions. It's also known
 * as the <i>Gaussian</i> distribution or referred to as the bell curve.
 * </p>
 * <font size=6><b>Continuous Data</b></font></br>
 * The normal distribution is an <i>ideal</i> model for continuous data.
 * The normal distribution is called normal, because it's seen as an ideal.
 * It's what someone can normally expect to see in real life, and it applies
 * for a lot of continuous data, such as measurements.
 * </p>
 * <font size=6><b>Probability density</b></font></br>
 * The normal distribution is in the shape of a bell curve.
 * The curve is symmetrical, with the highest
 * probability density in the center of the curve.
 * The probability density decreases the further away you get from the mean.
 * Both the mean and median are at the center and have the highest
 * probability density.
 * </p>
 * <font size=6><b>Parameters</b></font></br>
 * The normal distribution is defined by two parameters, &mu; and &sigma;.
 * &mu; tells where the center of the curve is, and &sigma; gives the spread.
 * If a continuous random variable X follows a normal distribution with mean
 * &mu; and standard deviation &sigma;, this is generally written </br>
 * <font size=3><b>X ~ N(&mu;, &sigma;&sup2;)</b></font
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public class NormalDistribution extends ProbabilityDistribution
{
	/**
	 * defines whether this probability distribution
	 * is continuous or discrete
	 */
	private boolean isDiscrete = false;
	
	/**
	 * sets the default expectation <i>0</i>
	 */
	private int expectation = 0;
	
	/**
	 * sets the default standard deviation to <i>1</i>
	 */
	private int standardDeviation = 1;
	
	/**
	 * here are the observers for this probability distribution
	 */
	private List<ObserverDistribution> observers = null;
	
	/**
	 * default constructor sets the name for this
	 * probability distribution. It's for displaying purposes.
	 */
	public NormalDistribution()
	{
		this.name = "Normal Distribution";
	}
	
	/**
	 * <font size=6><b>Clone Constructor</b></font></br>
	 * This constructor is passed an object to make an copy
	 * of it. Constructor is only visible to package.
	 * 
	 * @param distribution
	 * 		object to clone
	 */
	protected NormalDistribution(NormalDistribution distribution)
	{
		super(distribution);
		
		this.isDiscrete = distribution.isDiscrete();
		this.expectation = distribution.getExpectation();
		this.standardDeviation = distribution.getStandardDeviation();
	}
	
	/**
	 * this distribution doesn't use this method and therefore
	 * throws an {@link UnsupportedOperationException}.
	 * Use instead {@link #generatorDouble(Random)}.
	 */
	@Override
	protected NumberGenerator<Integer> generatorInt(Random random)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected NumberGenerator<Double> generatorDouble(Random random)
	{
		return new GaussianGenerator(this.expectation, this.standardDeviation, random);
	}

	@Override
	public String getDescription()
	{
		// Unicode mu, sigma²
		return ("X ~ N(\u03bc, \u03C3\u00B2)" +" = N(" +this.expectation +", " +this.standardDeviation +")");
	}
	
	/**
	 * Expectation gives a prediction of the results.
	 * The expectation of a variable X is like the mean,
	 * but for probability distributions.
	 * The expectation gives the typical or average
	 * value of a variable but it doesn't tell anything
	 * about how the values are spread out.
	 * 
	 * @param expectation
	 * 		E(X) = &Sigma;xP(X=x) = &mu;
	 */
	public void setExpectation(int expectation)
	{
		this.expectation = expectation;
	}
	
	@Override
	public int getExpectation()
	{
		return this.expectation;
	}
	
	/**
	 * As well as having a variance, probability distributions
	 * have a standard deviation. It's a way of measuring how
	 * far away from the center you can expect your values to be.
	 * 
	 * @param standardDeviation
	 * 		&sigma; = &radic;Var(X)
	 */
	public void setStandardDeviation(int standardDeviation)
	{
		this.standardDeviation = standardDeviation;
	}
	
	@Override
	public int getStandardDeviation()
	{
		return this.standardDeviation;
	}

	@Override
	public double getVariance()
	{
		return Math.pow(this.standardDeviation, 2);
	}

	@Override
	public boolean isDiscrete()
	{
		return this.isDiscrete;
	}
	
	@Override
	public Map<Double, Double> generateExpectedValues()
	{
		// <X, P(X=x)>
		Map<Double, Double> res = new HashMap<Double, Double>();
		
		// P(X=x)
		double p = 0;
		
		// X=x_i    i= 1, ..., n
		for(double i=this.getLowerBound(); i<=this.getUpperBound(); i++)
		{
			p = this.caclProbability(i, this.getStandardDeviation(), this.getExpectation());
			
			res.put(i, p);
		}
		
		return res;
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
		return new NormalDistribution(this);
	}
	
	/**
	 * <img src="pdf_normalDistribution.jpg" alt="pdf_normalDistribution.jpg"/>
	 * 
	 * @param x
	 * 		P(X=x)
	 * @param sigma
	 * 		&sigma; = &radic;Var(X)
	 * @param mu
	 * 		E(X) = &mu;
	 * @return
	 * 		P(x)
	 */
	private double caclProbability(double x, int sigma, int mu)
	{
		double p_x = 0;
		
		// -((x-mu)² / 2sigma²)
		double continousCorrection = -(Math.pow((x - mu), 2) / (2 * (Math.pow(sigma, 2))));
		
		// 1/(sigma * &radic;(2pi))
		double frac = (1/(sigma * Math.sqrt(2 * Math.PI)));
		
		// p(x) = frac * e^cc
		p_x = (frac * Math.exp(continousCorrection));
		
		return p_x;
	}
}