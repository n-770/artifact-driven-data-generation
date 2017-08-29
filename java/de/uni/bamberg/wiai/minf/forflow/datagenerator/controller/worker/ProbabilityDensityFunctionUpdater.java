package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.util.Map;

import javax.swing.SwingWorker;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.graph.Graph;

/**
 * This class updates the probability density function chart each
 * time either the distribution has changed or the values have 
 * changed. So the tester gets easily a first impression on how
 * the probability density is used. This helps to get a clue
 * of how P(X=x) looks like for each value.
 * </p>
 * <font size=6><b>Probability Density Function</b></font></br>
 * Here is an example how it is visualized.</br>
 * <img src="probabilityDensityFunction.jpg" alt="probabilityDensityFunction.jpg"/>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/05/09
 */
public class ProbabilityDensityFunctionUpdater extends SwingWorker<Void, Void>
{
	/**
	 * this is the distribution to visualize
	 */
	private ProbabilityDistribution dist = null;
	
	/**
	 * that's the graph component. It is a JFree chart
	 */
	private Graph graph = null;
	
	/**
	 * constructor is passed the selected distribution
	 * with its parameters and a graph object to draw
	 * the probability density function.
	 * 
	 * @param distribution
	 * 		probability distribution
	 * @param graph
	 * 		a graph is a more sophisticated way to visualize
	 * 		as it would be with a panel or something.
	 */
	public ProbabilityDensityFunctionUpdater(ProbabilityDistribution distribution, Graph graph)
	{
		this.dist = distribution;
		this.graph = graph;
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		Map<Double, Double> values = this.dist.generateExpectedValues();
		
		this.graph.generateGraph(this.dist.getDescription(),
								 values,
								 this.dist.getExpectation(),
								 this.dist.getStandardDeviation(),
								 this.dist.isDiscrete());
		
		return null;
	}
}