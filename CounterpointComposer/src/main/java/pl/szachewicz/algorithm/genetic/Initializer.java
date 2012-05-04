package pl.szachewicz.algorithm.genetic;

import pl.szachewicz.algorithm.Generator;
import ec.EvolutionState;
import ec.Individual;
import ec.Population;
import ec.simple.SimpleFitness;
import ec.simple.SimpleInitializer;
import ec.vector.IntegerVectorIndividual;

public class Initializer extends SimpleInitializer {

	private Generator generator;

	@Override
	public Population initialPopulation(EvolutionState state, int thread) {

		generator = (Generator) state.parameters.get("generator");

		Population p = setupPopulation(state, thread);

		Individual[] individuals = p.subpops[0].individuals;
		for (int i = 0; i < individuals.length; i++ ) {
			individuals[i] = new IntegerVectorIndividual();

			((IntegerVectorIndividual) individuals[i]).setGenome(
					generator.generateRandom().getPitchArray()
					//new int[] { 5,5,5,5,5, 5,5,5,5,5}
					);
			individuals[i].fitness = new SimpleFitness();
			individuals[i].species = p.subpops[0].species;
		}

        return p;
	}

}
