package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

/**
 * This is the implementation class of {@link ArtefactType}.
 * </p>
 * Because we wanna build up a generic system we also need
 * the artefact types generic. As a result of that constraint,
 * we can't use an enumeration. To work around that hard coded
 * enumeration we make use of this class.
 * For each artefact type supported we make an instance.
 * The types are pre-defined in an external startup XML file
 * which has to be parsed. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public class ArtefactTypeImpl implements ArtefactType
{
	/**
	 * defines an artefact type
	 */
	private String artefactType = null;
	
	/**
	 * flag, telling whether there is a dummy file
	 * attached to this kind of type.
	 */
	private boolean createDummyFile = false;
	
	/**
	 * sole default constructor
	 */
	protected ArtefactTypeImpl()
	{
	}
	
	/**
	 * constructor is passed the name of this
	 * artefact type.
	 * 
	 * @param artefactType
	 * 		name of an artefact type
	 */
	public ArtefactTypeImpl(String artefactType)
	{
		this.artefactType = artefactType;
	}
	
	/**
	 * constructor is passed the name of the artefact type
	 * and a flag for handling dummy files.
	 * 
	 * @param artefactType
	 * 		name of an artefact type
	 * @param createDummyFile
	 * 		tells, whether it is allowed to create a dummy file
	 * 		to this kind of type. This depends highly on the context.
	 */
	public ArtefactTypeImpl(String artefactType, boolean createDummyFile)
	{
		this.artefactType = artefactType;
		this.createDummyFile = createDummyFile;
	}
	
	@Override
	public void setName(String artefactType)
	{
		this.artefactType = artefactType;
	}
	
	@Override
	public final String getName()
	{
		return this.artefactType;
	}
	
	@Override
	public void isDummyFileAllowed(boolean dummy)
	{
		this.createDummyFile = dummy;
	}
	
	@Override
	public boolean isDummyFileAllowed()
	{
		return this.createDummyFile;
	}
	
	@Override
	public String toString()
	{
		return this.getName();
	}
}