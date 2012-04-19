package pl.szachewicz.view.preferences.evaluator;

import javax.swing.BoxLayout;

import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class EvaluatorPreferencesPanel extends AbstractPanel {

	private EvaluatorParametersPanel parametersPanel;
	private NoteJumpPunishmentsPanel noteJumpsPunishmentsPreferencesPanel;

	public EvaluatorPreferencesPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getParametersPanel());
		this.add(getNoteJumpsPunishmentsPreferencesPanel());
	}

	public EvaluatorParametersPanel getParametersPanel() {
		if (parametersPanel == null)
			parametersPanel = new EvaluatorParametersPanel();
		return parametersPanel;
	}

	public NoteJumpPunishmentsPanel getNoteJumpsPunishmentsPreferencesPanel() {
		if (noteJumpsPunishmentsPreferencesPanel == null)
			noteJumpsPunishmentsPreferencesPanel = new NoteJumpPunishmentsPanel();
		return noteJumpsPunishmentsPreferencesPanel;
	}

	public void fillViewFromModel(Object model) {
		noteJumpsPunishmentsPreferencesPanel.fillViewFromModel(model);
		parametersPanel.fillViewFromModel(model);
	}

	public void fillModelFromView(Object model) {
		noteJumpsPunishmentsPreferencesPanel.fillModelFromView(model);
		parametersPanel.fillModelFromView(model);
	}

}
