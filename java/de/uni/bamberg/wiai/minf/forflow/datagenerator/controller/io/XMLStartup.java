package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.ValidationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.ControllerFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * This class makes use of the <b>SAX2</b> parser to deserialize startup XML file
 * and uses <b>JDOM</b> to serialize updates on any XML file used by the application.
 * </p>
 * <font size=6><b>SAX</b></font></br>
 * SAX is the <i>Simple API for XML</i> and is a push API.
 * In other words, the parser tells when it finds things
 * in a XML document. Here's how SAX addresses the concerns:
 * </p>
 * <ul>
 * 		<li>SAX doesn't build an in-memory tree</li>
 * 		<li>SAX parser doesn't create any objects.
 * 			Of course you have the option to do so, but that's
 * 			not the parsers decision.
 * 		</li>
 * 		<li>SAX starts sending events immediately.
 * 			One doesn't have to wait for the parser to finish
 * 			reading a document.
 * 		</li>
 * </ul>
 * </p>
 * We use SAX to load an XML file at the startup phase.
 * In this file we're going to call it <i>startup.xml</i> holds
 * some important informations the system needs while running.
 * Of course, this file is meant to be extendable.
 * </p>
 * <font size=6><b>JDOM</b></font></br>
 * For modifying any existing XML file at runtime, we use <b>JDOM</b>. 
 * JDOM (Java Document Object Model) was created to be Java-specific and
 * thereby take advantage of Java's features, including method overloading
 * and collections.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/16/09
 */
public class XMLStartup
{
	/**
	 * sole default constructor
	 */
	public XMLStartup()
	{
	}
	
	/**
	 * In context of XML, validation involves writing a detailed
	 * specification for document's content in any of several schema
	 * languages, such as XSD or DTD. We expect to use XSD's.
	 * Validation is performed usually before any further processing
	 * of input takes place. So we have the change to reject any invalid
	 * document.
	 * Validation reports whether a document (XML file) adheres to the
	 * rules specified by a schema (XSD).
	 * 
	 * @param xml
	 * 		URI of the XML file to load at startup
	 * @param xsd
	 * 		URI of the XML Schema Definition file.
	 * 		This file describes the form of XML files we expect to load.
	 * @throws ValidationException
	 * 		thrown to indicate that something in the startup XML file
	 * 		is not valid. The file has to be valid at any time it is parsed
	 * 		by the system. With this exception we make sure of that.
	 * 		We don't parse invalid files, because that would lead to unpredictable
	 * 		system behaviour.
	 */
	public void loadStartUp(URI xml, URI xsd) throws ValidationException
	{
		boolean happendAnyBadThing = false;
		
		if(this.isValid(xml, xsd))
		{
			try
			{
				XMLReader reader = XMLReaderFactory.createXMLReader();
				
				XMLStartupHandler customizedHandler = new XMLStartupHandler();
				
				reader.setContentHandler(customizedHandler);
				reader.setErrorHandler(customizedHandler);
				
				reader.parse(xml.toString());
				
				// pass results to controller
				ControllerFactory.getController().parsedArtefactTypes(customizedHandler.getArtefactType());
				
				ControllerFactory.getController().setLogging(customizedHandler.getLogging());
				
				ControllerFactory.getController().setPRNG(customizedHandler.getPRNG());
				
				ControllerFactory.getController().setUnits(customizedHandler.getUnits());
				
				ControllerFactory.getController().setRawDate(customizedHandler.getDateMin(),
															 customizedHandler.getDateMax());
			}
			catch(SAXException saxe)
			{
				happendAnyBadThing = true;
			}
			catch(IOException ioe)
			{
				happendAnyBadThing = true;
			}
		}
		else
		{
			happendAnyBadThing = true;
		}
		
		if(happendAnyBadThing)
		{
			throw new ValidationException("Something bad has happend to startup file!");
		}
	}
	
