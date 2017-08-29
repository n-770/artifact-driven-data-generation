package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.boot;

/**
 * Links to external files used by the system.
 * An file is said to be external, if it's placed outside
 * a Java <i>*.jar</i> file.
 * </p>
 * This interface specifies the the path and files used from
 * outside the system. Rather to hard code the files we specify them
 * by that interface.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/16/09
 */
public interface Startup
{
	/**
	 * links to setup folder
	 */
//	public final String FOLDER_SETUP = "src/resource/Setup/";
	public final String FOLDER_SETUP = "Setup/"; // TODO shipping path
	
	/**
	 * startup XML file
	 */
	public final String STARTUP_XML = FOLDER_SETUP +"startup.xml";
	
	/**
	 * startup XSD file
	 */
	public final String STARTUP_XSD = FOLDER_SETUP +"startup.xsd";
}