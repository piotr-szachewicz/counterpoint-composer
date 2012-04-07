package pl.szachewicz.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class PhraseRankingTableModel extends DefaultTableModel {

	private List<EvaluatedPhrase> phrases = new ArrayList<EvaluatedPhrase>();

	public PhraseRankingTableModel() {
		phrases = new ArrayList<EvaluatedPhrase>();
	}

	public void setPhrases(List<EvaluatedPhrase> phrases) {
		this.phrases = phrases;
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0: return "Points";
		case 1: return "Phrase";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		if (phrases == null)
			return 0;
		return phrases.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		EvaluatedPhrase phrase = phrases.get(row);

		switch(column) {
			case 0: return phrase.getNumberOfPoints();
			case 1: return "Phrase " + row;
		}
		return null;
	}

}
