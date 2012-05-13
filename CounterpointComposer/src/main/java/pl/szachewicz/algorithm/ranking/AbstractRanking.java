package pl.szachewicz.algorithm.ranking;

import static pl.szachewicz.algorithm.Helper.debug;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jm.music.data.Phrase;
import pl.szachewicz.algorithm.Evaluator;
import pl.szachewicz.algorithm.Generator;
import pl.szachewicz.algorithm.library.PhrasesLibrary;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.preferences.Preferences;

public class AbstractRanking {

	protected Timer progressTimer;

	protected Phrase cantusFirmus;
	protected Preferences preferences;

	private final PropertyChangeSupport pcSupport;
	public static final String PROGRESS_PROPERTY = "progressProperty";

	protected final Generator generator;
	protected final Evaluator evaluator;

	protected PhrasesLibrary bestPhrasesLibrary;

	private boolean canceled = false;

	public AbstractRanking(Phrase cantusFirmus, Preferences preferences) {
		this.cantusFirmus = cantusFirmus;
		this.preferences = preferences;

		this.generator = new Generator(cantusFirmus, preferences);
		this.evaluator = new Evaluator(cantusFirmus, preferences);
		this.pcSupport = new PropertyChangeSupport(this);

		bestPhrasesLibrary = PhrasesLibrary.createPhrasesLibrary(preferences);
	}

	public Timer getProgressTimer() {
		if (progressTimer == null) {
			progressTimer = new Timer();
		}
		return progressTimer;
	}

	public void generateRanking() {

		progressTimer = new Timer();
		progressTimer.schedule(new ProgressTimerTask(), Calendar.getInstance().getTime(), 100);

		long startTime = System.currentTimeMillis();

		generationLoop();
		bestPhrasesLibrary.sort();

		long endTime = System.currentTimeMillis();

		System.out.println("========================= ");
		System.out.println("DONE - EXECUTION TIME [ms]: ");
		System.out.println(endTime - startTime);
		System.out.println("========================= ");

		fireProgressChanged();
		progressTimer.cancel();
	}

	protected void generationLoop() {
		while (generator.hasNext() && !canceled) {
			Phrase phrase = generator.generateNext();
			float points = evaluator.evaluatePhrase(phrase);
			debug(" - " + points);

			bestPhrasesLibrary.tryToAdd(phrase, points);
		}
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	private void fireProgressChanged() {
		pcSupport.firePropertyChange(PROGRESS_PROPERTY, null, getPercentageComplete());
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}

	public List<EvaluatedPhrase> getBestRanking() {
		return bestPhrasesLibrary.getBestPhrases();
	}

	protected int getPercentageComplete() {
		return generator.getPercentageComplete();
	}

	class ProgressTimerTask extends TimerTask {
		@Override
		public void run() {
			fireProgressChanged();
		}
	}

}
