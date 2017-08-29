package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.util;

import java.io.File;
import java.net.URI;

/**
 * <b>Splitter</b> class is used to separate the path from the file name.
 * It is needed to store the current working directory temporarily to get
 * back in an instant. That means, if you're doing I/O operations, it's helpful
 * to start in that directory where you left.
 * This class helps to achieve that goal.
 * </p>
 * The constructor of this class is private, so you have no change to get
 * an instance of it. But you should call the method provided directly, 'cos
 * it is declared as static.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public final class Splitter
{
	/**
	 * default constructor not visible to outside world.
	 */
	private Splitter()
	{
	}
	
	/**
	 * separates the path and the file name.
	 * The path is given as an <i>URI</i> reference object.
	 * </p>
	 * <font size=6><b>Example</b></font></br>
	 * Some examples of URIs are:
	 * <ul>
	 * 		<li>Web</li>
	 * 			<ul>
	 * 				<li><font color=red>http://java.sun.com/j2se/1.6/index.html</font></li>
	 * 			</ul>
	 * 		<li>Windows</li>
	 * 			<ul>
	 * 				<li><font color=red>file:/C:/j2se/1.6/index.html</font></li>
	 * 			</ul>
	 * 		<li>UNIX</li>
	 * 			<ul>
	 * 				<li><font color=red>file:/usr/j2se/1.6/index.html</font></li>
	 * 			</ul>
	 * </ul>
	 * 
	 * @param uri
	 * 		Represents a Uniform Resource Identifier reference.
	 * @return
	 * 		the path </p>
	 * 		<ul>
	 * 			<li>Windows</li>
	 * 				<ul>
	 * 					<li><font color=green>C:/j2se/1.6</font></li>
	 * 				</ul>
	 * 			<li>UNIX</li>
	 * 				<ul>
	 * 					<li><font color=green>/user/j2se/1.6</font></li>
	 * 				</ul>
	 * 		</ul>
	 */
	public static final URI splitPathAndFileName(URI uri)
	{
		// cut off file
		int pos = uri.toString().lastIndexOf("/");
		
		String tmp = uri.toString().substring(0, pos);
		
		/*
		 *  cut off schema
		 *  
		 *  OS Windows
		 */ 
		if(System.getProperty("file.separator").equals("\\"))
		{
			tmp = tmp.replace(uri.getScheme() +":/", "");
		}
		
		// OS UNIX
		else
		{
			tmp = tmp.replace(uri.getScheme() +":", "");
		}
		
		return URI.create(tmp);
	}
	
	/**
	 * separates the path and the file name.
	 * The path is given as an <i>URI</i> reference object.
	 * </p>
	 * <font size=6><b>Example</b></font></br>
	 * Some examples of URIs are:
	 * <ul>
	 * 		<li>Web</li>
	 * 			<ul>
	 * 				<li><font color=red>http://java.sun.com/j2se/1.6/index.html</font></li>
	 * 			</ul>
	 * 		<li>Windows</li>
	 * 			<ul>
	 * 				<li><font color=red>file:/C:/j2se/1.6/index.html</font></li>
	 * 			</ul>
	 * 		<li>UNIX</li>
	 * 			<ul>
	 * 				<li><font color=red>file:/usr/j2se/1.6/index.html</font></li>
	 * 			</ul>
	 * </ul>
	 * </p>
	 * <font size=6><b>Assumption</b></font></br>
	 * This method is based on an assumption made because the file name
	 * is not always clear. Assume the file name is something like <i>Course</i>
	 * and it is an ordinary text file. On Windows the full file name would be
	 * <font color=red><i>Course.txt</i></font>.
	 * Here's the file name is identified with the help of the
	 * file extension.
	 * </p>
	 * But what about UNIX systems. On UNIX the same file <i>Course</i>
	 * would be look like <font color=red><i>Course</i></font> if serialized.
	 * There's no such thing like an file extension.
	 * And we cannot guarantee Course isn't a directory, when
	 * this method is called. We assume it and it it up to the user jumping to
	 * the right conclusions, because we haven't got the context.
	 * 
	 * @param uri
	 * 		a path in the local file system
	 * @return
	 * 		the file name
	 */
	public static final String splitPathAndfileName(URI uri)
	{
		// cut off path
		int pos = uri.toString().lastIndexOf("/");
		
		/*
		 *  assume this is the file name!
		 *  
		 *  We could do a check on file extension,
		 *  but what about UNIX systems. There's no such
		 *  thing possible? 
		 */
		String name = uri.toString().substring(pos+1);
		
		return name;
	}
	
	/**
	 * gets only the file name and cuts off the file extension.
	 * There is a check, if the file has an extension and then the process
	 * of cutting is started.
	 * 
	 * @param file
	 * 		a file name with extension
	 * @return
	 * 		only file name or null
	 */
	public static final String getFileName(String file)
	{
		String name = null;
		
		if(hasFileExtension(file))
		{
			// cut off ext
			int pos = file.lastIndexOf(".");
			
			name = file.substring(0, pos);
		}
		else
		{
			name = file;
		}
		
		return name;
	}
	
	
	/**
	 * gets only the file name and cuts off the file extension.
	 * There is a check, if the file has an extension and then the process
	 * of cutting is started.
	 * 
	 * @param file
	 * 		a file name with extension
	 * @return
	 * 		only file name or null
	 */
	public static final String getFileName(File file)
	{
		return getFileName(file.getName());
	}
	
	/**
	 * gets the file extension of any file. It checks
	 * first if the file has got a file extension.
	 * If true, then the extension will be returned.
	 * Otherwise null is the returned result.
	 * 
	 * @param file
	 * 		any file to which the extension is needed
	 * @return
	 * 		file extension or null
	 */
	public static final String getFileExtension(URI file)
	{
		return getFileExtension(splitPathAndfileName(file));
	}
	
	/**
	 * gets the file extension of any file. It checks
	 * first if the file has got a file extension.
	 * If true, then the extension will be returned.
	 * Otherwise null is the returned result.
	 * 
	 * @param file
	 * 		any file to which the extension is needed
	 * @return
	 * 		file extension or null
	 */
	public static final String getFileExtension(File file)
	{
		return getFileExtension(file.getName());
	}
	
	/**
	 * gets the file extension of any file. It checks
	 * first if the file has got a file extension.
	 * If true, then the extension will be returned.
	 * Otherwise null is the returned result.
	 * 
	 * @param file
	 * 		any file to which the extension is needed
	 * @return
	 * 		file extension or null
	 */
	public static final String getFileExtension(String file)
	{
		if(hasFileExtension(file))
		{
			int n = file.lastIndexOf(".");
			
			return file.substring(n+1);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * checks whether the passed file, as an URI object,
	 * has any file extension. Usually this is an important
	 * question when a file is serialized. If the user
	 * typed in an valid (from the system supported) extension
	 * we wouldn't have done this again.
	 * 
	 * @param file
	 * 		the file to check.
	 * @return
	 * 		true if and only if a file extension has been found
	 */
	public static final boolean hasFileExtension(URI file)
	{
		return hasFileExtension(splitPathAndfileName(file));
	}
	
	/**
	 * checks whether the passed file, as an URI object,
	 * has any file extension. Usually this is an important
	 * question when a file is serialized. If the user
	 * typed in an valid (from the system supported) extension
	 * we wouldn't have done this again.
	 * 
	 * @param file
	 * 		the file to check.
	 * @return
	 * 		true if and only if a file extension has been found
	 */
	public static final boolean hasFileExtension(File file)
	{
		return hasFileExtension(file.getName());
	}
	
	/**
	 * checks whether the passed file, as an URI object,
	 * has any file extension. Usually this is an important
	 * question when a file is serialized. If the user
	 * typed in an valid (from the system supported) extension
	 * we wouldn't have done this again.
	 * 
	 * @param file
	 * 		the file to check.
	 * @return
	 * 		true if and only if a file extension has been found
	 */
	public static final boolean hasFileExtension(String file)
	{
		if(file.contains("."))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}