package pl.szachewicz.model.preferences;

public enum OptimalizationGoal {

	MAXIMIZE ("find the best counterpoints"),
	MINIMIZE ("find the worst counterpoints");

	private String displayName;

	private OptimalizationGoal(String disString) {
		this.displayName = disString;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
