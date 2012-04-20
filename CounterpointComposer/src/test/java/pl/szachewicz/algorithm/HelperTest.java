package pl.szachewicz.algorithm;

import java.util.List;

import jm.constants.Pitches;

import org.junit.Test;

import pl.szachewicz.model.Interval;

public class HelperTest {

	@Test
	public void getPitchesForIntervalTest() {

		List<Integer> pitchesForInterval = Helper.getPitchesForInterval(Pitches.c4, Interval.MINOR_THIRD, Pitches.f1, Pitches.g6);

		pitchesForInterval.contains(Pitches.a3);
		pitchesForInterval.contains(Pitches.a2);
		pitchesForInterval.contains(Pitches.a1);
		pitchesForInterval.contains(Pitches.ef4);
		pitchesForInterval.contains(Pitches.ef5);
		pitchesForInterval.contains(Pitches.ef6);

	}
}
