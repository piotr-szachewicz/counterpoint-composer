package pl.szachewicz.view;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private StavePanel stavePanel;

	public MainFrame() {
		super();
		this.setSize(400, 400);
		this.setTitle("Counterpoint composer");

		stavePanel = new StavePanel();
		add(stavePanel);
	}

}
