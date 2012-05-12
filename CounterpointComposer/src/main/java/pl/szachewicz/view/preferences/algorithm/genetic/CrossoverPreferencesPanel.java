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
	private FloatSpinner crossoverProbablitySpinner;

	public CrossoverPreferencesPanel() {
		this.setBorder(new TitledBorder("General preferences"));
		this.add(createInterface());
	}

	protected JPanel createInterface() {
		JPanel panel = new JPanel();

		JLabel crossoverTypeLabel = new JLabel("crossover type:");
		JLabel crossoverProbabilityLabel = new JLabel("note crossover probability:");

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(crossoverTypeLabel)
		        .addComponent(crossoverProbabilityLabel)
		);

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(getCrossoverTypeComboBox())
		        .addComponent(getCrossoverProbablitySpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(crossoverTypeLabel)
				.addComponent(getCrossoverTypeComboBox())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(crossoverProbabilityLabel)
				.addComponent(getCrossoverProbablitySpinner())
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
						enableCrossoverProbability(true);
					else
						enableCrossoverProbability(false);
				}
			});
		}
		return crossoverTypeComboBox;
	}

	protected void enableCrossoverProbability(boolean enable) {
		getCrossoverProbablitySpinner().setEnabled(enable);
	}

	public FloatSpinner getCrossoverProbablitySpinner() {
		if (crossoverProbablitySpinner == null)
			crossoverProbablitySpinner = new ProbabilitySpinner();
		return crossoverProbablitySpinner;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		GeneticAlgorithmPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;

		getCrossoverTypeComboBox().setSelectedItem(evolutionaryComputationPreferences.getCrossoverType());
		getCrossoverProbablitySpinner().setValue(evolutionaryComputationPreferences.getNoteCrossoverProbability());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		GeneticAlgorithmPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;

		evolutionaryComputationPreferences.setCrossoverType((CrossoverType) getCrossoverTypeComboBox().getSelectedItem());
		evolutionaryComputationPreferences.setNoteCrossoverProbability(getCrossoverProbablitySpinner().getValue());
	}

}
