package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Currency;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Custom;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Length;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Mass;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Math;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Miscellaneous;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Temperature;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Time;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.unit.Unit;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactFactory;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.model.datastructure.ArtefactType;

/**
 * The job of this class is to extend the default handler
 * for SAX events and is an customized event handler class.
 * </p>
 * <font size=6><b>SAX Event Handler</b></font></br>
 * The SAX API defines a number of events. With this class
 * we make sure to handle those events properly.
 * </p>
 * Notice, that SAX event are stateless events. In other words,
 * you can't look at an event and figure out its context.
 * </p>
 * The events we use here are:
 * </p>
 * <ul>
 * 		<li>startElement()</li>
 * 		<li>endElement()</li>
 * 		<li>characters()</li>
 * </ul>
 * For more information about these events have a look
 * at the method descriptions below.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/17/09
 */
public class XMLStartupHandler extends DefaultHandler
{
	/**
	 * here are the parsed artefact types.
	 */
	private List<ArtefactType> artefactTypes = null;
	
	/**
	 * holds the different units, that's weight
	 * and measurements.
	 */
	private List<Unit> units = null; 
	
	/**
	 * indicates whether logging is enabled.
	 * This comes from the startup XML file,
	 * so do not mix it up with the logging tag flag
	 * used below.
	 */
	private boolean isLoggingEnabled = false;
	
	/**
	 * holds the parsed random number generator.
	 * The controller checks, if it is a valid one.
	 */
	private String prng = null;
	
	/**
	 * the lower bound of dates.
	 * At this state it is still an simple integer.
	 * The controller does the conversion to date
	 * object.
	 */
	private int date_min = 0;
	
	/**
	 * the upper bound of dates.
	 * It is here a simple integer, the conversion
	 * to a date object does the controller itself.
	 */
	private int date_max = 0;
	
	/**
	 * indicates when <i>Artefact</i> has been parsed
	 */
	private boolean artefact = false;
	
	/**
	 * flag, telling when SAX parser parses <i>Type</i>
	 */
	private boolean type = false;
	
	/**
	 * indicates when <i>Logging</i> tag has been found
	 */
	private boolean logging = false;
	
	/**
	 * flag, states the <i>Date</i> tag has found.
	 */
	private boolean date = false;
	
	/**
	 * tells when the <i>Min</i> tag of <i>Date</i> has been found. 
	 */
	private boolean date_lowerBound = false;
	
	/**
	 * flag, telling when <i>Max</i> tag of <i>Date</i> has been parsed.
	 */
	private boolean date_upperBound = false;
	
	/**
	 * flag, telling when <i>PRNG</i> tag has found.
	 */
	private boolean rng = false;
	
	/**
	 * flag, telling when <i>Default</i> tag of PRNG has been found.
	 */
	private boolean rng_default = false;
	
	/**
	 * flag, telling when <i>Unit</i> has been parsed.
	 */
	private boolean unit = false;
	
	/**
	 * tells when SAX parser finds <i>Currency</i> element
	 */
	private boolean currency = false;
	
	/**
	 * indicates the <i>Length</bi> tag
	 */
	private boolean length = false;
	
	/**
	 * indicates when <i>Mass</i> has been parsed
	 */
	private boolean mass = false;
	
	/**
	 * indicates when <i>Math</i> has been found
	 */
	private boolean math = false;
	
	/**
	 * tells that <i>Temperature</i> tag has been found
	 */
	private boolean temperature = false;
	
	/**
	 * tells when <i>Time</i> tag has been parsed
	 */
	private boolean time = false;
	
	/**
	 * tells that SAX parser has <i>Miscellaneous</i> has found
	 */
	private boolean misc = false;
	
	/**
	 * indicates the <i>Custom</i> tag
	 */
	private boolean custom = false;
	
	/**
	 * indicates the <i>Name</i> tag
	 */
	private boolean name = false;
	
	/**
	 * indicates the <i>Content</i> tag
	 */
	private boolean content = false;
	
	/**
	 * temporary unit element
	 */
	private Unit unitTemp = null;
	
	/**
	 * temp misc object
	 */
	private Miscellaneous miscTemp = null;
	
	/**
	 * temporary custom element
	 */
	private Custom customTemp = null;
	
