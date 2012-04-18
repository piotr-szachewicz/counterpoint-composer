package pl.szachewicz.view.preferences;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import pl.szachewicz.view.AbstractPanel;

public class EvaluatorPreferencesPanel extends AbstractPanel {

	private JSpinner parallelMovementPunishmentSpinner;
	private JSpinner noteRepetitionPunishmentSpinner;
	private JSpinner trillPunishmentSpinner;
	private NoteJumpPunishmentsPanel noteJumpsPunishmentsPreferencesPanel;

	public EvaluatorPreferencesPanel() {
		JPanel panel = createInterface();
		JPanel noteJumpsPanel = getNoteJumpsPunishmentsPreferencesPanel();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(panel);
		this.add(noteJumpsPanel);
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

	public NoteJumpPunishmentsPanel getNoteJumpsPunishmentsPreferencesPanel() {
		if (noteJumpsPunishmentsPreferencesPanel == null)
			noteJumpsPunishmentsPreferencesPanel = new NoteJumpPunishmentsPanel();
		return noteJumpsPunishmentsPreferencesPanel;
	}

	protected JPanel createInterface() {

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("Parameters"));

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
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

		return panel;
	}

	public void fillViewFromModel(Object model) {
		// TODO Auto-generated method stub

	}

	public void fillModelFromView(Object model) {
		// TODO Auto-generated method stub

	}

}
