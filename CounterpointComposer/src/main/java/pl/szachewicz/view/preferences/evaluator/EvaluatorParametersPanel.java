package pl.szachewicz.view.preferences.evaluator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class EvaluatorParametersPanel extends AbstractPanel {

	private JSpinner parallelMovementPunishmentSpinner;
	private JSpinner noteRepetitionPunishmentSpinner;
	private JSpinner trillPunishmentSpinner;

	public EvaluatorParametersPanel() {
		createInterface();
	}

	protected void createInterface() {

		setBorder(new TitledBorder("Parameters"));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		JLabel parallelMovementLabel = new JLabel("Paralell movement punishment");
		JLabel noteRepetitionLabel = new JLabel("Note repetition punishment");
		JLabel tremoloPunishmentLabel = new JLabel("Trill punishment");

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(parallelMovementLabel)
		        .addComponent(noteRepetitionLabel)
		        .addComponent(tremoloPunishmentLabel)
		);

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(getParallelMovementPunishmentSpinner())
		        .addComponent(getNoteRepetitionPunishmentSpinner())
		        .addComponent(getTrillPunishmentSpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(parallelMovementLabel)
				.addComponent(getParallelMovementPunishmentSpinner())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(noteRepetitionLabel)
				.addComponent(getNoteRepetitionPunishmentSpinner())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(tremoloPunishmentLabel)
				.addComponent(getTrillPunishmentSpinner())
			);

		layout.setVerticalGroup(vGroup);
	}

	public JSpinner getParallelMovementPunishmentSpinner() {
		if (parallelMovementPunishmentSpinner == null)
			parallelMovementPunishmentSpinner = new JSpinner();
		return parallelMovementPunishmentSpinner;
	}

	public JSpinner getNoteRepetitionPunishmentSpinner() {
		if (noteRepetitionPunishmentSpinner == null)
			noteRepetitionPunishmentSpinner = new JSpinner();
		return noteRepetitionPunishmentSpinner;
	}

	public JSpinner getTrillPunishmentSpinner() {
		if (trillPunishmentSpinner == null)
			trillPunishmentSpinner = new JSpinner();
		return trillPunishmentSpinner;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;

		getParallelMovementPunishmentSpinner().setValue(preferences.getParallelMovementPunishment());
		getNoteRepetitionPunishmentSpinner().setValue(preferences.getNoteRepetitionPunishment());
		getTrillPunishmentSpinner().setValue(preferences.getTrillPunishment());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;

		preferences.setParallelMovementPunishment((Integer) getParallelMovementPunishmentSpinner().getValue());
		preferences.setNoteRepetitionPunishment((Integer) getNoteRepetitionPunishmentSpinner().getValue());
		preferences.setTrillPunishment((Integer) getTrillPunishmentSpinner().getValue());
	}

}
