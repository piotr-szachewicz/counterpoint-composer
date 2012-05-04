package pl.szachewicz.algorithm.genetic;

import java.util.ArrayList;
import java.util.List;

import jm.JMC;
import jm.music.data.Phrase;
import pl.szachewicz.algorithm.Evaluator;
import pl.szachewicz.algorithm.Generator;
import pl.szachewicz.model.EvaluatedPhrase;
import ec.EvolutionState;
import ec.Evolve;
import ec.simple.SimpleFitness;
import ec.simple.SimpleStatistics;
import ec.util.ParameterDatabase;
import ec.vector.IntegerVectorIndividual;

public class EvolutionaryAlgorithm {

	public static List<EvaluatedPhrase> run(Generator generator, Evaluator evaluator) {
		String s = "genetic.params";
		EvolutionState state;
		ParameterDatabase parameters = Evolve.loadParameterDatabase(new String[] {"-file", s});

		parameters.put("generator", generator);
		parameters.put("evaluator", evaluator);

		state = Evolve.initialize(parameters, 0);

		state.run(EvolutionState.C_STARTED_FRESH);

		Evolve.cleanup(state);

		List<EvaluatedPhrase> list = new ArrayList<EvaluatedPhrase>();

		SimpleStatistics statistics = (SimpleStatistics) state.statistics;
		IntegerVectorIndividual bestIndividual = (IntegerVectorIndividual) statistics.best_of_run[0];
		Phrase phrase = new Phrase();
		phrase.addNoteList(bestIndividual.genome, JMC.QUARTER_NOTE);

		SimpleFitness fitness = (SimpleFitness) bestIndividual.fitness;
		float fitnessValue = fitness.fitness();

		list.add(new EvaluatedPhrase(phrase, fitnessValue));

		return list;
	}

}
