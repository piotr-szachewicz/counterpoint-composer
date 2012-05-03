package pl.szachewicz.view;

import javax.swing.JOptionPane;

import pl.szachewicz.model.ValidationErrors;

public class Dialogs {

	public static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void showErrors(ValidationErrors errors) {
		showErrorDialog(errors.getErrors().get(0));
	}
}
