package pl.szachewicz.algorithm.library;

import static pl.szachewicz.algorithm.Helper.debug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jm.music.data.Phrase;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.EvaluatedPhraseComparator;
import pl.szachewicz.model.preferences.OptimalizationGoal;
import pl.szachewicz.model.preferences.Preferences;

public abstract class PhrasesLibrary {

	protected List<EvaluatedPhrase> bestPhrases = new ArrayList<EvaluatedPhrase>();

	protected final int numberOfRememberedPhrases = 15;

	protected float lastNumberOfPoints = Float.MAX_VALUE;
	protected int lastPhraseIndex;
	protected boolean watchOutForDuplicates = false;

	protected void recalculateLastPhrase() {
		lastNumberOfPoints = bestPhrases.get(0).getNumberOfPoints();

		for (int i = 0; i < bestPhrases.size(); i++) {
			float phrasePoints = bestPhrases.get(i).getNumberOfPoints();
			if (!isBetterThanLast(phrasePoints)) {
				lastNumberOfPoints = phrasePoints;
				lastPhraseIndex = i;
			}
			i++;
		}
	}

	protected abstract boolean isBetterThanLast(float points);

	public void tryToAdd(Phrase phrase, float points) {
		if (bestPhrases.size() >= numberOfRememberedPhrases) {
			if (isBetterThanLast(points)) {
				debug(" - ADDED (better than " + lastNumberOfPoints + ")");
				bestPhrases.remove(lastPhraseIndex);
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
			recalculateLastPhrase();
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

	public static PhrasesLibrary createPhrasesLibrary(Preferences preferences) {
		if (preferences.getOptimalizationGoal() == OptimalizationGoal.MAXIMIZE)
			return new BestPhrasesLibrary();
		else
			return new WorsePhrasesLibrary();
	}

}
