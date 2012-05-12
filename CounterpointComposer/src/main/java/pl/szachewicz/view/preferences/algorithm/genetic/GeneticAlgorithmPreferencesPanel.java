package pl.szachewicz.view.preferences.algorithm.genetic;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class GeneticAlgorithmPreferencesPanel extends AbstractPanel {

	private CrossoverPreferencesPanel crossoverPreferencesPanel;
	private GeneralPreferencesPanel generalPreferencesPanel;
	private MutationPreferencesPanel mutationPreferencesPanel;
	private SelectionPreferencesPanel selectionPreferencesPanel;

	public GeneticAlgorithmPreferencesPanel() {
		this.setBorder(new TitledBorder("Genetic algorithm preferences"));
		this.add(createInterface());
	}

	protected JPanel createInterface() {
		JPanel panel = new JPanel(new GridLayout(2, 2));

		panel.add(getGeneralPreferencesPanel());
		panel.add(getCrossoverPreferencesPanel());
		panel.add(getSelectionPreferencesPanel());
		panel.add(getMutationPreferencesPanel());

		return panel;
	}

	public CrossoverPreferencesPanel getCrossoverPreferencesPanel() {
		if (crossoverPreferencesPanel == null)
			crossoverPreferencesPanel = new CrossoverPreferencesPanel();
		return crossoverPreferencesPanel;
	}

	public GeneralPreferencesPanel getGeneralPreferencesPanel() {
		if (generalPreferencesPanel == null)
			generalPreferencesPanel = new GeneralPreferencesPanel();
		return generalPreferencesPanel;
	}

	public MutationPreferencesPanel getMutationPreferencesPanel() {
		if (mutationPreferencesPanel == null)
			mutationPreferencesPanel = new MutationPreferencesPanel();
		return mutationPreferencesPanel;
	}

	public SelectionPreferencesPanel getSelectionPreferencesPanel() {
		if (selectionPreferencesPanel == null)
			selectionPreferencesPanel = new SelectionPreferencesPanel();
		return selectionPreferencesPanel;
	}

	public void fillViewFromModel(Object model) {
		getCrossoverPreferencesPanel().fillViewFromModel(model);
		getGeneralPreferencesPanel().fillViewFromModel(model);
		getMutationPreferencesPanel().fillViewFromModel(model);
		getSelectionPreferencesPanel().fillViewFromModel(model);
	}

	public void fillModelFromView(Object model) {
		getCrossoverPreferencesPanel().fillModelFromView(model);
		getGeneralPreferencesPanel().fillModelFromView(model);
		getMutationPreferencesPanel().fillModelFromView(model);
		getSelectionPreferencesPanel().fillModelFromView(model);
	}

}
