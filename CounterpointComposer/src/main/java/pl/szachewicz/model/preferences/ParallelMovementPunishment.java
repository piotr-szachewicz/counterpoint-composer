package pl.szachewicz.model.preferences;

import pl.szachewicz.model.Interval;

public class ParallelMovementPunishment {

	private Interval interval;
	private float punishment;

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
	public float getPunishment() {
		return punishment;
	}
	public void setPunishment(float punishment) {
		this.punishment = punishment;
	}

}