	/**
	 * serializes the current document object in-memory to
	 * the specified destination.
	 * 
	 * @param xml
	 * 		old XML file and new XML file are in most
	 * 		cases the same. As a result of that is gonna be
	 * 		overwritten
	 * @param xsd
	 * 		the corresponding XSD file of XML file.
	 */
	private void save(URI xml, URI xsd, Document doc)
	{
		XMLOutputter xmlFile = new XMLOutputter();
		
		try
		{
			xmlFile.setFormat(Format.getPrettyFormat());
			
			xmlFile.output(doc, new FileOutputStream(new File(xml)));
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
	}
	
	/**
	 * Updates an existing XML file. Well in most cases, it's
	 * the startup XML file. This file is synchronized with the
	 * user interactions. When the tester adds or deletes elements
	 * in UI and those elements are needed at startup we synch it
	 * to the file. This is a convenient way to the tester to update
	 * XML files.
	 * 
	 * @param xml
	 * 		path to XML startup file.
	 * 		Usually it's called something like <i>startup.xml</i>.
	 * @param xsd
	 * 		path to startup's XSD file.
	 * 		While updating an existing XML 
	 * @param adding
	 * 		there two options possible: adding or removing the element
	 * 		if this flag is set to <i>true</i> is added to XML file.
	 * 		In the other case it's meant to remove it from XML file.
	 * 		</p>
	 * 		<i>true</i> = adding </br>
	 * 		<i>false</i> = removing
	 * @param tagElement
	 * 		defines, which part of the XML file to consider
	 * @param content
	 * 		here is the content to add to file.
	 * 		It is only important by adding enabled. Otherwise it's gonna skipped.
	 */
	public void update(URI xml, URI xsd, boolean adding, String tagElement, Object content)
	{
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = null;
		
		try
		{
			doc = builder.build(xml.toURL());
		}
		catch(JDOMException jdome)
		{
			ErrorMessage.getInstance().printMessage(jdome,
													this.getClass().getName(),
													"JDOMException");
		}
		catch(MalformedURLException murle)
		{
			ErrorMessage.getInstance().printMessage(murle,
													this.getClass().getName(),
													"MalformedURLException");
		}
		catch(IOException ioe)
		{
			ErrorMessage.getInstance().printMessage(ioe,
													this.getClass().getName(),
													"IOException");
		}
		
		Element root = doc.getRootElement();
		
		// add
		if(adding)
		{
			this.adding(doc, tagElement, content, root);
		}
		
		// remove
		else
		{
			this.removing(doc, tagElement, content, root);
		}
		
		this.save(xml, xsd, doc);
	}
	
	/**
	 * method adds a new element to the startup XML file.
	 * It's a convenient method to add new elements without
	 * modifying the XML file directly. Instead this is done
	 * by the system.
	 * 
	 * @param doc
	 * 		It's the XML file as a document
	 * @param tagElement
	 * 		defines which XML element to add new content to.
	 * @param content
	 * 		here's the actual content to add.
	 * @param start
	 * 		because we have to look out for the element defined
	 * 		by <i>xmlType</i> parameter, it's possible to do recursion.
	 * 		And that's the starting point for every recursion.
	 */
	@SuppressWarnings("unchecked")
	private void adding(Document doc, String tagElement, Object content, Element start)
	{
		List<Element> children = start.getChildren();
		
		for(int i=0; i<children.size(); i++)
		{
			Element child = children.get(i);
			
			// found
			if(child.getName().equals(tagElement))
			{
				// <Artefact>
				if(tagElement.equals(XMLStartupTag.ARTEFACT))
				{
					// <Type>
					Element toAdd = new Element(XMLStartupTag.TYPE);
					
					ArtefactType type = (ArtefactType) content;
					
					Attribute att_name = new Attribute("name", type.getName());
					
					Attribute att_dummyFile = new Attribute("createDummyFile",
															String.valueOf(type.isDummyFileAllowed()));
					
					toAdd.setAttribute(att_name);
					toAdd.setAttribute(att_dummyFile);
					
					child.addContent(toAdd);
				}
				
				// <Logging>
				else if(tagElement.equals(XMLStartupTag.LOGGING))
				{
					child.setText(content.toString());
				}
			}
			
			// search recursive
			else
			{
				this.adding(doc, tagElement, content, child);
			}
		}
	}
	
	/**
	 * removes an element from the startup file.
	 * It's a convenient way to get rid off of elements without
	 * using any XML editor. Notice, only elements defined in
	 * this startup are able to be deleted or added.
	 * 
	 * @param doc
	 * 		this is the XML file as a document object
	 * @param tagElement
	 * 		the type of element or tag to look out for changing
	 * @param content
	 * 		that's the content we want get rid off
	 * @param start
	 * 		because we browse through the XML file recursive we need
	 * 		a starting point each time.
	 */
	@SuppressWarnings("unchecked")
	private void removing(Document doc, String tagElement, Object content, Element start)
	{
		List<Element> children = start.getChildren();
		
		for(int i=0; i<children.size(); i++)
		{
			Element child = children.get(i);
			
			// found
			if(child.getName().equals(tagElement))
			{
				// <Artefact>
				if(tagElement.equals(XMLStartupTag.ARTEFACT))
				{
					// <Type>
					if(this.hasElement(child.getChildren(), content))
					{
						Element toRemove = this.getElement(child.getChildren(), content);
						
						child.removeContent(toRemove);
					}
				}
				
				// put other here
			}
			
			// search recursive
			else
			{
				this.removing(doc, tagElement, content, child);
			}
		}
	} 
	
	/**
	 * checks whether a XML type is in a list of XML elements.
	 * 
	 * @param children
	 * 		XML elements. These elements are also called tags.
	 * @param content
	 * 		that's the content we're looking for in the list.
	 * @return
	 * 		true, if found otherwise false will be returned
	 */
	private boolean hasElement(List<Element> children, Object content)
	{
		boolean has = false;
		
		for(int i=0; i<children.size(); i++)
		{
			Element child = children.get(i);
			
			if(child.getText().equals(content.toString()))
			{
				has = true;
			}
		}
		
		return has;
	}
	
	/**
	 * gets an element directly by iterating through a list of elements
	 * of a base child.
	 * 
	 * @param children
	 * 		list of sub-children of a element
	 * @param content
	 * 		here's the content we're looking for
	 * @return
	 * 		the desired element or null
	 */
	private Element getElement(List<Element> children, Object content)
	{
		Element res = null;
		
		for(int i=0; i<children.size(); i++)
		{
			Element child = children.get(i);
			
			if(child.getText().equals(content.toString()))
			{
				res = child;
			}
		}
		
		return res;
	}
	
	/**
	 * Before the <i>startup.xml</i> file is loaded, it has to be check
	 * whether it is valid or not. This is done with {@link Validator}.
	 * Validaton reports whether a document adheres to the rules specified by
	 * the schema.
	 * </p>
	 * It helps to check quickly that input is in the form one expects and
	 * rejects any document that is too far away from what is handled.
	 * 
	 * @param xml
	 * 		URI of XML file to load at startup
	 * @param xsd
	 * 		URI of XML Schema Defnition file, describing the XML file.
	 * @return
	 * 		true, if validation process has been successful.
	 */
	private boolean isValid(URI xml, URI xsd)
	{
		boolean isValid = false;
		
		// factory of W3C XSD
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		try
		{
			// compile schema
			Schema schema = factory.newSchema(new File(xsd));
			
			// get validator from schema
			Validator validator = schema.newValidator();
			
			// parse the document to check
			Source source = new StreamSource(new File(xml));
			
			// check document
			validator.validate(source);
			
			isValid = true;
		}
		catch(SAXException saxe)
		{
			ErrorMessage.getInstance().printMessage(saxe,
													this.getClass().getName(),
													"SAXException");
		}
		catch(IOException ioe)
		{
			isValid = false;
			
			ErrorMessage.getInstance().printMessage(ioe,
													this.getClass().getName(),
													"IOException");
		}
		
		return isValid;
	}
}