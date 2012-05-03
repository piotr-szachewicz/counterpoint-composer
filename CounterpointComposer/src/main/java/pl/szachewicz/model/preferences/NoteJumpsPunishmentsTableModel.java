package pl.szachewicz.model.preferences;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import pl.szachewicz.model.Interval;

public class NoteJumpsPunishmentsTableModel extends AbstractTableModel {

	private List<NoteJumpPunishmentRange> punishments = new ArrayList<NoteJumpPunishmentRange>();

	public void addItem(NoteJumpPunishmentRange jumpPunishment) {
		punishments.add(jumpPunishment);
		fireTableDataChanged();
	}

	public void removeElement(int index) {
		punishments.remove(index);
		fireTableDataChanged();
	}

	public void setPunishments(List<NoteJumpPunishmentRange> punishments) {
		this.punishments = punishments;
		fireTableDataChanged();
	}

	public List<NoteJumpPunishmentRange> getPunishments() {
		return punishments;
	}

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return punishments.size();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 2;
	}

	@Override
	public String getColumnName(int column) {
		switch(column) {
		case 0: return "min";
		case 1: return "max";
		case 2: return "punishment";
		}
		return null;
	}

	public Object getValueAt(int row, int col) {
		NoteJumpPunishmentRange punishment = punishments.get(row);

		switch(col) {
			case 0: return Interval.findIntervalByNumberOfSemitones(punishment.getMinSemitones());
			case 1:
				if (punishment.getMaxSemitones() == Integer.MAX_VALUE)
					return Interval.MORE_THAN_OCTAVE;
				else
					return Interval.findIntervalByNumberOfSemitones(punishment.getMaxSemitones());
			case 2: return punishment.getPunishment();
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 2) {
			punishments.get(rowIndex).setPunishment(Float.parseFloat((String) aValue));
		}
	}

}
