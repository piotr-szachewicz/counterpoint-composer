package pl.szachewicz.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import jm.constants.RhythmValues;
import jm.gui.cpn.BassStave;
import jm.gui.cpn.TrebleStave;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;

public class StavePanel extends JPanel {

	private final TrebleStave trebleStave;
	private final BassStave bassStave;

	public StavePanel() {

		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Score"));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		trebleStave = new TrebleStave();
		trebleStave.setAvailableRhythmValues(new double[] {RhythmValues.QUARTER_NOTE});
		bassStave = new BassStave();
		bassStave.setAvailableRhythmValues(new double[] {RhythmValues.QUARTER_NOTE});

		panel.add(trebleStave);
		panel.add(bassStave);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(MainFrame.WINDOW_WIDTH -200, 250));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(scrollPane);

	}

	public Score getScore() {
		Score score = new Score();
		Part treblePart = new Part(trebleStave.getPhrase());
		Part bassPart = new Part(bassStave.getPhrase());
		score.add(treblePart);
		score.add(bassPart);

		return score;
	}

	public Phrase getTrebleStavePhrase() {
		return trebleStave.getPhrase();
	}

	public Phrase getBassStavePhrase() {
		return bassStave.getPhrase();
	}

	public void setTrebleStavePhrase(Phrase phrase) {
		trebleStave.setPhrase(phrase);
	}

	public void setBassStavePhrase(Phrase phrase) {
		bassStave.setPhrase(phrase);
	}

}
