package pl.szachewicz.model.preferences;

import java.util.ArrayList;
import java.util.List;

import jm.constants.Pitches;
import pl.szachewicz.model.Interval;

public class Preferences {

	//generator
	private StaveType cantusFirmusStaveType;

	private List<Interval> startNoteIntervals;
	private List<Interval> availableIntervals;
	private List<Interval> lastNoteIntervals;

	private List<Integer> scale;

	//evaluator
	private List<NoteJumpPunishment> punishments;

	private float noteRepetitionPunishment = 6;
	private float trillPunishment = 5;

	private List<ParallelMovementPunishment> parallelMovementPunishments = new ArrayList<ParallelMovementPunishment>();

	public List<ParallelMovementPunishment> getParallelMovementPunishments() {
		return parallelMovementPunishments;
	}
	public void setParallelMovementPunishments(List<ParallelMovementPunishment> list) {
		this.parallelMovementPunishments = list;
	}
	public float getNoteRepetitionPunishment() {
		return noteRepetitionPunishment;
	}
	public void setNoteRepetitionPunishment(float noteRepetitionPunishment) {
		this.noteRepetitionPunishment = noteRepetitionPunishment;
	}
	public float getTrillPunishment() {
		return trillPunishment;
	}
	public void setTrillPunishment(float tremoloRepetitionPunishment) {
		this.trillPunishment = tremoloRepetitionPunishment;
	}
	public List<NoteJumpPunishment> getPunishments() {
		return punishments;
	}
	public void setPunishments(List<NoteJumpPunishment> punishments) {
		this.punishments = punishments;
	}
	public List<Interval> getAvailableIntervals() {
		return availableIntervals;
	}
	public void setAvailableIntervals(List<Interval> availableIntervals) {
		this.availableIntervals = availableIntervals;
	}

	public List<Interval> getStartNoteIntervals() {
		return startNoteIntervals;
	}
	public void setStartNoteIntervals(List<Interval> startNoteIntervals) {
		this.startNoteIntervals = startNoteIntervals;
	}
	public List<Interval> getLastNoteIntervals() {
		return lastNoteIntervals;
	}
	public void setLastNoteIntervals(List<Interval> lastNoteIntervals) {
		this.lastNoteIntervals = lastNoteIntervals;
	}
	public List<Integer> getScale() {
		return scale;
	}
	public void setScale(List<Integer> scale) {
		this.scale = scale;
	}
	public StaveType getCantusFirmusStaveType() {
		return cantusFirmusStaveType;
	}

	public void setCantusFirmusStaveType(StaveType cantusFirmusStaveType) {
		this.cantusFirmusStaveType = cantusFirmusStaveType;
	}

	public StaveType getCounterpointStaveType() {
		if (cantusFirmusStaveType == StaveType.TREBLE)
			return StaveType.BASS;
		else
			return StaveType.TREBLE;
	}

	public void setDefaults() {
		cantusFirmusStaveType = StaveType.TREBLE;

		availableIntervals = new ArrayList<Interval>();
		availableIntervals.add(Interval.PERFECT_FIFTH);
		availableIntervals.add(Interval.MINOR_THIRD);
		availableIntervals.add(Interval.MAJOR_THIRD);
		availableIntervals.add(Interval.MINOR_SIXTH);
		availableIntervals.add(Interval.MAJOR_SIXTH);

		startNoteIntervals = new ArrayList<Interval>();
		startNoteIntervals.add(Interval.UNISON);
		startNoteIntervals.add(Interval.PERFECT_FIFTH);
		startNoteIntervals.add(Interval.OCTAVE);

		lastNoteIntervals = new ArrayList<Interval>();
		//lastNoteIntervals.add(Interval.UNISON);
		lastNoteIntervals.add(Interval.PERFECT_FIFTH);
		lastNoteIntervals.add(Interval.OCTAVE);

		scale = new ArrayList<Integer>();
		scale.add(Pitches.c3);
		scale.add(Pitches.d3);
		scale.add(Pitches.e3);
		scale.add(Pitches.f3);
		scale.add(Pitches.g3);
		scale.add(Pitches.a3);
		scale.add(Pitches.b3);
		scale.add(Pitches.c4);
		scale.add(Pitches.d4);

		//evaluator
		punishments = new ArrayList<NoteJumpPunishment>();

		punishments.add(new NoteJumpPunishment(Interval.MINOR_THIRD, Interval.PERFECT_FIFTH, 2));
		punishments.add(new NoteJumpPunishment(Interval.TRITONE, Interval.TRITONE, 20));

		punishments.add(new NoteJumpPunishment(Interval.MINOR_SIXTH, Interval.OCTAVE, 8));

		punishments.add(new NoteJumpPunishment(Interval.MAJOR_SEVENTH, Interval.MAJOR_SEVENTH, 12));
		punishments.add(new NoteJumpPunishment(Interval.MINOR_SEVENTH, Interval.MINOR_SEVENTH, 12));

		parallelMovementPunishments.add(new ParallelMovementPunishment(Interval.PERFECT_FIFTH, 30));
		parallelMovementPunishments.add(new ParallelMovementPunishment(Interval.UNISON, 30));
		parallelMovementPunishments.add(new ParallelMovementPunishment(Interval.OCTAVE, 30));
		parallelMovementPunishments.add(new ParallelMovementPunishment(null, 4));
	}

	public float getPunishmentForParallelMovement(int harmonyIntervalInSemitones) {

		Interval interval = Interval.findIntervalByNumberOfSemitones(harmonyIntervalInSemitones);

		Float punishment = null;
		for (ParallelMovementPunishment pmPunishment: parallelMovementPunishments) {
			if (interval.equals(pmPunishment.getInterval())) {
				punishment = pmPunishment.getPunishment();
			}
		}
		if (punishment != null)
			return punishment;
		else {
			return getDefaultParallelMovementPunishment();
		}
	}

	public float getDefaultParallelMovementPunishment() {
		for (ParallelMovementPunishment pmPunishment: parallelMovementPunishments) {
			if (pmPunishment.getInterval() == null) {
				return pmPunishment.getPunishment();
			}
		}
		return 0.0F;
	}

}
