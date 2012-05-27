package pl.szachewicz.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import pl.szachewicz.utils.FormatUtils;

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
		case 0: return "Phrase";
		case 1: return "Points";
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
			case 0: return "Phrase " + (row+1);
			case 1: return FormatUtils.format(phrase.getNumberOfPoints());
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
