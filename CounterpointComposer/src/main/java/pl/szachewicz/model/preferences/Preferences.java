package pl.szachewicz.model.preferences;

import java.util.ArrayList;
import java.util.List;

import jm.constants.Pitches;
import pl.szachewicz.model.Interval;

public class Preferences {

	private StaveType cantusFirmusStaveType;

	private List<Interval> startNoteIntervals;
	private List<Interval> availableIntervals;
	private List<Interval> lastNoteIntervals;

	private List<Integer> scale;

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
	}

}
