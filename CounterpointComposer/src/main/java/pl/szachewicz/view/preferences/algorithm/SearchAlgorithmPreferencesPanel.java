package pl.szachewicz.view.preferences.algorithm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import pl.szachewicz.model.preferences.SearchAlgorithm;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.preferences.algorithm.genetic.GeneticAlgorithmPreferencesPanel;

public class SearchAlgorithmPreferencesPanel extends AbstractPanel implements PropertyChangeListener {

	private ChooseSearchAlgorithmPanel chooseSearchAlgorithmPanel;

	private JPanel otherPreferencesCardPanel;
	private GeneticAlgorithmPreferencesPanel evolutionaryComputationPreferencesPanel;

	public SearchAlgorithmPreferencesPanel() {
		this.setLayout(new BorderLayout());

		this.add(getChooseSearchAlgorithmPanel(), BorderLayout.NORTH);
		this.add(getOtherPreferencesCardPanel(), BorderLayout.CENTER);
	}

	public ChooseSearchAlgorithmPanel getChooseSearchAlgorithmPanel() {
		if (chooseSearchAlgorithmPanel == null) {
			chooseSearchAlgorithmPanel = new ChooseSearchAlgorithmPanel();
			chooseSearchAlgorithmPanel.addPropertyChangeListener(this);
		}
		return chooseSearchAlgorithmPanel;
	}

	public JPanel getOtherPreferencesCardPanel() {
		if (otherPreferencesCardPanel == null) {
			otherPreferencesCardPanel = new JPanel(new CardLayout());
			otherPreferencesCardPanel.add(new JPanel(), SearchAlgorithm.FULL_SEARCH.toString());
			otherPreferencesCardPanel.add(getEvolutionaryComputationPreferencesPanel(), SearchAlgorithm.GENETIC_ALGORITHM.toString());
		}
		return otherPreferencesCardPanel;
	}

	public GeneticAlgorithmPreferencesPanel getEvolutionaryComputationPreferencesPanel() {
		if (evolutionaryComputationPreferencesPanel == null)
			evolutionaryComputationPreferencesPanel = new GeneticAlgorithmPreferencesPanel();
		return evolutionaryComputationPreferencesPanel;
	}

	public void fillViewFromModel(Object model) {
		chooseSearchAlgorithmPanel.fillViewFromModel(model);
		evolutionaryComputationPreferencesPanel.fillViewFromModel(model);
	}

	public void fillModelFromView(Object model) {
		chooseSearchAlgorithmPanel.fillModelFromView(model);
		evolutionaryComputationPreferencesPanel.fillModelFromView(model);
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals(ChooseSearchAlgorithmPanel.ALGORITHM_CHANGED)) {
			SearchAlgorithm searchAlgorithm = (SearchAlgorithm) event.getNewValue();
			CardLayout cardLayout = (CardLayout) otherPreferencesCardPanel.getLayout();
			cardLayout.show(otherPreferencesCardPanel, searchAlgorithm.toString());
			System.out.println("changed to " + searchAlgorithm);
		}
	}

}
