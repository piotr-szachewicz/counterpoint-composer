package pl.szachewicz.worker;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import jm.music.data.Phrase;
import pl.szachewicz.algorithm.Ranking;
import pl.szachewicz.model.preferences.Preferences;

public class GenerateRankingWorker extends SwingWorker<Ranking, Void> implements PropertyChangeListener {

	private final Phrase cantusFirmus;
	private final Preferences preferences;

	private Ranking ranking;

    private final ProgressMonitor progressMonitor;

	public GenerateRankingWorker(Component invokingComponent, Phrase cantusFirmus, Preferences preferences) {
		progressMonitor = new ProgressMonitor(invokingComponent, "Composing. Please wait...", " ", 0, 100);
		progressMonitor.setProgress(0);
		//progressMonitor.setMillisToDecideToPopup(0);
		this.cantusFirmus = cantusFirmus;
		this.preferences = preferences;
	}

	@Override
	protected Ranking doInBackground() throws Exception {
		System.out.println("Calc ranking");
		ranking = new Ranking(cantusFirmus, preferences);
		ranking.addPropertyChangeListener(this);

		ranking.generateRanking();

		return ranking;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Ranking.PROGRESS_PROPERTY)) {
			progressMonitor.setProgress((Integer) evt.getNewValue());
			progressMonitor.setNote("Completed: " + evt.getNewValue() + "%");
			if (progressMonitor.isCanceled()) {
				ranking.setCanceled(true);
			}
		}
	}

}
