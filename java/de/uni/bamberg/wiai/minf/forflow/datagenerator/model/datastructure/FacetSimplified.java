package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

/**
 * This class represents a simplified version of {@link MetaAttribute}.
 * It has only attributes important for doing a special kind of job.
 * The class is used only for specifying <i>null</i> values and therefore
 * we do not make much use of the full attribute list.
 * </p>
 * It holds the attribute name of the original meta attribute class, it's
 * class name where it belongs and a flag indicating the <i>null</i> value
 * status.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/05/09
 */
public class FacetSimplified
{
	/**
	 * represents the name of the meta attribute's class.
	 */
	private String attributeName = null;
	
	/**
	 * here is the name of the meta class this attribute
	 * belongs to.
	 */
	private String className = null;
	
	/**
	 * a flag indicating whether null values are
	 * allowed or not.
	 * This is an important setting used later in test
	 * data generation process.
	 */
	private boolean isNullAllowed = false;
	
	/**
	 * a flag indicating whether multiple values are
	 * allowed to generated for this attribute.
	 */
	private boolean isMultipleValueAllowed = false;
	
	/**
	 * defines the artefact type.
	 */
	private ArtefactType type = null;
	
	/**
	 * sole default constructor
	 */
	protected FacetSimplified()
	{
	}
	
	/**
	 * sets the name of the meta attribute's class name.
	 * Because this is only a simplified version of this class,
	 * we borrow that name.
	 * 
	 * @param name
	 * 		the name of the meta attribute.
	 */
	public void setAttributeName(String name)
	{
		this.attributeName = name;
	}
	
	/**
	 * gets the name of the meta attribute class.
	 * Because this is only a simplified version of this class,
	 * we borrow that name.
	 * 
	 * @return
	 * 		name of meta attribute class
	 */
	public final String getAttributeName()
	{
		return this.attributeName;
	}
	
	/**
	 * set the name of the class, the original meta attribute
	 * belongs to.
	 * 
	 * @param name
	 * 		name of meta class
	 */
	public void setClassName(String name)
	{
		this.className = name;
	}
	
	
	/**
	 * gets the name of the meta class the original
	 * meta attriubte class belongs to.
	 * Always remember, this is only a simplified version,
	 * but reflects the same data.
	 * 
	 * @return
	 * 		class name of original meta attribute class
	 */
	public final String getClassName()
	{
		return this.className;
	}
	
	/**
	 * a flag inidcating whether <i>null</i> values
	 * are allowed later in test data generation process.
	 * Because, most times it's not usefull to allow it, 
	 * it's set to <i>false</i> as default setting.
	 * 
	 * @param allowed
	 * 		null values allowed in data generation?
	 */
	public void isNullAllowed(boolean allowed)
	{
		this.isNullAllowed = allowed;
	}
	
	/**
	 * a flag inidcating whether <i>null</i> values
	 * are allowed later in test data generation process.
	 * Because, most times it's not usefull to allow it, 
	 * it's set to <i>false</i> as default setting.
	 * 
	 * @return
	 * 		are null values allowed?
	 */
	public final boolean isNullAllowed()
	{
		return this.isNullAllowed;
	}
	
	/**
	 * a flag indicating, whether multiple values are
	 * allowed to generated later in test data generation process. 
	 * 
	 * @param allowed
	 * 		multiple values allowed? 
	 */
	public void isMultipleValueAllowed(boolean allowed)
	{
		this.isMultipleValueAllowed = allowed;
	}
	
	/**
	 * a flag indicating, whether multiple values are
	 * allowed to generated later in test data generation process. 
	 * </p>
	 * Default setting is <i>false</i>
	 * 
	 * @return
	 * 		setting enabled?
	 */
	public final boolean isMultipleValueAllowed()
	{
		return this.isMultipleValueAllowed;
	}
	
	/**
	 * sets the type of artefact.
	 * The type specifies the subset of this artefact.
	 * 
	 * @param type
	 * 		artefact type
	 */
	public void setArtefactType(ArtefactType type)
	{
		this.type = type;
	}
	
	/**
	 * gets the type of artefact this attribute
	 * belongs to.
	 * An artefact type specifies a subset of available
	 * artefacts.
	 * 
	 * @return
	 * 		artefact type
	 */
	public final ArtefactType getArtefactType()
	{
		return this.type;
	}
}