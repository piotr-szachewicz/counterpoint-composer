package pl.szachewicz.view.preferences.algorithm;

import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.OptimalizationGoal;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class ChooseOptimizationGoalPanel extends AbstractPanel {

	private JComboBox comboBox;

	public ChooseOptimizationGoalPanel() {
		setBorder(new TitledBorder("Choose optimization goal"));
		this.add(getComboBox());
	}

	public JComboBox getComboBox() {
		if (comboBox == null)
			comboBox = new JComboBox(OptimalizationGoal.values());
		return comboBox;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		getComboBox().setSelectedItem(preferences.getOptimalizationGoal());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		preferences.setOptimalizationGoal((OptimalizationGoal) getComboBox().getSelectedItem());
	}

}
