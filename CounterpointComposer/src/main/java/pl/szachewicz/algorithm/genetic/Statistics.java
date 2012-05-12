package pl.szachewicz.algorithm.genetic;

import java.util.List;

import jm.constants.RhythmValues;
import jm.music.data.Phrase;
import pl.szachewicz.algorithm.BestPhrasesLibrary;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.preferences.EvolutionaryComputationPreferences;
import pl.szachewicz.model.preferences.Preferences;
import ec.EvolutionState;
import ec.simple.SimpleFitness;
import ec.util.Parameter;
import ec.vector.IntegerVectorIndividual;

public class Statistics extends ec.Statistics{

	private final BestPhrasesLibrary bestPhrasesLibrary = new BestPhrasesLibrary();

	private double currentEvaluationNumber;
	private double totalNumberOfEvaluations;

	@Override
	public void setup(EvolutionState state, Parameter base) {
		super.setup(state, base);

		Preferences preferences = (Preferences) state.parameters.get("preferences");
		EvolutionaryComputationPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();

		int numberOfGenerations = evolutionaryComputationPreferences.getNumberOfGenerations();
		int populationSize = evolutionaryComputationPreferences.getPopulationSize();
		this.totalNumberOfEvaluations = numberOfGenerations * populationSize;
	}

	@Override
	public void postEvaluationStatistics(EvolutionState state) {

		IntegerVectorIndividual individual;
		for(int x=0;x<state.population.subpops.length;x++)
        {
			for(int y=1; y < state.population.subpops[x].individuals.length; y++) {
				individual = (IntegerVectorIndividual) state.population.subpops[x].individuals[y];

				Phrase phrase = new Phrase();
				phrase.addNoteList(individual.genome, RhythmValues.QUARTER_NOTE);
				float fitness = ((SimpleFitness) individual.fitness).fitness();

				bestPhrasesLibrary.tryToAdd(phrase, fitness);

				currentEvaluationNumber++;
			}
        }
	}

	public int getPercentageComplete() {
		return (int) Math.ceil(currentEvaluationNumber / totalNumberOfEvaluations * 100);
	}

	public List<EvaluatedPhrase> getBestPhrases() {
		/* population = 400
		 * generations = 5
		 *
		 * total = 400 * 5 (about)
		 */
		System.out.println("current = " + currentEvaluationNumber);
		return bestPhrasesLibrary.getBestPhrases();
	}

}
