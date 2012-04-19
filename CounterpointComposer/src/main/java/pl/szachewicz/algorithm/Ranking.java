package pl.szachewicz.algorithm;

import java.util.ArrayList;
import java.util.List;

import jm.music.data.Phrase;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.preferences.Preferences;

public class Ranking {
	private final Phrase cantusFirmus;
	//private final HashMap<Integer, Phrase> ranking = new HashMap<Integer, Phrase>();

	//private final List<Integer> bestPhrasesPoints = new ArrayList<Integer>();
	//private final List<Phrase> bestPhrases = new ArrayList<Phrase>();

	private final List<EvaluatedPhrase> bestPhrases = new ArrayList<EvaluatedPhrase>();

	private final Generator generator;
	private final Evaluator evaluator;

	private final int numberOfRememberedPhrases = 3;

	private int worseNumberOfPoints = Integer.MAX_VALUE;
	private int worsePhraseIndex;

	public Ranking(Phrase cantusFirmus, Preferences preferences) {
		this.cantusFirmus = cantusFirmus;
		this.generator = new Generator(cantusFirmus, preferences);
		this.evaluator = new Evaluator(cantusFirmus, preferences);
	}

	protected void recalculateWorstPhrase() {
		worseNumberOfPoints = Integer.MAX_VALUE;

		for (int i = 0; i < bestPhrases.size(); i++) {
			int phrasePoints = bestPhrases.get(i).getNumberOfPoints();
			if (phrasePoints < worseNumberOfPoints) {
				worseNumberOfPoints = phrasePoints;
				worsePhraseIndex = i;
			}
			i++;
		}
	}

	public void generateRanking() {
		while (generator.hasNext()) {
			Phrase phrase = generator.generateNext();
			int points = evaluator.evaluatePhrase(phrase);

			if (bestPhrases.size() >= numberOfRememberedPhrases) {
				if (points > worseNumberOfPoints) {
					bestPhrases.add(new EvaluatedPhrase(phrase, points));
					bestPhrases.remove(worsePhraseIndex);
					recalculateWorstPhrase();
				}
			}
			else {
				bestPhrases.add(new EvaluatedPhrase(phrase, points));
//				bestPhrases.remove(worsePhraseIndex);
				recalculateWorstPhrase();
			}
		}
	}

	public List<EvaluatedPhrase> getBestRanking() {
		return bestPhrases;
	}

	/*public Phrase getTheBestCounterpoint() {

		generateRanking();

		int i = 0;
		int max = Integer.MIN_VALUE;
		Phrase bestPhrase = null;
		for (int points: bestPhrasesPoints) {
			if (points > max) {
				bestPhrase = bestPhrases.get(i);
				max = points;
			}
			i++;
		}
		return bestPhrase;
	}*/

}
