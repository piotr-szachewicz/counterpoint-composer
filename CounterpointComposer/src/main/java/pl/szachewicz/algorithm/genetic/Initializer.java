package pl.szachewicz.algorithm.genetic;

import pl.szachewicz.algorithm.Generator;
import ec.BreedingSource;
import ec.EvolutionState;
import ec.Individual;
import ec.Population;
import ec.Species;
import ec.select.TournamentSelection;
import ec.simple.SimpleFitness;
import ec.simple.SimpleInitializer;
import ec.vector.IntegerVectorIndividual;
import ec.vector.IntegerVectorSpecies;
import ec.vector.breed.VectorCrossoverPipeline;
import ec.vector.breed.VectorMutationPipeline;

public class Initializer extends SimpleInitializer {

	private int counterpointSize = 10;
	private IntegerVectorSpecies species = null;
	private Generator generator;

	@Override
	public Population initialPopulation(EvolutionState state, int thread) {

		generator = (Generator) state.parameters.get("generator");
		counterpointSize = generator.getCantusFirmus().getSize();

		Population p = setupPopulation(state, thread);
		Species species = getSpecies();

		Individual[] individuals = p.subpops[0].individuals;
		for (int i = 0; i < individuals.length; i++ ) {
			individuals[i] = new IntegerVectorIndividual();

			((IntegerVectorIndividual) individuals[i]).setGenome(
					generator.generateRandom().getPitchArray()
					//new int[] { 5,5,5,5,5, 5,5,5,5,5}
					);
			individuals[i].fitness = new SimpleFitness();
			individuals[i].species = species;
		}

        return p;
	}

	protected Species getSpecies() {
		if (species == null) {
			species = new IntegerVectorSpecies();

			species.minGenes = new long[counterpointSize];
			for (int i = 0; i < species.minGenes.length; i++)
				species.minGenes[i] = 0;

			species.maxGenes = new long[counterpointSize];
			for (int i = 0; i < species.maxGenes.length; i++)
				species.maxGenes[i] = 255;
			//species.minGene(0);
			//species.maxGene(6);
			species.f_prototype = new SimpleFitness();
			species.i_prototype = new IntegerVectorIndividual();
			species.genomeSize = counterpointSize;
			species.crossoverProbability = 1.0F;
			species.crossoverType = 1;
			species.mutationProbability = 0.01F;
			species.chunksize = 5;

			species.pipe_prototype = new VectorMutationPipeline();
			species.pipe_prototype.sources = new BreedingSource[1];
			species.pipe_prototype.sources[0] = new VectorCrossoverPipeline();

			((VectorCrossoverPipeline)species.pipe_prototype.sources[0]).sources = new BreedingSource[2];
			((VectorCrossoverPipeline)species.pipe_prototype.sources[0]).sources[0] = new TournamentSelection();
			((VectorCrossoverPipeline)species.pipe_prototype.sources[0]).sources[1] = new TournamentSelection();
		}
		return species;
	}

	@Override
	public Population setupPopulation(EvolutionState state, int thread) {

		return super.setupPopulation(state, thread);

		/*Population p = new Population();

		p.subpops = new Subpopulation[1];

		p.subpops[0] = new Subpopulation();
		p.subpops[0].species = getSpecies();
		p.subpops[0].individuals = new Individual[100];
		p.subpops[0].numDuplicateRetries = 0;

		return p;*/
	}

}
