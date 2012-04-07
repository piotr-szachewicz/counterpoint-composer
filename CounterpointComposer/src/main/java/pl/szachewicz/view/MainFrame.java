package pl.szachewicz.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	public static int WINDOW_HEIGHT = 370;
	public static int WINDOW_WIDTH = 900;

	private final StavePanel stavePanel;

	JFileChooser fileChooser = new JFileChooser();

	public MainFrame() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle("Counterpoint composer");

		createMenu();

		JPanel panel = new JPanel(new BorderLayout());
		//panel.add(new JLabel("bebe"), BorderLayout.NORTH);

		stavePanel = new StavePanel();
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
				stavePanel.generateRanking();
			}
		});
		generateButton.setText("Generate ranking");
		buttonsPanel.add(generateButton);

		JButton getNextCounterpointButton = new JButton(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				stavePanel.getNextPhrase();
			}
		});
		getNextCounterpointButton.setText("Get next");
		buttonsPanel.add(getNextCounterpointButton);

		JButton evaluateButton = new JButton(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				stavePanel.evaluate();
			}
		});
		evaluateButton.setText("Evaluate");
		buttonsPanel.add(evaluateButton);

		panel.add(buttonsPanel, BorderLayout.SOUTH);
		this.add(panel);
	}

	protected void createMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("File");
		JMenuItem saveMenuItem = new JMenuItem(new SaveAction());
		menu.add(saveMenuItem);

		JMenuItem saveToMidiMenuItem = new JMenuItem(new SaveToMidiAction());
		menu.add(saveToMidiMenuItem);

		JMenuItem loadMenuItem = new JMenuItem(new LoadJmAction());
		menu.add(loadMenuItem);

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

}
