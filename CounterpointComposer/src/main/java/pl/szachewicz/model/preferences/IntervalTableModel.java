package pl.szachewicz.model.preferences;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import pl.szachewicz.model.Interval;

public class IntervalTableModel extends AbstractTableModel {

	private final boolean[] selections;

	public IntervalTableModel() {
		selections = new boolean[Interval.values().length];
	}

	public void setSelectedIntervals(List<Interval> selectedIntervals) {
		for (int i = 0; i < selections.length; i++)
			selections[i] = false;

		for (Interval interval: selectedIntervals) {
			for (int i = 0; i < Interval.values().length; i++) {
				if (interval == Interval.values()[i])
					selections[i] = true;
			}
		}
		fireTableDataChanged();
	}

	public List<Interval> getSelectedIntervals() {
		List<Interval> selectedIntervals = new ArrayList<Interval>();
		for (int i = 0; i < getRowCount(); i++) {
			if ((Boolean) getValueAt(i, 0)) {
				selectedIntervals.add(Interval.values()[i]);
			}
		}
		return selectedIntervals;
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return Interval.values().length;
	}

	public Object getValueAt(int row, int col) {
		if (col == 0) {
			return selections[row];
		}
		else {
			return Interval.values()[row];
		}
	}

	@Override
	public String getColumnName(int column) {
		switch(column) {
		case 0: return "selected";
		case 1: return "interval";
		}
		return super.getColumnName(column);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			selections[rowIndex] = (Boolean) aValue;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0)
			return Boolean.class;
		else
			return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return true;
		}
		else
			return false;
	}

}
