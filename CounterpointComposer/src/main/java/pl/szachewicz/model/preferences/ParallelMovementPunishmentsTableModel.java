package pl.szachewicz.model.preferences;

import javax.swing.table.AbstractTableModel;

public class ParallelMovementPunishmentsTableModel extends AbstractTableModel {

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return 0;
	}

	@Override
	public String getColumnName(int column) {
		switch(column) {
		case 0: return "Destination interval";
		case 1: return "punishment";
		}
		return null;
	}

	public Object getValueAt(int arg0, int arg1) {
		return null;
	}

}
