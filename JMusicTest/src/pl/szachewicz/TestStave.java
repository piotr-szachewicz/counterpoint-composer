package pl.szachewicz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.datatype.Duration;

import jm.constants.Durations;
import jm.constants.Pitches;
import jm.constants.Scales;
import jm.gui.cpn.BassStave;
import jm.gui.cpn.PianoStave;
import jm.gui.cpn.TrebleStave;
import jm.music.data.*;
import jm.JMC;
import jm.util.*;

public class TestStave extends JFrame {

	public static void main(String[] args) {
		new TestStave();
	}
	
	public TestStave() {
		super("A simple GUI interface for jMusic");
		this.setSize(400, 400);
		
		this.setLayout(new BorderLayout());
	       
		//add the button                  
		//JButton composeBtn = new JButton("Compose");
		//this.add(composeBtn);
		
		Note n = new Note(Pitches.f4, Durations.QUARTER_NOTE);
		System.out.println(Pitches.f1);
		System.out.println(Pitches.fs1);
		System.out.println(Pitches.ff1);
		System.out.println(Pitches.e1);
		System.out.println(Pitches.g1);
		//if (true)
			//return;

		//Scales.CHROMATIC_SCALE;
		
		
		ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		scrollPane.setSize(300, 200);

		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.setSize(300, 150);
		
		//PianoStave pianoStave = new PianoStave();
		
		TrebleStave trebleStave = new TrebleStave();
		BassStave bassStave = new BassStave();

		panel.add(trebleStave);
		panel.add(bassStave);
		//panel.add(pianoStave);

		scrollPane.add(panel);

		this.add(scrollPane);
		this.setVisible(true);
	}
}

