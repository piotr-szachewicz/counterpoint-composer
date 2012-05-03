package pl.szachewicz.view.preferences.evaluator;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.Punishment;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.controls.PunishmentSpinner;

public abstract class AddPunishmentToTableAbstractPanel extends AbstractPanel {

	public static String ADD_BUTTON_PRESSED = "addButtonPressed";
	public static String REMOVE_BUTTON_PRESSED = "removeButtonPressed";

	private PunishmentSpinner punishmentSpinner;
	private JButton addButton;
	private JButton removeButton;

	public AddPunishmentToTableAbstractPanel() {
		this.setBorder(new TitledBorder(getTitle()));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(createControls());
		add(createButtonsPanel());
	}

	protected abstract String getTitle();

	protected abstract JPanel createControls();

	protected JPanel createButtonsPanel() {
		JPanel panel = new JPanel();

		panel.add(getAddButton());
		panel.add(getRemoveButton());

		return panel;
	}

	public PunishmentSpinner getPunishmentSpinner() {
		if (punishmentSpinner == null)
			punishmentSpinner = new PunishmentSpinner();
		return punishmentSpinner;
	}

	public JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton(new AbstractAction() {

				public void actionPerformed(ActionEvent e) {
					fireAddButtonPressed();
				}
			});
			addButton.setText("Add");
		}
		return addButton;
	}

	public JButton getRemoveButton() {
		if (removeButton == null) {
			removeButton = new JButton(new AbstractAction() {

				public void actionPerformed(ActionEvent e) {
					fireRemoveButtonPressed();
				}
			});
			removeButton.setText("Remove");
		}
		return removeButton;
	}

	protected void fireAddButtonPressed() {
		firePropertyChange(ADD_BUTTON_PRESSED, null, null);
	}

	protected void fireRemoveButtonPressed() {
		firePropertyChange(REMOVE_BUTTON_PRESSED, null, null);
	}

	public abstract Punishment getPunishment();

	public void fillViewFromModel(Object model) {
	}

	public void fillModelFromView(Object model) {
	}

}
