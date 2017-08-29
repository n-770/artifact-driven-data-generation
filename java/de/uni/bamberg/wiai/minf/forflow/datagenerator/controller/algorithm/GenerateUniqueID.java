package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.algorithm;

import java.util.UUID;

/**
 * This class is responsible for generating unique ID's.
 * These ID's are used in the test data generation and each
 * concrete term gets one. So each artefact can uniquly identified
 * by this ID code.
 * </p>
 * ID's need a special treatment, since identifiers need to be difficult
 * to guess or forge. Simply using a series of consecutive digits is
 * improper.
 * </p>
 * <font size=6><b>UUID</b></font></br>
 * The generation of unique ID's is based on Java's <i>UUID</i> class
 * providing the needed means for generating unique ID's in a simple
 * and easy way. The ID's generated are called <i>universal unique identifiers</i>
 * (UUID).
 * </p>
 * <font size=6><b>Examples of UUID's</b></font></br>
 * <font color=red>067e6162-3b6f-4ae2-a171-2470b63dff00</font></br>
 * <font color=red>54947df8-0e9e-4471-a2f9-9af509fb5889</font>
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/13/09
 */
public final class GenerateUniqueID
{
	/**
	 * sole default constructor not visible to outside world
	 */
	private GenerateUniqueID()
	{
	}
	
	/**
	 * we use the Java mean for generating unique IDs.
	 * The ID's generated are so-called <i>universal unique identifiers</i>.
	 * 
	 * @return
	 * 		universal unique identifier
	 */
	public static final UUID getUniqueID()
	{
		return UUID.randomUUID();
	}
}