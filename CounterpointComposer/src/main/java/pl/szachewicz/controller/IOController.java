package pl.szachewicz.controller;

import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Read;
import jm.util.Write;
import pl.szachewicz.view.StavePanel;

public class IOController {

	private final StavePanel stavePanel;

	public IOController(StavePanel stavePanel) {
		this.stavePanel = stavePanel;
	}

	public void loadScoreFromJMFile(String filePath) {
		Score score = new Score();
		Read.jm(score, filePath);

		Part[] parts = score.getPartArray();

		Phrase treblePhrase = parts[0].getPhrase(0);
		stavePanel.setTrebleStavePhrase(treblePhrase);

		if (parts.length > 1 && parts[1].getPhrase(0) != null) {
			Phrase bassPhrase = parts[1].getPhrase(0);
			stavePanel.setBassStavePhrase(bassPhrase);
		}

		//ranking = new Ranking(treblePhrase, preferences);
	}


	public void saveToMidi(String filePath) {
		Write.midi(stavePanel.getScore(), filePath);
	}

	public void saveResults(String filePath) {
		Score score = stavePanel.getScore();
		Write.jm(score, filePath);
	}

}
