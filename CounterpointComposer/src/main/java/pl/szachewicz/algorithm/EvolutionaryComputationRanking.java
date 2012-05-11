package pl.szachewicz.algorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import jm.music.data.Phrase;
import pl.szachewicz.algorithm.genetic.CounterpointProblem;
import pl.szachewicz.algorithm.genetic.Initializer;
import pl.szachewicz.algorithm.genetic.Statistics;
import pl.szachewicz.model.preferences.EvolutionaryComputationPreferences;
import pl.szachewicz.model.preferences.Preferences;
import ec.EvolutionState;
import ec.Evolve;
import ec.util.ParameterDatabase;

public class EvolutionaryComputationRanking extends AbstractRanking {

	public EvolutionaryComputationRanking(Phrase cantusFirmus,
			Preferences preferences) {
		super(cantusFirmus, preferences);
		bestPhrasesLibrary.setWatchOutForDuplicates(true);
	}

	@Override
	public void generateRanking() {
		String s = "genetic.params";
		EvolutionState state;

		Dictionary<String, Object> paramsMap = getParametersHashmap();
		ParameterDatabase parameters;
		try {
			parameters = new ParameterDatabase(paramsMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		parameters.put("generator", generator);
		parameters.put("evaluator", evaluator);

		state = Evolve.initialize(parameters, 0);

		state.run(EvolutionState.C_STARTED_FRESH);

		Evolve.cleanup(state);

		Statistics statistics = (Statistics) state.statistics;

		//bestPhrases
		bestPhrasesLibrary.setBestPhrases(statistics.getBestPhrases());
		bestPhrasesLibrary.sort();
	}

	protected Dictionary<String, Object> getParametersHashmap() {
		Dictionary<String, Object> database = new Hashtable<String, Object>();
		EvolutionaryComputationPreferences ecPreferences = preferences.getEvolutionaryComputationPreferences();

		database.put("verbosity", 0);
		database.put("breedthreads", 1);
		database.put("evalthreads", 1);
		database.put("seed.0", 4357);

		database.put("state", "ec.simple.SimpleEvolutionState");

		database.put("pop", "ec.Population");
		database.put("init", Initializer.class.getCanonicalName());
		database.put("finish", "ec.simple.SimpleFinisher");
		database.put("breed", "ec.simple.SimpleBreeder");
		database.put("eval", "ec.simple.SimpleEvaluator");
		database.put("stat", Statistics.class.getCanonicalName());
		database.put("exch", "ec.simple.SimpleExchanger");

		database.put("generations", ecPreferences.getNumberOfGenerations());
		database.put("quit-on-run-complete", false);
		database.put("checkpoint", false);
		database.put("prefix", "ec");
		database.put("checkpoint-modulo", 1);
		database.put("stat.file", "$out.stat");

		database.put("pop.subpops", 1);
		database.put("pop.subpop.0","ec.Subpopulation");

		database.put("pop.subpop.0.size", ecPreferences.getPopulationSize());
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

		database.put("eval.problem", CounterpointProblem.class.getCanonicalName());

		return database;
	}

}
