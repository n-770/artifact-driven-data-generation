package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.dialog;

import java.awt.Frame;

import de.uni.bamberg.wiai.minf.forflow.datagenerator.view.DataGeneratorView;

/**
 * Factories are responsible for object creation and they know to to create them.
 * Instead of spreading object creation all over the place, we hold it at one place.
 * This helps in getting maintain benefits. 
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/08/09
 */
public final class DialogFactory
{
	/**
	 * sole default constructor not visible to outside world.
	 */
	private DialogFactory()
	{
	}
	
	/**
	 * creates a new {@link About} dialog object and
	 * sets its position relative to mainFrame.
	 * 
	 * @param mainFrame
	 * 		the main frame of the application framework
	 * @return
	 * 		About dialog
	 */
	public static About createAboutDialog(Frame mainFrame)
	{
		About about = new About(mainFrame);
		
		about.setLocationRelativeTo(mainFrame);
		
		return about;
	}
	
	/**
	 * creates a new {@link NullValues} object dialog
	 * and sets its position relative to mainFrame.
	 * 
	 * @param mainFrame
	 * 		the main frame of the application's framework.
	 * @return
	 * 		NullValues dialog
	 */
	public static NullValues createNullValueDialog(Frame mainFrame)
	{
		NullValues nv = new NullValues(mainFrame);
		
		nv.setLocationRelativeTo(mainFrame);
		
		return nv;
	}
	
	/**
	 * creates a new {@link Multiplicity} dialog and sets its
	 * position relative to mainFrame.
	 * 
	 * @param mainFrame
	 * 		the frame of the application framework.
	 * @return
	 * 		Multiplicity dialog
	 */
	public static Multiplicity createMultiplicityDialog(Frame mainFrame)
	{
		Multiplicity multi = new Multiplicity(mainFrame);
		
		multi.setLocationRelativeTo(mainFrame);
		
		return multi;
	}
	
	/**
	 * creates a new {@link ChooseArtefacts} dialog and sets
	 * its position relative to the mainFrame.
	 * 
	 * @param mainFrame
	 * 		parent frame of the application framework.
	 * @param view
	 * 		reference of main view.
	 * 		Do not mix it up with 1st parameter.
	 * 		It's different.
	 * @return
	 * 		ChooseArtefacts dialog
	 */
	public static ChooseArtefacts createChooseArtefactsDialog(Frame mainFrame, DataGeneratorView view)
	{
		ChooseArtefacts ca = new ChooseArtefacts(mainFrame, view);
		
		ca.setLocationRelativeTo(mainFrame);
		
		return ca;
	}
	
	/**
	 * creates a new dialog object handling the generation of test data.
	 * The dialog needs the reference of the swing application frame to
	 * set the relative position.
	 * 
	 * @param mainFrame
	 * 		this is the parent frame residing in swing application framework.
	 * @return
	 * 		GenerateData dialog
	 */
	public static GenerateData createGenerateDataDialog(Frame mainFrame)
	{
		GenerateData ga = new GenerateData(mainFrame);
		
		ga.setLocationRelativeTo(mainFrame);
		
		return ga;
	}
}