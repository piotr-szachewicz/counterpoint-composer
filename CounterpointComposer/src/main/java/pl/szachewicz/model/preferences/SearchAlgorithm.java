package pl.szachewicz.model.preferences;

import java.lang.reflect.Constructor;

import jm.music.data.Phrase;
import pl.szachewicz.algorithm.ranking.AbstractRanking;
import pl.szachewicz.algorithm.ranking.FullSearchRanking;
import pl.szachewicz.algorithm.ranking.GeneticAlgorithmRanking;


public enum SearchAlgorithm {

	FULL_SEARCH("full search", FullSearchRanking.class),
	GENETIC_ALGORITHM("genetic algorithm", GeneticAlgorithmRanking.class);

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
