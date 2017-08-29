package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

/**
 * Because we wanna build up a generic system we also need the artefact types
 * generic. As a result of that constraint, we can't use an enumeration.
 * To work around that hard coded enumeration we make use of this class.
 * </p>
 * For each artefact type supported we make an instance. The types are pre-defined
 * in an external startup XML file which has to be parsed.
 * </p>
 * <font size=6><b>Example</b></font></br>
 * The startup file looks something like that:
 * <pre>
 * < system>
 *   < startup>
 *     < artefact>
 *       < type><font color=red>type 1 supported</font>< /type>
 *       < type><font color=red>type 2 supported</font>< /type>
 *       ...
 *     < /artefact>
 *   < /startup>
 * < /system>
 * </pre>
 * The types here defined are later found in the UI.
 * When it comes to loading EMF Ecore model files the tester has 
 * the choice to link the data with one of this artefact types.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public interface ArtefactType
{
	/**
	 * sets a flag, to tell, whether it is allowed to create
	 * a dummy file to this kind of artefact type. Not all types
	 * should have a dummy file, because it wouldn't make sense,
	 * to do so.
	 * </p>
	 * <font size=6><b>Example</b></font>
	 * </p>
	 * Suppose you've got the artfact types <i>documents</i> and
	 * <i>products</i>. To <i>document</i> artefacts it is desired,
	 * to create dummy files to reflect the generated test data properly.
	 * But is is not desired to do so for <i>product</i> artefacts. 
	 * It is impossible to create a dummy file for example to a <i>refrigerator</i>.
	 * </p>
	 * With this flag it is easier to tell the data generation process the needs.
	 * 
	 * @param dummy
	 * 		is it allowed for this kind of type to create a dummy file.
	 */
	public void isDummyFileAllowed(boolean dummy);
	
	/**
	 * sets a flag, to tell, whether it is allowed to create
	 * a dummy file to this kind of artefact type. Not all types
	 * should have a dummy file, because it wouldn't make sense,
	 * to do so.
	 * </p>
	 * <font size=6><b>Example</b></font>
	 * </p>
	 * Suppose you've got the artfact types <i>documents</i> and
	 * <i>products</i>. To <i>document</i> artefacts it is desired,
	 * to create dummy files to reflect the generated test data properly.
	 * But is is not desired to do so for <i>product</i> artefacts. 
	 * It is impossible to create a dummy file for example to a <i>refrigerator</i>.
	 * </p>
	 * With this flag it is easier to tell the data generation process the needs.
	 * 
	 * @return
	 * 		true, if and only if there such a thing like a dummy file possible.
	 */
	public boolean isDummyFileAllowed();
	
	/**
	 * sets the name of the artefact type supported.
	 * Artefac types are loaded during initializing phase of
	 * the system. They're loaded before the UI is shown.
	 * When it comes to load EMF Ecore model files, the tester
	 * has the choice to link the data to one of the artefact types.
	 * </p>
	 * Notice, artefact types separate the data into disjunct sets of testing data.
	 * 
	 * @param artefactType
	 * 		name of an artefact type
	 */
	public void setName(String artefactType);
	
	/**
	 * gets the name of this artefact type.
	 * 
	 * @return
	 * 		name of artefact.
	 */
	public String getName(); 
}