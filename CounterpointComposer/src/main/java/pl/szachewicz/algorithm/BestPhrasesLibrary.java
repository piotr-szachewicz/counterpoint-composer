package pl.szachewicz.algorithm;

import static pl.szachewicz.algorithm.Helper.debug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jm.music.data.Phrase;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.EvaluatedPhraseComparator;

public class BestPhrasesLibrary {

	protected List<EvaluatedPhrase> bestPhrases = new ArrayList<EvaluatedPhrase>();

	private final int numberOfRememberedPhrases = 15;

	private float worseNumberOfPoints = Float.MAX_VALUE;
	private int worsePhraseIndex;

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

	public void tryToAdd(Phrase phrase, float points) {
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
	}

	public void sort() {
		Collections.sort(bestPhrases, new EvaluatedPhraseComparator());
		Collections.reverse(bestPhrases);
	}

	public List<EvaluatedPhrase> getBestPhrases() {
		return bestPhrases;
	}

	public void setBestPhrases(List<EvaluatedPhrase> bestPhrases) {
		this.bestPhrases = bestPhrases;
	}

}
