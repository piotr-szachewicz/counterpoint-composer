package pl.szachewicz;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker.StateValue;

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
import pl.szachewicz.view.MainFrame;
import pl.szachewicz.view.PhraseRankingTablePanel;
import pl.szachewicz.view.StavePanel;
import pl.szachewicz.worker.GenerateRankingWorker;

public class Controller implements PropertyChangeListener {

	//mainFrame elements
	private final MainFrame mainFrame;
	private final StavePanel stavePanel;
	private final PhraseRankingTablePanel phrasesTablePanel;

	private Preferences preferences = new Preferences();
	private Ranking ranking;

	private GenerateRankingWorker generateRankingWorker;

	private int selectedCounterpointIndex = 0;

	public Controller(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.stavePanel = mainFrame.getStavePanel();
		this.phrasesTablePanel = mainFrame.getPhrasesTablePanel();

		preferences.setDefaults();
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void saveResults(String filePath) {
		Score score = stavePanel.getScore();
		Write.jm(score, filePath);
	}

	public void newCounterpoint() {
		stavePanel.setTrebleStavePhrase(new Phrase());
		stavePanel.setBassStavePhrase(new Phrase());
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

	public void generateRanking() {
		selectedCounterpointIndex = -1;
		generateRankingWorker = new GenerateRankingWorker(mainFrame, stavePanel.getTrebleStavePhrase(), preferences);
		generateRankingWorker.addPropertyChangeListener(this);
		generateRankingWorker.execute();
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

	public List<EvaluatedPhrase> getRanking() {
		return ranking.getBestRanking();
	}

	public void propertyChange(PropertyChangeEvent event) {

		if (generateRankingWorker.getState() == StateValue.DONE) {
			try {
				ranking = generateRankingWorker.get();
				phrasesTablePanel.fillFromModel(ranking.getBestRanking());
				setPhrase(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
