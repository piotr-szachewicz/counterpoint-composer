package pl.szachewicz.view.preferences.evaluator;

import javax.swing.BoxLayout;

import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class EvaluatorPreferencesPanel extends AbstractPanel {

	private OtherEvaluatorParametersPanel parametersPanel;
	private NoteJumpPunishmentsPanel noteJumpsPunishmentsPreferencesPanel;
	private ParallelMovementPunishmentsPanel parallelMovementPunishmentsPanel;

	public EvaluatorPreferencesPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getParametersPanel());
		this.add(getNoteJumpsPunishmentsPreferencesPanel());
		this.add(getParallelMovementPunishmentsPanel());
	}

	public OtherEvaluatorParametersPanel getParametersPanel() {
		if (parametersPanel == null)
			parametersPanel = new OtherEvaluatorParametersPanel();
		return parametersPanel;
	}

	public NoteJumpPunishmentsPanel getNoteJumpsPunishmentsPreferencesPanel() {
		if (noteJumpsPunishmentsPreferencesPanel == null)
			noteJumpsPunishmentsPreferencesPanel = new NoteJumpPunishmentsPanel();
		return noteJumpsPunishmentsPreferencesPanel;
	}

	public ParallelMovementPunishmentsPanel getParallelMovementPunishmentsPanel() {
		if (parallelMovementPunishmentsPanel == null)
			parallelMovementPunishmentsPanel = new ParallelMovementPunishmentsPanel();
		return parallelMovementPunishmentsPanel;
	}

	public void fillViewFromModel(Object model) {
		noteJumpsPunishmentsPreferencesPanel.fillViewFromModel(model);
		parametersPanel.fillViewFromModel(model);
		parallelMovementPunishmentsPanel.fillViewFromModel(model);
	}

	public void fillModelFromView(Object model) {
		noteJumpsPunishmentsPreferencesPanel.fillModelFromView(model);
		parametersPanel.fillModelFromView(model);
		parallelMovementPunishmentsPanel.fillModelFromView(model);
	}

}
