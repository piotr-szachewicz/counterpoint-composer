package pl.szachewicz.view.preferences.generator;

import pl.szachewicz.model.preferences.Preferences;

public class LastNoteIntervalsPanel extends SelectIntervalsPanel {

	@Override
	protected String getTitle() {
		return "Last note intervals";
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		intervalsTableModel.setSelectedIntervals(preferences.getLastNoteIntervals());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		preferences.setLastNoteIntervals(intervalsTableModel.getSelectedIntervals());
	}
}
