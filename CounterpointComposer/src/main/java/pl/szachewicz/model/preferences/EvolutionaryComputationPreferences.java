package pl.szachewicz.model.preferences;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="evolutionaryComputationPreferences")
public class EvolutionaryComputationPreferences {

	private int numberOfGenerations = 5;
	private int populationSize = 400;

	public int getNumberOfGenerations() {
		return numberOfGenerations;
	}
	public void setNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
	}
	public int getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

}
