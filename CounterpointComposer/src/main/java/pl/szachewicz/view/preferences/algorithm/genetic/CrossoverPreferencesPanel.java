package pl.szachewicz.view.preferences.algorithm.genetic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.algorithm.genetic.CrossoverType;
import pl.szachewicz.model.preferences.GeneticAlgorithmPreferences;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.controls.FloatSpinner;
import pl.szachewicz.view.controls.ProbabilitySpinner;

public class CrossoverPreferencesPanel extends AbstractPanel {
	private JComboBox crossoverTypeComboBox;
	private FloatSpinner uniformCrossoverProbablitySpinner;
	private FloatSpinner crossoverProbabilitySpinner;

	public CrossoverPreferencesPanel() {
		this.setBorder(new TitledBorder("General preferences"));
		this.add(createInterface());
	}

	protected JPanel createInterface() {
		JPanel panel = new JPanel();

		JLabel crossoverProbabilityLabel = new JLabel("crossover probability");
		JLabel crossoverTypeLabel = new JLabel("crossover type:");
		JLabel uniformCrossoverProbabilityLabel = new JLabel("uniform crossover probability:");

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(crossoverProbabilityLabel)
		        .addComponent(crossoverTypeLabel)
		        .addComponent(uniformCrossoverProbabilityLabel)
		);

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(getCrossoverProbablitySpinner())
		        .addComponent(getCrossoverTypeComboBox())
		        .addComponent(getUniformCrossoverProbablitySpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(crossoverProbabilityLabel)
				.addComponent(getCrossoverProbablitySpinner())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(crossoverTypeLabel)
				.addComponent(getCrossoverTypeComboBox())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(uniformCrossoverProbabilityLabel)
				.addComponent(getUniformCrossoverProbablitySpinner())
			);

		layout.setVerticalGroup(vGroup);

		return panel;
	}

	public JComboBox getCrossoverTypeComboBox() {
		if (crossoverTypeComboBox == null) {
			crossoverTypeComboBox = new JComboBox(CrossoverType.values());
			crossoverTypeComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(crossoverTypeComboBox.getSelectedItem() == CrossoverType.UNIFORM)
						enableUniformCrossoverProbabilitySpinner(true);
					else
						enableUniformCrossoverProbabilitySpinner(false);
				}
			});
		}
		return crossoverTypeComboBox;
	}

	protected void enableUniformCrossoverProbabilitySpinner(boolean enable) {
		getUniformCrossoverProbablitySpinner().setEnabled(enable);
	}

	public FloatSpinner getUniformCrossoverProbablitySpinner() {
		if (uniformCrossoverProbablitySpinner == null)
			uniformCrossoverProbablitySpinner = new ProbabilitySpinner();
		return uniformCrossoverProbablitySpinner;
	}

	public FloatSpinner getCrossoverProbablitySpinner() {
		if (crossoverProbabilitySpinner == null)
			crossoverProbabilitySpinner = new ProbabilitySpinner();
		return crossoverProbabilitySpinner;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		GeneticAlgorithmPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;

		getCrossoverProbablitySpinner().setValue(evolutionaryComputationPreferences.getCrossoverProbability());
		getCrossoverTypeComboBox().setSelectedItem(evolutionaryComputationPreferences.getCrossoverType());
		getUniformCrossoverProbablitySpinner().setValue(evolutionaryComputationPreferences.getNoteCrossoverProbability());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		GeneticAlgorithmPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;

		evolutionaryComputationPreferences.setCrossoverProbability(getCrossoverProbablitySpinner().getValue());
		evolutionaryComputationPreferences.setCrossoverType((CrossoverType) getCrossoverTypeComboBox().getSelectedItem());
		evolutionaryComputationPreferences.setNoteCrossoverProbability(getUniformCrossoverProbablitySpinner().getValue());
	}

}
