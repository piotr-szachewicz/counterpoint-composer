package pl.szachewicz.view.controls;

import javax.swing.SpinnerNumberModel;

public class ProbabilitySpinner extends FloatSpinner {

	public ProbabilitySpinner() {
		super(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.01));
	}
}
