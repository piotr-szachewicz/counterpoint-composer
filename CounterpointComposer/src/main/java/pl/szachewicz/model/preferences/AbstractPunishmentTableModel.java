package pl.szachewicz.model.preferences;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class AbstractPunishmentTableModel<T extends Punishment>  extends AbstractTableModel {

	protected List<T> punishments = new ArrayList<T>();

	public void setPunishments(List<T> punishments) {
		this.punishments = punishments;
		fireTableDataChanged();
	}

	public void addItem(T punishment) {
		punishments.add(punishment);
		fireTableDataChanged();
	}

	public void removeElement(int index) {
		punishments.remove(index);
		fireTableDataChanged();
	}

	public List<T> getPunishments() {
		return punishments;
	}

	public int getRowCount() {
		return punishments.size();
	}

}
