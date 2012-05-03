package pl.szachewicz.view.preferences.generator;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import pl.szachewicz.model.ValidationErrors;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class GeneratorPreferencesPanel extends AbstractPanel {

	private final AvailableScalePanel scalePanel;

	private final StartNoteIntervalsPanel startNoteIntervalsPanel;
	private final AvailableIntervalsPanel availableIntervalsPanel;
	private final LastNoteIntervalsPanel lastNoteIntervalsPanel;

	public GeneratorPreferencesPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		scalePanel = new AvailableScalePanel();
		add(scalePanel);

		startNoteIntervalsPanel = new StartNoteIntervalsPanel();
		availableIntervalsPanel = new AvailableIntervalsPanel();
		lastNoteIntervalsPanel = new LastNoteIntervalsPanel();

		JPanel intervalsPanel = new JPanel(new GridLayout(1, 3));
		intervalsPanel.add(startNoteIntervalsPanel);
		intervalsPanel.add(availableIntervalsPanel);
		intervalsPanel.add(lastNoteIntervalsPanel);
		add(intervalsPanel);
	}

	public void fillViewFromModel(Object model) {
		scalePanel.fillViewFromModel(model);
		startNoteIntervalsPanel.fillViewFromModel(model);
		availableIntervalsPanel.fillViewFromModel(model);
		lastNoteIntervalsPanel.fillViewFromModel(model);
	}

	public void fillModelFromView(Object model) {
		scalePanel.fillModelFromView(model);
		startNoteIntervalsPanel.fillModelFromView(model);
		availableIntervalsPanel.fillModelFromView(model);
		lastNoteIntervalsPanel.fillModelFromView(model);
	}

	@Override
	public boolean validateView(ValidationErrors errors) {
		return scalePanel.validateView(errors)
				&& startNoteIntervalsPanel.validateView(errors)
				&& availableIntervalsPanel.validateView(errors)
				&& lastNoteIntervalsPanel.validateView(errors);
	}

}
