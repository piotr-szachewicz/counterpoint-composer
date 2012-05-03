package pl.szachewicz.view.preferences.evaluator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class OtherEvaluatorParametersPanel extends AbstractPanel {

	private JSpinner noteRepetitionPunishmentSpinner;
	private JSpinner trillPunishmentSpinner;

	public OtherEvaluatorParametersPanel() {
		createInterface();
	}

	protected void createInterface() {

		setBorder(new TitledBorder("Parameters"));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		JLabel noteRepetitionLabel = new JLabel("Note repetition punishment");
		JLabel tremoloPunishmentLabel = new JLabel("Trill punishment");

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(noteRepetitionLabel)
		        .addComponent(tremoloPunishmentLabel)
		);

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(getNoteRepetitionPunishmentSpinner())
		        .addComponent(getTrillPunishmentSpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

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

		getNoteRepetitionPunishmentSpinner().setValue(preferences.getNoteRepetitionPunishment());
		getTrillPunishmentSpinner().setValue(preferences.getTrillPunishment());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;

		preferences.setNoteRepetitionPunishment((Integer) getNoteRepetitionPunishmentSpinner().getValue());
		preferences.setTrillPunishment((Integer) getTrillPunishmentSpinner().getValue());
	}

}
