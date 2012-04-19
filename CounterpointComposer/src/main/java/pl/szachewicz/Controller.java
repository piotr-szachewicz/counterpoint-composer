package pl.szachewicz;

import java.util.List;

import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.Read;
import jm.util.Write;
import pl.szachewicz.algorithm.Evaluator;
import pl.szachewicz.algorithm.Ranking;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.StavePanel;

public class Controller {

	private Preferences preferences = new Preferences();
	private final StavePanel stavePanel;
	private Ranking ranking;

	private int selectedCounterpointIndex = 0;

	public Controller(StavePanel stavePanel) {
		this.stavePanel = stavePanel;
		preferences.setDefaults();
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void saveResults(String filePath) {
		Score score = stavePanel.getScore();
		Write.jm(score, filePath);
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

		ranking = new Ranking(treblePhrase, preferences);
	}

	public void playScore() {
		new Thread() {
			@Override
			public void run() {
				Play.midi(stavePanel.getScore());
			}
		}.start();
	}

	public void stopPlaying() {
		Play.stopMidi();
	}

	public void saveToMidi(String filePath) {
		Write.midi(stavePanel.getScore(), filePath);
	}

	public List<EvaluatedPhrase> getRanking() {
		if (ranking == null)
			return null;
		return ranking.getBestRanking();
	}

	public List<EvaluatedPhrase> generateRanking() {
		ranking = new Ranking(stavePanel.getTrebleStavePhrase(), preferences);
		ranking.generateRanking();
		selectedCounterpointIndex = -1;
		setPhrase(0);

		return ranking.getBestRanking();
	}

	public void setPhrase(int index) {
		Phrase generatedCounterpoint = ranking.getBestRanking().get(index).getPhrase();
		System.out.println("index " + index + " points = " + ranking.getBestRanking().get(index).getNumberOfPoints());
		stavePanel.setBassStavePhrase(generatedCounterpoint);
	}

	public void evaluate() {
		Evaluator evaluator = new Evaluator(stavePanel.getTrebleStavePhrase(), preferences);
		Phrase counterPoint = stavePanel.getBassStavePhrase();
		int result = evaluator.evaluatePhrase(counterPoint);
		System.out.println("Evaluation = " + result);
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
}
