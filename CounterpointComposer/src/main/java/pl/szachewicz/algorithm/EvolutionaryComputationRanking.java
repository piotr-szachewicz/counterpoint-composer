package pl.szachewicz.algorithm;

import jm.music.data.Phrase;
import pl.szachewicz.algorithm.genetic.EvolutionaryAlgorithm;
import pl.szachewicz.model.preferences.Preferences;

public class EvolutionaryComputationRanking extends AbstractRanking {

	public EvolutionaryComputationRanking(Phrase cantusFirmus,
			Preferences preferences) {
		super(cantusFirmus, preferences);
		bestPhrasesLibrary.setWatchOutForDuplicates(true);
	}

	@Override
	public void generateRanking() {
		bestPhrasesLibrary.setBestPhrases(EvolutionaryAlgorithm.run(generator, evaluator));
		bestPhrasesLibrary.sort();
	}

}
