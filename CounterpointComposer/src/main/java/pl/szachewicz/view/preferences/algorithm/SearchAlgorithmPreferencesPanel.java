package pl.szachewicz.view.preferences.algorithm;

import java.awt.BorderLayout;

import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class SearchAlgorithmPreferencesPanel extends AbstractPanel {

	private ChooseSearchAlgorithmPanel chooseSearchAlgorithmPanel;

	public SearchAlgorithmPreferencesPanel() {
		this.setLayout(new BorderLayout());

		this.add(getChooseSearchAlgorithmPanel(), BorderLayout.NORTH);
	}

	public ChooseSearchAlgorithmPanel getChooseSearchAlgorithmPanel() {
		if (chooseSearchAlgorithmPanel == null)
			chooseSearchAlgorithmPanel = new ChooseSearchAlgorithmPanel();
		return chooseSearchAlgorithmPanel;
	}

	public void fillViewFromModel(Object model) {
		chooseSearchAlgorithmPanel.fillViewFromModel(model);
	}

	public void fillModelFromView(Object model) {
		chooseSearchAlgorithmPanel.fillModelFromView(model);
	}

}
