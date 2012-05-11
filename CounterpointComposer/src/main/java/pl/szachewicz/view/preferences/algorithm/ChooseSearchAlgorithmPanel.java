package pl.szachewicz.view.preferences.algorithm;

import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.model.preferences.SearchAlgorithm;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class ChooseSearchAlgorithmPanel extends AbstractPanel {

	private JComboBox comboBox;

	public ChooseSearchAlgorithmPanel() {
		setBorder(new TitledBorder("Choose search algorithm"));

		this.add(getComboBox());
	}

	protected JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox(SearchAlgorithm.values());
		}
		return comboBox;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		getComboBox().setSelectedItem(preferences.getSearchAlgorithm());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		preferences.setSearchAlgorithm((SearchAlgorithm) comboBox.getSelectedItem());
	}

}
