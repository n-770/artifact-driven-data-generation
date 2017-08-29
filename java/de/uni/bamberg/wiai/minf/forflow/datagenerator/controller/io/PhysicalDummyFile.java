package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.logging.Level;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.DummyFile;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.Facet;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration.GeneratedArtefact;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * Here is the physical file created we use as a dummy
 * for each artefact generated when it is of artefact
 * type <i>document</i> and has all constraints set
 * to create a file. The constraints are defined in
 * the class {@link DummyFile}. This class holds the
 * date used in here.
 * </p>
 * There are two major methods. One to create a default
 * dummy file and one to create a dummy file with custom
 * content.
 * </p>
 * <font size=6><b>Default dummy file</b></font></br>
 * This kind of file has no additional information.
 * It only holds a default content within and gives 
 * some rough information about the creation process.
 * </p>
 * <font size=6><b>Custom dummy file</b></font></br>
 * With a custom file it is possible to put more helpful
 * information into it. It gives information about what
 * document type was created and represents it, respectively.
 * The complete generated artefact data is put into it
 * and mirrors these informations. The same informations
 * are found in the XML collection data set.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/18/09
 */
public class PhysicalDummyFile
{
	/**
	 * sole default constructor
	 */
	public PhysicalDummyFile()
	{
	}
	
	/**
	 * creates a physical dummy file at the given path.
	 * The parameter <i>file</i> has to hold the path
	 * and the file name all in one.
	 * </p>
	 * <font size=6><b>Default dummy file</b></font></br>
	 * The file created has the given name at the given folder.
	 * And additionally with the given file format.
	 * The method has been creating a default file. That means
	 * the file holds default content.
	 * 
	 * @param file
	 * 		a dummy file with file name and optional a file format.
	 * @return
	 * 		<i>true</i> if successfully created
	 */
	public boolean createDummyFile(URI file)
	{
		boolean successful = false;
		
		BufferedWriter writer = null;
		
		try
		{
			writer = new BufferedWriter(new FileWriter(file.getPath()));
			
			writer.write("Physical dummy file!");
			writer.newLine();
			writer.write("File was created by DataGenerator.");
			
			successful = true;
		}
		catch(IOException ioe)
		{
			ErrorMessage.getInstance().printMessage(ioe, "IOException", Level.INFO);
		}
		
		// close writer
		finally
		{
			try
			{
				if(writer != null)
				{
					writer.flush();
					writer.close();
				}
			}
			catch(IOException ioe)
			{
			}
		}
		
		return successful;
	}
	
	/**
	 * creates a physical dummy file at the given path.
	 * The parameter <i>file</i> has to hold the path
	 * and the file name all in one.
	 * </p>
	 * <font size=6><b>Default dummy file</b></font></br>
	 * The file created has the given name at the given folder.
	 * And additionally with the given file format.
	 * The method has been creating a default file. That means
	 * the file holds default content.
	 * 
	 * @param file
	 * 		a dummy file with file name and optional a file format.
	 * @return
	 * 		<i>true</i> if successfully created		
	 */
	public boolean createDummyFile(String file)
	{
		URI uri = null;
		
		try
		{
			uri = URI.create(file);
		}
		catch(IllegalArgumentException iae)
		{
			return false;
		}
		
		return this.createDummyFile(uri);
	}
	
	/**
	 * creates a physical dummy file at the given path.
	 * The parameter <i>file</i> has to hold the path
	 * and the file name all in one.
	 * </p>
	 * <font size=6><b>Extended dummy file</b></font></br>
	 * With this method it is possible to create extended
	 * dummy files. Unlike the default dummy files these
	 * have no default context, but the content of the
	 * generated artefact. That is, it tells something about
	 * the file the id and the generated facets. 
	 * 
	 * @param file
	 * 		the dummy file with file name and optionally with file format
	 * @param generatedArtefact
	 * 		the content of the dummy file has the content of the
	 * 		generated test data.
	 * @return
	 * 		<i>true</i> if the dummy file was created with success.
	 */
	public boolean createDummyFile(String file, GeneratedArtefact generatedArtefact)
	{
		URI uri = null;
		
		try
		{
			uri = URI.create(file);
		}
		catch(IllegalArgumentException iae)
		{
			return false;
		}
		
		return this.createDummyFile(uri, generatedArtefact);
	}
	
	/**
	 * 
	 * creates a physical dummy file at the given path.
	 * The parameter <i>file</i> has to hold the path
	 * and the file name all in one.
	 * </p>
	 * <font size=6><b>Extended dummy file</b></font></br>
	 * With this method it is possible to create extended
	 * dummy files. Unlike the default dummy files these
	 * have no default context, but the content of the
	 * generated artefact. That is, it tells something about
	 * the file the id and the generated facets. 
	 * 
	 * @param file
	 * 		the dummy file with file name and optionally with file format
	 * @param generatedArtefact
	 * 		the content of the dummy file has the content of the
	 * 		generated test data.
	 * @return
	 * 		<i>true</i> if the dummy file was created with success.
	 */
	public boolean createDummyFile(URI file, GeneratedArtefact generatedArtefact)
	{
		boolean success = false;
		
		BufferedWriter writer = null;
		
		try
		{
			writer = new BufferedWriter(new FileWriter(file.getPath()));
			
			// doc header
			writer.write("name: "
						+generatedArtefact.getName()
						+" id: "
						+generatedArtefact.getID()
						+" path: "
						+file.toString());
			writer.newLine();
			
			// facets (we use them as metadata)
			for(Iterator<Facet> i=generatedArtefact.iterator(); i.hasNext();)
			{
				Facet facet = i.next();
				
				if(facet.getValue() != null)
				{
					writer.write("\t"
								+facet.getName()
								+": "
								+facet.getValueAndUnit());
					writer.newLine();
				}
			}
			
			success = true;
		}
		catch(IOException ioe)
		{
			ErrorMessage.getInstance().printMessage(ioe, "IOException", Level.INFO);
		}
		
		// close writer
		finally
		{
			try
			{
				if(writer != null)
				{
					writer.flush();
					writer.close();
				}
			}
			catch(IOException ioe)
			{
			}
		}
		
		return success;
	}
}