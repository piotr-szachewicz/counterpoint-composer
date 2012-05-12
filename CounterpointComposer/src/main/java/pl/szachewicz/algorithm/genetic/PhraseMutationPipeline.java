package pl.szachewicz.algorithm.genetic;

import pl.szachewicz.algorithm.Generator;
import ec.BreedingPipeline;
import ec.EvolutionState;
import ec.Individual;
import ec.util.Parameter;
import ec.vector.IntegerVectorIndividual;
import ec.vector.IntegerVectorSpecies;
import ec.vector.VectorDefaults;

public class PhraseMutationPipeline extends BreedingPipeline {

	public static final String P_MUTATION = "mutate";
	public static final int NUM_SOURCES = 1;

	private Generator generator;

	@Override
	public void setup(EvolutionState state, Parameter base) {
		super.setup(state, base);
		generator = (Generator) state.parameters.get("generator");
	}

	public Parameter defaultBase() {
		return VectorDefaults.base().push(P_MUTATION);
	}

	@Override
	public int numSources() {
		return NUM_SOURCES;
	}

	@Override
	public int produce(final int min,
			final int max,
			final int start,
			final int subpopulation,
			final Individual[] inds,
			final EvolutionState state,
			final int thread) {

		//grab individuals from our source and stick 'em right into inds.
		//we'll modify them from there
		int n = sources[0].produce(min,max,start,subpopulation,inds,state,thread);

		// should we bother?
		if (!state.random[thread].nextBoolean(likelihood))
			return reproduce(n, start, subpopulation, inds, state, thread, false);
			// DON'T produce children from source -- we already did

		// clone the individuals if necessary
		if (!(sources[0] instanceof BreedingPipeline))
			for(int q=start;q<n+start;q++)
				inds[q] = (Individual)(inds[q].clone());

		// mutate 'em!
		IntegerVectorSpecies species = (IntegerVectorSpecies)(inds[start].species);
        for(int q=start;q<n+start;q++) {
			IntegerVectorIndividual i = (IntegerVectorIndividual) inds[q];
			for (int x = 0; x < i.genome.length; x++)
				if (state.random[thread].nextBoolean(species.mutationProbability))
					i.genome[x] = generator.generateRandomPitch(x);
			// it's a "new" individual, so it's no longer been evaluated
			i.evaluated = false;
		}

        return n;
    }

}
