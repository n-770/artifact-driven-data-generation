package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.List;

import javax.swing.SwingWorker;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.io.FileExt;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;
import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message.ErrorMessage;

/**
 * Writes a typed in list for a given attribute as a file
 * into the file system. Because this leads to an I/O bound task
 * a swing worker does the job in background.
 * </p>
 * To create the plain text file the current working directory
 * the file name and the content of the file are needed.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/29/09
 */
public class SaveValueFile extends SwingWorker<Void, Void>
{
	/**
	 * the current working dir.
	 * We jump right into that, so the users
	 * doesn't have to browse by themselves.
	 * We do the annoying job.
	 */
	private URI workingDir = null;
	
	/**
	 * here's the name of the file to create
	 */
	private String fileName = null;
	
	/**
	 * here are the values the user has typed in
	 * into the editor component in the UI.
	 * We are going to save them as plain text.
	 */
	private List<String> values = null;
	
	/**
	 * the constructor is passed the current working
	 * directory. This is helpful, because the system
	 * jumps right into that <i>dir</i>, rather letting
	 * the user browse. The constructor is also passed
	 * the values typed in into the editor component
	 * of the UI. We're gonna save them as plain text.
	 * 
	 * @param workingDir
	 * 		the latest working dir.
	 * 		We assume the user will save it here,
	 * 		or at least starts browsing from here.
	 * @param values
	 * 		the list of possible values for an attribute.
	 * 		Those are typed in values and provided by the user.
	 * @param fileName
	 * 		the name of the file to create
	 */
	public SaveValueFile(URI workingDir, List<String> values, String fileName)
	{
		this.workingDir = workingDir;
		this.values = values;
		this.fileName = fileName;
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		ProgressListener progress = new ProgressListener(DataGeneratorView.getProgressBar(),
														 false,
														 this.values.size());
		
		try
		{
			String file = this.workingDir.toString() +"/" +this.fileName +"." +FileExt.FILE_EXT_TXT[0];
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			
			for(int i=0; i<this.values.size(); i++)
			{
				out.append(this.values.get(i));
				out.newLine();
				
				progress.setProgress();
			}
			
			out.flush();
			out.close();
		}
		catch(IOException ioe)
		{
			ErrorMessage.getInstance().printMessage(ioe,
													this.getClass().getName(),
													"IOException");
		}
		
		progress.reset();
		
		return null;
	}
}