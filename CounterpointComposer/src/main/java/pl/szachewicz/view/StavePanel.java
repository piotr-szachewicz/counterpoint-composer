package pl.szachewicz.view;

import java.awt.Dimension;
import java.util.List;

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
import pl.szachewicz.algorithm.Evaluator;
import pl.szachewicz.algorithm.Ranking;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.preferences.Preferences;

public class StavePanel extends JPanel {

	private Preferences preferences;

	private final TrebleStave trebleStave;
	private final BassStave bassStave;
	private Ranking ranking;

	private int selectedCounterpointIndex = 0;

	public StavePanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		trebleStave = new TrebleStave();
		bassStave = new BassStave();

		panel.add(trebleStave);
		panel.add(bassStave);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(MainFrame.WINDOW_WIDTH -200, 250));
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

		if (parts.length > 1 && parts[1].getPhrase(0) != null) {
			Phrase bassPhrase = parts[1].getPhrase(0);
			bassStave.setPhrase(bassPhrase);
		}

		ranking = new Ranking(treblePhrase, preferences);
	}

	public void playScore() {
		new Thread() {
			@Override
			public void run() {
				Play.midi(getScore());
			}
		}.start();
	}

	public void stopPlaying() {
		Play.stopMidi();
	}

	public void saveToMidi(String filePath) {
		Write.midi(getScore(), filePath);
	}

	public List<EvaluatedPhrase> getRanking() {
		if (ranking == null)
			return null;
		return ranking.getBestRanking();
	}

	public List<EvaluatedPhrase> generateRanking() {
		ranking = new Ranking(trebleStave.getPhrase(), preferences);
		ranking.generateRanking();
		selectedCounterpointIndex = -1;
		setPhrase(0);

		return ranking.getBestRanking();
	}

	public void setPhrase(int index) {
		Phrase generatedCounterpoint = ranking.getBestRanking().get(index).getPhrase();
		System.out.println("index " + index + " points = " + ranking.getBestRanking().get(index).getNumberOfPoints());
		bassStave.setPhrase(generatedCounterpoint);
	}

	public void evaluate() {
		Evaluator evaluator = new Evaluator(trebleStave.getPhrase(), preferences);
		Phrase counterPoint = bassStave.getPhrase();
		int result = evaluator.evaluatePhrase(counterPoint);
		System.out.println("Evaluation = " + result);
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
}
