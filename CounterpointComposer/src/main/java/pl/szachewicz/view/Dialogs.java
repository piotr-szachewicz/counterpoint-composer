package pl.szachewicz.view;

import javax.swing.JOptionPane;

public class Dialogs {

	public static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
