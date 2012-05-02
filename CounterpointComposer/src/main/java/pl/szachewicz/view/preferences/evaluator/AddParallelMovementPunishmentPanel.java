package pl.szachewicz.view.preferences.evaluator;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.Interval;
import pl.szachewicz.model.preferences.ParallelMovementPunishment;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class AddParallelMovementPunishmentPanel extends AbstractPanel {

	private JComboBox intervalComboBox;
	private JSpinner punishmentSpinner;
	private JButton addPunishmentButton;

	public AddParallelMovementPunishmentPanel() {
		setBorder(new TitledBorder("Add punishment"));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(createControls());
		add(getAddPunishmentButton());
	}

	protected JPanel createControls() {
		JPanel panel = new JPanel();

		JLabel intervalLabel = new JLabel("interval");
		JLabel punishmentLabel = new JLabel("punishment");

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(intervalLabel)
		        .addComponent(punishmentLabel)
		);

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(getIntervalComboBox())
		        .addComponent(getPunishmentSpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(intervalLabel)
				.addComponent(getIntervalComboBox())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(punishmentLabel)
				.addComponent(getPunishmentSpinner())
			);

		layout.setVerticalGroup(vGroup);

		return panel;
	}

	public JComboBox getIntervalComboBox() {
		if (intervalComboBox == null)
			intervalComboBox = new JComboBox(Interval.values());
		return intervalComboBox;
	}

	public JSpinner getPunishmentSpinner() {
		if (punishmentSpinner == null)
			punishmentSpinner = new JSpinner();
		return punishmentSpinner;
	}

	public JButton getAddPunishmentButton() {
		if (addPunishmentButton == null)
			addPunishmentButton = new JButton("Add");
		return addPunishmentButton;
	}

	public void fillViewFromModel(Object model) {
	}

	public void fillModelFromView(Object model) {
	}

	public void addActionListener(ActionListener actionListener) {
		getAddPunishmentButton().addActionListener(actionListener);
	}

	public ParallelMovementPunishment getPunishment() {
		Interval interval = (Interval) getIntervalComboBox().getSelectedItem();
		int punishment = (Integer) getPunishmentSpinner().getValue();

		return new ParallelMovementPunishment(interval, punishment);
	}

}
