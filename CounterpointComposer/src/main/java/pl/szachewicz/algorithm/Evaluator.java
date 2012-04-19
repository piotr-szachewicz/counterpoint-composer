package pl.szachewicz.algorithm;

import jm.music.data.Phrase;
import pl.szachewicz.model.preferences.NoteJumpPunishmentRange;
import pl.szachewicz.model.preferences.Preferences;

public class Evaluator {

	private final Phrase cantusFirmus;
	private final Preferences preferences;

	private StringBuilder evaluationLog;

	public Evaluator(Phrase cantusFirmus, Preferences preferences) {
		this.cantusFirmus = cantusFirmus;
		this.preferences = preferences;
	}

	public int evaluatePhrase(Phrase phraseForEvaluation) {

		Phrase phrase = phraseForEvaluation.copy();

		evaluationLog = new StringBuilder();
		int points = 0;

		evaluationLog.append("==================\n");
		evaluationLog.append("Phrase evaluation:\n");

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
					points -= preferences.getParallelMovementPunishment();
					log(i-1, i, "parallel movement", preferences.getParallelMovementPunishment());
				}

				//powtarzanie dźwięku
				if (counterpointPitch == counterpointPreviousPitch) {
					points -= preferences.getNoteRepetitionPunishment();
					log(i-1, i, "note repetition  ", preferences.getNoteRepetitionPunishment());
				}

				//nie lubię skoków
				int interval = counterpointPitch - counterpointPreviousPitch;
				int counterPointJump = Math.abs(counterpointPitch - counterpointPreviousPitch);
				for (NoteJumpPunishmentRange range: preferences.getPunishments()) {
					if (counterPointJump >= range.getMinSemitones() && counterPointJump <= range.getMaxSemitones()) {
						points -= range.getPunishment();
						if (range.getPunishment() != 0)
							log(i-1, i, "jump punishment  ", range.getPunishment());
					}
				}

				//żeby nie było "d-c-d-c-d"
				if (i > 3) {
					int counterpoint2 = phrase.getNote(i-2).getPitch();
					int counterpoint3 = phrase.getNote(i-3).getPitch();
					int counterpoint4 = phrase.getNote(i-4).getPitch();

					if (Math.signum(counterpoint4 - counterpoint3) == Math.signum(counterpoint2 - counterpointPreviousPitch) &&
						Math.signum(counterpoint3 - counterpoint2) == Math.signum(counterpointPreviousPitch - counterpointPitch)
						) {
						points -= preferences.getTrillPunishment();
						log(i-4, i, "trill", preferences.getTrillPunishment());
					}
				}
			}
		}
		evaluationLog.append("SUMMARY points: " + points + "\n");
		evaluationLog.append("==================\n");

		return points;
	}

	protected void log(Integer startNote, Integer lastNote, String problem, double punishment) {
		evaluationLog.append("Notes ");
		evaluationLog.append(startNote+1);
		evaluationLog.append("-");
		evaluationLog.append(lastNote+1);
		evaluationLog.append("\t");
		evaluationLog.append(problem);
		evaluationLog.append("\t");
		evaluationLog.append(-punishment);
		evaluationLog.append("\n");
	}

	public String getEvaluationLog() {
		return evaluationLog.toString();
	}

}
