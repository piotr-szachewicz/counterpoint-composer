package pl.szachewicz.algorithm;

import jm.music.data.Phrase;
import pl.szachewicz.model.Interval;

public class Evaluator {

	private final Phrase cantusFirmus;

	public Evaluator(Phrase cantusFirmus) {
		this.cantusFirmus = cantusFirmus;
	}

	public int evaluatePhrase(Phrase phrase) {
		int points = 0;

		for (int i = 0; i < phrase.size(); i++) {
			int counterpointPitch = phrase.getNote(i).getPitch();

			if (i > 0) {
				int counterpointPreviousPitch = phrase.getNote(i-1).getPitch();
				int cantusFirmusPitch = cantusFirmus.getNote(i).getPitch();
				int cantusFirmusPreviousPitch = cantusFirmus.getNote(i-1).getPitch();

				if (
						(cantusFirmusPitch > cantusFirmusPreviousPitch
						&& counterpointPitch > counterpointPreviousPitch)
						|| (cantusFirmusPitch < cantusFirmusPreviousPitch
						&& counterpointPitch < counterpointPreviousPitch)) {
					points--;
				}

				if (counterpointPitch == counterpointPreviousPitch) {
					points -= 3;
				}

				int interval = counterpointPitch - counterpointPreviousPitch;
				if (counterpointPitch - counterpointPreviousPitch > Interval.MAJOR_SECOND.getNumberOfSemitones()) {
					if (interval > Interval.MAJOR_THIRD.getNumberOfSemitones())
						points -= 7;
					else
						points--;
				}

				if (i > 1) {
					int counterpointTwoBehind = phrase.getNote(i-2).getPitch();
					if (counterpointTwoBehind == counterpointPreviousPitch
							&& counterpointPreviousPitch == counterpointPitch) {
						points -= 10;
					}
				}
			}
		}

		return points;
	}
}
