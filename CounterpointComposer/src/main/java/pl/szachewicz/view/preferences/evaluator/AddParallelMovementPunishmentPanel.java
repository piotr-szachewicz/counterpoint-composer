package pl.szachewicz.view.preferences.evaluator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.szachewicz.model.Interval;
import pl.szachewicz.model.preferences.ParallelMovementPunishment;

public class AddParallelMovementPunishmentPanel extends AddPunishmentToTableAbstractPanel {

	private JComboBox intervalComboBox;

	public AddParallelMovementPunishmentPanel() {
		super();
	}

	@Override
	protected String getTitle() {
		return "Add parallel movement punishment";
	}

	@Override
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

	public ParallelMovementPunishment getPunishment() {
		Interval interval = (Interval) getIntervalComboBox().getSelectedItem();
		float punishment = getPunishmentSpinner().getValue();

		return new ParallelMovementPunishment(interval, punishment);
	}

}
