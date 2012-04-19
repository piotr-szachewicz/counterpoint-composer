package pl.szachewicz.view.preferences.generator;

import pl.szachewicz.model.preferences.Preferences;

public class StartNoteIntervalsPanel extends SelectIntervalsPanel {

	@Override
	protected String getTitle() {
		return "Start note intervals";
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		intervalsTableModel.setSelectedIntervals(preferences.getStartNoteIntervals());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		preferences.setStartNoteIntervals(intervalsTableModel.getSelectedIntervals());
	}
}
