package pl.szachewicz.view.preferences;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.AbstractPunishmentTableModel;
import pl.szachewicz.model.preferences.Punishment;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.preferences.evaluator.AddPunishmentToTableAbstractPanel;

public abstract class AbstractPunishmentTablePanel<T extends Punishment> extends AbstractPanel implements KeyListener, PropertyChangeListener {

	protected JTable punishmentsTable;
	protected AbstractPunishmentTableModel<T> tableModel;
	protected AddPunishmentToTableAbstractPanel addPunishmentPanel;

	public AbstractPunishmentTablePanel() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(getTitle()));
		add(new JScrollPane(getPunishmentsTable()), BorderLayout.CENTER);
		add(getAddPunishmentPanel(), BorderLayout.EAST);

		getAddPunishmentPanel().addPropertyChangeListener(AddPunishmentToTableAbstractPanel.ADD_BUTTON_PRESSED, this);
		getAddPunishmentPanel().addPropertyChangeListener(AddPunishmentToTableAbstractPanel.REMOVE_BUTTON_PRESSED, this);
	}

	protected abstract String getTitle();

	public JTable getPunishmentsTable() {
		if (punishmentsTable == null) {
			punishmentsTable = new JTable(getTableModel());
			punishmentsTable.addKeyListener(this);
			punishmentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return punishmentsTable;
	}

	public abstract AbstractPunishmentTableModel getTableModel();
	public abstract AddPunishmentToTableAbstractPanel getAddPunishmentPanel();

	public void keyPressed(KeyEvent event) {

		if (event.getKeyCode() == KeyEvent.VK_DELETE) {
			removeSelectedElement();
		}
	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (AddPunishmentToTableAbstractPanel.ADD_BUTTON_PRESSED.equals(event.getPropertyName())) {
			getTableModel().addItem(getAddPunishmentPanel().getPunishment());
		} else if (AddPunishmentToTableAbstractPanel.REMOVE_BUTTON_PRESSED.equals(event.getPropertyName())){
			removeSelectedElement();
		}
	}

	protected void removeSelectedElement() {
		ListSelectionModel selectionModel = punishmentsTable.getSelectionModel();
		int index = selectionModel.getMinSelectionIndex();
		if (index != -1)
			tableModel.removeElement(index);
	}

}
