package de.uni.bamberg.wiai.minf.forflow.datagenerator.view.message;

import javax.swing.JOptionPane;

/**
 * <b>Message</b> class is used to create different kind of messages used in the
 * user interface (UI).
 * Access is allowed from every package to generate messages in a fast way.
 * 
 * @author Michael Munz
 * @version 0.1
 * @since Apr/06/09
 */
public final class Message
{
	/**
	 * default constructor not visible to outside world.
	 */
	private Message()
	{
	}
	
	/**
	 * creates a message dialog.
	 * The parameters are in various ways adaptable.
	 * 
	 * @param text
	 * 		text shown in the message pane.
	 * @param headline
	 * 		text in the headline of the message pane.
	 * @param messageType
	 * 		defines the type of message to show, e.g.:
	 * 		<ul>
	 * 			<li>JOptionPane.ERROR_MESSAGE</li>
	 * 			<li>JOptionPane.INFORMATION_MESSAGE</li>
	 * 			<li>JOptionPane.QUESTION_MESSAGE</li>
	 * 			<li>JOptionPane.PLAIN_MESSAGE</li>
	 * 			<li>JOptionPane.WARNING_MESSAGE</li>
	 * 		</ul>
	 */
	public static void showMessageDialog(String text, String headline, int messageType)
	{
		JOptionPane.showMessageDialog(null, text, headline, messageType);
	}
	
	/**
	 * creates and shows an option dialog.
	 * The dialog is customizable with a variety of parameters.
	 * The call is then passed to JOptionPane
	 * 
	 * @param message
	 * 		as the parameters says: it's the message to display
	 * @param title
	 * 		the headline of the dialog frame
	 * @param optionType
	 * 		defines the type of option
	 * @param messageType
	 * 		specifies the type of message
	 * @param options
	 * 		it's also possible to customize the supported options.
	 * @param initialValue
	 * 		and last, the initial values can be pre-defined by the system.
	 */
	public static int showOptionDialog(String message,
										String title, 
										int optionType,
										int messageType,
										Object[] options,
										Object[] initialValue)
	{
		return JOptionPane.showOptionDialog(null, message, title, optionType, messageType, null, options, initialValue);
	}
	
	/**
	 * creates a confirmation dialog.
	 * The parameters are adaptable in various ways.
	 * 
	 * @param text
	 * 		the text to show in the message pane.
	 * @param headline
	 * 		text in the headline of the message pane.
	 * @param messageType
	 * 		defines the type of confirmation dialog to show.
	 * 		Possible types are:</br>
	 * 		<ul>
	 * 			<li>JOptionPane.DEFAULT_OPTION</li>
	 * 			<li>JOptionPane.YES_NO_OPTION</li>
	 * 			<li>JOptionPane.YES_NO_CANCEL_OPTION</li>
	 * 			<li>JOptionPane.OK_CANCEL_OPTION</li>
	 * 		</ul>
	 * @return
	 * 		an integer, representing the created confirmation dialog.
	 */
	public static int showConfirmDialog(String text, String headline, int messageType)
	{
		return JOptionPane.showConfirmDialog(null, text, headline, messageType);
	}
}