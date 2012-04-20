package pl.szachewicz.algorithm;

import java.math.BigInteger;
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

	private BigInteger numberOfPossiblePhrases;
	private BigInteger currentPhraseNumber;

	public Generator(Phrase cantusFirmus, Preferences preferences) {
		this.cantusFirmus = cantusFirmus;
		positions = new int[cantusFirmus.getSize()];
		setPreferences(preferences);
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
	}

	protected void prepareAvailablePitches() {
		availablePitches = new ArrayList<List<Integer>>();

		if (cantusFirmus.length() == 0) {
			incrementEnd = true;
		}

		int noteNumber = 0;
		numberOfPossiblePhrases = new BigInteger("1");
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

			if (pitches.size() == 0) {
				pitches.add(Pitches.REST);
			}

			BigInteger b = new BigInteger(Integer.toString(pitches.size()));
			numberOfPossiblePhrases = numberOfPossiblePhrases.multiply(b);

			noteNumber++;
		}

		System.out.println("number of possible phrases: " + numberOfPossiblePhrases);
		currentPhraseNumber = new BigInteger("1");
	}

	public Phrase generateNext() {

		if (incrementEnd)
			return null;

		/*System.out.print("[");
		for (int position: positions) {
			System.out.print(position + ", ");
		}
		System.out.println("] " + currentPhraseNumber + " / " + numberOfPossiblePhrases);*/
		Phrase phrase = new Phrase();
		for (int i = 0; i < cantusFirmus.getSize(); i++) {

			List<Integer> pitches = availablePitches.get(i);
			phrase.add(new Note(pitches.get(positions[i]), RhythmValues.QUARTER_NOTE));

		}
		incrementPositions();
		currentPhraseNumber = currentPhraseNumber.add(new BigInteger("1")); //++

		return phrase;
	}

	protected void incrementPositions() {
		int i = positions.length - 1;
		positions[i]++;

		for (; i >= 0; i--) {
			if (positions[i] >= availablePitches.get(i).size()) {
				positions[i] = 0;
				if (i - 1 >= 0)
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

	public BigInteger getCurrentPhraseNumber() {
		return currentPhraseNumber;
	}

	public BigInteger getNumberOfPossiblePhrases() {
		return numberOfPossiblePhrases;
	}

	public int getPercentageComplete() {
		BigInteger multBy100 = currentPhraseNumber.multiply(new BigInteger("100"));
		BigInteger percentage = multBy100.divide(numberOfPossiblePhrases);
		return percentage.intValue();
	}

}
