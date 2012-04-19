package pl.szachewicz.view.preferences;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import pl.szachewicz.view.AbstractDialog;

public class PreferencesDialog extends AbstractDialog {

	private GeneratorPreferencesPanel generatorPreferencesPanel;
	private EvaluatorPreferencesPanel evaluatorPreferencesPanel;

	public PreferencesDialog() {
		this.setModal(true);
		//this.setPreferredSize(new Dimension(400, 300));
		this.setMinimumSize(new Dimension(800, 650));
	}

	@Override
	protected JPanel createInterface() {

		JPanel panel = new JPanel(new BorderLayout());

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Generator", getGeneratorTab());
		tabbedPane.add("Evaluator", createEvaluatorTab());

		panel.add(tabbedPane, BorderLayout.CENTER);

		return panel;
	}

	protected JPanel getGeneratorTab() {
		if (generatorPreferencesPanel == null)
			generatorPreferencesPanel = new GeneratorPreferencesPanel();
		return generatorPreferencesPanel;
	}

	protected JPanel createEvaluatorTab() {
		if (evaluatorPreferencesPanel == null)
			evaluatorPreferencesPanel = new EvaluatorPreferencesPanel();
		return evaluatorPreferencesPanel;
	}

	public void fillViewFromModel(Object model) {
		generatorPreferencesPanel.fillViewFromModel(model);
		evaluatorPreferencesPanel.fillViewFromModel(model);
	}

	public void fillModelFromView(Object model) {
		generatorPreferencesPanel.fillModelFromView(model);
		evaluatorPreferencesPanel.fillModelFromView(model);
	}

}