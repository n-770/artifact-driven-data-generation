package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Here are the units gathered, which do not
 * fit into any other category. It is part of a customized
 * weight and measurement. This customized IF is itself
 * part of weight and measurement.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/20/09
 */
public class Miscellaneous implements WeightAndMeasurementCustomized
{
	/**
	 * name of misc
	 */
	private String name = null;
	
	/**
	 * list of misc unit elements
	 */
	private List<Custom> type = null;
	
	/**
	 * tracks the current selected element
	 */
	private int selected = 0;
	
	/**
	 * here are the observers for this unit type: <i>Misc</i>
	 */
	private List<ObserverUnitCustom> observerUnit = null;
	
	/**
	 * default constructor.
	 * Assigns its class name as name
	 */
	public Miscellaneous()
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
		
		this.notifyObserverUnitCustom();
	}
	
	@Override
	public final String getSelected()
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setList(List<String> list)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void addToList(String type)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean remove(String type)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<String> getList()
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Iterator<String> iterator()
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final Custom getSelectedCustom(int selected)
	{
		return this.type.get(this.selected);
	}
	
	@Override
	public void setListCustom(List<Custom> list)
	{
		this.type = list;
	}
	
	@Override
	public void addToListCustom(Custom custom)
	{
		if(this.type == null)
		{
			this.type = new ArrayList<Custom>();
		}
		
		this.type.add(custom);
		
		this.notifyObserverUnitCustom();
	}
	
	@Override
	public boolean remove(Custom custom)
	{
		boolean successful = false;
		
		if(this.type != null)
		{
			for(Iterator<Custom> i=this.type.iterator(); i.hasNext();)
			{
				Custom custom2 = i.next();
				
				if(custom2.getName().equals(custom.getName()))
				{
					i.remove();
					
					successful = true;
					
					this.notifyObserverUnitCustom();
				}
			}
		}
		
		return successful;
	}
	
	@Override
	public List<Custom> getListCustom()
	{
		return this.type;
	}
	
	@Override
	public Iterator<Custom> iteratorCustom()
	{
		return this.type.iterator();
	}

	@Override
	public void notifyObserverUnit()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void registerObserverUnit(ObserverUnit observer, String name)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void notifyObserverUnitCustom()
	{
		if(this.observerUnit != null)
		{
			for(Iterator<ObserverUnitCustom> i=this.observerUnit.iterator(); i.hasNext();)
			{
				ObserverUnitCustom observer = i.next();
				
				observer.updateUnitCustom(this.type, this.getName());
			}
		}
	}
	
	@Override
	public void removeObserverUnit(ObserverUnit observer, String name)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void registerObserverUnitCustom(ObserverUnitCustom observer, String name)
	{
		if(this.observerUnit == null)
		{
			this.observerUnit = new ArrayList<ObserverUnitCustom>();
		}
		
		this.observerUnit.add(observer);
		
		this.notifyObserverUnitCustom();
	}

	@Override
	public void removeObserverUnitCustom(ObserverUnitCustom observer, String name)
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
}