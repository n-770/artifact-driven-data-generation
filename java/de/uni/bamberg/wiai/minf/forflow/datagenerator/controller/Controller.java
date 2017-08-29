package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ObserverDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.PRNG;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.distribution.ProbabilityDistribution;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.Generator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype.ObserverGenerator;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.ObservableUnit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.ObservableUnitCustom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.Model;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.Artefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ObservableArtefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;

/**
 * <font size=6><b>Controller</b></font></br>
 * <i>Controller</i> interface provides the methods
 * to get access to the controller and model layer.
 * </p>
 * It is not permitted to access the model layer directly.
 * Instead one has to access the model via the controller layer.
 * The controller has all informations about how to access
 * the model layer. 
 *
 * @author Michael Munz
 * @version 0.1
 * @since Mar/24/09
 */
public interface Controller extends ObservableArtefact,
									ObservableUnit,
									ObservableUnitCustom
{
	/**
	 * Using the EMF persistence framework enables us
	 * to load a XMI serialized form of UML models,
	 * called Ecore.
	 * </p>
	 * Notice, the <i>Ecore</i> meta model lays outside the
	 * system and is loaded into it using the EMF persistance framework.
	 *
	 * @param uri
	 * 		the URI specifies the path to a *.ecore file
	 * return
	 * 		meta model
	 */
	public EPackage loadEcoreModel(URI uri);
	
	/**
	 * @see Controller#loadEcoreModel(URI)
	 * 
	 * @param uri
	 * 		URI.toString as input parameter
	 * @return
	 * 		<i>ecore</i> meta model in-memory
	 */
	public EPackage loadEcoreModel(String uri);
	
	/**
	 * sets the <i>ecore</i> meta model into {@link Model} layer.
	 * 
	 * @param ePackage
	 */
	public void setEcoreModel(EPackage ePackage);
	
	/**
	 * gets the <i>ecore</i> meta model from the {@link Model} layer.
	 * 
	 * @return
	 * 		ecore in-memory 
	 */
	public EPackage getEcoreModel();
	
	/**
	 * print the current ecore model to console.
	 * Only for testing purposes.
	 * 
	 * @param ePackage
	 * 		the <i>ecore</i> meta model
	 */
	public void printEcoreModel(EPackage ePackage);
	
	/**
	 * this method is highly important because it loads a xml
	 * file at startup time.
	 * To indicate the failure of loading, an exception is thrown
	 * to the invoker. In this case, the exception has to be handled
	 * properly and the whole application is going to get shut down
	 * immediately.
	 * </p>
	 * It could also be, that the XML file is not valid.
	 * A valid file is described by a XSD file. Both files
	 * are shipped with the application.
	 * 
	 * @param xml
	 * 		a startup file needed at initializing phase.
	 * @param xsd
	 * 		XML Schema Definition, describing the XML.
	 * @throws
	 * 		if the application can't start properly.
	 * 		That happens when starup file is corrupted or invalid
	 * 		or other bad things have had happened. 
	 */
	public void loadStartupFile(java.net.URI xml, java.net.URI xsd);
	
	/**
	 * For each artefact type there exists one artefact
	 * collection holding all artefact elements of one type.
	 * </p>
	 * At any time, the following must hold:</br>
	 * <ul>
	 * 		<li>N_AT = number of artefact types</li>
	 * 		<li>N_A = number of artefacts</li>
	 * </ul>
	 * <code>N_AT = N_A</code>
	 * 
	 * @param artefact
	 * 		a new artefact collection
	 */
	public void addArtefact(Artefact artefact);
	
	/**
	 * gets the artefacts data structure from the model layer.
	 * That could be a whole bunch of different artefacts.
	 * With this method all are returned.
	 * 
	 * @return
	 * 		artefact object
	 */
	public List<Artefact> getArtefacts();
	
	/**
	 * gets an artefact collection holding a whole bunch
	 * of classes. It's only a subset of the complete artefacts,
	 * because we splitted the set in disjunct artefact types.
	 * Those types are initially available or they are added
	 * by the tester. 
	 * 
	 * @param type
	 * 		tells which subset of artefacts to get
	 * @return
	 * 		artefact object defined by the type or null
	 */
	public Artefact getArtefact(ArtefactType type);
	
	/**
	 * tries to find a meta class with a given name.
	 * The type helps to determine the collection where
	 * to start the search.
	 * 
	 * @param name
	 * 		looking for a meta class by name
	 * @param type
	 * 		determines the collection to look for
	 * @return
	 * 		meta class with given name or null
	 */
	public MetaClass getMetaClass(String name, ArtefactType type);
	
	/**
	 * gets an iterator for artefacts.
	 * 
	 * @return
	 * 		iterator
	 */
	public Iterator<Artefact> iteratorArtefacts();
	
	/**
	 * adds one artefact of a particular type to the
	 * data structure.
	 * To avoid duplicate artefacts, it's run a check first.
	 * 
	 * @param artefact
	 * 		has to be one of the supported types of artefacts.
	 * @return
	 * 		true, if operation has been successful.
	 */
	public boolean addToArtefact(MetaClass artefact);
	
	/**
	 * <font size=6>
	 * 		<b>This method is one of the most important!</b></br>
	 * 		This is the place to go, when the data structure of
	 * 		the EMF Ecore Model is initially build up.
	 * </font>
	 * </p>
	 * It adds a list of available artefacts into the data structure.
	 * This method is supposed to be called, when the user has chosen
	 * the initial list of artefacts.
	 * </p>
	 * Based on that list the data structure is build up.
	 * 
	 * @param selection
	 * 		the list of artefacts the user has chosen or selected respectively.
	 * @param view
	 * 		referece of main view. This is where most of the 
	 * 		components are nested. In other words, it's the view
	 * 		you see, when the application starts.
	 */
	public void createArtefacts(List<MetaClass> selection, DataGeneratorView view);
	
	/**
	 * before any data structre can be build up, the system needs
	 * to know which artefacts are used. That is especially important
	 * for any subsequent computation.
	 * </p>
	 * This method iterates throught the <i>Ecore</i> model and picks
	 * only the classes out of it. Those classes are then shown to the
	 * user which in turn has to select artefacts to generate test data. 
	 * 
	 * @param ePackage
	 * 		Ecore Model
	 * @return
	 * 		list of meta classes describing the available artefacts
	 */
	public List<MetaClass> getArtefacts(EPackage ePackage);
	
	/**
	 * adds an new artefact type to the list of supported
	 * types. Remember, those types are highly important, because
	 * they match loaded EMF data to a particular collection.
	 * Each single data is adhered to a artefact type and separate
	 * different types.
	 * At all times, there're exactly as much different collections
	 * as artefact types.
	 * 
	 * @param type
	 * 		new artefact type
	 */
	public void addArtefactType(ArtefactType type);
	
	/**
	 * adds an new artefact type to the list of supported
	 * types. Remember, those types are highly important, because
	 * they match loaded EMF data to a particular collection.
	 * Each single data is adhered to a artefact type and separate
	 * different types.
	 * At all times, there're exactly as much different collections
	 * as artefact types.
	 * 
	 * @param name
	 * 		a string defines the new artefact type
	 * @param createDummyFile
	 * 		this flag is used to indicate, whether or not physical
	 * 		dummy files are desired.
	 */
	public void addArtefactType(String name, boolean createDummyFile);
	
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
	 * tries to find an artefact type by a given name.
	 * The method doesn't do a containing check.
	 * If you wanna be sure that this type is really
	 * available, use the following method first:</br>
	 * {@link #hasArtefactType(String)}
	 * 
	 * @param name
	 * 		the name of an artefact type 
	 * @return
	 * 		the artefact type or null
	 */
	public ArtefactType getArtefactType(String name);
	
	/**
	 * removes an artefact type from the available artefact type
	 * list in model layer and its corresponding artefact collection.
	 * The latter step is necessary, because the number of artefact types
	 * and the number of artefact collections are always the same.
	 * It wouldn't make much sense to advertise something we don't
	 * have.  
	 * 
	 * @param name
	 * 		the name of the artefact type given as a string
	 * @return
	 * 		true, if successfully removed
	 */
	public boolean removeArtefactType(String name);
	
	/**
	 * This method is like the above one, but with an artefact type
	 * as parameter.
	 * It removes an artefact type from the available artefact type
	 * list in model layer and its corresponding artefact collection.
	 * The latter step is necessary, because the number of artefact types
	 * and the number of artefact collections are always the same.
	 * It wouldn't make much sense to advertise something we don't
	 * have.
	 * 
	 * @param type
	 * 		artefact type to remove
	 * @return
	 * 		true, if successfully removed
	 */
	public boolean removeArtefactType(ArtefactType type);
	
	/**
	 * checks, whether an artefact type is supported or not.
	 * If this method finds a type with the given name as input, <i>true</i>
	 * is returned, otherwise <i>false</i>.
	 * 
	 * @param name
	 * 		looking for a artefact type with that name
	 * @return
	 * 		true, if artefact in model layer has been found
	 */
	public boolean hasArtefactType(String name);
	
	/**
	 * gets the iterator for artefact types.
	 * 
	 * @return
	 * 		iterator
	 */
	public Iterator<ArtefactType> iteratorArtefactType();
	
	/**
	 * keeps track of the amount of attributes above
	 * all artefact collections. This is a global number.
	 * Notice, each artefact collection has still its own counter.
	 * 
	 * @return
	 * 		number of attributes as a global amount
	 */
	public int getNoOfAttributes();
	
	/**
	 * keeps track of the number above artefact collections.
	 * This is a global number and each artefact collection
	 * has still its own counter.
	 * 
	 * @return
	 * 		number of classes (global)
	 */
	public int getNoOfClasses();
	
	/**
	 * The parsed artefact types of the startup.xml file.
	 * 
	 * @param artefactTypes
	 * 		types of this type
	 */
	public void parsedArtefactTypes(List<ArtefactType> artefactTypes);
	
	/**
	 * the job of this method is to set the logging
	 * flag. At the initial stage, the logging is
	 * enabled. This helps to track down any problems
	 * by examining the <i>log</i> file.
	 * Remember, this is a prototype, and it is recommended
	 * to log.
	 * </p>
	 * Because logging is time consuming and slows down the
	 * system performance, we log only when an exception or
	 * error occurs. If no unexpected things happen no <i>log</i>
	 * file will be created, but logging is still enabled.
	 * 
	 * @param logging
	 * 		should the system log if any unexpected exception
	 * 		is thrown?
	 */
	public void setLogging(boolean logging);
	
	/**
	 * gets the current setting for the logging mechanism.
	 * 
	 * @return
	 * 		if logging enabled <i>true</i> otherwise <i>false</i>
	 */
	public boolean isLogging();
	
	/**
	 * sets the pseudo random number generator (PRNG).
	 * If this method is used, the default PRNG is overwritten
	 * with this one. The value passed in comes from the
	 * setup.xml file at startup.
	 * </p>
	 * The default generator is MersenneTwister.
	 * 
	 * @param prng
	 * 		at startup the default PRNG can be defined to use
	 * 		for number generation. There are conventions on
	 * 		which one are available. The supported one are listed
	 * 		in {@link PRNG}.
	 * @see PRNG
	 */
	public void setPRNG(String prng);
	
	/**
	 * sets the pseudo random number generator (PRNG).
	 * If this method is used, the default PRNG is overwritten
	 * with this one. The value passed in comes from the
	 * setup.xml file at startup.
	 * </p>
	 * The default generator is MersenneTwister.
	 * 
	 * @param prng
	 * 		at startup the default PRNG can be defined to use
	 * 		for number generation. There are conventions on
	 * 		which one are available. The supported one are listed
	 * 		in {@link PRNG}.
	 * @see PRNG
	 */
	public void setPRNG(PRNG prng);
	
	/**
	 * gets the pseudo random number generator used to generate
	 * numbers. The default is MersenneTwister. But in setup.xml
	 * an alternative one could be defined to use instead.
	 * 
	 * @return
	 * 		PRNG
	 */
	public PRNG getPRNG();
	
	/**
	 * sets the unit list at once.
	 * This method is usually called, when
	 * the <i>startup.xml</i> file has been parsed.
	 * 
	 * @param units
	 * 		list of units
	 */
	public void setUnits(List<Unit> units);
	
	/**
	 * adds an unit element to the list.
	 * This might happen, when testers
	 * add new once. 
	 * 
	 * @param unit
	 * 		a new unit element
	 */
	public void addUnit(Unit unit);
	
	/**
	 * gets the units as a whole.
	 * 
	 * @return
	 * 		list of units
	 */
	public List<Unit> getUnits();
	
	/**
	 * Returns an iterator over the elements
	 * in this list in proper sequence.
	 * 
	 * @return
	 * 		iterator
	 */
	public Iterator<Unit> iteratorUnit();
	
	/**
	 * sets the upper and lower dates as simple
	 * integers. The values are passed to the
	 * controller by the XML parser and the values
	 * come from the startup XML file.
	 * </p>
	 * Remember, because these are still integers,
	 * the controller does the conversion to
	 * date objects.
	 * </p>
	 * The method is used to be able to generate
	 * dates in an specific interval. The interval
	 * boundaries are defined by these values.
	 * It could be possible that the upper bound 
	 * is not specified clearly, that is the value
	 * is <i>0</i>. The controller will take in that
	 * case the current year.
	 * Whereas the lower bound of the date interval
	 * has to be specified at all times.
	 * 
	 * @param lower
	 * 		the lower bound of the date interval
	 * @param upper
	 * 		the upper bound of the date interval
	 */
	public void setRawDate(int lower, int upper);
	
	/**
	 * as the method name says, it creates the fill behaviour
	 * generators. Each time a generator is created and the
	 * observer is registered to it, the observable generator
	 * sends a notification to the UI. With this notification
	 * the UI registers the generator into a list which
	 * is then available to users.
	 * 
	 * @param observer
	 * 		observer is needed to register after creation of
	 * 		any generator. Remember, generators are fill behaviour.
	 */
	public void createAndRegisterGenerator(ObserverGenerator observer);
	
	/**
	 * it unregisters a generator from the list of available
	 * generators in UI. The result is, users won't be able
	 * to select this generator anymore.
	 * 
	 * @param generator
	 * 		a generator to dismiss from UI list.
	 * @param observer
	 * 		unregistration is initiated by observer
	 */
	public void unregisterGenerator(Generator generator, ObserverGenerator observer);
	
	/**
	 * as the method name already tells it creates the probability
	 * distributions and passes the observer to them to register.
	 * Any observer which needs to know which probability distributions
	 * are available in model layer needs to register to them. This is,
	 * because any layer above the controller <i>must</i> access via
	 * the controller layer.
	 * 
	 * @param observer
	 * 		it's usually a component in the view layer to get the latest
	 * 		updates on available probability distributions hanging around
	 * 		in the model layer.
	 */
	public void createAndRegisterDistribution(ObserverDistribution observer);
	
	/**
	 * of course, it's possible to unregister any probability distribution.
	 * Unregistering in terms of probability distributions means the controller
	 * removes them from the model layer. So the removed ones are no longer
	 * available in the model. Because the view layer doesn't know about that a
	 * change had happened, a notification has to be send out by the subject
	 * which is gonna be deleted. If notification doesn't happen or the view
	 * layer can't rely on that users have the choice to select unavailable
	 * probability distributions. And that's definitely not what's intended.
	 * 
	 * @param distribution
	 * 		this is the subject that is gonna be eliminated from the model
	 * 		layer. In other words, the subject has to make sure it's 
	 * 		corresponding object is also removed from the view layer.
	 * @param observer
	 * 		no notify the observer the controller passes the observer down.
	 */
	public void unregisterDistribution(ProbabilityDistribution distribution, ObserverDistribution observer);
}