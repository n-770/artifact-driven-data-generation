package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;


/**
 * <b>ArtefactFactory</b> class uses the <i>Factory</i> pattern
 * to create data structure objects.
 * </p>
 * Factories handle the details of object creation.
 * That means, this class has the job of creating objects related
 * to {@link Artefact}s.
 * </p>
 * Please use for object creation the static methods. It won't be
 * possible to use the constructor.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public class ArtefactFactory
{
	/**
	 * default constructor is not visible to outside world.
	 */
	private ArtefactFactory()
	{
	}
	
	/**
	 * create an artefact object.
	 * 
	 * @return
	 * 		{@link Artefact}
	 */
	public static final Artefact createArtefact()
	{
		return new Artefact();
	}
	
	/**
	 * creates a attribute object for a class.
	 * 
	 * @return
	 * 		{@link MetaAttribute}
	 */
	public static final MetaAttribute createMetaAttribute()
	{
		return new MetaAttribute();
	}
	
	/**
	 * creates a class, which is derived from artefacts.
	 * 
	 * @return
	 * 		{@link MetaClass}
	 */
	public static final MetaClass createMetaClass()
	{
		return new MetaClass();
	}
	
	/**
	 * creates a new class with the given name.
	 * The name is passed to the constructor of the destination
	 * class.
	 * 
	 * @param name
	 * 		the name of the class to create
	 * @return
	 * 		meta class
	 */
	public static final MetaClass createMetaClass(String name)
	{
		return new MetaClass(name);
	}
	
	/**
	 * creates a new and empty ArtefactType object.
	 * 
	 * @return
	 * 		artefact type
	 */
	public static final ArtefactType createArtefactType()
	{
		return new ArtefactTypeImpl();
	}
	
	/**
	 * creates a new artefact type object and returns
	 * the object to the invoker. The parameter is passed
	 * down to the destination class.
	 * 
	 * @param name
	 * 		the name of the new artefact type
	 * @return
	 * 		artefact type
	 */
	public static final ArtefactType createArtefactType(String name)
	{
		return new ArtefactTypeImpl(name);
	}
	
	/**
	 * creates a new artefact type object and returns
	 * the object to the invoker. The parameter is passed
	 * down to the destination class.
	 * 
	 * @param name
	 * 		the name of the new artefact type
	 * @param dummyFile
	 * 		tells, whether it is allowed to create a dummy file
	 * 		to this kind of type. This depends highly on the context.
	 * @return
	 * 		artefact type
	 */
	public static final ArtefactType createArtefactType(String name, boolean dummyFile)
	{
		return new ArtefactTypeImpl(name, dummyFile);
	}
	
	/**
	 * creates a new and empty FacetSimplified object.
	 * This kind uses a sub-set of the whole meta attribute class
	 * and is used to do a special kind of job.
	 * If you want to have full functionality, please use {@link MetaAttribute}
	 * class instead.
	 * 
	 * @return
	 * 		simplified facet object to mirror special meta-attribute object
	 * 		attributes.
	 */
	public static final FacetSimplified createFacetSimplified()
	{
		return new FacetSimplified();
	}
	
	/**
	 * creates an data type object. It can hold all primitive types
	 * and some pre-defined complex ones, like <i>String</i> or <i>Date</i>.
	 * </p>
	 * To model other complex data types modeled by programmers, any
	 * name can be used. But those are not considered in test data generation.
	 * 
	 * @return
	 * 		data type object
	 */
	public static final DataType createDataType(String dataType)
	{
		return new DataTypeImpl(dataType);
	}
}