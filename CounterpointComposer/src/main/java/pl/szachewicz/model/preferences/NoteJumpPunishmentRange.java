package pl.szachewicz.model.preferences;


public class NoteJumpPunishmentRange {

	private int minSemitones;
	private int maxSemitones;

	private double punishment;

	public NoteJumpPunishmentRange() {
	}

	public NoteJumpPunishmentRange(int minSemitones, int maxSemitones,
			double punishment) {
		super();
		this.minSemitones = minSemitones;
		this.maxSemitones = maxSemitones;
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

	public double getPunishment() {
		return punishment;
	}

	public void setPunishment(double punishment) {
		this.punishment = punishment;
	}

}
