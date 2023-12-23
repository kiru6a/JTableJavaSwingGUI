import javax.swing.*;

/**
 * Entry point of the application that initiates the Swing-based GUI.
 */
public class Main
{
	public static void main(String[] args)
	{
		// Invoke the Swing application on the Event Dispatch Thread
		SwingUtilities.invokeLater(TableFrame::new);
	}
}
