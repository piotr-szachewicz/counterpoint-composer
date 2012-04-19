package pl.szachewicz.model;

import java.util.Comparator;

public class EvaluatedPhraseComparator implements Comparator<EvaluatedPhrase> {

	public int compare(EvaluatedPhrase phrase1, EvaluatedPhrase phrase2) {

		if (phrase1.getNumberOfPoints() > phrase2.getNumberOfPoints())
			return 1;
		else if (phrase1.getNumberOfPoints() == phrase2.getNumberOfPoints())
			return 0;
		else
			return -1;
	}

}
