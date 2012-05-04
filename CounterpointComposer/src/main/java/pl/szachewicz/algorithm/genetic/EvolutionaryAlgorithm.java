package pl.szachewicz.algorithm.genetic;

import ec.EvolutionState;
import ec.Evolve;
import ec.util.ParameterDatabase;

public class EvolutionaryAlgorithm {

	public static void run() {
		String s = "genetic.params";
		EvolutionState state;
		ParameterDatabase parameters = Evolve.loadParameterDatabase(new String[] {"-file", s});
		state = Evolve.initialize(parameters, 0);
		state.run(EvolutionState.C_STARTED_FRESH);

		Evolve.cleanup(state);
	}

}
