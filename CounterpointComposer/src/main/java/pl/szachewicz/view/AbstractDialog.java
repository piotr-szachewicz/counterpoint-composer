package pl.szachewicz.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class AbstractDialog extends JDialog implements FillableView {

	private boolean initialized = false;
	private JButton okButton;

	private boolean closedWithOk;

	protected abstract JPanel createInterface();

	public AbstractDialog(JFrame owner) {
		super(owner);
	}

	public AbstractDialog() {

	}

	public boolean showDialog(Object model) {
		if (!initialized) {
			this.setLayout(new BorderLayout());
			this.add(createInterface(), BorderLayout.CENTER);
			this.add(createControlPane(), BorderLayout.SOUTH);
			initialized = true;
		}

		fillViewFromModel(model);

		System.out.println("setting visible");
		this.setVisible(true);
		System.out.println("END");

		fillModelFromView(model);

		return closedWithOk;

	}

	private JPanel createControlPane() {
		JPanel controlPane = new JPanel();
		controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.X_AXIS));
		controlPane.setBorder(new EmptyBorder(3, 0, 0, 0));
		controlPane.add(Box.createHorizontalGlue());

		okButton = new JButton(new OkAction());
		controlPane.add(okButton);

		/*if (isCancellable()) {
			controlPane.add(Box.createHorizontalStrut(3));
			controlPane.add(getCancelButton());
		}*/

		return controlPane;
	}

	class OkAction extends AbstractAction {

		public OkAction() {
			super("OK");
		}

		public void actionPerformed(ActionEvent e) {
			closedWithOk = true;
			setVisible(false);
		}

	}

}
