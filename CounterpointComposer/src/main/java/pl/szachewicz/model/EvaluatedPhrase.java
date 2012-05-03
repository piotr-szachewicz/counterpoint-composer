package pl.szachewicz.model;

import jm.music.data.Phrase;

public class EvaluatedPhrase {

	private Phrase phrase;
	private float numberOfPoints;

	public EvaluatedPhrase(Phrase phrase, float numberOfPoints) {
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

	public float getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(float numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

}
