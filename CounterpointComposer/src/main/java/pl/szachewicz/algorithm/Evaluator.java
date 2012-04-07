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

				//jeśli jest ruch równoległy: -1 punkt
				if (
						(cantusFirmusPitch > cantusFirmusPreviousPitch
						&& counterpointPitch > counterpointPreviousPitch)
						|| (cantusFirmusPitch < cantusFirmusPreviousPitch
						&& counterpointPitch < counterpointPreviousPitch)) {
					points--;
				}

				//powtarzanie dźwięku
				if (counterpointPitch == counterpointPreviousPitch) {
					points -= 6;
				}

				int interval = counterpointPitch - counterpointPreviousPitch;
				//nie lubię skoków
				int counterPointJump = Math.abs(counterpointPitch - counterpointPreviousPitch);
				if (counterPointJump > Interval.MAJOR_SECOND.getNumberOfSemitones()) {
					if (interval > Interval.MAJOR_THIRD.getNumberOfSemitones())
						points -= 7;
					else
						points -= 3;
				}

				//żeby nie było "d-c-d-c-d"
				if (i > 3) {
					int counterpoint2 = phrase.getNote(i-2).getPitch();
					int counterpoint3 = phrase.getNote(i-3).getPitch();
					int counterpoint4 = phrase.getNote(i-4).getPitch();

					if (Math.signum(counterpoint4 - counterpoint3) == Math.signum(counterpoint2 - counterpointPreviousPitch) &&
						Math.signum(counterpoint3 - counterpoint2) == Math.signum(counterpointPreviousPitch - counterpointPitch)
						) {
						points -= 5;
					}
				}
			}
		}

		return points;
	}
}
