package pl.szachewicz.algorithm.library;

public class WorsePhrasesLibrary extends PhrasesLibrary {

	@Override
	protected boolean isBetterThanLast(float points) {
		return points < lastNumberOfPoints;
	}

}
