package pl.szachewicz.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jm.constants.Pitches;
import jm.constants.RhythmValues;
import jm.music.data.Note;
import jm.music.data.Phrase;
import pl.szachewicz.model.Interval;
import pl.szachewicz.model.preferences.Preferences;

public class Generator {

	private final Phrase cantusFirmus;

	private Preferences preferences;
	private int minimumPitch;
	private int maximumPitch;

	private List<List<Integer>> availablePitches;
	private final int[] positions;
	private boolean incrementEnd = false;

	public Generator(Phrase cantusFirmus) {
		this.cantusFirmus = cantusFirmus;
		positions = new int[cantusFirmus.getSize()];
	}

	protected List<Integer> getScale() {
		return preferences.getScale();
	}

	protected void addPitchesIfInScale(List<Integer> result, List<Integer> pitchesToAdd) {
		for (int pitch: pitchesToAdd) {
			if (getScale().contains(pitch)) {
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

			List<Interval> intervals;
			if (noteNumber == 0) {
				intervals = preferences.getStartNoteIntervals();
			}
			else if (noteNumber == cantusFirmus.getSize() - 1) {
				intervals = preferences.getLastNoteIntervals();
			}
			else {
				intervals = preferences.getAvailableIntervals();
			}

			for (Interval interval : intervals) {
				addPitchesIfInScale(pitches, Helper.getPitchesForInterval(basePitch, interval, minimumPitch, maximumPitch));
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

			List<Integer> pitches = availablePitches.get(i);
			if (i >= positions.length) {
				phrase.add(new Note(Pitches.REST, RhythmValues.QUARTER_NOTE));
			}
			else {
				phrase.add(new Note(pitches.get(positions[i]), RhythmValues.QUARTER_NOTE));
			}
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

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;

		minimumPitch = Collections.min(preferences.getScale());
		maximumPitch = Collections.max(preferences.getScale());

		prepareAvailablePitches();
	}
}
