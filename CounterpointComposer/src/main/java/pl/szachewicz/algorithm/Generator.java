package pl.szachewicz.algorithm;

import java.util.ArrayList;
import java.util.List;

import jm.constants.Pitches;
import jm.constants.RhythmValues;
import jm.music.data.Note;
import jm.music.data.Phrase;
import pl.szachewicz.model.Interval;

public class Generator {

	private final Phrase cantusFirmus;

	private final List<Interval> availableIntervals;

	private final int minimumPitch = Pitches.c3;
	private final int maximumPitch = Pitches.c4;
	private final List<Integer> scale;

	private List<List<Integer>> availablePitches;
	private final int[] positions;
	private boolean incrementEnd = false;

	public Generator(Phrase cantusFirmus) {
		this.cantusFirmus = cantusFirmus;
		positions = new int[cantusFirmus.getSize()];

		availableIntervals = new ArrayList<Interval>();
		availableIntervals.add(Interval.PERFECT_FIFTH);
		availableIntervals.add(Interval.MINOR_THIRD);
		availableIntervals.add(Interval.MAJOR_THIRD);
		availableIntervals.add(Interval.MINOR_SIXTH);
		availableIntervals.add(Interval.MAJOR_SIXTH);

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

		prepareAvailablePitches();
	}

	protected void addPitchesIfInScale(List<Integer> result, List<Integer> pitchesToAdd) {
		for (int pitch: pitchesToAdd) {
			if (scale.contains(pitch)) {
				result.add(pitch);
			}
		}
		//pitches.addAll(pitches);
	}

	protected void prepareAvailablePitches() {
		availablePitches = new ArrayList<List<Integer>>();

		int noteNumber = 0;
		for (Note note: cantusFirmus.getNoteArray()) {
			List<Integer> pitches = new ArrayList<Integer>();
			int basePitch = note.getPitch();

			if (noteNumber == 0) {
				addPitchesIfInScale(pitches, Helper.getPitchesForInterval(basePitch, Interval.UNISON, minimumPitch, maximumPitch));
				addPitchesIfInScale(pitches, Helper.getPitchesForInterval(basePitch, Interval.PERFECT_FIFTH, minimumPitch, maximumPitch));
				addPitchesIfInScale(pitches, Helper.getPitchesForInterval(basePitch, Interval.OCTAVE, minimumPitch, maximumPitch));
			}
			else {
				for (Interval interval: availableIntervals) {
					addPitchesIfInScale(pitches, Helper.getPitchesForInterval(basePitch, interval, minimumPitch, maximumPitch));
				}
			}
			availablePitches.add(pitches);

			noteNumber++;
		}
	}

	public Phrase generateNext() {

		if (incrementEnd)
			return null;

		Phrase phrase = new Phrase();
		for (int i = 0; i < cantusFirmus.getSize(); i++) {

			//int previousPitch = phrase.getNote(i-1).getPitch();
			//availablePitches;

			List<Integer> pitches = availablePitches.get(i);
			phrase.add(new Note(pitches.get(positions[i]), RhythmValues.QUARTER_NOTE));
		}
		incrementPositions();

		return phrase;
	}

	protected void incrementPositions() {
		int i = positions.length - 1;
		positions[i]++;

		for (; i > 0; i--) {
			if (positions[i] >= availablePitches.get(i).size()) {
				positions[i] = 0;
				if (i - 1 > 0)
					positions[i-1]++;
				else
					incrementEnd = true;
			}
		}
	}

	public Phrase getCantusFirmus() {
		return cantusFirmus;
	}

	public boolean hasNext() {
		return !incrementEnd;
	}
}
