package pl.szachewicz.view.preferences;

import pl.szachewicz.model.preferences.Preferences;

public class AvailableIntervalsPanel extends SelectIntervalsPanel {

	@Override
	protected String getTitle() {
		return "Available intervals";
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		intervalsTableModel.setSelectedIntervals(preferences.getAvailableIntervals());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		preferences.setAvailableIntervals(intervalsTableModel.getSelectedIntervals());
	}
}
