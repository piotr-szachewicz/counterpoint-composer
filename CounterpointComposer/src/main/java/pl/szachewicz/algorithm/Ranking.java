package pl.szachewicz.algorithm;

import java.util.HashMap;

import jm.music.data.Phrase;

public class Ranking {
	private final Phrase cantusFirmus;
	private final HashMap<Integer, Phrase> ranking = new HashMap<Integer, Phrase>();
	private final Generator generator;
	private final Evaluator evaluator;

	public Ranking(Phrase cantusFirmus) {
		this.cantusFirmus = cantusFirmus;
		this.generator = new Generator(cantusFirmus);
		this.evaluator = new Evaluator(cantusFirmus);
	}

	public Phrase getTheBestCounterpoint() {
		while (generator.hasNext()) {
			Phrase phrase = generator.generateNext();
			int points = evaluator.evaluatePhrase(phrase);

			ranking.put(points, phrase);
		}

		int maximum = Integer.MIN_VALUE;
		for (Integer points: ranking.keySet()) {
			if (points > maximum)
				maximum = points;
		}

		return ranking.get(maximum);
	}
}
