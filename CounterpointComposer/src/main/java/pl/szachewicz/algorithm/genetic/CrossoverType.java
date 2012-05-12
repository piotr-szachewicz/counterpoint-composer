package pl.szachewicz.algorithm.genetic;

public enum CrossoverType {

	ONE_POINT("one point", "one"),
	TWO_POINT("two point", "two"),
	UNIFORM("uniform", "any");

	private String displayName;
	private String ecjCode;

	private CrossoverType(String displayName, String ecjCode) {
		this.displayName = displayName;
		this.ecjCode = ecjCode;
	}

	public String getEcjCode() {
		return ecjCode;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
