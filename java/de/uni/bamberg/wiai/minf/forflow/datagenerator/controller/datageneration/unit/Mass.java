package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is the unit of mass.
 * It's the primary instrument to measure mass and is part of
 * weight and measurements.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public class Mass implements WeightAndMeasurement
{
	/**
	 * holds the name of the current mass measurement
	 */
	private String name = null;
	
	/**
	 * holds the elements to this type of unit
	 */
	private List<String> types = null;
	
	/**
	 * tracks the last selected type.
	 */
	private int selected = 0;
	
	/**
	 * observer list
	 */
	private List<ObserverUnit> observerUnit = null;
	
	/**
	 * default constructor.
	 * Sets itself the name of the class.
	 */
	public Mass()
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
		return this.types.get(this.selected);
	}
	
	@Override
	public void setList(List<String> list)
	{
		this.types = list;
	}
	
	@Override
	public void addToList(String type)
	{
		if(this.types == null)
		{
			this.types = new ArrayList<String>();
		}
		
		this.types.add(type);
	}
	
	@Override
	public boolean remove(String type)
	{
		boolean successful = false;
		
		if(this.types != null)
		{
			for(Iterator<String> i=this.types.iterator(); i.hasNext();)
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
		return this.types;
	}
	
	@Override
	public Iterator<String> iterator()
	{
		return this.types.iterator();
	}

	@Override
	public void notifyObserverUnit()
	{
		if(this.observerUnit != null)
		{
			for(Iterator<ObserverUnit> i=this.observerUnit.iterator(); i.hasNext();)
			{
				ObserverUnit observer = i.next();
				
				observer.updateUnit(this.types, this.getName());
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