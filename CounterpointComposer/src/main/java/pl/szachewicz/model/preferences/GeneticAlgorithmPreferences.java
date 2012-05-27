package pl.szachewicz.model.preferences;

import pl.szachewicz.algorithm.genetic.CrossoverType;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="geneticAlgorithmPreferences")
public class GeneticAlgorithmPreferences {

	private int numberOfGenerations = 100;
	private int populationSize = 20;

	private CrossoverType crossoverType = CrossoverType.ONE_POINT;
	private float noteCrossoverProbability = 0.05F;
	private float noteMutationProbability = 0.1F;
	private int tournamentSize = 5;

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
	public CrossoverType getCrossoverType() {
		return crossoverType;
	}
	public void setCrossoverType(CrossoverType crossoverType) {
		this.crossoverType = crossoverType;
	}
	public float getNoteCrossoverProbability() {
		return noteCrossoverProbability;
	}
	public void setNoteCrossoverProbability(float crossoverProbability) {
		this.noteCrossoverProbability = crossoverProbability;
	}
	public float getNoteMutationProbability() {
		return noteMutationProbability;
	}
	public void setNoteMutationProbability(float noteMutationProbability) {
		this.noteMutationProbability = noteMutationProbability;
	}
	public int getTournamentSize() {
		return tournamentSize;
	}
	public void setTournamentSize(int tournamentSize) {
		this.tournamentSize = tournamentSize;
	}

}
