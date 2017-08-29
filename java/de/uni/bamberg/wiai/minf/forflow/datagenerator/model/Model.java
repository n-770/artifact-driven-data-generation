package de.uni.bamberg.wiai.minf.forflow.datagenerator.model;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Generator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;

/**
 * <b>Model</b> interface is the signature for a particular implementation
 * of the model. The listed methods have to be supported at least.
 * And to notify the view layer that data has changed, we also provide
 * an observable model.
 * </p>
 * Because the model is only observable, it doesn't mean we have allow direct
 * access to the model layer. But with notification we tell the view, that it
 * has to get the latest updates via the controller layer.
 * </p>
 * Notification goes from the model directly to the view. But on the other side,
 * the view has only access via the controller.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Mar/24/09
 */
public interface Model
{
	/**
	 * here is the <i>Ecore</i> meta model stored.
	 * Which is loaded from outside the system.
	 * We parse it via the EMF Framework and build our
	 * own model out of that.
	 * 
	 * @param ePackage
	 * 		EMF Ecore meta model
	 */
	public void setResource(EPackage ePackage);
	
	/**
	 * gets the <i>Ecore</i> meta model.
	 * 
	 * @return
	 * 		EPackage
	 */
	public EPackage getResource();
	
	/**
	 * sets the artefact, which is a customized model
	 * of the EMF <i>Ecore</i> model. We use that to
	 * generate test data sets, because it's easier to
	 * extend and to modify.
	 * </p>
	 * Notice, that this is a wrapper for all artefacts.
	 * It holds all supported types at one place, but in
	 * disjunct sets.
	 * 
	 * @param artefact
	 * 		creates a new artefact set, which holds all
	 * 		artefacts of one type.
	 */
	public void addArtefact(Artefact artefact);
	
	/**
	 * gets the artefacts data structure which is a customized model
	 * of the EMF <i>Ecore</i> model. We use that to
	 * generate test data sets, because it's easier to
	 * extend and to modify.
	 * </p>
	 * Notice, that this is a wrapper for all artefacts.
	 * It holds all supported types at one place, but in
	 * disjunct sets.
	 * 
	 * @return
	 * 		all artefacts
	 */
	public List<Artefact> getArtefacts();
	
	/**
	 * gets an collection of artefacts by specifying the type.
	 * That's possible, because each artefact type has it's own
	 * collection of artefacts.
	 * 
	 * @param artefactType
	 * 		the type of artefacts to access
	 * @return
	 * 		collection of artefacts specified by type
	 */
	public Artefact getArtefact(ArtefactType artefactType);
	
	/**
	 * gets an iterator for artefacts.
	 * 
	 * @return
	 * 		iterator of artefacts.
	 */
	public Iterator<Artefact> iteratorArtefact();
	
	/**
	 * sets an new artefact type into model layer.
	 * They're later displayed, when EMF Ecore models
	 * loaded and the data has to be linked with an artefact type.
	 * </p>
	 * Artefact types split up the set of test data. 
	 * 
	 * @param artefactType
	 * 		holds the name of an artefact type
	 */
	public void addArtefactType(ArtefactType artefactType);
	
	/**
	 * tries to find an artefact type by name.
	 * If its found it's returned, otherwise null.
	 * 
	 * @param name
	 * 		name of artefact type
	 * @return
	 * 		artefact type or null
	 */
	public ArtefactType getArtefactType(String name);
	
	/**
	 * Returns an iterator over the elements in this
	 * list in proper sequence. 
	 * 
	 * @return
	 * 		iterator of artefact types.
	 */
	public Iterator<ArtefactType> iteratorArtefactType();
	
	/**
	 * gets the list of supported artefact types.
	 * At any time there are as much collections as
	 * artefact types.
	 * 
	 * @return
	 * 		list of artefact types.
	 */
	public List<ArtefactType> getArtefactTypes();
	
	/**
	 * backtracks the total amount of facets hold in data structure.
	 * Note, this number is in the model layer.
	 * And do not confuse it with the counter in artefact
	 * collections.
	 * 
	 * @return
	 * 		number of attributes (global)
	 */
	public int getNoOfAttributesInTotal();
	
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
	public int getNoOfClassesInTotal();
	
//	public void 
	
	/**
	 * sets the units. An initial set of
	 * units can be found in the <i>startup.xml</i> file.
	 * 
	 * @param units
	 * 		list of units
	 */
	public void setUnits(List<Unit> units);
	
	/**
	 * adds an unit element to the list.
	 * 
	 * @param unit
	 * 		unit element
	 */
	public void addUnit(Unit unit);
	
	/**
	 * gets the list of units
	 * 
	 * @return
	 * 		list of units
	 */
	public List<Unit> getUnits();
	
	/**
	 * Returns an iterator over the elements in this
	 * list in proper sequence.
	 * 
	 * @return
	 * 		iterator for units
	 */
	public Iterator<Unit> iteratorUnits();
	
	/**
	 * adds a generator to the model layer.
	 * This method is called by controller layer.
	 * 
	 * @param generator
	 * 		a generator to store in model layer.
	 */
	public void addGenerator(Generator generator);
	
	/**
	 * removes a generator from the data structure.
	 * This happens usually, when the controller
	 * unregister a generator.
	 * 
	 * @param generator
	 * 		a generator is removed, when controller
	 * 		unregisters it.
	 */
	public void removeGenerator(Generator generator);
	
	/**
	 * adds a new probability distribution to the model layer.
	 * Note, this method can only be called by controller layer.
	 * 
	 * @param distribution
	 * 		a probability distribution to add
	 */
	public void addProbabilityDistribution(ProbabilityDistribution distribution);
	
	/**
	 * removes a probability distribution from the model layer.
	 * 
	 * @param distribution
	 * 		probability distribution to take off from list.
	 */
	public void removeProbabilityDistribution(ProbabilityDistribution distribution);
	
	/**
	 * only for debugging and testing purposes.
	 * </p>
	 * With this method the complete set of artefacts are
	 * printed to standard console.
	 */
	public void print();
}