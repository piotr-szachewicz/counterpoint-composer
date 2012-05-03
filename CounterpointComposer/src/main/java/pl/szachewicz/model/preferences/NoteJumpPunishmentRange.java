package pl.szachewicz.model.preferences;

import pl.szachewicz.model.Interval;


public class NoteJumpPunishmentRange {

	private int minSemitones;
	private int maxSemitones;

	private float punishment;

	public NoteJumpPunishmentRange() {
	}

	public NoteJumpPunishmentRange(int minSemitones, int maxSemitones,
			float punishment) {
		super();
		this.minSemitones = minSemitones;
		this.maxSemitones = maxSemitones;
		this.punishment = punishment;
	}

	public NoteJumpPunishmentRange(Interval minInterval, Interval maxInterval, float punishment) {
		super();
		this.minSemitones = minInterval.getNumberOfSemitones();
		this.maxSemitones = maxInterval.getNumberOfSemitones();
		this.punishment = punishment;
	}

	public int getMinSemitones() {
		return minSemitones;
	}

	public void setMinSemitones(int minSemitones) {
		this.minSemitones = minSemitones;
	}

	public int getMaxSemitones() {
		return maxSemitones;
	}

	public void setMaxSemitones(int maxSemitones) {
		this.maxSemitones = maxSemitones;
	}

	public float getPunishment() {
		return punishment;
	}

	public void setPunishment(float punishment) {
		this.punishment = punishment;
	}

}
