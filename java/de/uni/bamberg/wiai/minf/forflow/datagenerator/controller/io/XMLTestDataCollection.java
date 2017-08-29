package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.Facet;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.GeneratedArtefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * This class has the job of saving the generated test data collection
 * as a physical XML file to the file system. Do accomplish this, the 
 * class makes use of the <i>JDOM</i> technology. That means, the whole
 * collection is build in-memory before it is serialized. A backdraw
 * of this approach is, of course, the intensive use of memory.
 * The more test data to save the memory assumptions. A better approach
 * would be to save each test data separate at the time when created, but
 * this isn't straightforward.
 * </p>
 * <font size=6><b>JDOM</b></font></br>
 * JDOM is open source, tree-based and pure Java for parsing, creating,
 * manipulating and serializing XML documents. Like <i>DOM</i> it represents
 * XML documents as a tree composed of elements, attributes, comments, text nodes
 * and so forth. JDOM can access any part of the tree at any time. All kinds
 * of nodes in the tree are represented by concrete classes rather than interfaces.
 * </p>
 * JDOM doesn't itself include a parser. Instead it depends on a SAX parser to parse
 * documents and build JDOM models from them. Xerces parser is bundled with JDOM.
 * However, it can work with equally well with any SAX2 compliant parser.
 * </p>
 * A JDOM tree is fully read-write. All parts of the tree can be moved, deleted
 * and added to. Unlike <i>DOM</i>, there're no annoying read-only sections of
 * the tree you can't change.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/16/09
 */
public class XMLTestDataCollection
{
	/**
	 * sole default constrcutor
	 */
	public XMLTestDataCollection()
	{
	}
	
	/**
	 * serializes the generated test data collection to
	 * the given path. It builds an in-memory XML document
	 * first based on the <i>JDOM</i> technology. Then the
	 * whole XML file is written out as XML. The success
	 * of the process, building the XML and saving is indicated
	 * by a flag which is returned. If <i>true</i> has been
	 * returned to the invoker everything is gone fine, otherwise
	 * <i>false</i> indicates that something is gone bad.
	 * In the latter case no physical XML file exists.
	 * 
	 * @param xml
	 * 		the path of the test data collection to save to.
	 * 		The collection is based on XML syntax.
	 * @param xsd
	 * 		the corresponding XSD file of XML file.
	 * @param collection
	 * 		the generated test data sets
	 * @return
	 * 		true, if successful
	 */
	public boolean save(URI xml, URI xsd, List<GeneratedArtefact> collection)
	{
		boolean successful = false;
		
		// here is the important part
		Document doc = this.makeDoc(collection);
		
		XMLOutputter xmlFile = new XMLOutputter();
		
		try
		{
			xmlFile.setFormat(Format.getPrettyFormat());
			
			xmlFile.output(doc, new FileOutputStream(new File(xml.toString())));
			
			// OK
			successful = true;
		}
		catch(FileNotFoundException fnfe)
		{
			ErrorMessage.getInstance().printMessage(fnfe,
													this.getClass().getName(),
													"FileNotFoundException");
		}
		catch(IOException ioe)
		{
			ErrorMessage.getInstance().printMessage(ioe,
													this.getClass().getName(),
													"IOException");
		}
		
		return successful;
	}
	
	/**
	 * builds up the whole XML file structure in-memory with
	 * the <i>JDOM</i> technology.
	 * 
	 * @param collection
	 * 		the generated test data collection
	 * @return
	 * 		JDOM document. This is the XML file to save physically.
	 */
	private Document makeDoc(List<GeneratedArtefact> collection)
	{
		Document doc = new Document();
		
		Element dataGenerator = new Element("DataGenerator");
		Element testDataCollection = new Element("TestDataCollection");
		Element documents = new Element("Documents");
		Element products = new Element("Products");
		
		int d = 0;
		int p = 0;
		
		for(int i=0; i<collection.size(); i++)
		{
			GeneratedArtefact ga = collection.get(i);
			
			if(ga.getArtefactType().getName().equals("Documents"))
			{
				d++;
			}
			
			else if(ga.getArtefactType().getName().equals("Products"))
			{
				p++;
			}
		}
		
		// documents amount
		Attribute att_amount_d = new Attribute("amount", String.valueOf(d));
		
		documents.setAttribute(att_amount_d);
		
		// products amount
		Attribute att_amount_p = new Attribute("amount", String.valueOf(p));
		
		products.setAttribute(att_amount_p);
		
		// create hierarchy
		doc.addContent(dataGenerator);
		dataGenerator.addContent(testDataCollection);
		testDataCollection.addContent(documents);
		testDataCollection.addContent(products);
		
		// iterate through generated test data
		for(Iterator<GeneratedArtefact> i=collection.iterator(); i.hasNext();)
		{
			GeneratedArtefact ga = i.next();
			
			// documents
			if(ga.getArtefactType().getName().equals("Documents"))
			{
				Element element_document = new Element("Document");
				
				element_document.setAttribute("name", ga.getName());
				
				element_document.setAttribute("id", ga.getID());
				
				if(ga.getDummyFile() != null)
				{
					if(!ga.getDummyFile().getPathWithFileName().equals(""))
					{
						element_document.setAttribute("path", ga.getDummyFile().getPathWithFileName());
					}
				}
				
				// iterate through facets
				for(Iterator<Facet> j=ga.iterator(); j.hasNext();)
				{
					Facet facet = j.next();
					
					Element element_facet = new Element("Facet");
					
					element_facet.setAttribute("name", facet.getName());
					
					if(!facet.getValueAndUnit().equals(""))
					{
						element_facet.setText(facet.getValueAndUnit());
					}
					
					// add
					if(!element_facet.getValue().equals(""))
					{
						element_document.addContent(element_facet);
					}
				}
				
				documents.addContent(element_document);
			}
			
			// products
			else if(ga.getArtefactType().getName().equals("Products"))
			{
				Element element_product = new Element("Product");
				
				element_product.setAttribute("name", ga.getName());
				
				element_product.setAttribute("id", ga.getID());
				
				// iterate through facets
				for(Iterator<Facet> j=ga.iterator(); j.hasNext();)
				{
					Facet facet = j.next();
					
					Element element_facet = new Element("Facet");
					
					element_facet.setAttribute("name", facet.getName());
					
					element_facet.setText(facet.getValueAndUnit());
					
					// add
					if(!element_facet.getValue().equals(""))
					{
						element_product.addContent(element_facet);
					}
				}
				
				products.addContent(element_product);
			}
		}
		
		return doc;
	}
}