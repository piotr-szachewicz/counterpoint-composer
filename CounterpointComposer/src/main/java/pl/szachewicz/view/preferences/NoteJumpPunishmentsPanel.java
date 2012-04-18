package pl.szachewicz.view.preferences;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.NoteJumpPunishmentRange;
import pl.szachewicz.model.preferences.NoteJumpsPunishmentsTableModel;
import pl.szachewicz.view.AbstractPanel;

public class NoteJumpPunishmentsPanel extends AbstractPanel implements ActionListener {

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

	}

	public void fillModelFromView(Object model) {

	}

}
