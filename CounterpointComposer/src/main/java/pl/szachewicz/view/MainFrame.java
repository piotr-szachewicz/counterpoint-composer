package pl.szachewicz.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.view.preferences.PreferencesDialog;

public class MainFrame extends JFrame implements ListSelectionListener {

	public static int WINDOW_HEIGHT = 370;
	public static int WINDOW_WIDTH = 990;

	private final StavePanel stavePanel;
	private final PhraseRankingTablePanel phrasesTablePanel;

	private final JFileChooser fileChooser = new JFileChooser();
	private final PreferencesDialog preferencesDialog = new PreferencesDialog();
	private final Preferences preferences = new Preferences();

	public MainFrame() {
		super();
		preferences.setDefaults();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle("Counterpoint composer");

		createMenu();

		JPanel panel = new JPanel(new BorderLayout());

		stavePanel = new StavePanel();
		stavePanel.setPreferences(preferences);
		panel.add(stavePanel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		JButton playButton = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				stavePanel.playScore();
			}
		});
		playButton.setText("Play");
		buttonsPanel.add(playButton);

		JButton stopButton = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				stavePanel.stopPlaying();
			}
		});
		stopButton.setText("Stop");
		buttonsPanel.add(stopButton);

		JButton generateButton = new JButton(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				List<EvaluatedPhrase> phrases = stavePanel.generateRanking();
				phrasesTablePanel.fillFromModel(phrases);
			}
		});
		generateButton.setText("Generate ranking");
		buttonsPanel.add(generateButton);

		JButton evaluateButton = new JButton(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				stavePanel.evaluate();
			}
		});
		evaluateButton.setText("Evaluate");
		buttonsPanel.add(evaluateButton);

		panel.add(buttonsPanel, BorderLayout.SOUTH);

		//table
		phrasesTablePanel = new PhraseRankingTablePanel();
		panel.add(phrasesTablePanel, BorderLayout.WEST);
		phrasesTablePanel.addSelectionListener(this);

		this.add(panel);
	}

	protected void createMenu() {
		JMenuBar menuBar = new JMenuBar();

		//File
		JMenu menu = new JMenu("File");
		JMenuItem saveMenuItem = new JMenuItem(new SaveAction());
		menu.add(saveMenuItem);

		JMenuItem saveToMidiMenuItem = new JMenuItem(new SaveToMidiAction());
		menu.add(saveToMidiMenuItem);

		JMenuItem loadMenuItem = new JMenuItem(new LoadJmAction());
		menu.add(loadMenuItem);

		menuBar.add(menu);

		//Preferences
		menu = new JMenu("Preferences");
		JMenuItem editPreferencesMenuItem = new JMenuItem(new EditPreferencesAction());
		menu.add(editPreferencesMenuItem);

		menuBar.add(menu);

		this.setJMenuBar(menuBar);
	}

	class SaveAction extends AbstractAction {

		public SaveAction() {
			super("Save");
		}

		public void actionPerformed(ActionEvent arg0) {
			int returnVal = fileChooser.showSaveDialog(MainFrame.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	File selectedFile = fileChooser.getSelectedFile();
	        	stavePanel.saveResults(selectedFile.getAbsolutePath());
	        }
		}

	}

	class LoadJmAction extends AbstractAction {
		public LoadJmAction() {
			super("Load JM");
		}

		public void actionPerformed(ActionEvent arg0) {
			int returnVal = fileChooser.showOpenDialog(MainFrame.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	File selectedFile = fileChooser.getSelectedFile();
	        	stavePanel.loadScoreFromJMFile(selectedFile.getAbsolutePath());
	        }

		}
	}

	class SaveToMidiAction extends AbstractAction {
		public SaveToMidiAction() {
			super("Save to MIDI");
		}

		public void actionPerformed(ActionEvent e) {
			int returnVal = fileChooser.showSaveDialog(MainFrame.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	File selectedFile = fileChooser.getSelectedFile();
	        	stavePanel.saveToMidi(selectedFile.getAbsolutePath());
	        }
		}
	}

	class EditPreferencesAction extends AbstractAction {
		public EditPreferencesAction() {
			super("Preferences");
		}

		public void actionPerformed(ActionEvent e) {
			preferencesDialog.showDialog(preferences);
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		List<EvaluatedPhrase> ranking = stavePanel.getRanking();
		if (ranking != null) {
			int selectedIndex = phrasesTablePanel.getSelectedIndex();
			if (selectedIndex != -1)
				stavePanel.setPhrase(selectedIndex);
		}
	}

}
