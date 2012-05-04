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

public class CounterpointProblem extends Problem implements SimpleProblemForm {

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

		IntegerVectorIndividual ind2 = (IntegerVectorIndividual) ind;

		Phrase phrase = new Phrase();
		phrase.addNoteList(ind2.genome, RhythmValues.QUARTER_NOTE);

		float points = evaluator.evaluatePhrase(phrase);

		((SimpleFitness) ind2.fitness).setFitness(state, points);

		ind2.evaluated = true;

		printIndividualData(ind2);

	}

	protected void printIndividualData(IntegerVectorIndividual ind) {
		System.out.print("fitness: " + ((SimpleFitness)ind.fitness).fitness() +" ::: ");
		for (int i = 0; i < ind.genome.length; i++) {
			System.out.print(ind.genome[i] + ", ");
		}
		System.out.println("");
	}

	@Override
	public void describe(EvolutionState state, Individual ind,
			int subpopulation, int threadnum, int log) {

		ind.printIndividualForHumans(state, 0);
		super.describe(state, ind, subpopulation, threadnum, log);
	}

}
