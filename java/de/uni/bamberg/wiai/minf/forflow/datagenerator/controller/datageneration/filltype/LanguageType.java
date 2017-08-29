package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

/**
 * This is an enumeration of supported languages to generate test
 * data to. Because real life data has different customers in
 * different countries, we simulate here those countries.
 * In other words, if you choose to generate data to <i>US</i>,
 * then the results of the data contain US names, first names, 
 * zip codes, email addresses etc. Depending on the context
 * and the attribute to fill, of course.  
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/30/09
 */
public enum LanguageType
{
	/**
	 * specifies the US language type.
	 * If this has been chosen, generated data
	 * is in this language.
	 */
	US("us"),
	
	/**
	 * specifies the DE language type.
	 * If this has been chosen, the data
	 * is in German.
	 */
	DE("de");
	
	/**
	 * this is for pretty printing formatted
	 * styling. In other words, this value is
	 * used when it comes to display the enum, rather
	 * the enum itself.
	 */
	private String name = null;
	
	/**
	 * constructor is not visible to ouside world.
	 * It is passed the name of the language in
	 * a pretty printing styling. This name is used
	 * rather the enum type itself, when printed out.
	 * 
	 * @param language
	 * 		what language does it represent?
	 */
	private LanguageType(String language)
	{
		this.name = language;
	}
	
	/**
	 * gets the name of the language to represent.
	 * Because generated data can be language dependent
	 * we use this to indicate which styling to use.
	 * </p>
	 * The name returned is a more pretty printing way
	 * to display as the raw enum type.
	 * 
	 * @return
	 * 		name of the language
	 */
	public final String getLanguageName()
	{
		return this.name;
	}
}