package pl.szachewicz.view.preferences.algorithm.genetic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.GeneticAlgorithmPreferences;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;
import pl.szachewicz.view.controls.IntegerSpinner;

public class SelectionPreferencesPanel extends AbstractPanel {
	private IntegerSpinner tournamentSizeSpinner;

	public SelectionPreferencesPanel() {
		this.setBorder(new TitledBorder("Selection"));
		this.add(createInterface());
	}

	protected JPanel createInterface() {
		JPanel panel = new JPanel();

		JLabel tournamentSizeLabel = new JLabel("tournament size:");

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(tournamentSizeLabel)
		);

		hGroup.addGroup(
		        layout.createParallelGroup()
		        .addComponent(getTournamentSizeSpinner())
		);

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(tournamentSizeLabel)
				.addComponent(getTournamentSizeSpinner())
			);

		layout.setVerticalGroup(vGroup);

		return panel;
	}

	public IntegerSpinner getTournamentSizeSpinner() {
		if (tournamentSizeSpinner == null)
			tournamentSizeSpinner = new IntegerSpinner(new SpinnerNumberModel(5, 1, Integer.MAX_VALUE, 1));
		return tournamentSizeSpinner;
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;
		GeneticAlgorithmPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;

		getTournamentSizeSpinner().setValue(evolutionaryComputationPreferences.getTournamentSize());
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;
		GeneticAlgorithmPreferences evolutionaryComputationPreferences = preferences.getEvolutionaryComputationPreferences();;

		evolutionaryComputationPreferences.setTournamentSize(getTournamentSizeSpinner().getValue());
	}

}
