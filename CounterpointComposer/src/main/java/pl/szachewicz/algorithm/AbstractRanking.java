package pl.szachewicz.algorithm;

import static pl.szachewicz.algorithm.Helper.debug;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import jm.music.data.Phrase;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.preferences.Preferences;

public class AbstractRanking {
	protected Phrase cantusFirmus;
	protected Preferences preferences;

	private final PropertyChangeSupport pcSupport;
	public static final String PROGRESS_PROPERTY = "progressProperty";

	protected final Generator generator;
	protected final Evaluator evaluator;

	protected final BestPhrasesLibrary bestPhrasesLibrary = new BestPhrasesLibrary();

	private boolean canceled = false;

	public AbstractRanking(Phrase cantusFirmus, Preferences preferences) {
		this.cantusFirmus = cantusFirmus;
		this.preferences = preferences;

		this.generator = new Generator(cantusFirmus, preferences);
		this.evaluator = new Evaluator(cantusFirmus, preferences);
		this.pcSupport = new PropertyChangeSupport(this);
	}

	public void generateRanking() {

		while (generator.hasNext() && !canceled) {
			Phrase phrase = generator.generateNext();
			float points = evaluator.evaluatePhrase(phrase);
			debug(" - " + points);

			bestPhrasesLibrary.tryToAdd(phrase, points);
			fireProgressChanged();
		}
		bestPhrasesLibrary.sort();
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	private void fireProgressChanged() {
		pcSupport.firePropertyChange(PROGRESS_PROPERTY, null, generator.getPercentageComplete());
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}

	public List<EvaluatedPhrase> getBestRanking() {
		return bestPhrasesLibrary.getBestPhrases();
	}

}
