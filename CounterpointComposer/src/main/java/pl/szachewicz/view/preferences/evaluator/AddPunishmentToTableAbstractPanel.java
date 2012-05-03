package pl.szachewicz.view.preferences.evaluator;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.Punishment;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.controls.PunishmentSpinner;

public abstract class AddPunishmentToTableAbstractPanel extends AbstractPanel {

	private PunishmentSpinner punishmentSpinner;
	private JButton addButton;

	public AddPunishmentToTableAbstractPanel() {
		this.setBorder(new TitledBorder(getTitle()));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(createControls());
		add(getAddButton());
	}

	protected abstract String getTitle();

	protected abstract JPanel createControls();

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

	public void addActionListener(ActionListener actionListener) {
		getAddButton().addActionListener(actionListener);
	}

	public abstract Punishment getPunishment();

	public void fillViewFromModel(Object model) {
	}

	public void fillModelFromView(Object model) {
	}

}
