package pl.szachewicz.algorithm.genetic;

import java.util.List;

import jm.constants.RhythmValues;
import jm.music.data.Phrase;
import pl.szachewicz.algorithm.BestPhrasesLibrary;
import pl.szachewicz.model.EvaluatedPhrase;
import ec.EvolutionState;
import ec.simple.SimpleFitness;
import ec.vector.IntegerVectorIndividual;

public class Statistics extends ec.Statistics{

	private final BestPhrasesLibrary bestPhrasesLibrary = new BestPhrasesLibrary();

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
			}
        }
	}

	public List<EvaluatedPhrase> getBestPhrases() {
		return bestPhrasesLibrary.getBestPhrases();
	}

}
