package pl.szachewicz.view.preferences.evaluator;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.Interval;
import pl.szachewicz.model.preferences.NoteJumpPunishmentRange;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.controls.PunishmentSpinner;

public class AddNoteJumpPunishmentPanel extends AbstractPanel {

	private JComboBox minIntervalComboBox;
	private JComboBox maxIntervalComboBox;
	private PunishmentSpinner punishmentSpinner;
	private JButton addButton;

	public AddNoteJumpPunishmentPanel() {
		this.setBorder(new TitledBorder("Add note jump"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(createControls());
		add(getAddButton());
	}

	protected JPanel createControls() {
		JPanel panel = new JPanel();

		JLabel minLabel = new JLabel("min");
		JLabel maxLabel = new JLabel("max");
		JLabel punishmentLabel = new JLabel("punishment");
//
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

	public PunishmentSpinner getPunishmentSpinner() {
		if (punishmentSpinner == null)
			punishmentSpinner = new PunishmentSpinner();
		return punishmentSpinner;
	}

	public JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton("Add");
		}
		return addButton;
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
		punishment.setPunishment(punishmentSpinner.getValue());
		return punishment;
	}

	public void addActionListener(ActionListener actionListener) {
		getAddButton().addActionListener(actionListener);
	}

	public void fillViewFromModel(Object model) {
		// TODO Auto-generated method stub

	}

	public void fillModelFromView(Object model) {
		// TODO Auto-generated method stub

	}

}
