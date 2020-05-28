package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Table;

public class AverageDialog extends JDialog {

	private JLabel lbl;
	private double avg;
	private int counter;
	private Table table;
	private MyTableModel model;
	private int selectedRow;
	private int selectedColumn;
	private JComboBox cmbbox;
	////
	
	public AverageDialog(TableView tableView) {
		setPreferredSize(new Dimension(500, 200));
		setTitle("Average");
		setLocationRelativeTo(null);
		pack();
		double sum = 0;
		counter = 0;
		selectedColumn = tableView.getSelectedColumn();
		selectedRow = tableView.getSelectedRow();
		String val = null;
		for(int i = 0; i < tableView.getRowCount(); i++) {
			if(tableView.getValueAt(i, selectedColumn)==null)
				continue;
			else
				val = tableView.getValueAt(i, selectedColumn).toString();
			if(isNumeric(val)){
				sum = sum + Double.parseDouble(val);
				counter++;
			}
		}
		if(counter==0) {
			OptionDialog dialogOpt = new OptionDialog("Format kolone nije numericki");
			return;
		}
		avg = sum / counter;
		lbl = new JLabel("Average vrednost za tabelu " + tableView.getTable().getName() + " Kolone: "+ tableView.getColumnName(selectedColumn)+ " je: " + avg );
		lbl.setPreferredSize(new Dimension(50, 50));
		add(lbl,BorderLayout.CENTER);
		setVisible(true);
	}
	
	public static boolean isNumeric(String strNum) {
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	private int getColumnByName(TableView table, String name) {
	    for (int i = 0; i < table.getColumnCount(); ++i)
	        if (table.getColumnName(i).equals(name))
	            return i;
	    return -1;
	}
}
