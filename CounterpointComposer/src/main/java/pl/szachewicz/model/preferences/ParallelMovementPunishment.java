package pl.szachewicz.model.preferences;

import pl.szachewicz.model.Interval;

public class ParallelMovementPunishment {

	private Interval interval;
	private Integer punishment;

	public ParallelMovementPunishment(Interval interval, Integer punishment) {
		this.interval = interval;
		this.punishment = punishment;
	}

	public Interval getInterval() {
		return interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	public Integer getPunishment() {
		return punishment;
	}
	public void setPunishment(Integer punishment) {
		this.punishment = punishment;
	}

}
