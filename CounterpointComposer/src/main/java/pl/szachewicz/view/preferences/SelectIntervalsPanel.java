package pl.szachewicz.view.preferences;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import pl.szachewicz.model.preferences.IntervalTableModel;
import pl.szachewicz.view.AbstractPanel;

public abstract class SelectIntervalsPanel extends AbstractPanel{

	private final JTable intervalsTable;
	protected final IntervalTableModel intervalsTableModel;

	public SelectIntervalsPanel() {
		setBorder(new TitledBorder(getTitle()));
		setLayout(new BorderLayout());

		intervalsTableModel = new IntervalTableModel();
		intervalsTable = new JTable(intervalsTableModel);

        add(new JScrollPane(intervalsTable));
	}

	protected abstract String getTitle();
}
