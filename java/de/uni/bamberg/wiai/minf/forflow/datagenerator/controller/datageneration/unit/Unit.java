package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.XMLStartupTag;

/**
 * An unit is a standard of a weight or a measurement.
 * There are different systems of measurement, like metric system,
 * natural system, imperial system.
 * </p>
 * The weight and measurements object is based on <i>Strategy</i> pattern.
 * It defines a family of algorithms, encapsulates each one, and makes
 * them interchangeable. Strategy lets the algorithm vary
 * independently from clients ({@link Unit}) that use it.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public class Unit
{
	/**
	 * reference to a weight or measurement type
	 */
	private WeightAndMeasurement wam = null;
	
	/**
	 * sole default constructor
	 */
	public Unit()
	{
	}
	
	/**
	 * constructor is passed the weight and measurements
	 * unit type.
	 * 
	 * @param weightAndMeasurement
	 * 		defines at creation time which kind to use.
	 */
	public Unit(WeightAndMeasurement weightAndMeasurement)
	{
		this.wam = weightAndMeasurement;
	}
	
	/**
	 * sets a weight or a measurement unit
	 * 
	 * @param wam
	 * 		weight or measurement
	 */
	public void setWeightAndMeasurement(WeightAndMeasurement wam)
	{
		this.wam = wam;
	}
	
	/**
	 * gets the weight or measurement unit.
	 * Use this method if {@link #isCustomized()} evaluates to <i>false</i>.
	 * Otherwise you should use {@link #getWeightAndMeasurementCustom()}.
	 * </p>
	 * If you do not take this advise, then you've to cast it
	 * into the appropriate type by yourself.
	 * 
	 * @return
	 * 		weight or measurement type
	 */
	public WeightAndMeasurement getWeightAndMeasurement()
	{
		return this.wam;
	}
	
	/**
	 * returns the weight or measurement unit.
	 * Before you try to get any unit, please check with {@link #isCustomized()}
	 * the status.
	 * If it evaluates to <i>true</i>, then go on and use this method.
	 * But if it evaluates to <i>false</i> take the above method
	 * {@link #getWeightAndMeasurement()} instead.
	 * </p>
	 * If you do not take this advise, then you've to cast it
	 * into the appropriate type by yourself.
	 * 
	 * @return
	 * 		customized weight or measurement type
	 */
	public WeightAndMeasurementCustomized getWeightAndMeasurementCustom()
	{
		return (WeightAndMeasurementCustomized) this.wam;
	}
	
	/**
	 * gets the name of the current selected or
	 * used unit.
	 * This is only for displaying concerns.
	 * 
	 * @return
	 * 		weight or measurement
	 */
	public final String getName()
	{
		if(this.wam != null)
		{
			return this.wam.getName();
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * sets the customized setting.
	 * If customized is enabled, the unit is handled
	 * in a slightly different way to handle customization.
	 * </p>
	 * If this method evaluates to <i>true</i>, then you should
	 * use the {@link #getWeightAndMeasurementCustom()} method.
	 * If it evaluates to <i>false</i>, you can take the other
	 * method {@link #getWeightAndMeasurement()} instead.
	 * </p>
	 * It's possible always to take the latter method, but when
	 * this method returns <i>true</i>, you've to do more work
	 * by yourself and to cast it into the approprivate type.
	 * 
	 * @return
	 * 		is this unit customized?
	 * 		If the answer is <i>yes</i>, it's handled different,
	 * 		to reflect customization.
	 */
	public final boolean isCustomized()
	{
		return (this.getName().equals(XMLStartupTag.MISCELLANEOUS)) ? true : false;
	}
}