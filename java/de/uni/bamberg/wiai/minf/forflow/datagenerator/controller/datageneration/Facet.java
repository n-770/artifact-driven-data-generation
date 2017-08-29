package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration;

import java.util.ArrayList;
import java.util.List;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaAttribute;

/**
 * This class represents a single facet. The difference to
 * {@link MetaAttribute} is that this one is ready to get
 * serialized into an XML file. MetaAttribute class is not
 * designed to do that. The keyword here is <b>separation of affairs.</b>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/13/09
 */
public class Facet
{
	/**
	 * here is the name of the facet
	 */
	private String name = null;
	
	/**
	 * the unit type of this facet.
	 * Only products can have it.
	 */
	private String unit = null;
	
	/**
	 * the values are in a list, 'cos there
	 * might be more than just one value.
	 * This depends on <i>multipleValues?</i>
	 * attribute.
	 */
	private List<String> values = null;
	
	/**
	 * sole default constructor
	 */
	public Facet()
	{
	}
	
	/**
	 * constructor is passed the name at creation time
	 * 
	 * @param name
	 * 		the name of the facet
	 */
	public Facet(String name)
	{
		this.name = name;
	}
	
	/**
	 * sets the name of the facet
	 * 
	 * @param name
	 * 		facet name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * gets the name of the facet
	 * 
	 * @return
	 * 		string
	 */
	public final String getName()
	{
		return (this.name == null) ? "" : this.name;
	}
	
	/**
	 * sets the values for this facet at once.
	 * 
	 * @param values
	 * 		generated values
	 */
	public void setValues(List<String> values)
	{
		this.values = values;
	}
	
	/**
	 * adds once value at a time to the list.
	 * 
	 * @param value
	 * 		generated value
	 */
	public void addValue(String value)
	{
		if(this.values == null)
		{
			this.values = new ArrayList<String>();
		}
		
		this.values.add(value);
	}
	
	/**
	 * gets the value of this facet.
	 * Notice, at any time there's only one
	 * value returned. This is because all
	 * values are gathered to one single value
	 * and then it is returned.
	 * </p>
	 * <font size=6><b>Example</b></font></br>
	 * Suppose you've generated values to facet <i>author</i>
	 * and there is <i>multipleValues?</i> enabled.
	 * As a result of these settings you could get the following:</br>
	 * <font color=red>"Portman, Moore, Simpson"</font>
	 * </p>
	 * But in the data structure these values are still separate.
	 * 
	 * @return
	 * 		gets the generated value
	 */
	public final String getValue()
	{
		if(this.values != null)
		{
			if(this.values.size() > 0)
			{
				StringBuffer sb = new StringBuffer();
				
				for(int i=0; i<this.values.size(); i++)
				{
					if(i+1 < this.values.size())
					{
						sb.append(this.values.get(i) +", ");
					}
					else
					{
						sb.append(this.values.get(i));
					}
				}
				
				return sb.toString();
			}
			else
			{
				return new String("");
			}
		}
		else
		{
			return new String("");
		}
	}
	
	/**
	 * sets the unit of this facet. A unit could be
	 * any weight or measurement. Use one of the pre-defined
	 * unit entities or make a custom one if no category fits.
	 * 
	 * @param unit
	 * 		the weight or measurement of this facet
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	
	/**
	 * gets the weight or measurement of this facet.
	 * 
	 * @return
	 * 		unit
	 */
	public String getUnit()
	{
		return (this.unit == null) ? new String("") : this.unit;
	}
	
	/**
	 * gets the value and the unit in one string object
	 * with its intended text formation.
	 * 
	 * @return
	 * 		value + unit
	 */
	public String getValueAndUnit()
	{
		StringBuffer sb = new StringBuffer();
		
		if(!this.getValue().equals(""))
		{
			sb.append(this.getValue());
		}
		
		if(!this.getUnit().equals(""))
		{
			sb.append(" " +this.getUnit());
		}
		
		return (sb.length() > 0) ? sb.toString() : new String("");
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(this.getName());
		
		if(this.getValue() != null)
		{
			sb.append(" " +this.getValue() +" ");
		}
		if(this.getUnit() != null)
		{
			sb.append(" " +this.getUnit() +"\n");
		}
		
		return sb.toString();
	}
}