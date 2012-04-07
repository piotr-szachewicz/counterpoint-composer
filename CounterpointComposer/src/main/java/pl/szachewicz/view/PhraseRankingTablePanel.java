package pl.szachewicz.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import pl.szachewicz.model.EvaluatedPhrase;
import pl.szachewicz.model.PhraseRankingTableModel;

public class PhraseRankingTablePanel extends JPanel {

	private JTable table;
	private PhraseRankingTableModel tableModel;

	public PhraseRankingTablePanel() {
		super();
		initialize();
	}

	public void addSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);
	}

	protected void initialize() {
		TitledBorder border = new TitledBorder("Best phrases");
		this.setBorder(border);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableModel = new PhraseRankingTableModel();
		table.setModel(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(200, 250));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(scrollPane);
	}

	public void fillFromModel(List<EvaluatedPhrase> phrases) {
		tableModel.setPhrases(phrases);
	}

	public int getSelectedIndex() {
		ListSelectionModel selectionModel = table.getSelectionModel();
		return selectionModel.getMinSelectionIndex();
	}

}
