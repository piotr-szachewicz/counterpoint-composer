package pl.szachewicz.view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import jm.constants.Pitches;
import jm.gui.cpn.BassStave;
import jm.gui.cpn.TrebleStave;

public class StavePanel extends JPanel {

	private TrebleStave trebleStave;
	private BassStave bassStave;
	
	public StavePanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		trebleStave = new TrebleStave();
		bassStave = new BassStave();
		
		panel.add(trebleStave);
		panel.add(bassStave);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(MainFrame.WINDOW_WIDTH, 250));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scrollPane);
		
	}
}
