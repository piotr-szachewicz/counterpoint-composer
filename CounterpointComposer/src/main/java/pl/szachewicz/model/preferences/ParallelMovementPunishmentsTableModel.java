package pl.szachewicz.model.preferences;

import pl.szachewicz.model.Interval;
import pl.szachewicz.view.Dialogs;

public class ParallelMovementPunishmentsTableModel extends AbstractPunishmentTableModel<ParallelMovementPunishment> {

	public int getColumnCount() {
		return 2;
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

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
			try{
				punishments.get(rowIndex).setPunishment(Float.parseFloat((String) aValue));
			} catch(Exception e) {
				//ignore - do not change the value
			}
		}
	}

	@Override
	public void removeElement(int index) {
		if (punishments.get(index).getInterval() == null) {
			Dialogs.showErrorDialog("Default parallel punishment movement cannot be deleted");
			return;
		}
		super.removeElement(index);
	}

}
