package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

/**
 * Summarizes XML tag elements of startup XML file.
 * This interface is needed when the file is parsed at
 * the startup phase.
 * The tag elements are placed at its on place, because
 * it is more maintainable when separated instead looking
 * through the code.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/17/09
 */
public interface XMLStartupTag
{
	/**
	 * XML tag element < Artefact>
	 */
	public final String ARTEFACT = "Artefact";
	
	/**
	 * XML tag element < Type>
	 */
	public final String TYPE = "Type";
	
	/**
	 * XML tag element < Logging>
	 */
	public final String LOGGING = "Logging";
	
	/**
	 * XML tag element < PRNG>
	 */
	public final String PRNG = "PRNG";
	
	/**
	 * XML tag element < Default>
	 */
	public final String PRNG_DEFAULT = "Default";
	
	/**
	 * XML tag element < Date>
	 */
	public final String DATE = "Date";
	
	/**
	 * XML tag element < Min> of < Date>
	 */
	public final String DATE_MIN = "Min";
	
	/**
	 * XML tag element < Max> of < Date>
	 */
	public final String DATE_MAX = "Max";
	
	/**
	 * XML tag element < Unit>
	 */
	public final String UNIT = "Unit";
	
	/**
	 * XML tag element < Currency>
	 */
	public final String CURRENCY = "Currency";
	
	/**
	 * XML tag element < Length>
	 */
	public final String LENGTH = "Length";
	
	/**
	 * XML tag element < Mass>
	 */
	public final String MASS = "Mass";
	
	/**
	 * XML tag element < Math>
	 */
	public final String MATH = "Math";
	
	/**
	 * XML tag element < Temperature>
	 */
	public final String TEMPERATURE = "Temperature";
	
	/**
	 * XML tag element < Time>
	 */
	public final String TIME = "Time";
	
	/**
	 * XML tag element < Miscellaneous>
	 */
	public final String MISCELLANEOUS = "Miscellaneous";
	
	/**
	 * XML tag element < Custom>
	 */
	public final String CUSTOM = "Custom";
	
	/**
	 * XML tag element < Name>
	 */
	public final String NAME = "Name";
	
	/**
	 * XML tag element < Content>
	 */
	public final String CONTENT = "Content";
}