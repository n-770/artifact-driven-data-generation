package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.datageneration;

import java.net.URI;

/**
 * This class represents a dummy file. To each single artefact
 * of artefact type <i>Document</i> a dummy will be created.
 * A dummy file consists of a path (folder to save to) a file name
 * and a file format.
 * </p>
 * <font size=6><b>Restrictions</b></font></br>
 * A path and a file name must exist at any time, but file extensions
 * doesn't necessarily have to be set. In UNIX file extensions are not
 * needed and we assume that habit as default. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/17/09
 */
public class DummyFile
{
	/**
	 * the path to save it
	 */
	private String path = null;
	
	/**
	 * the file name
	 */
	private String fileName = null;
	
	/**
	 * the file format of the dummy file
	 */
	private String fileFormat = null;
	
	/**
	 * sole default constructor
	 */
	public DummyFile()
	{
	}
	
	/**
	 * constructor awaits the path to save the file
	 * and the file name
	 * 
	 * @param path
	 * 		the path to save the dummy file
	 * @param fileName
	 * 		the name of the file
	 */
	public DummyFile(String path, String fileName)
	{
		this.path = path;
		this.fileName = fileName;
	}
	
	/**
	 * constructor awaits the path to save the file
	 * and the file name
	 * 
	 * @param path
	 * 		the path to save the dummy file
	 * @param fileName
	 * 		the name of the file
	 */
	public DummyFile(URI path, String fileName)
	{
		this.setPath(path);
		this.fileName = fileName;
	}
	
	/**
	 * constructor is passed the path to save the dummy
	 * file, the file name and the file format.
	 * 
	 * @param path
	 * 		the path to save the dummy
	 * @param fileName
	 * 		name of the file
	 * @param fileFormat
	 * 		file extension
	 */
	public DummyFile(String path, String fileName, String fileFormat)
	{
		this.path = path;
		this.fileName = fileName;
		this.fileFormat = fileFormat;
	}
	
	/**
	 * constructor is passed the path to save the dummy
	 * file, the file name and the file format.
	 * 
	 * @param path
	 * 		the path to save the dummy
	 * @param fileName
	 * 		name of the file
	 * @param fileFormat
	 * 		file extension
	 */
	public DummyFile(URI path, String fileName, String fileFormat)
	{
		this.setPath(path);
		this.fileName = fileName;
		this.fileFormat = fileFormat;
	}
	
	/**
	 * sets the path to save the dummy file
	 * 
	 * @param path
	 * 		the path as URI
	 */
	public void setPath(URI path)
	{
		if(path != null)
		{
			this.path = path.toString();
		}
	}
	
	/**
	 * sets the path of the dummy file.
	 * 
	 * @param path
	 * 		the path as String
	 */
	public void setPath(String path)
	{
		this.path = path;
	}
	
	/**
	 * gets the path
	 * 
	 * @return
	 * 		path of file
	 */
	public final String getPath()
	{
		return this.path;
	}
	
	/**
	 * sets the file name of the dummy file
	 * 
	 * @param fileName
	 * 		name of the file
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * gets the file name
	 * 
	 * @return
	 * 		file name
	 */
	public final String getFileName()
	{
		return this.fileName;
	}
	
	/**
	 * sets the file extension
	 * 
	 * @param fileFormat
	 * 		file extension
	 */
	public void setFileFormat(String fileFormat)
	{
		this.fileFormat = fileFormat;
	}
	
	/**
	 * gets the file extension
	 * 
	 * @return
	 * 		file extension
	 */
	public final String getFileFormat()
	{
		return this.fileFormat;
	}
	
	/**
	 * gets the path with the file name and the file format
	 * attached to it.
	 * 
	 * @return
	 * 		complete path
	 */
	public final String getPathWithFileName()
	{
		StringBuffer sb = new StringBuffer();
		
		// path
		if(this.getPath() != null)
		{
			sb.append(this.getPath());
			
			// file name
			if(this.getFileName() != null)
			{
				sb.append("/" +this.getFileName());
				
				// file format
				if(this.getFileFormat() != null)
				{
					sb.append("." +this.getFileFormat());
				}
			}
		}
		
		return (sb.length() > 0) ? sb.toString() : new String("");
	}
	
	@Override
	public String toString()
	{
		return this.getPathWithFileName();
	}
}