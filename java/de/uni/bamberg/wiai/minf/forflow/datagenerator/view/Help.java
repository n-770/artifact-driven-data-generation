package de.uni.bamberg.wiai.minf.forflow.datagenerator.view;

/**
 * Gives the path to the help file. The content where help informations
 * are stored is in its own folder. The help is only related to the UI
 * and therefore the view layer is responsible to manage it.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/20/09
 */
public interface Help
{
	/**
	 * specifies the help folder
	 */
//	public final String HELP_FOLDER = "src/resource/Help/";
	public final String HELP_FOLDER = "Help/"; // TODO shipping path
	
	/**
	 * this is the <i>index.html</i> file.
	 * The file knows the folder and it is not necessary
	 * to give information about it separate.
	 */
	public final String HELP_INDEX = HELP_FOLDER +"index.html";
}