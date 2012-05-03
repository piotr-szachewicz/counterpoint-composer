package pl.szachewicz.view.preferences;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.AbstractPunishmentTableModel;
import pl.szachewicz.model.preferences.Punishment;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.preferences.evaluator.AddPunishmentToTableAbstractPanel;

public abstract class AbstractPunishmentTablePanel<T extends Punishment> extends AbstractPanel implements KeyListener, ActionListener {

	protected JTable punishmentsTable;
	protected AbstractPunishmentTableModel<T> tableModel;
	protected AddPunishmentToTableAbstractPanel addPunishmentPanel;

	public AbstractPunishmentTablePanel() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(getTitle()));
		add(new JScrollPane(getPunishmentsTable()), BorderLayout.CENTER);
		add(getAddPunishmentPanel(), BorderLayout.EAST);

		getAddPunishmentPanel().addActionListener(this);
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

	public void actionPerformed(ActionEvent event) {
		getTableModel().addItem(getAddPunishmentPanel().getPunishment());
	}

	public void keyPressed(KeyEvent event) {

		if (event.getKeyCode() == KeyEvent.VK_DELETE) {
			ListSelectionModel selectionModel = punishmentsTable.getSelectionModel();
			int index = selectionModel.getMinSelectionIndex();
			tableModel.removeElement(index);
		}
	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
	}

}
