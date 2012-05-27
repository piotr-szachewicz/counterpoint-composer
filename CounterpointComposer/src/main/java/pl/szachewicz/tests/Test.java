package pl.szachewicz.tests;

import java.util.ArrayList;
import java.util.List;

import jm.constants.Pitches;
import jm.constants.RhythmValues;
import jm.music.data.Note;
import jm.music.data.Phrase;
import pl.szachewicz.algorithm.ranking.AbstractRanking;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.preferences.GeneticAlgorithmPreferences;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.model.preferences.SearchAlgorithm;

public class Test {

	private final Preferences preferences;
	private final StringBuilder logBuilder = new StringBuilder();

	private final int[] notes = new int[] {
			Pitches.c4, Pitches.g4, Pitches.f4, Pitches.e4,
			Pitches.f4, Pitches.e4, Pitches.d4, Pitches.e4,
			Pitches.f4, Pitches.e4, Pitches.f4, Pitches.e4
	};

	public static void main(String[] args) {
		Test test = new Test();
		test.performTest();
	}

	public Test() {
		preferences = new Preferences();
		preferences.setDefaults();
		preferences.setSearchAlgorithm(SearchAlgorithm.GENETIC_ALGORITHM);
		GeneticAlgorithmPreferences ecPreferences = preferences.getEvolutionaryComputationPreferences();
		/*ecPreferences.setNumberOfGenerations(5);
		ecPreferences.setPopulationSize(400);
		ecPreferences.setTournamentSize(2);
		ecPreferences.setCrossoverProbability(1.0F);*/
	}

	public void performTest() {
		SearchAlgorithm searchAlgorithm = preferences.getSearchAlgorithm();

		//for (int length = 4; length <= notes.length; length++) {
		for (int length = notes.length; length >= 4; length--) {
			Phrase cantusFirmus = getCantusFirmus(length);
			AbstractRanking ranking = searchAlgorithm.createRanking(cantusFirmus, preferences);
			List<Float> points = new ArrayList<Float>();
			List<Float> executionTimes = new ArrayList<Float>();

			for (int i = 0; i < 10; i++) {
				ranking.generateRanking();

				List<EvaluatedPhrase> bestPhrases = ranking.getBestRanking();

				EvaluatedPhrase bestCounterpoint = bestPhrases.get(0);
				int size = bestCounterpoint.getPhrase().size();
				float numberOfPoints = bestCounterpoint.getNumberOfPoints();

				points.add(numberOfPoints);
				executionTimes.add(ranking.getExecutionTime());
			}

			float avgPoints = calculateAverage(points);
			float avgTime = calculateAverage(executionTimes);

			String s = cantusFirmus.length() + "\t" + avgTime + "\t" + avgPoints;
			System.out.println(s);
			logBuilder.append(s + "\n");
		}

		System.out.println("=============");
		System.out.println("TEST SUMMARY:");
		System.out.println(logBuilder.toString());

	}

	protected float calculateAverage(List<Float> list) {
		float avg = 0.0F;
		for (Float point: list)
			avg += point;
		avg = avg / list.size();

		return avg;
	}

	protected Phrase getCantusFirmus(int size) {

		Phrase cantusFirmusPhrase = new Phrase();

		for (int i = 0; i < size; i++) {
			cantusFirmusPhrase.addNote(new Note(notes[i], RhythmValues.QUARTER_NOTE));
		}

		return cantusFirmusPhrase;
	}

}