	/**
	 * sole default constructor only visible to package
	 */
	protected XMLStartupHandler()
	{
	}
	
	/**
	 * the SAX driver will signal the start of an element and passes
	 * some parameters to the method.
	 * 
	 * @param uri
	 * 		the namespace URI.
	 * @param localName
	 * 		the unqualified name of the element
	 * @param name
	 * 		the qualified name of the element.
	 * 		This is the namespace prefix combined with the local
	 * 		name of the element.
	 * @param attributes
	 * 		An object, contains all of the attributes for an element.
	 * 		This object provides several methods to get the name and value
	 * 		of its attributes, along with the number of attributes the
	 * 		element has.
	 */
	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
	{
		// <Artefac>
		if(localName.equals(XMLStartupTag.ARTEFACT))
		{
			this.artefactTypes = new ArrayList<ArtefactType>();
			
			this.artefact = true;
		}
		
		// <Type>
		else if(localName.equals(XMLStartupTag.TYPE) && this.artefact)
		{
			this.type = true;
			
			ArtefactType type = ArtefactFactory.createArtefactType();
			
			for(int i=0; i<attributes.getLength(); i++)
			{
				if(attributes.getLocalName(i).equals("name"))
				{
					type.setName(attributes.getValue(i));
				}
				
				else if(attributes.getLocalName(i).equals("createDummyFile"))
				{
					type.isDummyFileAllowed(Boolean.valueOf(attributes.getValue(i)));
				}
			}
			
			this.artefactTypes.add(type);
		}
		
		// <PRNG>
		else if(localName.equals(XMLStartupTag.PRNG))
		{
			this.rng = true;
		}
		
		// <PRNG> <Default>
		else if(localName.equals(XMLStartupTag.PRNG_DEFAULT))
		{
			this.rng_default = true;
		}
		
		// <Logging>
		else if(localName.equals(XMLStartupTag.LOGGING))
		{
			this.logging = true;
		}
		
		// <Date>
		else if(localName.equals(XMLStartupTag.DATE))
		{
			this.date = true;
		}
		
		// Date <Min>
		else if(localName.equals(XMLStartupTag.DATE_MIN))
		{
			this.date_lowerBound = true;
		}
		
		// Date <Max>
		else if(localName.equals(XMLStartupTag.DATE_MAX))
		{
			this.date_upperBound = true;
		}
		
		// <Unit>
		else if(localName.equals(XMLStartupTag.UNIT))
		{
			this.units = new ArrayList<Unit>();
			
			this.unit = true;
		}
		
		// <Unit> <Type>
		else if(localName.equals(XMLStartupTag.TYPE) && this.unit)
		{
			this.type = true;
		}
		
		// <Currency>
		else if(localName.equals(XMLStartupTag.CURRENCY))
		{
			this.unitTemp = new Unit(new Currency());
			
			this.currency = true;
		}
		
		// <Length>
		else if(localName.equals(XMLStartupTag.LENGTH))
		{
			this.unitTemp = new Unit(new Length());
			
			this.length = true;
		}
		
		// <Mass>
		else if(localName.equals(XMLStartupTag.MASS))
		{
			this.unitTemp = new Unit(new Mass());
			
			this.mass = true;
		}
		
		// <Math>
		else if(localName.equals(XMLStartupTag.MATH))
		{
			this.unitTemp = new Unit(new Math());
			
			this.math = true;
		}
		
		// <Temperature>
		else if(localName.equals(XMLStartupTag.TEMPERATURE))
		{
			this.unitTemp = new Unit(new Temperature());
			
			this.temperature = true;
		}
		
		// <Time>
		else if(localName.equals(XMLStartupTag.TIME))
		{
			this.unitTemp = new Unit(new Time());
			
			this.time = true;
		}
		
		// <Misc>
		else if(localName.equals(XMLStartupTag.MISCELLANEOUS))
		{
			this.unitTemp = new Unit();
			this.miscTemp = new Miscellaneous();
			
			this.misc = true;
		}
		
		// <Custom>
		else if(localName.equals(XMLStartupTag.CUSTOM))
		{
			this.customTemp = new Custom();
			
			this.custom = true;
		}
		
		// <Name>
		else if(localName.equals(XMLStartupTag.NAME))
		{
			this.name = true;
		}
		
		// <Content>
		else if(localName.equals(XMLStartupTag.CONTENT))
		{
			this.content = true;
		}
	}
	
