package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.SwingWorker;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/25/2009
 */
public class LoadValueFile extends SwingWorker<List<String>, Void>
{
	/**
	 * the path to the file to load
	 */
	private URI file = null;
	
	/**
	 * constructor awaits the working directory and the name
	 * of the file to load.
	 * 
	 * @param file
	 * 		the name of the file to load.
	 */
	public LoadValueFile(URI file)
	{
		this.file = file;
	}
	
	@Override
	protected List<String> doInBackground() throws Exception
	{
		List<String> valueFiles = null;
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(this.file.getPath()));
			
			String tmp = null;
			
			do
			{
				if(valueFiles == null)
				{
					valueFiles = new ArrayList<String>();
				}
				
				tmp = reader.readLine();
				
				valueFiles.add(tmp);
			}
			while(tmp != null);
			
			reader.close();
		}
		catch(FileNotFoundException fnfe)
		{
			ErrorMessage.getInstance().printMessage(fnfe, "FileNotFoundException", Level.FINEST);
		}
		catch(IOException ioe)
		{
			ErrorMessage.getInstance().printMessage(ioe, "IOException", Level.FINEST);
		}
		
		return valueFiles;
	}
}