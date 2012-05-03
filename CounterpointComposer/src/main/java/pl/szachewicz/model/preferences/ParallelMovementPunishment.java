package pl.szachewicz.model.preferences;

import pl.szachewicz.model.Interval;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="parallelMovementPunishment")
public class ParallelMovementPunishment extends Punishment {

	private Interval interval;

	public ParallelMovementPunishment(Interval interval, float punishment) {
		this.interval = interval;
		this.punishment = punishment;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

}
