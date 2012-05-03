package pl.szachewicz.view.preferences.evaluator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.szachewicz.model.Interval;
import pl.szachewicz.model.preferences.NoteJumpPunishmentRange;

public class AddNoteJumpPunishmentPanel extends AddPunishmentToTableAbstractPanel {

	private JComboBox minIntervalComboBox;
	private JComboBox maxIntervalComboBox;

	public AddNoteJumpPunishmentPanel() {
		super();
	}

	@Override
	protected String getTitle() {
		return "Add note jump";
	}

	@Override
	protected JPanel createControls() {
		JPanel panel = new JPanel();

		JLabel minLabel = new JLabel("min");
		JLabel maxLabel = new JLabel("max");
		JLabel punishmentLabel = new JLabel("punishment");

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(minLabel)
		        .addComponent(maxLabel)
		        .addComponent(punishmentLabel)
		);

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(getMinInterval())
		        .addComponent(getMaxInterval())
		        .addComponent(getPunishmentSpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(minLabel)
				.addComponent(getMinInterval())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(maxLabel)
				.addComponent(getMaxInterval())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(punishmentLabel)
				.addComponent(getPunishmentSpinner())
			);

		layout.setVerticalGroup(vGroup);

		return panel;
	}

	public JComboBox getMinInterval() {
		if (minIntervalComboBox == null) {
			minIntervalComboBox = new JComboBox(Interval.values());
		}
		return minIntervalComboBox;
	}

	public JComboBox getMaxInterval() {
		if (maxIntervalComboBox == null) {
			maxIntervalComboBox = new JComboBox(Interval.valuesWithMoreThanOctave());
		}
		return maxIntervalComboBox;
	}

	protected int getSemitonesFromComboBox(JComboBox comboBox) {
		Object selectedItem = comboBox.getSelectedItem();
		if (selectedItem instanceof Interval) {
			return ((Interval) selectedItem).getNumberOfSemitones();
		}
		else {
			return Integer.MAX_VALUE; //infinity
		}
	}

	public NoteJumpPunishmentRange getPunishment() {
		NoteJumpPunishmentRange punishment = new NoteJumpPunishmentRange();

		punishment.setMinSemitones(getSemitonesFromComboBox(getMinInterval()));
		punishment.setMaxSemitones(getSemitonesFromComboBox(getMaxInterval()));
		punishment.setPunishment(getPunishmentSpinner().getValue());
		return punishment;
	}

}
