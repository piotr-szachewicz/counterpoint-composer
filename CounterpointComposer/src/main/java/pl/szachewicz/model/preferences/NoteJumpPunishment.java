package pl.szachewicz.model.preferences;

import pl.szachewicz.model.Interval;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="noteJumpPunishment")
public class NoteJumpPunishment extends Punishment {

	private int minSemitones;
	private int maxSemitones;

	public NoteJumpPunishment() {
	}

	public NoteJumpPunishment(int minSemitones, int maxSemitones,
			float punishment) {
		super();
		this.minSemitones = minSemitones;
		this.maxSemitones = maxSemitones;
		this.punishment = punishment;
	}

	public NoteJumpPunishment(Interval minInterval, Interval maxInterval, float punishment) {
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

}
