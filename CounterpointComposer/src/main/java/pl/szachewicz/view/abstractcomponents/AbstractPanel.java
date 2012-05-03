package pl.szachewicz.view.abstractcomponents;

import javax.swing.JPanel;

import pl.szachewicz.model.ValidationErrors;

public abstract class AbstractPanel extends JPanel implements FillableView {

	public boolean validateView(ValidationErrors errors) {
		return true;
	}
}
