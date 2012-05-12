package pl.szachewicz.model.preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jm.constants.Pitches;
import pl.szachewicz.model.Interval;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="preferences")
public class Preferences {

	private SearchAlgorithm searchAlgorithm = SearchAlgorithm.FULL_SEARCH;
	private OptimalizationGoal optimalizationGoal = OptimalizationGoal.MAXIMIZE;
	//generator
	private StaveType cantusFirmusStaveType;

	private List<Interval> startNoteIntervals;
	private List<Interval> availableIntervals;
	private List<Interval> lastNoteIntervals;

	private List<Integer> scale;

	//evaluator
	private List<NoteJumpPunishment> noteJumpPunishments;

	private float noteRepetitionPunishment = 6;
	private float trillPunishment = 5;

	private List<ParallelMovementPunishment> parallelMovementPunishments = new ArrayList<ParallelMovementPunishment>();

	private GeneticAlgorithmPreferences evolutionaryComputationPreferences = new GeneticAlgorithmPreferences();

	public Preferences() {
	}

	public void copyFrom(Preferences other) {
		this.cantusFirmusStaveType = other.cantusFirmusStaveType;
		this.startNoteIntervals = other.startNoteIntervals;
		this.availableIntervals = other.availableIntervals;
		this.lastNoteIntervals = other.lastNoteIntervals;

		this.scale = other.scale;
		this.noteJumpPunishments = other.noteJumpPunishments;
		this.trillPunishment = other.trillPunishment;
		this.parallelMovementPunishments = other.parallelMovementPunishments;
	}

	public SearchAlgorithm getSearchAlgorithm() {
		return searchAlgorithm;
	}
	public void setSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
		this.searchAlgorithm = searchAlgorithm;
	}
	public OptimalizationGoal getOptimalizationGoal() {
		return optimalizationGoal;
	}
	public void setOptimalizationGoal(OptimalizationGoal optimalizationGoal) {
		this.optimalizationGoal = optimalizationGoal;
	}
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
	public List<NoteJumpPunishment> getNoteJumpPunishments() {
		return noteJumpPunishments;
	}
	public void setNoteJumpPunishments(List<NoteJumpPunishment> punishments) {
		this.noteJumpPunishments = punishments;
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

	public void setCounterpointStaveType(StaveType counterpointStaveType) {
		if (counterpointStaveType == StaveType.TREBLE)
			cantusFirmusStaveType = StaveType.BASS;
		else
			cantusFirmusStaveType = StaveType.TREBLE;
	}

	public void setDefaults() {
		searchAlgorithm = SearchAlgorithm.FULL_SEARCH;

		cantusFirmusStaveType = StaveType.TREBLE;

		availableIntervals = new ArrayList<Interval>();
		availableIntervals.add(Interval.MINOR_THIRD);
		availableIntervals.add(Interval.MAJOR_THIRD);
		availableIntervals.add(Interval.PERFECT_FIFTH);
		availableIntervals.add(Interval.MINOR_SIXTH);
		availableIntervals.add(Interval.MAJOR_SIXTH);
		availableIntervals.add(Interval.OCTAVE);

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
		noteJumpPunishments = new ArrayList<NoteJumpPunishment>();

		noteJumpPunishments.add(new NoteJumpPunishment(Interval.MINOR_THIRD, Interval.PERFECT_FOURTH, 2));
		noteJumpPunishments.add(new NoteJumpPunishment(Interval.TRITONE, Interval.TRITONE, 20));

		noteJumpPunishments.add(new NoteJumpPunishment(Interval.PERFECT_FIFTH, Interval.MAJOR_SIXTH, 8));

		noteJumpPunishments.add(new NoteJumpPunishment(Interval.MINOR_SEVENTH, Interval.MAJOR_SEVENTH, 12));
		noteJumpPunishments.add(new NoteJumpPunishment(Interval.OCTAVE.getNumberOfSemitones(), Integer.MAX_VALUE, 30));

		parallelMovementPunishments = new ArrayList<ParallelMovementPunishment>();
		parallelMovementPunishments.add(new ParallelMovementPunishment(Interval.UNISON, 30));
		parallelMovementPunishments.add(new ParallelMovementPunishment(Interval.PERFECT_FIFTH, 30));
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

	public GeneticAlgorithmPreferences getEvolutionaryComputationPreferences() {
		return evolutionaryComputationPreferences;
	}

	public void setEvolutionaryComputationPreferences(
			GeneticAlgorithmPreferences evolutionaryComputationPreferences) {
		this.evolutionaryComputationPreferences = evolutionaryComputationPreferences;
	}

	public int getMinimumCounterpointPitch() {
		return Collections.min(getScale());
	}

	public int getMaximumCounterpointPitch() {
		return Collections.max(getScale());
	}

}
