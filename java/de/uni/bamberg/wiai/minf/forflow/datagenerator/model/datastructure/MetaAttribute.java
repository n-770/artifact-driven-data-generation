package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

import java.util.ArrayList;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.dependency.Dependency;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.FillBehaviour;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.FillType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Interval;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Name;

/**
 * <b>MetaAttribute</b> represents an <i>Ecore's</i> <i>EAttribute</i> and
 * can also be called a <b>MetaFacet</b>.
 * This class is part of {@link MetaClass} objects. A meta attribute
 * is called meta because it describes attributes and is a model for those.
 * It can hold and specify much more than an ordinary attribute ever could.
 * The following holds for every attribute object:
 * <ul>
 * 		<li>it has a <b>name</b></li>
 * 		<li>a <b>data type</b> specifies the data it represents</li>
 * 		<li>a flag <b>null values</b> allowed?</li>
 * 		<li>a flag for <b>multiplicity</b> values.
 * 			It means, is it possible to generate more than one value.
 * 			As an example the facet <i>author</i> helps getting the picutre.
 * 			For a <i>document</i> it should be possible to have more than one
 * 			creator. 
 * 		</li>
 * 		<li>there's a flag for <b>restricted</b> values.
 * 			It indicates that not all values should be allowed.
 * 			For a lot of facets this is true. A simple example is <i>file type</i>.
 * 			A file type for a document is usually restricted to a handful.
 *  		Imagine writing a word doc. Legal values are:
 *  		<ul>
 *  			<li><b>docx</b> Word 2007 documents</li>
 *  			<li><b>doc</b> for Word 1997-2003 documents used</li>
 *  			<li><b>txt</b> ordinary plain text</li>
 *  			<li><b>rtf</b> rich text format</li>
 *  			<li><b>dotx</b> Word 2007 templates</li>
 *  			<li><b>wps</b> to load & save Works documents</li>
 *  		</ul>
 * 		</li>
 * 		<li>Sometimes the value of an attribute depends on other attributes.
 * 			In this case we say it <i>exists a dependency</i>.
 * 			To get a legal value for this attribute all other attributes it depends
 * 			on must exist, available and previously generated.
 * 		</li>
 * 		<li>To get to know how the value or values should be generated a <i>fill type</i>
 * 			is available. Here testers can specify where get data to that attribute.
 * 			That could be a file. That is highly useful and helpy when it's a restricted
 * 			attribute. Other ways are to generated random value when constraints say yes.
 * 		</li>
 * 		<li>Of course there's a parameter to store the generated values to this attribute.
 * 			It's the <i>facet value</i>.
 * 		</li>
 * 		<li>Most facets or attributes should have attached an unit to get the data
 * 			information more precisely. And it helps to get rid off of confusions.
 * 			A major problem is to specify all of possible unit data, but the system
 * 			is shipped with a bunch of the most general. And, of course, feel free
 * 			to extend it and adapt it to your needs.
 * 		</li>
 * 		<li>A few points above, we talked about dependencies. If it is enabled with
 * 			the corresponding flag, you have to define to with attributes it depends.
 * 			This field is non-trivial and is a very complicated subject, actually.
 * 			That means, it's possible that, at that state, your needs are not covered, yet. 
 * 		</li>
 * </ul>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public class MetaAttribute
{
	/**
	 * the name of the attribute or facet
	 */
	private String name = null;
	
	/**
	 * the datatype of the attribute
	 */
	private DataType datatype = null;
	
	/**
	 * a list holding the values for an attribute.
	 * Some attributes might have more than one value, e.g.
	 * a <b>Document</b> with the facet <b>author</b>.
	 * </p>
	 * The objects are specified by the data type attribute.
	 */
	private List<Object> values = null;
	
	/**
	 * We use a preview value to display the tester,
	 * that we do the right kind.
	 * The preview is represented as a string always, 'cos
	 * of convenience.
	 */
	private String preview = null;
	
	/**
	 * defines the lower bound of the values.
	 * The default value is <i>'1'</i>, and says that at least
	 * one value should be stored for that attribute.
	 * </p>
	 * @see #upperBound
	 */
	private int lowerBound = 1;
	
	/**
	 * defines the upper bound of values.
	 * If you don't specify this, it's the default is used.
	 * The default value is <i>'1'</i>.
	 * But if you specify it, you limit the values to that number.
	 * </p>
	 * @see #lowerBound
	 */
	private int upperBound = 1;
	
	/**
	 * flag, indicating whether multiple values for this
	 * attribute are allowed or not.
	 * It has direct impact to the above {@link #upperBound} parameter.
	 * The default value of upper bound can only be changed, if
	 * and only if this flag is set to <b>true</b>.
	 * </p>
	 * The default is <b>false</b>, and therefore multiple values
	 * for this attriubte are not allowed.
	 */
	private boolean areMultipleValuesAllowed = false;
	
	/**
	 * states whether <i>NULL</i> values are allowed for this
	 * kind of attribute.
	 * Null values have the effect that it is possible to
	 * skip generation of any data for this attribute and leave 
	 * it as it is, when it comes to test data generation.
	 * </p>
	 * Default value is set to 'false'.
	 */
	private boolean isNullValueAllowed = false;
	
	/**
	 * in case <i>Null</i> vales are allowed,
	 * the tester has to specify the amount of null values
	 * to generate. The value is in percent.
	 */
	private int percentOfNullValues = 0;
	
	/**
	 * flag, indicating whether values to this attribute
	 * are limited.
	 */
	private boolean isValueRestricted = false;
	
	/**
	 * does dependencies to any other attributes exit?
	 */
	private boolean existDependency = false;
	
	/**
	 * does dependencies exist between attributes?
	 * If the answer is <i>yes</i>, then it is stored
	 * here.
	 */
	private List<Dependency> dependencies = null;
	
	/**
	 * defines the type to fill with values.
	 */
	private FillType fillType = null;
	
	/**
	 * holds the type of unit. That could be a weight or
	 * measurement and refers to a standard defined in
	 * <i>International System of Units (SI)</i>.
	 */
	private String unit = null;
	
	/**
	 * defines the probability distribution to generate data.
	 */
	private ProbabilityDistribution distribution = null;
	
	/**
	 * default constructor with restricted visibility.
	 * To set values methods are provided.
	 */
	protected MetaAttribute()
	{
	}
	
	/**
	 * sets the name of the attribute
	 * 
	 * @param name
	 * 		the name of the attribute
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * gets the name of an attribute
	 * 
	 * @return
	 * 		String
	 */
	public final String getName()
	{
		return this.name;
	}
	
	/**
	 * sets the data type of an attribute
	 * 
	 * @param datatype
	 */
	public void setDataType(DataType datatype)
	{
		this.datatype = datatype;
	}
	
	/**
	 * gets the data type for an attribute
	 * 
	 * @return
	 * 		name of data type
	 */
	public final DataType getDataType()
	{
		return this.datatype;
	}
	
	/**
	 * sets the values for an attribute.
	 * That are the concrete values for an attribute, when
	 * it comes to data generation.
	 * </p> Values specified here, occur later in generated test data.
	 * 
	 * @param values
	 * 		list of values
	 */
	public void setValues(List<Object> values)
	{
		this.values = values;
	}
	
	/**
	 * adds an value to the attribute.
	 * All values which are added here, are later in
	 * generated test data sets.
	 * 
	 * @param ob
	 * 		it's an {@link Object}, to be as generic as possible.
	 * 		Because if we specify a particular data type her, it would
	 * 		be limited to a subset. And that's not what we want.
	 */
	public void addValue(Object ob)
	{
		if(this.values == null)
		{
			this.values = new ArrayList<Object>();
		}
		
		this.values.add(ob);
	}
	
	/**
	 * gets the values of an attribute
	 * 
	 * @return
	 * 		a list of values
	 */
	public final List<Object> getValues()
	{
		return this.values;
	}
	
	/**
	 * this gets a preview of the data generation values.
	 * It helps the tester to get a clue of the data that
	 * is gonna be generated for that attribute and the
	 * system gives a feedback that it does the right kind
	 * of things, according to its settings.
	 * 
	 * @return
	 * 		preview of the data to generate
	 */
	public final String getPreviewValue()
	{
		return this.preview;
	}
	
	/**
	 * sets the lower bound of possible values of an attribute.
	 * The default value is set to <i>'1'</i>.
	 * 
	 * @param lowerBound
	 */
	public void setLowerBound(int lowerBound)
	{
		this.lowerBound = lowerBound;
	}
	
	/**
	 * gets the lower bound of values.
	 * 
	 * @return
	 * 		integer
	 */
	public final int getLowerBound()
	{
		return this.lowerBound;
	}
	
	/**
	 * sets the upper bound of possible values for an attribute.
	 * The default value is set to <i>'1'</i> meaning the upper bound
	 * of this attribute is at most one.
	 * If you specify it by any other number, it means the possible
	 * amount of values is limited to that number.
	 * </p> 
	 * 
	 * @param upperBound
	 * 		set upper bound of values for this attribute.
	 */
	public void setUpperBound(int upperBound)
	{
		this.upperBound = upperBound;
	}
	
	/**
	 * gets the upper bound for values of an attribute.
	 * 
	 * @return
	 * 		integer
	 */
	public final int getUpperBound()
	{
		return this.upperBound;
	}
	
	/**
	 * flag, indicating whether multiple values for this
	 * attribute are allowed or not.
	 * This setting has direct impact to {@link #getUpperBound()}.
	 * In default setting it's not allowed to have multiple values
	 * and therefore the upper bound is <i>1</i>.
	 * </p>
	 * But if you set this to <i>true</i>, you have to specify
	 * the upper bound by yourself.
	 * </p>
	 * For clarity we give an example:
	 * </p>
	 * Suppose you've got an attribute (facet) <i>author</i>.
	 * Because for an object, say book, more than one author might
	 * be involved it has to be reflected in the test data too.
	 * 
	 * @param allowed
	 * 		does this attribute got more than one value?
	 */
	public void isMultiplicityAllowed(boolean allowed)
	{
		this.areMultipleValuesAllowed = allowed;
		
		// reset
		if(!allowed)
		{
			this.upperBound = 1;
		}
	}
	
	/**
	 * returns the flag, indicating multiplicity status
	 * for this attribute.
	 * When <i>true</i> is returned, the upper bound
	 * of this attribute tells, how many values to generate.
	 * </p>
	 * Default is set to <i>false</i>.
	 * 
	 * @return
	 * 		default is false
	 */
	public final boolean isMultiplicityAllowed()
	{
		return this.areMultipleValuesAllowed;
	}
	
	/**
	 * states whether <i>NULL</i> values are allowed for this
	 * kind of attribute.
	 * Null values have the effect that it is possible to
	 * skip generation of any data for this attribute and leave 
	 * it as it is, when it comes to test data generation.
	 * </p>
	 * Default value is set to 'false'.
	 * 
	 * @param allowed
	 * 		do you wanna allow the generation of null values 
	 * 		for this kind of attribute?
	 */
	public void isNullValueAllowed(boolean allowed)
	{
		this.isNullValueAllowed = allowed;
		
		// reset
		if(!allowed)
		{
			this.percentOfNullValues = 0;
		}
	}
	
	/**
	 * gets the <i>NULL</i> value flag.
	 * </p>
	 * Default value is set to <i>false</i>.
	 * 
	 * @return
	 * 		true, if null values are allowed for this attribute
	 */
	public final boolean isNullValueAllowed()
	{
		return this.isNullValueAllowed;
	}
	
	/**
	 * If <i>Null</i> values are allowed, then the 
	 * amount of null values has to be specified by
	 * the tester.
	 * 
	 * @param percent
	 * 		amount of null values in percent.
	 */
	public void setPercentOfNullValues(int percent)
	{
		this.percentOfNullValues = percent;
	}
	
	/**
	 * If <i>Null</i> values are allowed, then the 
	 * amount of null values has to be specified by
	 * the tester.
	 * As default value <i>0</i> is set. This means,
	 * no data set of this attribute has any <i>null</i>
	 * value.
	 * </p>
	 * Example:
	 * </br>
	 * when 10 has been returned it means 10 percent
	 * of the values should be <i>null</i>
	 * 
	 * @return
	 * 		amount of null values in percent
	 */
	public final int getPercentOfNullVallues()
	{
		return this.percentOfNullValues;
	}
	
	/**
	 * set whether values are restricted or not.
	 * Attributes with this flag enabled are only allowed to
	 * have a subset of possible values.
	 * In real life data most attributes will have this
	 * parameter enabled.
	 * 
	 * @param restricted
	 * 		enables or disables restriction
	 * 		
	 */
	public void isValueRestricted(boolean restricted)
	{
		this.isValueRestricted = restricted;
	}
	
	/**
	 * indicates the restriction setting.
	 * Restricted attributes can have only pre-defined
	 * values, when generation is done.
	 * It is not allowed to have any values not defined.
	 * </p>
	 * Default value is se to <i>false</i>.
	 * 
	 * @return
	 * 		restriction setting enabled or disabled
	 */
	public final boolean isValueRestricted()
	{
		return this.isValueRestricted;
	}
	
	/**
	 * sets the setting to handle dependencies.
	 * Enable this setting to any attribute which depends
	 * on a value of another attribute.
	 * </p>
	 * If this is enabled one has to specify the attributes
	 * it depends on.
	 * 
	 * @param dependency
	 * 		sets the dependency setting
	 */
	public void existsDependency(boolean dependency)
	{
		this.existDependency = dependency;
		
		// create
		if(dependency)
		{
			this.dependencies = new ArrayList<Dependency>();
		}
		
		// reset
		else
		{
			this.dependencies = null;
		}
	}
	
	/**
	 * tells if dependencies are exist or not to this
	 * attribute.
	 * In most cases it can be ignored, because there are
	 * no dependencies between attributes.
	 * </p>
	 * Default is set to <i>false</i>.
	 * 
	 * @return
	 * 		true, if dependency exits
	 */
	public final boolean exitsDependency()
	{
		return this.existDependency;
	}
	
	/**
	 * sets the attributes this one depends upon.
	 * The attributes listed here <i>must</i> computed
	 * first before any dependent attribute is considered.
	 * 
	 * @param dependencies
	 * 		list of attributes this one depends upon.
	 */
	public void setDependencies(List<Dependency> dependencies)
	{
		this.dependencies = dependencies;
	}
	
	/**
	 * adds an attribute to the list to consider first.
	 * 
	 * @param dependency
	 * 		a new attribute this one depends.
	 */
	public void addDependency(Dependency dependency)
	{
		this.dependencies.add(dependency);
	}
	
	/**
	 * gets all attributes this one depends.
	 * The list will only return elements, if
	 * and only if {@link #exitsDependency()}
	 * is enabled.
	 * 
	 * @return
	 * 		list of attributes this one depends or null
	 */
	public final List<Dependency> getDependencies()
	{
		return this.dependencies;
	}
	
	/**
	 * specifies the type of filling. In other words,
	 * it defines the way test data is gonna be generated
	 * for this attribute. There are a different types
	 * possible, for example by linking to an external file,
	 * by a pre-defined generator, by an interval or even by
	 * a formula.
	 * </p>
	 * There are more than just one ways possible to define
	 * a fill type, because it is based on <i>Strategy Pattern</i>.
	 * 
	 * @param fillType
	 * 		type of filling this attribute with data.
	 */
	public void setFillType(FillType fillType)
	{
		this.fillType = fillType;
		
		if(this.fillType.getFillBehaviour() != null)
		{
			this.setFacetValuePreview(this.fillType.getFillBehaviour());
			
			this.notifyDistribution(this.fillType.getFillBehaviour());
		}
	}
	
	/**
	 * gets the type to fill data to this attribute.
	 * 
	 * @return
	 * 		type of filling or null
	 */
	public final FillType getFillType()
	{
		return this.fillType;
	}
	
	/**
	 * registers the fill behaviour to fill type.
	 * This is for convenience. The behaviour could also
	 * be registered using the following method:
	 * </p>
	 * {@link #setFillType(FillType)} whereas FillType is
	 * replaced by <i>new FillType().setFillBehaviour(behaviour)</i>.
	 * 
	 * @param behaviour
	 * 		the type of filling attributes with generated data.
	 * 		Because it is based on <i>Strategy</i>, the algorithm
	 * 		to fill is substitutable.
	 */
	public void setFillBehaviour(FillBehaviour behaviour)
	{
		if(this.fillType == null)
		{
			this.fillType = new FillType();
		}
		
		this.fillType.setFillBehaviour(behaviour);
		
		this.setFacetValuePreview(behaviour);
		
		this.notifyDistribution(behaviour);
	}
	
	/**
	 * sets the unit of an attribute.
	 * An unit is a weight or a measure and is defined
	 * by a standard. A few examples for units are:
	 * </p>
	 * <ul>
	 * 		<li>length</li>
	 * 			<ul>
	 * 				<li>mm, cm, m, km, ...</li>
	 * 			</ul>
	 * 		<li>mass</li>
	 * 			<ul>
	 * 				<li>g, kg, t, ...</li>
	 * 			</ul>
	 * 		<li>time</li>
	 * 			<ul>
	 * 				<li>sec, min, h, day, ...</li>
	 * 			</ul>
	 * 		<li>temperature</li>
	 * 			<ul>
	 * 				<li>C°, F°, K</li>
	 * 			</ul>
	 * 		<li>currency</li>
	 * 			<ul>
	 * 				<li>Euro, Dollar, Pounds, ...</li>
	 * 			</ul>
	 * 		<li>math</li>
	 * 			<ul>
	 * 				<li>percent, degree, ...</li>
	 * 			</ul>
	 * </ul> 
	 * </p>
	 * Of course, not all attributes make use of this setting.
	 * 
	 * @param unit
	 * 		weight or a measurement or null
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	
	/**
	 * gets the unit. That is a weight or a 
	 * measurement.
	 * Notice, not all attributes make use of this setting.
	 * In that case, null will be returned!
	 * 
	 * @return
	 * 		weight or measurement or null
	 */
	public final String getUnit()
	{
		return this.unit;
	}
	
	/**
	 * sets the probability distribution to an attribute.
	 * With this setting values are generated or selected
	 * by a distribution. For example, if you wanna generate
	 * data with and each possible value has the same probability,
	 * you go for en <i>even</i> distribution.
	 * 
	 * @param pd
	 * 		probability distribution
	 */
	public void setDistribution(ProbabilityDistribution pd)
	{
		this.distribution = pd;
		
		this.registerDistribution(pd);
	}
	
	/**
	 * gets the probability distribution attached to this
	 * attribute. It specifies how a value is generated or
	 * chosen to it.
	 * 
	 * @return
	 * 		probability distribution or null
	 */
	public final ProbabilityDistribution getDistribution()
	{
		return this.distribution;
	}
	
	/**
     * when fill behaviour has been set, all of its <i>potential</i> observers
     * have to be notified. We refer to observers as potential, because at
     * this time it could be possible that no observers are created, yet.
     * But in case they are, we register them.
     * </p>
     * <font size=2><b>When used?</b></font></br>
     * This method is called, when probability distribution has been set
     * before any fill behaviour was set. In that case the distribution couldn't
     * register to fill behaviour. But registration is needed to get latest
     * updates on that strategy, because distribution needs additional infos
     * form there to work.
     * </p>
     * <font size=2><b>Situation</b></font></br>
     * The image below summarizes, when confronted with this situation.</br>
     * <img src="registerDistToFillBehaviour.jpg" alt="registerDistToFillBehaviour.jpg"/>
     * 
     * @param fillBehaviour
     * 		fill behaviour is one of more possible fill strategies.
     * 		{@link FillBehaviour} is based on the <i>Strategy Pattern</i>,
     * 		so we don't know much there are, but Distribution needs to get
     * 		updated on that.
     */
	private void notifyDistribution(FillBehaviour fillBehaviour)
	{
		// already set, so we register dist
		if(this.getDistribution() != null)
		{
			fillBehaviour.registerObserverFillBehaviour(this.getDistribution());
		}
	}
	
	/**
	 * of course, it's also possible that the fill behaviour
	 * has been set by the user before the distribution.
	 * That case is visualized in the image below.
	 * </p>
	 * <img src="registerDistToFillBehaviour2.jpg" alt="registerDistToFillBehaviour2.jpg"/>
	 * 
	 * @param dist
	 * 		the latest selected distribution
	 */
	private void registerDistribution(ProbabilityDistribution dist)
	{
		// already set
		if(this.getFillType() != null)
		{
			this.getFillType().getFillBehaviour().registerObserverFillBehaviour(dist);
		}
	}
	
	/**
	 * this method is called when any fill behaviour has been set.
	 * Then we can also set the preview value. As default strategy
	 * we choose the 1st value available.
	 */
	private void setFacetValuePreview(FillBehaviour behaviour)
	{
		if(behaviour == null)
		{
			this.preview = null;
		}
		else
		{
			// name is handled different
			if(behaviour instanceof Name)
			{
				Name name = (Name) behaviour;
				
				this.preview = name.getValueAt(0, 0);
			}
			
			// interval is handled different
			else if(behaviour instanceof Interval)
			{
				Interval interval = (Interval) behaviour;
				
				this.preview = interval.getValueAt(interval.getLowerBound());
			}
			
			// other
			else
			{
				this.preview = behaviour.getValueAt(0);
			}
		}
	}
}