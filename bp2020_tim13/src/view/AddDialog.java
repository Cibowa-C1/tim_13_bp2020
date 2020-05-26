package view;

import java.util.Vector;

import javax.swing.JDialog;

import model.Table;

public class AddDialog extends JDialog {
	
	
	
	public AddDialog(TableView t) {
		MyTableModel mtm = (MyTableModel)t.getModel();
		Vector attributes = mtm.getColumnV();
		for (Object object : attributes) {
			
		}
	}
}
