package pl.szachewicz.view;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class ConsolePanel extends AbstractPanel {

	private final JTextArea textArea;

	public ConsolePanel() {
		setBorder(new TitledBorder("Console"));
		setLayout(new BorderLayout());

		textArea = new JTextArea();
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	public void fillViewFromModel(Object model) {
		String string = (String) model;
		textArea.setText(string);
	}

	public void fillModelFromView(Object model) {
	}

}
