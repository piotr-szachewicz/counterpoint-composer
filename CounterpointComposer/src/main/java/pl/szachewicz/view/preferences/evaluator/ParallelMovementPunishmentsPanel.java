package pl.szachewicz.view.preferences.evaluator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.ParallelMovementPunishment;
import pl.szachewicz.model.preferences.ParallelMovementPunishmentsTableModel;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class ParallelMovementPunishmentsPanel extends AbstractPanel implements KeyListener, ActionListener {

	private JTable punishmentsTable;
	private ParallelMovementPunishmentsTableModel tableModel;
	private AddParallelMovementPunishmentPanel addPunishmentPanel;

	public ParallelMovementPunishmentsPanel() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Parallel movement punishments"));
		add(new JScrollPane(getPunishmentsTable()), BorderLayout.CENTER);
		add(getAddPunishmentPanel(), BorderLayout.EAST);
	}

	public JTable getPunishmentsTable() {
		if (punishmentsTable == null) {
			punishmentsTable = new JTable(getTableModel());
			punishmentsTable.addKeyListener(this);
			punishmentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return punishmentsTable;
	}

	public ParallelMovementPunishmentsTableModel getTableModel() {
		if (tableModel == null)
			tableModel = new ParallelMovementPunishmentsTableModel();
		return tableModel;
	}

	public AddParallelMovementPunishmentPanel getAddPunishmentPanel() {
		if (addPunishmentPanel == null) {
			addPunishmentPanel = new AddParallelMovementPunishmentPanel();
			addPunishmentPanel.addActionListener(this);
		}
		return addPunishmentPanel;
	}

	public void actionPerformed(ActionEvent event) {
		ParallelMovementPunishment punishment = addPunishmentPanel.getPunishment();
		getTableModel().addItem(punishment);
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;

		tableModel.setPunishments(preferences.getParallelMovementPunishments());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;

		preferences.setParallelMovementPunishments(tableModel.getPunishments());
	}

	public void keyPressed(KeyEvent event) {

		if (event.getKeyCode() == KeyEvent.VK_DELETE) {
			ListSelectionModel selectionModel = punishmentsTable.getSelectionModel();
			int index = selectionModel.getMinSelectionIndex();
			tableModel.removeElement(index);
		}
	}

	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub

	}

}
