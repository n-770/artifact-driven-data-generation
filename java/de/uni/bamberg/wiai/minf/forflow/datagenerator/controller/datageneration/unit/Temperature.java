package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is the unit of temperature.
 * The class is derived from weight and measurements and basic
 * units of temperature <b>T</b> are <i>Kelvin (K)</i> and <i>Celsius (C)</i>. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public class Temperature implements WeightAndMeasurement
{
	/**
	 * names the current temperature.
	 */
	private String name = null;
	
	/**
	 * holds the elements for this kind of unit type
	 */
	private List<String> type = null;
	
	/**
	 * it's helpy when tracking down the last
	 * selected element
	 */
	private int selected = 0;
	
	/**
	 * holds observers for <i>Temperature</i>
	 */
	private List<ObserverUnit> observerUnit = null;
	
	/**
	 * sole default constructor.
	 * Names itself after the class name.
	 */
	public Temperature()
	{
		this.name = this.getClass().getSimpleName();
	}
	
	@Override
	public final String getName()
	{
		return this.name;
	}
	
	@Override
	public void setSelected(int selected)
	{
		this.selected = selected;
		
		this.notifyObserverUnit();
	}
	
	@Override
	public final String getSelected()
	{
		return this.type.get(this.selected);
	}
	
	@Override
	public void setList(List<String> list)
	{
		this.type = list;
	}
	
	@Override
	public void addToList(String type)
	{
		if(this.type == null)
		{
			this.type = new ArrayList<String>();
		}
		
		this.type.add(type);
	}
	
	@Override
	public boolean remove(String type)
	{
		boolean successful = false;
		
		if(this.type != null)
		{
			for(Iterator<String> i=this.type.iterator(); i.hasNext();)
			{
				String s = i.next();
				
				if(s.equals(type))
				{
					i.remove();
					
					successful = true;
					
					this.notifyObserverUnit();
				}
			}
		}
		
		return successful;
	}
	
	@Override
	public List<String> getList()
	{
		return this.type;
	}
	
	@Override
	public Iterator<String> iterator()
	{
		return this.type.iterator();
	}

	@Override
	public void notifyObserverUnit()
	{
		if(this.observerUnit != null)
		{
			for(Iterator<ObserverUnit> i=this.observerUnit.iterator(); i.hasNext();)
			{
				ObserverUnit observer = i.next();
				
				observer.updateUnit(this.type, this.getName());
			}
		}
	}

	@Override
	public void registerObserverUnit(ObserverUnit observer, String name)
	{
		if(this.observerUnit == null)
		{
			this.observerUnit = new ArrayList<ObserverUnit>();
		}
		
		this.observerUnit.add(observer);
		
		this.notifyObserverUnit();
	}

	@Override
	public void removeObserverUnit(ObserverUnit observer, String name)
	{
		if(this.observerUnit != null)
		{
			int n = this.observerUnit.indexOf(observer);
			
			if(n >= 0)
			{
				this.observerUnit.remove(n);
			}
		}
	}

	@Override
	public final String toString()
	{
		return this.getName();
	}
}