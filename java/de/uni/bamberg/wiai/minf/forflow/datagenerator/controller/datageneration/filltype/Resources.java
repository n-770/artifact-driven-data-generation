package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

/**
 * Here are the resources for the generators placed.
 * Generators use the paths to create realistic and
 * meaningful test data values for attributes.
 * To achieve this we make use of value lists. The 
 * list are split into sets of different languages.
 * At this point, we make use of US & DE, only. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/01/09
 */
public interface Resources
{
	/**
	 * links to resources folder
	 */
//	public final String FOLDER_RESOURCES = "src/resource/Resources/";
	public final String FOLDER_RESOURCES = "Resources/"; // TODO shipping path
	
	/**
	 * language identification for German values
	 */
	public final String FOLDER_DE = "de/";
	
	/**
	 * language identification for US values
	 */
	public final String FOLDER_US = "us/";
	
	/**
	 * addresses file
	 */
	public final String ADDRESSES = "addresses.txt";
	
	/**
	 * cities file
	 */
	public final String CITIES = "cities.txt";
	
	/**
	 * file holding countries
	 */
	public final String COUNTRIES = "countries.txt";
	
	/**
	 * a file for e-mails
	 */
	public final String EMAILS = "e-mails.txt";
	
	/**
	 * names' file
	 */
	public final String NAMES = "names.txt";
	
	/**
	 * first names' file holding boy names
	 */
	public final String PRENAME_BOYS = "prename_boys.txt";
	
	/**
	 * first names' file holding girl names 
	 */
	public final String PRENAME_GIRLS = "prename_girls.txt";
	
	/**
	 * sex file
	 */
	public final String SEX = "sex.txt";
}