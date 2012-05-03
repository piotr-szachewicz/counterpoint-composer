package pl.szachewicz.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias(value="interval")
public enum Interval {

	UNISON(0),
	MINOR_SECOND(1),
	MAJOR_SECOND(2),
	MINOR_THIRD(3),
	MAJOR_THIRD(4),
	PERFECT_FOURTH(5),
	TRITONE(6),
	PERFECT_FIFTH(7),
	MINOR_SIXTH(8),
	MAJOR_SIXTH(9),
	MINOR_SEVENTH(10),
	MAJOR_SEVENTH(11),
	OCTAVE(12);

	public static String MORE_THAN_OCTAVE = "> OCTAVE";

	private int numberOfSemitones;
	private String symbol;
	private String name;

	Interval(int numberOfSemitones) {
		this.numberOfSemitones = numberOfSemitones;
	}

	public static Interval getInterval(int pitch1, int pitch2) {
		int semitoneDistance = Math.abs(pitch1 - pitch2);
		return findIntervalByNumberOfSemitones(semitoneDistance);
	}

	public static Interval findIntervalByNumberOfSemitones(int numberOfSemitones) {

		while(numberOfSemitones > 12)
			numberOfSemitones -= 12;

		for (Interval interval: Interval.values()) {
			if (interval.numberOfSemitones == numberOfSemitones)
				return interval;
		}
		return null;
	}

	public int getNumberOfSemitones() {
		return numberOfSemitones;
	}

	public static Object[] valuesWithMoreThanOctave() {
		Object[] val = new Object[Interval.values().length+1];
		int i = 0;
		for (; i < Interval.values().length; i++) {
			val[i] = Interval.values()[i];
		}
		val[i] = Interval.MORE_THAN_OCTAVE;

		return val;
	}
}
