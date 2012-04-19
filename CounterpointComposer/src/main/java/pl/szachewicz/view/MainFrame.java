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
import javax.swing.JSeparator;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.szachewicz.Controller;
import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.view.preferences.PreferencesDialog;

public class MainFrame extends JFrame implements ListSelectionListener {

	public static int WINDOW_HEIGHT = 370;
	public static int WINDOW_WIDTH = 990;

	private Controller controller;
	private StavePanel stavePanel;
	private PhraseRankingTablePanel phrasesTablePanel;

	private final JFileChooser fileChooser = new JFileChooser();
	private final PreferencesDialog preferencesDialog = new PreferencesDialog(this);

	public MainFrame() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle("Counterpoint composer");
		this.setLocationRelativeTo(null);

		createMenu();

		JPanel panel = new JPanel(new BorderLayout());

		panel.add(getStavePanel(), BorderLayout.CENTER);
		panel.add(createButtonsPanel(), BorderLayout.SOUTH);

		//table
		panel.add(getPhrasesTablePanel(), BorderLayout.WEST);

		this.add(panel);
	}

	protected JPanel createButtonsPanel() {
		JPanel buttonsPanel = new JPanel();
		JButton playButton = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				getController().playScore();
			}
		});
		playButton.setText("Play");
		buttonsPanel.add(playButton);

		JButton stopButton = new JButton(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				getController().stopPlaying();
			}
		});
		stopButton.setText("Stop");
		buttonsPanel.add(stopButton);

		JButton generateButton = new JButton(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				getController().generateRanking();
			}
		});
		generateButton.setText("Generate ranking");
		buttonsPanel.add(generateButton);

		JButton evaluateButton = new JButton(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				getController().evaluate();
			}
		});
		evaluateButton.setText("Evaluate");
		buttonsPanel.add(evaluateButton);

		return buttonsPanel;
	}

	public PhraseRankingTablePanel getPhrasesTablePanel() {
		if (phrasesTablePanel == null) {
			phrasesTablePanel = new PhraseRankingTablePanel();
			phrasesTablePanel.addSelectionListener(this);
		}
		return phrasesTablePanel;
	}

	public StavePanel getStavePanel() {
		if (stavePanel == null)
			stavePanel = new StavePanel();
		return stavePanel;
	}

	public Controller getController() {
		if (controller == null)
			controller = new Controller(this);
		return controller;
	}

	protected void createMenu() {
		JMenuBar menuBar = new JMenuBar();

		//File
		JMenu menu = new JMenu("File");

		JMenuItem newMenuItem = new JMenuItem(new NewAction());
		menu.add(newMenuItem);

		menu.add(new JSeparator());

		JMenuItem saveMenuItem = new JMenuItem(new SaveAction());
		menu.add(saveMenuItem);

		JMenuItem loadMenuItem = new JMenuItem(new LoadJmAction());
		menu.add(loadMenuItem);

		menu.add(new JSeparator());

		JMenuItem saveToMidiMenuItem = new JMenuItem(new SaveToMidiAction());
		menu.add(saveToMidiMenuItem);

		menuBar.add(menu);

		//Preferences
		menu = new JMenu("Preferences");
		JMenuItem editPreferencesMenuItem = new JMenuItem(new EditPreferencesAction());
		menu.add(editPreferencesMenuItem);

		menuBar.add(menu);

		this.setJMenuBar(menuBar);
	}

	class NewAction extends AbstractAction {
		public NewAction() {
			super("New");
		}

		public void actionPerformed(ActionEvent arg0) {
			getController().newCounterpoint();
		}
	}

	class SaveAction extends AbstractAction {

		public SaveAction() {
			super("Save");
		}

		public void actionPerformed(ActionEvent arg0) {
			int returnVal = fileChooser.showSaveDialog(MainFrame.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	File selectedFile = fileChooser.getSelectedFile();
	        	getController().saveResults(selectedFile.getAbsolutePath());
	        }
		}
	}

	class LoadJmAction extends AbstractAction {
		public LoadJmAction() {
			super("Load");
		}

		public void actionPerformed(ActionEvent arg0) {
			int returnVal = fileChooser.showOpenDialog(MainFrame.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	File selectedFile = fileChooser.getSelectedFile();
	        	getController().loadScoreFromJMFile(selectedFile.getAbsolutePath());
	        }

		}
	}

	class SaveToMidiAction extends AbstractAction {
		public SaveToMidiAction() {
			super("Export to MIDI");
		}

		public void actionPerformed(ActionEvent e) {
			int returnVal = fileChooser.showSaveDialog(MainFrame.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	File selectedFile = fileChooser.getSelectedFile();
	        	getController().saveToMidi(selectedFile.getAbsolutePath());
	        }
		}
	}

	class EditPreferencesAction extends AbstractAction {
		public EditPreferencesAction() {
			super("Preferences");
		}

		public void actionPerformed(ActionEvent e) {
			preferencesDialog.showDialog(getController().getPreferences());
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		List<EvaluatedPhrase> ranking = getController().getRanking();
		if (ranking != null) {
			int selectedIndex = phrasesTablePanel.getSelectedIndex();
			if (selectedIndex != -1)
				getController().setPhrase(selectedIndex);
		}
	}

}
