package pl.szachewicz.model.preferences;

public enum OptimalizationGoal {

	MAXIMIZE ("find the best counterpoint"),
	MINIMIZE ("find the worse counterpoint");

	private String displayName;

	private OptimalizationGoal(String disString) {
		this.displayName = disString;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
