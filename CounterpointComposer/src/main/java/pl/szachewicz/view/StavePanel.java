package pl.szachewicz.view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import jm.gui.cpn.BassStave;
import jm.gui.cpn.TrebleStave;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.Read;
import jm.util.Write;

public class StavePanel extends JPanel {

	private final TrebleStave trebleStave;
	private final BassStave bassStave;

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

	protected Score getScore() {
		Score score = new Score();
		Part treblePart = new Part(trebleStave.getPhrase());
		Part bassPart = new Part(bassStave.getPhrase());
		score.add(treblePart);
		score.add(bassPart);

		return score;
	}

	public void saveResults(String filePath) {

		Score score = getScore();
		Write.jm(score, filePath);
	}

	public void loadScoreFromJMFile(String filePath) {
		Score score = new Score();
		Read.jm(score, filePath);

		Part[] parts = score.getPartArray();

		Phrase treblePhrase = parts[0].getPhrase(0);
		trebleStave.setPhrase(treblePhrase);

		if (parts.length > 1) {
			Phrase bassPhrase = parts[1].getPhrase(0);
			bassStave.setPhrase(bassPhrase);
		}
	}

	public void playScore() {
		Play.midi(getScore());
	}
}
