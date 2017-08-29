package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

/**
 * Here are the supported file extensions placed.
 * The extensions are supposed to be extensible and therefore
 * placed into an array.
 * </p>
 * Other stuff related to external files are also placed here or
 * in case it isn't yet, it will be in the future.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/06/09
 */
public interface FileExt
{
	/**
	 * the file descriptions of EMF Ecore meta-models
	 */
	public final String[] FILE_DESC_EMF = {"EMF Metamodel (Ecore)"};
	
	/**
	 * file extensions of EMF Ecore files is <i>ecore</i>
	 */
	public final String[] FILE_EXT_EMF = {"ecore"};
	
	/**
	 * file descriptions of plain text files also known as
	 * value files.
	 */
	public final String[] FILE_DESC_TXT = {"ValueFile"};
	
	/**
	 * file extensions of value files. Value files are flat
	 * text files.
	 */
	public final String[] FILE_EXT_TXT = {"txt"};
	
	/**
	 * file description of test data collections.
	 * A collection is said to hold a bunch of test data sets.
	 */
	public final String[] FILE_DESC_XML = {"Test data collection"};
	
	/**
	 * file extensions of generated test data sets. These
	 * are XML files, actually.
	 */
	public final String[] FILE_EXT_XML = {"xml"};
}