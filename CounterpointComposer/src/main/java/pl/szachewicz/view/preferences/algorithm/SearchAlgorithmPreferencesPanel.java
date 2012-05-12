package pl.szachewicz.view.preferences.algorithm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import pl.szachewicz.model.preferences.SearchAlgorithm;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.preferences.algorithm.genetic.GeneticAlgorithmPreferencesPanel;

public class SearchAlgorithmPreferencesPanel extends AbstractPanel implements PropertyChangeListener {

	private ChooseSearchAlgorithmPanel chooseSearchAlgorithmPanel;
	private ChooseOptimizationGoalPanel chooseOptimizationGoalPanel;

	private JPanel otherPreferencesCardPanel;
	private GeneticAlgorithmPreferencesPanel evolutionaryComputationPreferencesPanel;

	public SearchAlgorithmPreferencesPanel() {
		this.setLayout(new BorderLayout());

		this.add(createNorthPanel(), BorderLayout.NORTH);
		this.add(getOtherPreferencesCardPanel(), BorderLayout.CENTER);
	}

	protected JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(getChooseOptimizationGoalPanel());
		panel.add(getChooseSearchAlgorithmPanel());

		return panel;
	}

	public ChooseSearchAlgorithmPanel getChooseSearchAlgorithmPanel() {
		if (chooseSearchAlgorithmPanel == null) {
			chooseSearchAlgorithmPanel = new ChooseSearchAlgorithmPanel();
			chooseSearchAlgorithmPanel.addPropertyChangeListener(this);
		}
		return chooseSearchAlgorithmPanel;
	}

	public ChooseOptimizationGoalPanel getChooseOptimizationGoalPanel() {
		if (chooseOptimizationGoalPanel == null)
			chooseOptimizationGoalPanel = new ChooseOptimizationGoalPanel();
		return chooseOptimizationGoalPanel;
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
		chooseOptimizationGoalPanel.fillViewFromModel(model);
		evolutionaryComputationPreferencesPanel.fillViewFromModel(model);
	}

	public void fillModelFromView(Object model) {
		chooseSearchAlgorithmPanel.fillModelFromView(model);
		chooseOptimizationGoalPanel.fillModelFromView(model);
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
