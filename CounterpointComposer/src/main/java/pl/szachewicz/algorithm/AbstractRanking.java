package pl.szachewicz.algorithm;

import static pl.szachewicz.algorithm.Helper.debug;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jm.music.data.Phrase;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.EvaluatedPhraseComparator;
import pl.szachewicz.model.preferences.Preferences;

public class AbstractRanking {
	protected Phrase cantusFirmus;

	private final PropertyChangeSupport pcSupport;
	public static final String PROGRESS_PROPERTY = "progressProperty";

	protected List<EvaluatedPhrase> bestPhrases = new ArrayList<EvaluatedPhrase>();

	protected final Generator generator;
	protected final Evaluator evaluator;

	private final int numberOfRememberedPhrases = 15;

	private float worseNumberOfPoints = Float.MAX_VALUE;
	private int worsePhraseIndex;

	private boolean canceled = false;

	public AbstractRanking(Phrase cantusFirmus, Preferences preferences) {
		this.cantusFirmus = cantusFirmus;
		this.generator = new Generator(cantusFirmus, preferences);
		this.evaluator = new Evaluator(cantusFirmus, preferences);
		this.pcSupport = new PropertyChangeSupport(this);
	}

	protected void recalculateWorstPhrase() {
		worseNumberOfPoints = Integer.MAX_VALUE;

		for (int i = 0; i < bestPhrases.size(); i++) {
			float phrasePoints = bestPhrases.get(i).getNumberOfPoints();
			if (phrasePoints < worseNumberOfPoints) {
				worseNumberOfPoints = phrasePoints;
				worsePhraseIndex = i;
			}
			i++;
		}
	}

	public void generateRanking() {

		while (generator.hasNext() && !canceled) {
			Phrase phrase = generator.generateNext();
			float points = evaluator.evaluatePhrase(phrase);
			debug(" - " + points);

			if (bestPhrases.size() >= numberOfRememberedPhrases) {
				if (points > worseNumberOfPoints) {
					debug(" - ADDED (better than " + worseNumberOfPoints + ")");
					bestPhrases.add(new EvaluatedPhrase(phrase, points));
					bestPhrases.remove(worsePhraseIndex);
					recalculateWorstPhrase();
				}
			}
			else {
				debug(" - ADDED");
				bestPhrases.add(new EvaluatedPhrase(phrase, points));
				recalculateWorstPhrase();
			}
			debug("\n");
			fireProgressChanged();
		}
		sortBestCounterpointRanking();
	}

	protected void sortBestCounterpointRanking() {
		Collections.sort(bestPhrases, new EvaluatedPhraseComparator());
		Collections.reverse(bestPhrases);
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
		return bestPhrases;
	}

}
