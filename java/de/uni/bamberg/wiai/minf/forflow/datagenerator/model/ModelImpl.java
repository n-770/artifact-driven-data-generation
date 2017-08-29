package de.uni.bamberg.wiai.minf.forflow.datagenerator.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Generator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.Model;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;

/**
 * This class of the Model layer is the concrete implementation of
 * its interface {@link Model}.
 * </p>
 * Here are the data structures stored which are used in the system to
 * reach the goal of test data generation.
 * To be always up to date with the top-layer, that is the view, the model
 * is observable. Whenever a change in the data occurs, it is notified to
 * its observers and displayed at the view right after the change.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Mar/24/09
 */
public class ModelImpl implements Model
{
	/**
	 * the meta model of the <i>Ecore</i> file
	 */
	private EPackage resource = null;
	
	/**
	 * here is the artefact object.
	 * It has all supported artefact types gathered
	 * at one place.
	 * </p>
	 * The internal structure is as follows:</br>
	 * each artefact type has it's own set
	 */
	private List<Artefact> artefacts = null;
	
	/**
	 * holds all artefact types known by the system.
	 */
	private List<ArtefactType> artefactTypes = null;
	
	/**
	 * Holds the set of available units. Initial
	 * units can be found in the startup XML file.
	 */
	private List<Unit> units = null;
	
	/**
	 * here are the available pre-defined generators
	 */
	private List<Generator> generators = null;
	
	/**
	 * all available probability distributions are here
	 */
	private List<ProbabilityDistribution> distributions = null;
	
	/**
	 * default constructor. Its visibility is restricted
	 * to same package.
	 */
	protected ModelImpl()
	{
	}

	@Override
	public EPackage getResource()
	{
		return this.resource;
	}

	@Override
	public void setResource(EPackage ePackage)
	{
		this.resource = ePackage;
	}

	@Override
	public List<Artefact> getArtefacts()
	{
		return this.artefacts;
	}
	
	@Override
	public Artefact getArtefact(ArtefactType artefactType)
	{
		Artefact artefact = null;
		
		for(Iterator<Artefact> i=this.iteratorArtefact(); i.hasNext();)
		{
			Artefact arte = i.next();
			
			if(arte.getArtefactType().getName().equals(artefactType.getName()))
			{
				artefact = arte;
			}
		}
		
		return artefact;
	}
	
	@Override
	public Iterator<Artefact> iteratorArtefact()
	{
		return this.artefacts.iterator();
	}

	@Override
	public void addArtefact(Artefact artefact)
	{
		boolean alreadyInIt = false;
		
		if(this.artefacts == null)
		{
			this.artefacts = new ArrayList<Artefact>();
		}
		
		// check if already in it
		if(!this.artefacts.isEmpty())
		{
			for(Iterator<Artefact> i=this.iteratorArtefact(); i.hasNext();)
			{
				Artefact arte = i.next();
				
				if(arte.getArtefactType().getName().equals(artefact.getArtefactType().getName()))
				{
					alreadyInIt = true;
				}
			}
		}
		
		if(!alreadyInIt)
		{
			this.artefacts.add(artefact);
		}
	}
	
	@Override
	public void addArtefactType(ArtefactType artefactType)
	{
		if(this.artefactTypes == null)
		{
			this.artefactTypes = new ArrayList<ArtefactType>();
		}
		
		if(!this.hasArtefactType(artefactType))
		{
			this.artefactTypes.add(artefactType);
		}
	}
	
	@Override
	public ArtefactType getArtefactType(String name)
	{
		ArtefactType res = null;
		
		for(Iterator<ArtefactType> i=this.artefactTypes.iterator(); i.hasNext();)
		{
			ArtefactType type = i.next();
			
			if(type.getName().equals(name))
			{
				res = type;
			}
		}
		
		return res;
	}
	
	@Override
	public Iterator<ArtefactType> iteratorArtefactType()
	{
		return this.artefactTypes.iterator();
	}
	
	@Override
	public List<ArtefactType> getArtefactTypes()
	{
		return this.artefactTypes;
	}
	
	/**
	 * keeps track of total meta classes over
	 * all artefact collections.
	 * Note, this number is in model layer.
	 * But there are also counter in the collections
	 * itself.
	 * 
	 * @return
	 * 		number of classes (global)
	 */
	public int getNoOfClassesInTotal()
	{
		int number = 0;
		
		for(Iterator<Artefact> i=this.iteratorArtefact(); i.hasNext();)
		{
			Artefact artefact = i.next();
			
			number += artefact.noOfClasses();
		}
		
		return number;
	}
	
	/**
	 * backtracks the total amount of facets hold in data structure.
	 * Note, this number is in the model layer.
	 * And do not confuse it with the counter in artefact
	 * collections.
	 * 
	 * @return
	 * 		number of attributes (global)
	 */
	public int getNoOfAttributesInTotal()
	{
		int number = 0;
		
		for(Iterator<Artefact> i=this.iteratorArtefact(); i.hasNext();)
		{
			Artefact artefact = i.next();
			
			number += artefact.noOfAttributes();
		}
		
		return number;
	}
	
	@Override
	public void setUnits(List<Unit> units)
	{
		this.units = units;
	}
	
	@Override
	public void addUnit(Unit unit)
	{
		if(this.units == null)
		{
			this.units = new ArrayList<Unit>();
		}
		
		this.units.add(unit);
	}
	
	@Override
	public List<Unit> getUnits()
	{
		return this.units;
	}
	
	@Override
	public Iterator<Unit> iteratorUnits()
	{
		return this.units.iterator();
	}
	
	@Override
	public void addGenerator(Generator generator)
	{
		if(this.generators == null)
		{
			this.generators = new ArrayList<Generator>();
		}
		
		this.generators.add(generator);
	}
	
	@Override
	public void removeGenerator(Generator generator)
	{
		if(this.generators != null)
		{
			int n = this.generators.indexOf(generator);
			
			if(n >= 0)
			{
				this.generators.remove(n);
			}
		}
	}
	
	@Override
	public void addProbabilityDistribution(ProbabilityDistribution distribution)
	{
		if(this.distributions == null)
		{
			this.distributions = new ArrayList<ProbabilityDistribution>();
		}
		
		this.distributions.add(distribution);
	}
	
	@Override
	public void removeProbabilityDistribution(ProbabilityDistribution distribution)
	{
		if(this.distributions != null)
		{
			int n = this.distributions.indexOf(distribution);
			
			if(n >= 0)
			{
				this.distributions.remove(n);
			}
		}
	}
	
	/**
	 * only for debugging and testing purposes.
	 */
	public void print()
	{
		for(Iterator<Artefact> i=this.iteratorArtefact(); i.hasNext();)
		{
			Artefact artefact = i.next();
			
			artefact.print();
		}
	}
	
	/**
	 * checks, if a new artefact type is already in
	 * collection.
	 * 
	 * @param type
	 * 		new artefact type
	 * @return
	 * 		true, if already in collection
	 */
	private boolean hasArtefactType(ArtefactType type)
	{
		boolean has = false;
		
		for(Iterator<ArtefactType> i=this.iteratorArtefactType(); i.hasNext();)
		{
			ArtefactType at = i.next();
			
			if(at.getName().equals(type.getName()))
			{
				has = true;
			}
		}
		
		return has;
	}
}