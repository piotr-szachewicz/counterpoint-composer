package pl.szachewicz.view.controls;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class IntegerSpinner extends JSpinner {

	public IntegerSpinner(SpinnerNumberModel model) {
		super(model);
	}

    @Override
    public Integer getValue() {
            return ((Number) super.getValue()).intValue();
    }

    /**
     * Sets the maximum value which can be set using this spinner.
     * @param maximum the maximum value which can be set using this spinner
     */
    public void setMaximumValue(double maximum) {
            SpinnerNumberModel model = (SpinnerNumberModel) super.getModel();
            model.setMaximum(maximum);
            this.setModel(model);
    }
}

