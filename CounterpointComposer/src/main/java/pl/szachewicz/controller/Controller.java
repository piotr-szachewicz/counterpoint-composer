package pl.szachewicz.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker.StateValue;

import jm.music.data.Phrase;
import jm.util.Play;
import pl.szachewicz.algorithm.Evaluator;
import pl.szachewicz.algorithm.Ranking;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.Dialogs;
import pl.szachewicz.view.MainFrame;
import pl.szachewicz.view.PhraseRankingTablePanel;
import pl.szachewicz.view.StavePanel;
import pl.szachewicz.worker.GenerateRankingWorker;

public class Controller implements PropertyChangeListener {

	private final IOController ioController;

	//mainFrame elements
	private final MainFrame mainFrame;
	private final StavePanel stavePanel;
	private final PhraseRankingTablePanel phrasesTablePanel;

	private Preferences preferences = new Preferences();
	private Ranking ranking;

	private GenerateRankingWorker generateRankingWorker;

	public Controller(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.stavePanel = mainFrame.getStavePanel();
		this.phrasesTablePanel = mainFrame.getPhrasesTablePanel();

		ioController = new IOController(stavePanel);

		//preferences.setDefaults();
		preferences = PreferencesManager.loadPreferences();
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void newCounterpoint() {
		stavePanel.setTrebleStavePhrase(new Phrase());
		stavePanel.setBassStavePhrase(new Phrase());
		mainFrame.getConsolePanel().fillViewFromModel("");
		mainFrame.getPhrasesTablePanel().fillFromModel(null);
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

	public void generateRanking() {
		generateRankingWorker = new GenerateRankingWorker(mainFrame, stavePanel.getTrebleStavePhrase(), preferences);
		generateRankingWorker.addPropertyChangeListener(this);
		generateRankingWorker.execute();
	}

	public void setPhrase(int index) {
		Phrase generatedCounterpoint = ranking.getBestRanking().get(index).getPhrase();
		//System.out.println("index " + index + " points = " + ranking.getBestRanking().get(index).getNumberOfPoints());
		stavePanel.setBassStavePhrase(generatedCounterpoint);
		evaluate();
	}

	public void evaluate() {
		Phrase cantusFirmus = stavePanel.getTrebleStavePhrase();
		Phrase counterPoint = stavePanel.getBassStavePhrase();

		String evaluationLog = "";

		if (cantusFirmus.size() != counterPoint.size()) {
			Dialogs.showErrorDialog("Cantus firmus and counterpoint must of equal lengths.");
		}
		else {
			Evaluator evaluator = new Evaluator(cantusFirmus, preferences);
			evaluator.evaluatePhrase(counterPoint);
			evaluationLog = evaluator.getEvaluationLog();
		}

		mainFrame.getConsolePanel().fillViewFromModel(evaluationLog);
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
				if (ranking.getBestRanking().size() > 0)
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

	public void loadScoreFromJMFile(String filePath) {
		ioController.loadScoreFromJMFile(filePath);
	}

	public void saveToMidi(String filePath) {
		ioController.saveToMidi(filePath);
	}

	public void saveResults(String filePath) {
		ioController.saveResults(filePath);
	}

}
