package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Customized class to represent weight and measurements.
 * Here are the units which do not fit in any of the other 
 * categories.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/22/09
 */
public class Custom
{
	/**
	 * the name of the customized unit
	 */
	private String name = null;
	
	/**
	 * the content. We do not know how many
	 * elements or items there will be.
	 * So it is a list.
	 */
	private List<String> content = null;
	
	/**
	 * sole default constructor.
	 * Notice, customized unit elements do not name itself.
	 */
	public Custom()
	{
	}
	
	/**
	 * sets the name of the customized unit
	 * 
	 * @param name
	 * 		name of customized unit
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * gets the name of customized unit
	 * 
	 * @return
	 * 		name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * sets the list of content of this
	 * customized unit at once.
	 * 
	 * @param list
	 * 		list of customized items
	 */
	public void setContent(List<String> list)
	{
		this.content = list;
	}
	
	/**
	 * adds an element to the list of content
	 * 
	 * @param content
	 * 		adds a new item to content
	 */
	public void addToContent(String content)
	{
		if(this.content == null)
		{
			this.content = new ArrayList<String>();
		}
		
		this.content.add(content);
	}
	
	/**
	 * gets the whole list at once.
	 * 
	 * @return
	 * 		content list
	 */
	public List<String> getContent()
	{
		return this.content;
	}
	
	/**
	 * Returns an iterator over the elements
	 * in this list in proper sequence. 
	 * 
	 * @return
	 * 		iterator
	 */
	public Iterator<String> iterator()
	{
		return this.content.iterator();
	}
}