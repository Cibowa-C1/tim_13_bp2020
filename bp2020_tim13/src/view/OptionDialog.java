package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class OptionDialog extends JDialog {

	private JLabel lbl;
	
	public OptionDialog() {
		setPreferredSize(new Dimension(500, 500));
		setTitle("Warning!");
		lbl = new JLabel("Niste uneli validne podatke.");
		add(lbl,BorderLayout.CENTER);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
}
