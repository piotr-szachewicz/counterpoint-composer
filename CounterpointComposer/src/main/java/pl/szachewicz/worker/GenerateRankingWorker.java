package pl.szachewicz.worker;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import jm.music.data.Phrase;
import pl.szachewicz.algorithm.ranking.AbstractRanking;
import pl.szachewicz.algorithm.ranking.FullSearchRanking;
import pl.szachewicz.model.preferences.Preferences;
import pl.szachewicz.model.preferences.SearchAlgorithm;

public class GenerateRankingWorker extends SwingWorker<AbstractRanking, Void> implements PropertyChangeListener {

	private final Phrase cantusFirmus;
	private final Preferences preferences;

	private AbstractRanking ranking;

    private final ProgressMonitor progressMonitor;

	public GenerateRankingWorker(Component invokingComponent, Phrase cantusFirmus, Preferences preferences) {
		progressMonitor = new ProgressMonitor(invokingComponent, "Composing. Please wait...", " ", 0, 100);
		progressMonitor.setProgress(0);
		this.cantusFirmus = cantusFirmus;
		this.preferences = preferences;
	}

	@Override
	protected AbstractRanking doInBackground() throws Exception {
		System.out.println("Calc ranking");

		SearchAlgorithm searchAlgorithm = preferences.getSearchAlgorithm();
		ranking = searchAlgorithm.createRanking(cantusFirmus, preferences);

		ranking.addPropertyChangeListener(this);

		ranking.generateRanking();

		return ranking;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(FullSearchRanking.PROGRESS_PROPERTY)) {
			progressMonitor.setProgress((Integer) evt.getNewValue());
			progressMonitor.setNote("Completed: " + evt.getNewValue() + "%");
			if (progressMonitor.isCanceled()) {
				ranking.setCanceled(true);
			}
		}
	}

}
