package pl.szachewicz.algorithm.library;

public class BestPhrasesLibrary extends PhrasesLibrary {

	@Override
	protected boolean isBetterThanLast(float points) {
		return points > lastNumberOfPoints;
	}

}
