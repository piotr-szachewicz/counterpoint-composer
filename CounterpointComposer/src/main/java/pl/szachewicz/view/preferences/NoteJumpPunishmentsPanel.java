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

import pl.szachewicz.model.preferences.NoteJumpPunishmentRange;
import pl.szachewicz.model.preferences.NoteJumpsPunishmentsTableModel;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.AbstractPanel;

public class NoteJumpPunishmentsPanel extends AbstractPanel implements ActionListener, KeyListener {

	private JTable punishmentsTable;
	private NoteJumpsPunishmentsTableModel tableModel;
	private AddNoteJumpPunishmentPanel addPunishmentPanel;

	public NoteJumpPunishmentsPanel() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Note jumps punishments"));
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

	public NoteJumpsPunishmentsTableModel getTableModel() {
		if (tableModel == null)
			tableModel = new NoteJumpsPunishmentsTableModel();
		return tableModel;
	}

	public AddNoteJumpPunishmentPanel getAddPunishmentPanel() {
		if (addPunishmentPanel == null) {
			addPunishmentPanel = new AddNoteJumpPunishmentPanel();
			addPunishmentPanel.addActionListener(this);
		}
		return addPunishmentPanel;
	}

	public void actionPerformed(ActionEvent event) {
		NoteJumpPunishmentRange punishment = addPunishmentPanel.getPunishment();
		getTableModel().addItem(punishment);
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;

		tableModel.setPunishments(preferences.getPunishments());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;

		preferences.setPunishments(tableModel.getPunishments());
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
