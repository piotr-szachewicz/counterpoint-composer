/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
 */

package pl.szachewicz.algorithm.genetic;

import jm.constants.RhythmValues;
import jm.music.data.Phrase;
import pl.szachewicz.algorithm.Evaluator;
import ec.EvolutionState;
import ec.Individual;
import ec.Problem;
import ec.simple.SimpleFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import ec.vector.IntegerVectorIndividual;

public class MaxOnes extends Problem implements SimpleProblemForm {

	private Evaluator evaluator;

	@Override
	public void setup(EvolutionState state, Parameter base) {
		super.setup(state, base);

		this.evaluator = (Evaluator) state.parameters.get("evaluator");
	}

	public void evaluate(final EvolutionState state, final Individual ind,
			final int subpopulation, final int threadnum) {
		if (ind.evaluated)
			return;

		if (!(ind instanceof IntegerVectorIndividual))
			state.output.fatal("Whoa!  It's not a IntegerVectorIndividual!!!",
					null);

		int sum = 0;
		IntegerVectorIndividual ind2 = (IntegerVectorIndividual) ind;

		Phrase phrase = new Phrase();
		phrase.addNoteList(ind2.genome, RhythmValues.QUARTER_NOTE);

		float points = evaluator.evaluatePhrase(phrase);

		((SimpleFitness) ind2.fitness).setFitness(state, points);

		/*for (int x = 0; x < ind2.genome.length; x++)
			sum += (ind2.genome[x] % 2 == 0 ? 1 : 0);

		if (!(ind2.fitness instanceof SimpleFitness))
			state.output.fatal("Whoa!  It's not a SimpleFitness!!!", null);
		((SimpleFitness) ind2.fitness).setFitness(state,
		// / ...the fitness...
				(float) (((double) sum) / ind2.genome.length),
				// /... is the individual ideal? Indicate here...
				sum == ind2.genome.length);*/




		ind2.evaluated = true;

		System.out.println("---------------");
		ind2.printIndividualForHumans(state, 0);
		System.out.println("---------------");
	}

	@Override
	public void describe(EvolutionState state, Individual ind,
			int subpopulation, int threadnum, int log) {

		ind.printIndividualForHumans(state, 0);
		super.describe(state, ind, subpopulation, threadnum, log);
	}

}
