package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.filltype;

/**
 * This enumeration lists the possible sex types. This
 * is needed, when names are generated. To get accurate names
 * for a male, or a female. It's important to distinguish between
 * these sex.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/30/09
 */
public enum SexType
{
	/**
	 * the type of sex is male
	 */
	MALE("male"),
	
	/**
	 * the sex type is female
	 */
	FEMALE("female");
	
	/**
	 * holds the enum type in a pretty formatted way.
	 */
	private final String name;
	
	/**
	 * this constructor is not visible to outside world.
	 * It assigns a pretty formatted name for display
	 * purposes.
	 * 
	 * @param name
	 * 		name of sex type
	 */
	private SexType(String name)
	{
		this.name = name;
	}
	
	/**
	 * gets the sex type in a pretty formatted way.
	 * 
	 * @return
	 * 		sex type of this enum
	 */
	public final String getSexType()
	{
		return this.name;
	}
}