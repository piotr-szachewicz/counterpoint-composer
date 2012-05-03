package pl.szachewicz.view.preferences;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class PreferencesPresetsPanel extends AbstractPanel {

	public static String SAVE_PRESET_BUTTON_PRESSED = "savePresetButtonPressed";
	public static String LOAD_PRESET_BUTTON_PRESSED = "loadPresetButtonPressed";
	public static String RESET_TO_DEFAULTS_BUTTON_PRESSED = "resetToDefaultsButtonPressed";

	private JButton savePresetButton;
	private JButton loadPresetButton;
	private JButton resetToDefaultsButton;

	public PreferencesPresetsPanel() {
		setBorder(new TitledBorder("Preferences presets"));

		add(getSavePresetButton());
		add(getLoadPresetButton());
		add(getResetToDefaultsButton());
	}

	public JButton getSavePresetButton() {
		if (savePresetButton == null) {
			savePresetButton = new JButton(new AbstractAction() {

				public void actionPerformed(ActionEvent arg0) {
					PreferencesPresetsPanel.this.firePropertyChange(SAVE_PRESET_BUTTON_PRESSED, null, null);
				}
			});
			savePresetButton.setText("Save to file");
		}
		return savePresetButton;
	}

	public JButton getLoadPresetButton() {
		if (loadPresetButton == null) {
			loadPresetButton = new JButton(new AbstractAction() {

				public void actionPerformed(ActionEvent e) {
					PreferencesPresetsPanel.this.firePropertyChange(LOAD_PRESET_BUTTON_PRESSED, null, null);
				}
			});
			loadPresetButton.setText("Load from file");
		}
		return loadPresetButton;
	}

	public JButton getResetToDefaultsButton() {
		if (resetToDefaultsButton == null) {
			resetToDefaultsButton = new JButton(new AbstractAction() {

				public void actionPerformed(ActionEvent e) {
					PreferencesPresetsPanel.this.firePropertyChange(RESET_TO_DEFAULTS_BUTTON_PRESSED, null, null);
				}
			});
			resetToDefaultsButton.setText("Reset to defaults");
		}
		return resetToDefaultsButton;
	}

	public void fillViewFromModel(Object model) {
	}

	public void fillModelFromView(Object model) {
	}

}
