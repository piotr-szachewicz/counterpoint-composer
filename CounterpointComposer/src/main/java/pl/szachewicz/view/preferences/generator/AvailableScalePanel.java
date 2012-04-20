package pl.szachewicz.view.preferences.generator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import jm.JMC;
import jm.constants.RhythmValues;
import jm.gui.cpn.BassStave;
import jm.gui.cpn.Stave;
import jm.gui.cpn.TrebleStave;
import jm.music.data.Note;
import jm.music.data.Phrase;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.model.preferences.StaveType;
import pl.szachewicz.view.abstractcomponents.AbstractPanel;

public class AvailableScalePanel extends AbstractPanel implements ItemListener {

	private TrebleStave trebleStave;
	private BassStave bassStave;
	private JPanel stavePanel;
	private JComboBox staveTypeComboBox;

	private final StaveType selectedStave = StaveType.TREBLE;

	public AvailableScalePanel() {
		super();
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Counterpoint scale"));
		this.add(createStavePanel(), BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.add(getStaveTypeComboBox());
		this.add(panel, BorderLayout.SOUTH);
	}

	protected JComponent createStavePanel() {
		stavePanel = new JPanel(new CardLayout());
		//stavePanel.setLayout(new BoxLayout(stavePanel, BoxLayout.Y_AXIS));

		trebleStave = new TrebleStave();
		trebleStave.setAvailableRhythmValues(new double[] {RhythmValues.QUARTER_NOTE});
		bassStave = new BassStave();
		bassStave.setAvailableRhythmValues(new double[] {RhythmValues.QUARTER_NOTE});

		stavePanel.add(trebleStave, StaveType.TREBLE.toString());
		stavePanel.add(bassStave, StaveType.BASS.toString());

		JScrollPane scrollPane = new JScrollPane(stavePanel);
		scrollPane.setPreferredSize(new Dimension(470, 125));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		return scrollPane;
	}

	protected JComboBox getStaveTypeComboBox() {
		if (staveTypeComboBox == null) {
			staveTypeComboBox = new JComboBox(StaveType.values());
			staveTypeComboBox.addItemListener(this);
			staveTypeComboBox.setEnabled(false);
		}
		return staveTypeComboBox;
	}

	public StaveType getSelectedStaveType() {
		return (StaveType) getStaveTypeComboBox().getSelectedItem();
	}

	public Stave getCurrentStave() {
		if (getSelectedStaveType() == StaveType.TREBLE)
			return trebleStave;
		else
			return bassStave;
	}

	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			CardLayout cl = (CardLayout) (stavePanel.getLayout());
			cl.show(stavePanel, event.getItem().toString());
		}
	}

	public void fillViewFromModel(Object model) {
		Preferences preferences = (Preferences) model;

		Phrase phrase = new Phrase();
		for (int note: preferences.getScale()) {
			phrase.add(new Note(note, JMC.QUARTER_NOTE));
		}

		StaveType counterpointStaveType = preferences.getCounterpointStaveType();
		getStaveTypeComboBox().setSelectedItem(counterpointStaveType);
		getCurrentStave().setPhrase(phrase);
	}

	public void fillModelFromView(Object model) {
		Preferences preferences = (Preferences) model;

		List<Integer> scale = new ArrayList<Integer>();
		for (Note note: getCurrentStave().getPhrase().getNoteArray()) {
			scale.add(note.getPitch());
		}
		preferences.setScale(scale);
	}

}
