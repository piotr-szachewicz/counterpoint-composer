package pl.szachewicz.view.preferences.evaluator;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.NoteJumpPunishmentRange;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class AddNoteJumpPunishmentPanel extends AbstractPanel {

	private JSpinner minSpinner;
	private JSpinner maxSpinner;
	private JSpinner punishmentSpinner;
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
		        .addComponent(getMinSpinner())
		        .addComponent(getMaxSpinner())
		        .addComponent(getPunishmentSpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(minLabel)
				.addComponent(getMinSpinner())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(maxLabel)
				.addComponent(getMaxSpinner())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(punishmentLabel)
				.addComponent(getPunishmentSpinner())
			);

		layout.setVerticalGroup(vGroup);

		return panel;
	}

	public JSpinner getMinSpinner() {
		if (minSpinner == null)
			minSpinner = new JSpinner();
		return minSpinner;
	}

	public JSpinner getMaxSpinner() {
		if (maxSpinner == null) {
			maxSpinner = new JSpinner();
			maxSpinner.setMinimumSize(new Dimension(70, 10));
		}
		return maxSpinner;
	}

	public JSpinner getPunishmentSpinner() {
		if (punishmentSpinner == null)
			punishmentSpinner = new JSpinner();
		return punishmentSpinner;
	}

	public JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton("Add");
		}
		return addButton;
	}

	public NoteJumpPunishmentRange getPunishment() {
		NoteJumpPunishmentRange punishment = new NoteJumpPunishmentRange();
		punishment.setMinSemitones((Integer) getMinSpinner().getValue());
		punishment.setMaxSemitones((Integer) getMaxSpinner().getValue());
		punishment.setPunishment((Integer) getPunishmentSpinner().getValue());
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