	/**
	 * the SAX driver will signal the end of an element and passes some
	 * parameters to the method.
	 * 
	 * @param uri
	 * 		the namespace URI.
	 * @param localName
	 * 		the unqualified name of the element.
	 * @param name
	 * 		the qualified name of the element.
	 * 		This is the namespace prefix combined with the local name
	 * 		of the element.
	 */
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{
		// <Artefact>
		if(localName.equals(XMLStartupTag.ARTEFACT))
		{
			this.artefact = false;
		}
		
		// <Type>
		else if(localName.equals(XMLStartupTag.TYPE))
		{
			this.type = false;
		}
		
		// <PRNG>
		else if(localName.equals(XMLStartupTag.PRNG))
		{
			this.rng = false;
		}
		
		// <PRNG> <Default>
		else if(localName.equals(XMLStartupTag.PRNG_DEFAULT))
		{
			this.rng_default = false;
		}
		
		// <Logging>
		else if(localName.equals(XMLStartupTag.LOGGING))
		{
			this.logging = false;
		}
		
		// <Date>
		else if(localName.equals(XMLStartupTag.DATE))
		{
			this.date = false;
		}
		
		// Date <Min>
		else if(localName.equals(XMLStartupTag.DATE_MIN))
		{
			this.date_lowerBound = false;
		}
		
		// Date <Max>
		else if(localName.equals(XMLStartupTag.DATE_MAX))
		{
			this.date_upperBound = false;
		}
		
		// <Unit>
		else if(localName.equals(XMLStartupTag.UNIT))
		{
			this.unit = false;
		}
		
		// <Currency>
		else if(localName.equals(XMLStartupTag.CURRENCY))
		{
			this.units.add(this.unitTemp);
			
			this.unitTemp = null;
			
			this.currency = false;
		}
		
		// <Length>
		else if(localName.equals(XMLStartupTag.LENGTH))
		{
			this.units.add(this.unitTemp);
			
			this.unitTemp = null;
			
			this.length = false;
		}
		
		// <Mass>
		else if(localName.equals(XMLStartupTag.MASS))
		{
			this.units.add(this.unitTemp);
			
			this.unitTemp = null;
			
			this.mass = false;
		}
		
		// <Math>
		else if(localName.equals(XMLStartupTag.MATH))
		{
			this.units.add(this.unitTemp);
			
			this.unitTemp = null;
			
			this.math = false;
		}
		
		// <Temperature>
		else if(localName.equals(XMLStartupTag.TEMPERATURE))
		{
			this.units.add(this.unitTemp);
			
			this.unitTemp = null;
			
			this.temperature = false;
		}
		
		// <Time>
		else if(localName.equals(XMLStartupTag.TIME))
		{
			this.units.add(this.unitTemp);
			
			this.unitTemp = null;
			
			this.time = false;
		}
		
		// <Misc>
		else if(localName.equals(XMLStartupTag.MISCELLANEOUS))
		{
			this.unitTemp.setWeightAndMeasurement(this.miscTemp);
			
			this.units.add(this.unitTemp);
			
			this.unitTemp = null;
			this.miscTemp = null;
			
			this.misc = false;
		}
		
		// <Custom>
		else if(localName.equals(XMLStartupTag.CUSTOM))
		{
			this.miscTemp.addToListCustom(this.customTemp);
			
			this.customTemp = null;
			
			this.custom = false;
		}
		
		// <Name>
		else if(localName.equals(XMLStartupTag.NAME))
		{
			this.name = false;
		}
		
		// <Content>
		else if(localName.equals(XMLStartupTag.CONTENT))
		{
			this.content = false;
		}
	}
	
