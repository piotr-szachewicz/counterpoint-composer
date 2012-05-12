package pl.szachewicz.algorithm.genetic;

public class MyEvolutionState extends ec.simple.SimpleEvolutionState {

	private boolean cancelled = false;

	@Override
	public int evolve() {
		if (cancelled)
			return R_SUCCESS;
		return super.evolve();
	}

	public void cancel() {
		this.cancelled = true;
	}

	public boolean isCancelled() {
		return cancelled;
	}

}
