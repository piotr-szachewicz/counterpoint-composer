package pl.szachewicz.view.controls;

import javax.swing.SpinnerNumberModel;

public class PunishmentSpinner extends FloatSpinner {

	public PunishmentSpinner() {
		super(new SpinnerNumberModel(0.0, -1000, 1000, 0.1));
	}
}
