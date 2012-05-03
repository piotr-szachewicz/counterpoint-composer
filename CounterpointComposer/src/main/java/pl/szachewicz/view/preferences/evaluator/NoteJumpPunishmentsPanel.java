package pl.szachewicz.view.preferences.evaluator;

import pl.szachewicz.model.preferences.NoteJumpPunishment;
import pl.szachewicz.model.preferences.NoteJumpsPunishmentsTableModel;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.preferences.AbstractPunishmentTablePanel;

public class NoteJumpPunishmentsPanel extends AbstractPunishmentTablePanel<NoteJumpPunishment> {

	public NoteJumpPunishmentsPanel() {
		super();
	}

	@Override
	protected String getTitle() {
		return "Note jump punishments";
	}

	@Override
	public NoteJumpsPunishmentsTableModel getTableModel() {
		if (tableModel == null)
			tableModel = new NoteJumpsPunishmentsTableModel();
		return (NoteJumpsPunishmentsTableModel) tableModel;
	}

	@Override
	public AddNoteJumpPunishmentPanel getAddPunishmentPanel() {
		if (addPunishmentPanel == null) {
			addPunishmentPanel = new AddNoteJumpPunishmentPanel();
		}
		return (AddNoteJumpPunishmentPanel) addPunishmentPanel;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;

		tableModel.setPunishments(preferences.getPunishments());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;

		preferences.setPunishments(tableModel.getPunishments());
	}

}
