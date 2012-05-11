package pl.szachewicz.model.preferences;

import java.lang.reflect.Constructor;

import jm.music.data.Phrase;
import pl.szachewicz.algorithm.AbstractRanking;
import pl.szachewicz.algorithm.EvolutionaryComputationRanking;
import pl.szachewicz.algorithm.FullSearchRanking;


public enum SearchAlgorithm {

	FULL_SEARCH("full search", FullSearchRanking.class),
	EVOLUTIONARY_COMPUTATION("evoluationary computation", EvolutionaryComputationRanking.class);

	private String displayName;
	private Class rankingClass;

	private SearchAlgorithm(String displayName, Class rankingClass) {
		this.displayName = displayName;
		this.rankingClass = rankingClass;
	}

	public AbstractRanking createRanking(Phrase cantusFirmus,Preferences preferences) {

		try {
			Constructor constructor = rankingClass.getConstructor(Phrase.class,Preferences.class);
			return (AbstractRanking) constructor.newInstance(cantusFirmus, preferences);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
