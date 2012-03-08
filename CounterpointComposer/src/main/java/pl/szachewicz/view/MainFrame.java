package pl.szachewicz.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jm.gui.cpn.TrebleStave;

public class MainFrame extends JFrame {

	public static int WINDOW_HEIGHT = 350;
	public static int WINDOW_WIDTH = 700;
	
	private StavePanel stavePanel;

	public MainFrame() {
		super();
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle("Counterpoint composer");

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("bebe"), BorderLayout.NORTH);
		
		stavePanel = new StavePanel();
		panel.add(stavePanel, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		JButton playButton = new JButton("Play");
		buttonsPanel.add(playButton);
		
		panel.add(buttonsPanel, BorderLayout.SOUTH);
		this.add(panel);
	}

}
