package pl.szachewicz.view.preferences.algorithm;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.EvolutionaryComputationPreferences;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.controls.IntegerSpinner;

public class EvolutionaryComputationPreferencesPanel extends AbstractPanel {

	private IntegerSpinner numberOfGenerationsSpinner;
	private IntegerSpinner populationSizeSpinner;

	public EvolutionaryComputationPreferencesPanel() {
		this.setBorder(new TitledBorder("Evoluationary search preferences"));
		this.add(createInterface());
	}

	protected JPanel createInterface() {
		JPanel panel = new JPanel();

		JLabel numberOfGenerationsLabel = new JLabel("number of generations");
		JLabel populationSizeLabel = new JLabel("population size");

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(numberOfGenerationsLabel)
		        .addComponent(populationSizeLabel)
		);

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(getNumberOfGenerationsSpinner())
		        .addComponent(getPopulationSizeSpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(numberOfGenerationsLabel)
				.addComponent(getNumberOfGenerationsSpinner())
			);

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(populationSizeLabel)
				.addComponent(getPopulationSizeSpinner())
			);

		layout.setVerticalGroup(vGroup);

		return panel;
	}

	public IntegerSpinner getNumberOfGenerationsSpinner() {
		if (numberOfGenerationsSpinner == null)
			numberOfGenerationsSpinner = new IntegerSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		return numberOfGenerationsSpinner;
	}

	public IntegerSpinner getPopulationSizeSpinner() {
		if (populationSizeSpinner == null)
			populationSizeSpinner = new IntegerSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		return populationSizeSpinner;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		EvolutionaryComputationPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;

		getNumberOfGenerationsSpinner().setValue(evolutionaryComputationPreferences.getNumberOfGenerations());
		getPopulationSizeSpinner().setValue(evolutionaryComputationPreferences.getPopulationSize());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		EvolutionaryComputationPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;
		evolutionaryComputationPreferences.setNumberOfGenerations(getNumberOfGenerationsSpinner().getValue());
		evolutionaryComputationPreferences.setPopulationSize(getPopulationSizeSpinner().getValue());
	}

}
