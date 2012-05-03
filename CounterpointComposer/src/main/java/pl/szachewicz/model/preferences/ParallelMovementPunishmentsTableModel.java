package pl.szachewicz.model.preferences;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import pl.szachewicz.model.Interval;

public class ParallelMovementPunishmentsTableModel extends AbstractTableModel {

	private List<ParallelMovementPunishment> punishments = new ArrayList<ParallelMovementPunishment>();

	public void setPunishments(List<ParallelMovementPunishment> punishments) {
		this.punishments = punishments;
		fireTableDataChanged();
	}

	public void addItem(ParallelMovementPunishment punishment) {
		punishments.add(punishment);
		fireTableDataChanged();
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return punishments.size();
	}

	@Override
	public String getColumnName(int column) {
		switch(column) {
		case 0: return "Destination interval";
		case 1: return "punishment";
		}
		return null;
	}

	public Object getValueAt(int row, int col) {
		ParallelMovementPunishment punishment = punishments.get(row);
		switch(col) {
		case 0:
			Interval interval = punishment.getInterval();
			return interval == null ? "Default" : interval;
		case 1: return punishment.getPunishment();
		}
		return null;
	}

	public List<ParallelMovementPunishment> getPunishments() {
		return punishments;
	}

	public void removeElement(int index) {
		punishments.remove(index);
		fireTableDataChanged();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
			try{
				punishments.get(rowIndex).setPunishment(Double.parseDouble((String) aValue));
			} catch(Exception e) {
				//ignore - do not change the value
			}
		}
	}

}
