package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.net.URI;

import javax.swing.SwingWorker;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.XMLStartup;

/**
 * To reflect the user interactions with the system data structure and
 * its serialized data we update that synchronized.
 * In other word, if something has been added or deleted by user interaction
 * it is automatically reflected in the serialized data structure.
 * </p>
 * Deleting or adding artefact, for example causes such an update in the 
 * <i>startup.xml</i> file.
 * Because this is I/O bound task, we do this in background.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/19/09
 */
public class XMLUpdater extends SwingWorker<Void, Void>
{
	/**
	 * path to XML startup file
	 */
	private URI xml = null;
	
	/**
	 * path to XSD file of startup file
	 */
	private URI xsd = null;
	
	/**
	 * XML knows how to handle the startup file
	 */
	private XMLStartup xmlHandler = null;
	
	/**
	 * flag, indicating one of two cases:
	 * adding or removing
	 */
	private boolean adding = false;
	
	/**
	 * defines which part of the XML file to consider
	 */
	private String tagElement = null;
	
	/**
	 * when new elements are added to the file
	 * the content is important.
	 */
	private Object content = null;
	
	/**
	 * runs an update on the XML startup file to reflect the user actions.
	 * The method is usually invoked, when a change in the data structure
	 * is proposed. Adding or deleting artefact types leads to a change in
	 * the structure for example.
	 * </p>
	 * Notice, only relevant elements to the file are synched, not everything. 
	 * 
	 * @param xml
	 * 		path to XML startup file
	 * @param xsd
	 * 		path to XML's XSD file
	 * @param xmlHandler
	 * 		class which handles the action to reflect the change in the file
	 * @param adding
	 * 		indicates whether to add or to remove something from the XML file
	 * @param tagElement
	 * 		to get a clue where to make the change in the XML file structure.
	 * 		XMLType refers to a XMl element tag used within.
	 * @param content
	 * 		here's the object when it comes to add something new to the file.
	 * 		Together with XMLType controller knows which class type it is, when
	 * 		casting is done.
	 * 		</p>
	 * 		When parameter <i>adding</i> is false it means an element is gonna be
	 * 		removed from the file. In that case <i>content</i> parameter defines
	 * 		content to remove. 
	 */
	public XMLUpdater(URI xml, URI xsd, XMLStartup xmlHandler, boolean adding, String tagElement, Object content)
	{
		this.xml = xml;
		this.xsd = xsd;
		this.xmlHandler = xmlHandler;
		this.adding = adding;
		this.tagElement = tagElement;
		this.content = content;
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		this.xmlHandler.update(this.xml, this.xsd, this.adding, this.tagElement, this.content);
		
		return null;
	}
}