package pl.szachewicz.model.preferences;

import pl.szachewicz.model.Interval;

public class ParallelMovementPunishment {

	private Interval interval;
	private double punishment;

	public ParallelMovementPunishment(Interval interval, double punishment) {
		this.interval = interval;
		this.punishment = punishment;
	}

	public Interval getInterval() {
		return interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	public double getPunishment() {
		return punishment;
	}
	public void setPunishment(double punishment) {
		this.punishment = punishment;
	}

}
