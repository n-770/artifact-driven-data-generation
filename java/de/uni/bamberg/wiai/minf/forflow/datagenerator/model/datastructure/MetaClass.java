package de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <b>MetaClass</b> represents the <i>Ecore's</i> <i>EClass</i>.
 * A class has one or more attributes, which are represented in {@link MetaAttribute}.
 * That means each attribute is itself a class. The advantage of
 * this approach is that additional informations can be stored to each
 * attribute without affecting others. So each attribute can independently be
 * tuned.
 * </p>
 * This class provides only the default constructor. This is, because the values
 * must be set explicit with its corresponding methods. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public class MetaClass
{
	/**
	 * the name of the meta class
	 */
	private String name = null;
	
	/**
	 * here are the attributes of a meta class.
	 */
	private List<MetaAttribute> attributes = null;
	
	/**
	 * defines the type of artefact to which the meta class belongs.
	 * That's because, we hold the different types disjunct.
	 */
	private ArtefactType artefactType = null;
	
	/**
	 * indicating weather the class is derived or not
	 */
	private boolean hasSuperClass = false;
	
	/**
	 * the name of the super class.
	 * Only relevant, when a class is derived from another.
	 */
	private MetaClass superClass = null;
	
	/**
	 * indicates, whether this class is a concrete specification.
	 * Classes with the flag to <i>true</i> are not abstract terms
	 * of classification.
	 */
	private boolean isConcreteSpec = false;
	
	/**
	 * that's the amount of test data to generate.
	 * Quantities can only be set to concrete terms.
	 */
	private int quantity = 0;
	
	/**
	 * default constructor. It has restricted visibility.
	 * Use the supported methods to set values.
	 */
	protected MetaClass()
	{
	}
	
	/**
	 * constructor is passed the name of this meta class.
	 * 
	 * @param name
	 * 		the name of this class
	 */
	protected MetaClass(String name)
	{
		this.setName(name);
	}
	
	/**
	 * set the name of the meta class.
	 * 
	 * @param name
	 * 		name of the meta class.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * gets the name of the meta class.
	 * 
	 * @return
	 * 		String
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * sets the attributes as a list of objects for
	 * a meta class.
	 * 
	 * @param attributes
	 * 		list of {@link MetaAttribute}
	 */
	public void setAttributes(List<MetaAttribute> attributes)
	{
		this.attributes = attributes;
	}
	
	/**
	 * adds one attribute at a time to a meta class object.
	 * 
	 * @param attribute
	 */
	public void addAttriubte(MetaAttribute attribute)
	{
		if(this.attributes == null)
		{
			this.attributes = new ArrayList<MetaAttribute>();
		}
		
		this.attributes.add(attribute);
	}
	
	/**
	 * tries to find an attribute by a given name.
	 * If there exists one with that name it's returned,
	 * otherwise null.
	 * 
	 * @param name
	 * 		looking for a attribute by name
	 * @return
	 * 		meta attribute or null
	 */
	public MetaAttribute getAttribute(String name)
	{
		MetaAttribute res = null;
		
		for(int i=0; i<this.attributes.size(); i++)
		{
			MetaAttribute mAttribute = this.attributes.get(i);
			
			if(mAttribute.getName().equals(name))
			{
				res = mAttribute;
			}
		}
		
		return res;
	}
	
	/**
	 * removes an attribute from an meta class.
	 * Notice, once an attribute is removed from the data structure,
	 * it is impossible to restore it.
	 * 
	 * @param mAttribute
	 * 		attribute to delete
	 * @return
	 * 		true, if successfully deleted
	 */
	public boolean deleteAttribute(MetaAttribute mAttribute)
	{
		boolean deleted = false;
		
		int n = this.attributes.indexOf(mAttribute);
		
		if(n >= 0)
		{
			this.attributes.remove(n);
			
			deleted = true;
		}
		
		return deleted;
	}
	
	/**
	 * removes an attribute from this meta class by a given name.
	 * The data structure is searched through and if it was found
	 * it's removed. And to indicate this, true is returned.
	 * Otherwise false.
	 * Once it has been removed there's no way back. 
	 * 
	 * @param name
	 * 		attribute to delete
	 * @return
	 * 		true, if successfully deleted
	 */
	public boolean deleteAttribute(String name)
	{
		boolean deleted = false;
		
		for(int i=0; i<this.attributes.size(); i++)
		{
			MetaAttribute mAttriubte = this.attributes.get(i);
			
			if(mAttriubte.getName().equals(name))
			{
				this.attributes.remove(i);
				
				deleted = true;
			}
		}
		
		return deleted;
	}
	
	/**
	 * gets the list of {@link MetaAttribute}
	 * 
	 * @return
	 * 		List of attributes of a meta class.
	 */
	public List<MetaAttribute> getAttributes()
	{
		return this.attributes;
	}
	
	/**
	 * sets the type of artefact the meta class represents.
	 * At this time, there are only two disjunct types possible:
	 * <i>documents</i> and <i>products</i>.
	 * 
	 * @param artefactType
	 */
	public void setArtefactType(ArtefactType artefactType)
	{
		this.artefactType = artefactType;
	}
	
	/**
	 * gets the type of artefact for this meta class.
	 * 
	 * @return
	 * 		{@link ArtefactType}
	 */
	public ArtefactType getArtefactType()
	{
		return this.artefactType;
	}
	
	/**
	 * sets the flag of this class to concrete specification.
	 * It indicates that this class is a leaf in UML diagram.
	 * </p>
	 * Example:</p>
	 * <pre>
	 *                         <i>Artefact</i>
	 *                         /      \
	 *                        /        \
	 *                     <i>Product</i>   <i>Document</i>
	 *                     /    \     /    \
	 *                    /      \   /      \
	 *                 Screw    <b>Nut</b> <b>CAD</b>    <b>ScopeOfStatement</b>
	 *                  /
	 *             <b>SquareHeadBold</b>
	 * </pre>
	 * In the above example are those concrete specifications in <b>bold</b>
	 * characters, that is a leaf in terms of a tree.
	 */
	public void setToConcreteSpec()
	{
		this.isConcreteSpec = true;
	}
	
	/**
	 * indicates, whether it is a concrete specification
	 * of an abstract term of classification.
	 * 
	 * @return
	 * 		true, if and only if this class is a concrete term.
	 */
	public boolean isConcreteSpec()
	{
		return this.isConcreteSpec;
	}
	
	/**
	 * checks, whether this class inherits behaviour
	 * of a base class
	 * 
	 * @return
	 * 		true, if a base class exists
	 */
	public boolean hasSuperClass()
	{
		return this.hasSuperClass;
	}
	
	/**
	 * sets the name of the super class.
	 * 
	 * @param superClass
	 */
	public void setSuperClass(MetaClass superClass)
	{
		this.superClass = superClass;
		this.hasSuperClass = true;
	}
	
	/**
	 * gets the name of the super class
	 * 
	 * @return
	 * 		String
	 */
	public MetaClass getSuperClass()
	{
		return this.superClass;
	}
	
	/**
	 * determines the amount of test data
	 * to generate for this class.
	 * Only concrete terms can have this attitude.
	 * 
	 * @param quantitiy
	 * 		number of test sets to generate
	 */
	public void setQuantity(int quantitiy)
	{
		if(this.isConcreteSpec())
		{
			this.quantity = quantitiy;
		}
	}
	
	/**
	 * the amount of test data sets to
	 * generate for this meta class.
	 * Only concrete terms can have a quantity
	 * greater than <i>0</i>.
	 * 
	 * @return
	 * 		how many test sets are gonna be generated?
	 */
	public final int getQuantity()
	{
		return this.quantity;
	}
	
	/**
	 * returns the number of attributes this class has.
	 * 
	 * @return
	 * 		attribute size
	 */
	public int noOfAttributes()
	{
		return this.attributes.size();
	}
	
	/**
	 * Returns an iterator over the elements in this list in proper sequence. 
	 * 
	 * @return
	 * 		iterator for attributes
	 */
	public Iterator<MetaAttribute> iterator()
	{
		return this.attributes.iterator();
	}
	
	/**
	 * Two meta classes are said to be equal, if
	 * there names are the same.
	 * This is because in an UML diagram can only
	 * one class with a given name exist at a time.
	 * Otherwise it would be ambiguous. 
	 */
	@Override
	public boolean equals(Object ob) throws ClassCastException
	{
		if(ob instanceof MetaClass)
		{
			MetaClass mClass = (MetaClass) ob;
			
			if(this.getName().equals(mClass.getName()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			throw new ClassCastException("MetaClass object expected");
		}
	}
	
	/**
	 * prints the name of the class and when the
	 * artefact type is set also its type name.
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(this.name);
		
		if(this.artefactType != null)
		{
			sb.append(" (" +this.artefactType.getName() +")");
		}
		
		return sb.toString();
	}
}