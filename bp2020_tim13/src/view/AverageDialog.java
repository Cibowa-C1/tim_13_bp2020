package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import app.AppCore;
import model.Column;
import model.ColumnType;
import model.Row;
import model.Table;

public class AverageDialog extends JDialog {

	private JLabel lbl;
	private double avg;
	private int counter;
	private Table table;
	private MyTableModel model;
	private int selectedRow;
	private int selectedColumn;
	private List<JCheckBox> checkBoxes;
	private JCheckBox check;
	private JComboBox comboBox;
	private Vector columns;
	private Vector columns2;
	private String selectedCol;
	private JButton btnOk;
	private JLabel labela;
	private JLabel izabVr;

	////
	
	public AverageDialog(TableView tableView) {
		setPreferredSize(new Dimension(600, 600));
		setTitle("Average");
		setLocationRelativeTo(null);
		pack();
		double sum = 0;
		counter = 0;
		selectedRow = tableView.getSelectedRow();
		String val = null;
		checkBoxes = new ArrayList<>();
		columns = new Vector(tableView.getTable().getChildCount());
		columns2 = new Vector(tableView.getTable().getChildCount());

		izabVr = new JLabel("Izaberite vrednosti za average: ");
		btnOk = new JButton("Ok");
		labela = new JLabel("Izaberite po cemu radite Average funkciju: ");
		izabVr.setPreferredSize(new Dimension(120, 20));
		btnOk.setPreferredSize(new Dimension(120, 20));
		labela.setPreferredSize(new Dimension(120, 20));

		//COLUMNS ONLY NUMBERS
		for(int i = 0; i < tableView.getTable().getChildren().size(); i++) {
			if(tableView.getTable().getChildren().get(i).getType().equals(ColumnType.INT) 
			|| tableView.getTable().getChildren().get(i).getType().equals(ColumnType.DECIMAL)
			|| tableView.getTable().getChildren().get(i).getType().equals(ColumnType.NUMERIC)
			|| tableView.getTable().getChildren().get(i).getType().equals(ColumnType.FLOAT))
				columns.add(tableView.getTable().getChildren().get(i).getName());
			columns2.add(tableView.getTable().getChildren().get(i).getName());
		}
		
		//POPULATE ComboBox
		comboBox = new JComboBox<String>(columns);
		
		add(labela);
		//populate CHECKBOXES
		for(int i = 0; i < tableView.getTable().getChildren().size(); i++) {
			if(columns2.get(i).toString().equals(selectedCol))
				continue;
			check = new JCheckBox(columns2.get(i).toString());
			check.setPreferredSize(new Dimension(120, 20));
			check.setName(columns2.get(i).toString());
			checkBoxes.add(check);
			add(check);
		}
		add(izabVr);
		add(comboBox);
		add(btnOk);
		pack();	
		this.setLayout(new GridLayout(checkBoxes.size()+2, 1));
		
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Connection connection = AppCore.startConnection();
				StringBuilder query = new StringBuilder("SELECT AVG("+ comboBox.getSelectedItem().toString()+")");
				for (JCheckBox jCheckBox : checkBoxes) {
					if(jCheckBox.isSelected()) {
						query.append(", "+jCheckBox.getName());
					}
				}
				query.append(" FROM " + tableView.getTable().getName() +" GROUP BY ");
				boolean flag = true;
				for (JCheckBox jCheckBox : checkBoxes) {
					if(jCheckBox.isSelected()) {
						if(flag) {
						query.append(jCheckBox.getName());
						flag=false;
						}
						else {
							query.append(", "+jCheckBox.getName());
						}
					}
				}
				try {
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
                ResultSet rs = preparedStatement.executeQuery();
                List<Row> rows = new ArrayList<Row>();
                while (rs.next()){

                    Row row = new Row(tableView.getTable().getName());

                    ResultSetMetaData resultSetMetaData = rs.getMetaData();
                    for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                        row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                    }
                    rows.add(row);
				}
				}
				catch(Exception es){
					es.printStackTrace();
				}
				AppCore.CloseConnection(connection);
			}
		});
		
		//selectedCol = comboBox.getSelectedItem().toString();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
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
		*/
		setVisible(true);
	}
	
	
	
	
	
	
	
	
	public static boolean isNumeric(String strNum) {
		if(strNum==null)
			return false;
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
