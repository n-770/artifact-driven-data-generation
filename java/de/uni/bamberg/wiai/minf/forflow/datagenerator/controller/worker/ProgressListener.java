package de.uni.bamberg.wiai.minf.forflow.datagenerator.controller.worker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;

/**
 * <b>ProgressListener</b> class is used to give a feedback to users,
 * which have invoked long running processes in background.
 * To show the status to them we do this by a progress bar.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/07/09
 */
public class ProgressListener implements PropertyChangeListener
{
	/**
	 * reference of an existing progress bar needed for reuse.
	 */
	private JProgressBar progressBar = null;
	
	/**
	 * tracks the progess
	 */
	private int counter = 0;
	
	/**
	 * constructor is passed an reference of an existing progress bar
	 * for reuse and a flag indicating which progress should be shown
	 * to the user as feedback.
	 * 
	 * @param progressBar
	 * 		an existing progress bar
	 * @param isIndeterminate
	 * 		determines if it is indeterminate or determinate
	 * @param max
	 * 		the maximum value for the progress bar
	 */
	protected ProgressListener(JProgressBar progressBar, boolean isIndeterminate, int max)
	{
		this.progressBar = progressBar;
		
		this.progressBar.setMinimum(0);
		this.progressBar.setMaximum(max);
		this.progressBar.setValue(this.progressBar.getMinimum());
		this.progressBar.setIndeterminate(isIndeterminate); 
	}
	
	/**
	 * sets the progress one value further.
	 */
	protected void setProgress()
	{
		this.progressBar.setValue(this.counter++);
	}
	
	/**
	 * sets the progress by value
	 * 
	 * @param n
	 * 		progress 
	 */
	protected void setProgressValue(int n)
	{
		this.progressBar.setValue(n);
	}
	
	/**
	 * sets the progress bar to the initial state.
	 * When this method isn't called after the job has
	 * finished, the progress bar stays with a full beam
	 */
	protected void reset()
	{
		DataGeneratorView.getProgressBar().setIndeterminate(false);
		DataGeneratorView.getProgressBar().setValue(
				DataGeneratorView.getProgressBar().getMinimum());
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent pce)
	{
		if(pce.getPropertyName().equals("process"))
		{
			if(this.progressBar.isIndeterminate())
			{
				int progress = (Integer) pce.getNewValue();
				
				this.progressBar.setValue(progress);
			}
		}
	}
}