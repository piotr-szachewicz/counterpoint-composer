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
	private boolean watchOutForDuplicates = false;

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
				bestPhrases.remove(worsePhraseIndex);
				addPhrase(phrase, points);
			}
		}
		else {
			debug(" - ADDED");
			addPhrase(phrase, points);
		}
		debug("\n");
	}

	protected void addPhrase(Phrase phrase, float points) {
		if (!isPhraseAlreadyIncluded(phrase, points)) {
			bestPhrases.add(new EvaluatedPhrase(phrase, points));
			recalculateWorstPhrase();
		}
	}

	public void sort() {
		Collections.sort(bestPhrases, new EvaluatedPhraseComparator());
		Collections.reverse(bestPhrases);
	}

	public boolean isPhraseAlreadyIncluded(Phrase phrase, float points) {
		for (EvaluatedPhrase libraryPhrase: bestPhrases) {
			if (libraryPhrase.getNumberOfPoints() == points) {	//to make the search faster
				if (libraryPhrase.getPhrase().equals(phrase)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<EvaluatedPhrase> getBestPhrases() {
		return bestPhrases;
	}

	public void setBestPhrases(List<EvaluatedPhrase> bestPhrases) {
		this.bestPhrases = bestPhrases;
	}

	public void setWatchOutForDuplicates(boolean watchOutForDuplicates) {
		this.watchOutForDuplicates = watchOutForDuplicates;
	}

	public boolean isWatchOutForDuplicates() {
		return watchOutForDuplicates;
	}

}
