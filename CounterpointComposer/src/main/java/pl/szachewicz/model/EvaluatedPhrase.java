package pl.szachewicz.model;

import jm.music.data.Phrase;

public class EvaluatedPhrase {

	private Phrase phrase;
	private int numberOfPoints;

	public EvaluatedPhrase(Phrase phrase, int numberOfPoints) {
		super();
		this.phrase = phrase;
		this.numberOfPoints = numberOfPoints;
	}

	public Phrase getPhrase() {
		return phrase;
	}

	public void setPhrase(Phrase phrase) {
		this.phrase = phrase;
	}

	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

}
