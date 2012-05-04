package pl.szachewicz.algorithm.genetic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
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

		Dictionary<String, Object> paramsMap = getParametersHashmap();
		ParameterDatabase parameters;
		try {
			parameters = new ParameterDatabase(paramsMap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

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

	protected static Dictionary<String, Object> getParametersHashmap() {
		Dictionary<String, Object> database = new Hashtable<String, Object>();

		database.put("verbosity", 0);
		database.put("breedthreads", 1);
		database.put("evalthreads", 1);
		database.put("seed.0", 4357);

		database.put("state", "ec.simple.SimpleEvolutionState");

		database.put("pop", "ec.Population");
		database.put("init", "pl.szachewicz.algorithm.genetic.Initializer");
		database.put("finish", "ec.simple.SimpleFinisher");
		database.put("breed", "ec.simple.SimpleBreeder");
		database.put("eval", "ec.simple.SimpleEvaluator");
		database.put("stat", "ec.simple.SimpleStatistics");
		database.put("exch", "ec.simple.SimpleExchanger");

		database.put("generations", 5);
		database.put("quit-on-run-complete", false);
		database.put("checkpoint", false);
		database.put("prefix", "ec");
		database.put("checkpoint-modulo", 1);
		database.put("stat.file", "$out.stat");

		database.put("pop.subpops", 1);
		database.put("pop.subpop.0","ec.Subpopulation");

		database.put("pop.subpop.0.size", 10);
		database.put("pop.subpop.0.duplicate-retries", 0);

		database.put("pop.subpop.0.species", "ec.vector.IntegerVectorSpecies");
		database.put("pop.subpop.0.species.min-gene", 0);
		database.put("pop.subpop.0.species.max-gene", 6);

		database.put("pop.subpop.0.species.fitness", "ec.simple.SimpleFitness");
		database.put("pop.subpop.0.species.ind", "ec.vector.IntegerVectorIndividual");

		database.put("pop.subpop.0.species.genome-size", 10);
		database.put("pop.subpop.0.species.crossover-type", "one");
		database.put("pop.subpop.0.species.crossover-prob", 1.0F);
		database.put("pop.subpop.0.species.mutation-prob", 0.01F);

		database.put("pop.subpop.0.species.pipe", "ec.vector.breed.VectorMutationPipeline");
		database.put("pop.subpop.0.species.pipe.source.0", "ec.vector.breed.VectorCrossoverPipeline");
		database.put("pop.subpop.0.species.pipe.source.0.source.0", "ec.select.TournamentSelection");
		database.put("pop.subpop.0.species.pipe.source.0.source.1", "ec.select.TournamentSelection");

		database.put("select.tournament.size", 2);

		database.put("eval.problem", "pl.szachewicz.algorithm.genetic.MaxOnes");

		return database;
	}

}
