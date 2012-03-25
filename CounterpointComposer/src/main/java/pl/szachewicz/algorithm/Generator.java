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
	private final int minimumPitch = Pitches.c4;
	private final int maximumPitch = Pitches.c5;

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

		prepareAvailablePitches();
	}

	protected void prepareAvailablePitches() {
		availablePitches = new ArrayList<List<Integer>>();
		for (Note note: cantusFirmus.getNoteArray()) {
			List<Integer> pitches = new ArrayList<Integer>();
			int basePitch = note.getPitch();

			for (Interval interval: availableIntervals) {
				pitches.addAll(Helper.getPitchesForInterval(basePitch, interval, minimumPitch, maximumPitch));
			}
			availablePitches.add(pitches);
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
