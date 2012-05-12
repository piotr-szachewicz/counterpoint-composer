package pl.szachewicz.view.preferences.algorithm.genetic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.GeneticAlgorithmPreferences;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.controls.ProbabilitySpinner;

public class MutationPreferencesPanel extends AbstractPanel {
	private ProbabilitySpinner noteMutationProbabilitySpinner;

	public MutationPreferencesPanel() {
		this.setBorder(new TitledBorder("Mutation"));
		this.add(createInterface());
	}

	protected JPanel createInterface() {
		JPanel panel = new JPanel();

		JLabel noteMutationProbabilityLabel = new JLabel("note mutation probability:");

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(noteMutationProbabilityLabel)
		        .addComponent(getNoteMutationProbabilitySpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(noteMutationProbabilityLabel)
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(getNoteMutationProbabilitySpinner())
			);

		layout.setVerticalGroup(vGroup);

		return panel;
	}

	public ProbabilitySpinner getNoteMutationProbabilitySpinner() {
		if (noteMutationProbabilitySpinner == null)
			noteMutationProbabilitySpinner = new ProbabilitySpinner();
		return noteMutationProbabilitySpinner;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		GeneticAlgorithmPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;

		getNoteMutationProbabilitySpinner().setValue(evolutionaryComputationPreferences.getNoteMutationProbability());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		GeneticAlgorithmPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;

		evolutionaryComputationPreferences.setNoteMutationProbability(getNoteMutationProbabilitySpinner().getValue());
	}

}
