package pl.szachewicz.algorithm;

import java.util.ArrayList;
import java.util.List;

import pl.szachewicz.model.Interval;

public class Helper {

	public static List<Integer> getPitchesForInterval(int basePitch,
			Interval interval, int minimumPitch, int maximumPitch) {

		List<Integer> pitches = new ArrayList<Integer>();

		if (basePitch > minimumPitch) {
			int currentPitch = basePitch - interval.getNumberOfSemitones();

			while(currentPitch >= minimumPitch) {
				if (currentPitch <= maximumPitch)
					pitches.add(currentPitch);
				currentPitch -= 12;
			}
		}

		if (basePitch < maximumPitch){
			int currentPitch = basePitch + interval.getNumberOfSemitones();

			while(currentPitch <= maximumPitch) {
				if (currentPitch >= minimumPitch)
					pitches.add(currentPitch);
				currentPitch += 12;
			}
		}

		return pitches;
	}


	// maxPitch > basePitch + 12*x + interval > minPitch
	/*double x = (minimumPitch - basePitch - interval.getNumberOfSemitones()) / 12.0;

	if (basePitch < minimumPitch) {
		x = Math.ceil(x);
	}
	else {
		x = Math.floor(x);
		int inverseSemitones = Interval.OCTAVE.getNumberOfSemitones() - interval.getNumberOfSemitones();
		interval = Interval.findIntervalByNumberOfSemitones(inverseSemitones);
	}

	int pitch = basePitch + 12 * (int)x + interval.getNumberOfSemitones();
	int i = 0;
	while (pitch + 12 * i <= maximumPitch) {
		pitches.add(pitch + 12 * i);
		i++;
	}*/
}
