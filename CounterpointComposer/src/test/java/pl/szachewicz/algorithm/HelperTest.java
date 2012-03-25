package pl.szachewicz.algorithm;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import pl.szachewicz.model.Interval;

public class HelperTest {

	@Test
	public void getPitchesForIntervalTest() {
		List<Integer> pitchesForInterval = Helper.getPitchesForInterval(60, Interval.MAJOR_SECOND, 26, 50);

		assertEquals(3, pitchesForInterval.size());
		assertEquals(26, (int)pitchesForInterval.get(0));
		assertEquals(38, (int)pitchesForInterval.get(1));
		assertEquals(50, (int)pitchesForInterval.get(2));


	}
}
