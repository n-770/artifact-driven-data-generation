package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm.GenerateUniqueID;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.MetaClass;

/**
 * This class holds the artefacts ready to get serialized
 * into a XML file. Artefacts stored in here passed the
 * test data generation process and and wait to get written
 * out. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/13/09
 */
public class GeneratedArtefact
{
	/**
	 * the name of the artefact
	 */
	private String name = null;
	
	/**
	 * an unique ID generated on Java's
	 * UUID.
	 */
	private UUID id = null;
	
	/**
	 * dummy file of this generated artefact
	 */
	private DummyFile dummyFile = null;
	
	/**
	 * artefact type. This is needed to separate
	 * the different types in XML file.
	 */
	private ArtefactType type = null;
	
	/**
	 * list of facets for this artefact.
	 * Here are all facets an artfact can have,
	 * inclusive inherited ones. This is different
	 * to {@link MetaClass}.
	 */
	private List<Facet> facets = null;
	
	/**
	 * default constructor generates an unique ID.
	 */
	public GeneratedArtefact()
	{
		this.id = GenerateUniqueID.getUniqueID();
	}
	
	/**
	 * constructor is passed the name of this artefact.
	 * At creation time of this object an unique ID
	 * is assigned too.
	 * 
	 * @param name
	 * 		the name of this artefact
	 */
	public GeneratedArtefact(String name)
	{
		this.name = name;
		this.id = GenerateUniqueID.getUniqueID();
	}
	
	/**
	 * sets the name of this artefact.
	 * It the name of the MetaClass.
	 * 
	 * @param name
	 * 		artefact name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * gets the name of this artefact
	 * 
	 * @return
	 * 		string
	 */
	public final String getName()
	{
		return (this.name == null) ? "" : this.name;
	}
	
	/**
	 * gets the uniquee ID of this artefact.
	 * Each artefact has one and is set at the time
	 * the object is created. It can't be set by
	 * hand.
	 * 
	 * @return
	 * 		UUID
	 */
	public final String getID()
	{
		return (this.id == null) ? "" : this.id.toString();
	}
	
	/**
	 * sets the path to the dummy file.
	 * The path consists of file name + file extension.
	 * Only documents do have this.
	 * 
	 * @param dummyFile
	 * 		path to dummy file
	 */
	public void setDummyFile(DummyFile dummyFile)
	{
		this.dummyFile = dummyFile;
	}
	
	/**
	 * sets the path to dummy file.
	 * sets the path to the dummy file.
	 * The path consists of file name + file extension.
	 * Only documents do have this.
	 * 
	 * @param path
	 * 		path to dummy file
	 * @param fileName
	 * 		the name of the file
	 */
	public void setDummyFile(URI path, String fileName)
	{
		if(this.dummyFile == null)
		{
			this.dummyFile = new DummyFile(path, fileName);
		}
		else
		{
			this.dummyFile.setPath(path);
			this.dummyFile.setFileName(fileName);
		}
	}
	
	/**
	 * sets the path to dummy file.
	 * sets the path to the dummy file.
	 * The path consists of file name + file extension.
	 * Only documents do have this.
	 * 
	 * @param path
	 * 		path to dummy file
	 * @param fileName
	 * 		the name of the file
	 * @param fileFormat
	 * 		the file extension
	 */
	public void setDummyFile(URI path, String fileName, String fileFormat)
	{
		if(this.dummyFile == null)
		{
			this.dummyFile = new DummyFile(path, fileName, fileFormat);
		}
		else
		{
			this.dummyFile.setPath(path);
			this.dummyFile.setFileName(fileName);
			this.dummyFile.setFileFormat(fileFormat);
		}
	}
	
	/**
	 * sets the path to dummy file.
	 * sets the path to the dummy file.
	 * The path consists of file name + file extension.
	 * Only documents do have this.
	 * 
	 * @param path
	 * 		path to dummy file
	 * @param fileName
	 * 		the name of the file
	 */
	public void setDummyFile(String path, String fileName)
	{
		if(this.dummyFile == null)
		{
			this.dummyFile = new DummyFile(path, fileName);
		}
		else
		{
			this.dummyFile.setPath(path);
			this.dummyFile.setFileName(fileName);
		}
	}
	
	/**
	 * sets the path to dummy file.
	 * sets the path to the dummy file.
	 * The path consists of file name + file extension.
	 * Only documents do have this.
	 * 
	 * @param path
	 * 		path to dummy file
	 * @param fileName
	 * 		the name of the file
	 * @param fileFormat
	 * 		the file extension
	 */
	public void setDummyFile(String path, String fileName, String fileFormat)
	{
		if(this.dummyFile == null)
		{
			this.dummyFile = new DummyFile(path, fileName, fileFormat);
		}
		else
		{
			this.dummyFile.setPath(path);
			this.dummyFile.setFileName(fileName);
			this.dummyFile.setFileFormat(fileFormat);
		}
	}
	
	/**
	 * gets the path to dummy file.
	 * Each artefact generated gets an dummy file
	 * with the same name in the file name as 
	 * the artfact.
	 * </p>
	 * Notice, only documents do have a path to a
	 * persisted file.
	 * 
	 * @return
	 * 		path to dummy file
	 */
	public final DummyFile getDummyFile()
	{
		return (this.dummyFile != null) ? this.dummyFile : null;
	}
	
	/**
	 * sets the artefact type. This is important
	 * to keep different artfacts separated.
	 * 
	 * @param type
	 * 		artefact type
	 */
	public void setArtefactType(ArtefactType type)
	{
		this.type = type;
	}
	
	/**
	 * tells to which artefact type this artefact belongs
	 * 
	 * @return
	 * 		artefact type
	 */
	public final ArtefactType getArtefactType()
	{
		return this.type;
	}
	
	/**
	 * sets the facets at once.
	 * 
	 * @param facets
	 * 		list of facets
	 */
	public void setFacets(List<Facet> facets)
	{
		this.facets = facets;
	}
	
	/**
	 * adds one facet to the list
	 * 
	 * @param facet
	 * 		a facet is an attribute this artefact has
	 */
	public void addFacet(Facet facet)
	{
		if(this.facets == null)
		{
			this.facets = new ArrayList<Facet>();
		}
		
		this.facets.add(facet);
	} 
	
	/**
	 * gets the facets
	 * 
	 * @return
	 * 		list of facets to this artefact
	 */
	public final List<Facet> getFacets()
	{
		return this.facets;
	}
	
	/**
	 * Returns an iterator over the elements
	 * in this list in proper sequence.
	 * 
	 * @return
	 * 		iterator
	 */
	public Iterator<Facet> iterator()
	{
		return this.facets.iterator();
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(this.getName() +" ");
		sb.append(this.getID() +" ");
		sb.append(this.getDummyFile() +"\n");
		
		for(Iterator<Facet> i=this.iterator(); i.hasNext();)
		{
			sb.append("\t" +i.next());
		}
		
		return sb.toString();
	}
}