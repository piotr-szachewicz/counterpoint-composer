package pl.szachewicz.view.preferences;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import pl.szachewicz.controller.PreferencesManager;
import pl.szachewicz.model.ValidationErrors;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.abstractcomponents.AbstractDialog;
import pl.szachewicz.view.preferences.evaluator.EvaluatorPreferencesPanel;
import pl.szachewicz.view.preferences.generator.GeneratorPreferencesPanel;

public class PreferencesDialog extends AbstractDialog implements PropertyChangeListener {

	private Preferences preferences;

	private GeneratorPreferencesPanel generatorPreferencesPanel;
	private EvaluatorPreferencesPanel evaluatorPreferencesPanel;

	private PreferencesPresetsPanel preferencesPresetsPanel;

	public PreferencesDialog(JFrame owner) {
		super(owner);
		this.setModal(true);
		this.setMinimumSize(new Dimension(800, 650));
		this.setLocationRelativeTo(owner);
		this.setTitle("Preferences");
	}

	@Override
	protected JPanel createInterface() {

		JPanel panel = new JPanel(new BorderLayout());

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Generator", getGeneratorTab());
		tabbedPane.add("Evaluator", createEvaluatorTab());

		panel.add(tabbedPane, BorderLayout.CENTER);

		panel.add(getPreferencesPresetsPanel(), BorderLayout.SOUTH);

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

	public PreferencesPresetsPanel getPreferencesPresetsPanel() {
		if (preferencesPresetsPanel == null) {
			preferencesPresetsPanel = new PreferencesPresetsPanel();
			preferencesPresetsPanel.addPropertyChangeListener(this);
		}
		return preferencesPresetsPanel;
	}

	public void fillViewFromModel(Object model) {
		preferences = (Preferences) model;
		generatorPreferencesPanel.fillViewFromModel(model);
		evaluatorPreferencesPanel.fillViewFromModel(model);
	}

	public void fillModelFromView(Object model) {
		generatorPreferencesPanel.fillModelFromView(model);
		evaluatorPreferencesPanel.fillModelFromView(model);
	}

	@Override
	protected boolean validateDialog(ValidationErrors errors) {
		boolean ok = true;

		if (!generatorPreferencesPanel.validateView(errors))
			ok = false;
		else if (!evaluatorPreferencesPanel.validateView(errors))
			ok = false;

		return ok;
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (PreferencesPresetsPanel.SAVE_PRESET_BUTTON_PRESSED.equals(event.getPropertyName())) {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
				return;
			File file = fileChooser.getSelectedFile();

			Preferences newPreferences = new Preferences();
			fillModelFromView(newPreferences);

			PreferencesManager.savePreferencesToFile(newPreferences, file);
		} else if (PreferencesPresetsPanel.LOAD_PRESET_BUTTON_PRESSED.equals(event.getPropertyName())) {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
				return;
			File file = fileChooser.getSelectedFile();

			Preferences newPreferences = PreferencesManager.loadPreferencesFromFile(file);
			preferences.copyFrom(newPreferences);
			fillViewFromModel(preferences);
		} else if (PreferencesPresetsPanel.RESET_TO_DEFAULTS_BUTTON_PRESSED.equals(event.getPropertyName())) {
			preferences.setDefaults();
			fillViewFromModel(preferences);
		}
	}

}
