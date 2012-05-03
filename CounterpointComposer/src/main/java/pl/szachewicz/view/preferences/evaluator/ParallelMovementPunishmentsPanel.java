package pl.szachewicz.view.preferences.evaluator;

import pl.szachewicz.model.preferences.ParallelMovementPunishment;
import pl.szachewicz.model.preferences.ParallelMovementPunishmentsTableModel;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.preferences.AbstractPunishmentTablePanel;

public class ParallelMovementPunishmentsPanel extends AbstractPunishmentTablePanel<ParallelMovementPunishment> {

	public ParallelMovementPunishmentsPanel() {
		super();
	}

	@Override
	protected String getTitle() {
		return "Parallel movement punishments";
	}

	@Override
	public ParallelMovementPunishmentsTableModel getTableModel() {
		if (tableModel == null)
			tableModel = new ParallelMovementPunishmentsTableModel();
		return (ParallelMovementPunishmentsTableModel) tableModel;
	}

	@Override
	public AddParallelMovementPunishmentPanel getAddPunishmentPanel() {
		if (addPunishmentPanel == null) {
			addPunishmentPanel = new AddParallelMovementPunishmentPanel();
		}
		return (AddParallelMovementPunishmentPanel) addPunishmentPanel;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;

		tableModel.setPunishments(preferences.getParallelMovementPunishments());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;

		preferences.setParallelMovementPunishments(tableModel.getPunishments());
	}

}