	/**
	 * contains the characters that the parser has found in the
	 * source file. In spirit of minimizing memroy consumptioin, this
	 * event contains an array of characters; this is more lightweight
	 * than a String object.
	 * 
	 * @param ch
	 * 		an array of characters found by the parser.
	 * 		The start & length parameters indicate the portion
	 * 		of the array that generated this event.
	 * @param start
	 * 		the index of the first character in the array that
	 * 		belongs to this event.
	 * @param length
	 * 		the number of characters in this event.
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		// <PRNG>
		if(this.rng && this.rng_default)
		{
			this.prng = new String(ch, start, length);
		}
		
		// <Logging>
		else if(this.logging)
		{
			String tmp = new String(ch, start, length);
			
			this.isLoggingEnabled = Boolean.valueOf(tmp).equals(true) ? true : false;
		}
		
		// Date <Min>
		else if(this.date && this.date_lowerBound)
		{
			this.date_min = Integer.parseInt(new String(ch, start, length));
		}
		
		// Date <Max>
		else if(this.date && this.date_upperBound)
		{
			this.date_max = Integer.parseInt(new String(ch, start, length));
		}
		
		// <Currency>
		else if(this.unit && this.currency && this.type)
		{
			this.unitTemp.getWeightAndMeasurement().addToList(new String(ch, start, length));
		}
		
		// <Length>
		else if(this.unit && this.length && this.type)
		{
			this.unitTemp.getWeightAndMeasurement().addToList(new String(ch, start, length));
		}
		
		// <Mass>
		else if(this.unit && this.mass && this.type)
		{
			this.unitTemp.getWeightAndMeasurement().addToList(new String(ch, start, length));
		}
		
		// <Math>
		else if(this.unit && this.math && this.type)
		{
			this.unitTemp.getWeightAndMeasurement().addToList(new String(ch, start, length));
		}
		
		// <Temperature>
		else if(this.unit && this.temperature && this.type)
		{
			this.unitTemp.getWeightAndMeasurement().addToList(new String(ch, start, length));
		}
		
		// <Time>
		else if(this.unit && this.time && this.type)
		{
			this.unitTemp.getWeightAndMeasurement().addToList(new String(ch, start, length));
		}
		
		// <Name>
		else if(this.unit && this.misc && this.custom && this.name)
		{
			this.customTemp.setName(new String(ch, start, length));
		}
		
		// <Content>
		else if(this.unit && this.misc && this.content && this.content)
		{
			this.customTemp.addToContent(new String(ch, start, length));
		}
	}
	
	/**
	 * gets the set of parsed artefact types.
	 * 
	 * @return
	 * 		list of artefact types.
	 */
	protected List<ArtefactType> getArtefactType()
	{
		return this.artefactTypes;
	}
	
	/**
	 * gets the parsed set of units found in
	 * the startup XML file.
	 * 
	 * @return
	 * 		list of units.
	 */
	protected List<Unit> getUnits()
	{
		return this.units;
	}
	
	/**
	 * depending on the parsed startup XML file
	 * logging could be set to <i>true</i> or
	 * <i>false</i>. Logging is enabled as default
	 * parameter.
	 * 
	 * @return
	 * 		isLoggingEnabled?
	 */
	protected boolean getLogging()
	{
		return this.isLoggingEnabled;
	}
	
	/**
	 * gets the minimum date interval
	 * found in the startup XML file.
	 * Generated dates are beginning at
	 * least at that point.
	 * </p>
	 * The lower bound is still represented
	 * as a simple integer. The conversation
	 * does the controller itself.
	 * 
	 * @return
	 * 		min date interval
	 */
	protected int getDateMin()
	{
		return this.date_min;
	}
	
	/**
	 * gets the upper bound for generating
	 * dates. Because it should be possible
	 * to generate dates between an interval
	 * the lower and upper bounds are specified
	 * in the startup XML file.
	 * Whereas the minimum must be specified by
	 * any value the maximum does not have this
	 * restriction. When un-specfied the controller
	 * will be taking the current year.
	 * </p>
	 * Notice, the values are at this stage still
	 * simple integers. The conversation does the
	 * controller.
	 * 
	 * @return
	 * 		upper bound of the date interval
	 */
	protected int getDateMax()
	{
		return this.date_max;
	}
	
	/**
	 * gets the parsed PRNG. The definition of a random
	 * number generator is optional. If no one has been
	 * defined, the controller takes the default one.
	 * 
	 * @return
	 * 		parsed PRNG
	 */
	protected String getPRNG()
	{
		return this.prng;
	}
}