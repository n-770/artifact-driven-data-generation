package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.log;

/**
 * This interface is part of the logging mechanism and
 * sets the name of the <i>log</i> file used to serialize
 * exceptions.
 * </p>
 * <font size=6><b>Log folder</b></font></br>
 * The class specifies the folder to store the logging file(s) and
 * also defines the name of them.
 * </p>
 * <font size=6><b>Sequence of <i>log</i> files</b></font></br>
 * Instead of using just one <i>log</i> file
 * and reusing it over and over again we do it a little more sophisticated.
 * Instead of overwriting useful information by one <i>log</i> file, a
 * more effective way is possible. The logging API allows a sequence of
 * files to hold <i>log</i> information. When a file fills up, the oldest
 * file is emptied and logging resumes in that file.
 * </p>
 * <font size=6><b>Limiting the size</b></font></br>
 * By default we limit the <i>log</i> file to a constant value.
 * Remember, we use more <i>log</i> files, and each has the same limit.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since May/05/09
 */
public interface Log
{
	/**
	 * link to log folder
	 */
//	public final String FOLDER_LOG = "src/resource/Log/";
	public final String FOLDER_LOG = "Log/"; // TODO shipping path
	
	/**
	 * name of the <i>log</i> file.
	 * A log file number is used to generated ranges from 0 to n-1
	 * log files. When specifying the file name we need to include
	 * the location of the number using <i>%g</i> place holder.
	 * Each log file has a limit of <i>x</i> MB.
	 */
	public final String LOG_FILE = FOLDER_LOG +"Log%g.log";
	
	/**
	 * here is the log file limit specified.
	 */
	public final int LOG_FILE_LIMIT = (50 * 1024);
	
	/**
	 * we limit the size and use more than one
	 * log file as a rotating sequence.
	 * If the limit has been reached, the file is automatically
	 * emptied. However, this approach has drawbacks of discarding useful
	 * information. So we use a more effective way by allowing
	 * to use a sequence of files to hold the log information.
	 * When a file is filled up the oldest file is emptied and logging
	 * resumes in that file.
	 */
	public final int LOG_FILE_NO = 3;
}