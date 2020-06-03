package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import action.repository.Repository;
import app.AppCore;
import model.Column;
import model.ColumnType;
import model.Row;
import model.Table;

public class SearchDialog extends JDialog {
	
	private JComboBox comboBoxCol;
	private JComboBox<String> comboBoxOperation;
	private JComboBox<String> comboBoxAndOr;
	private Vector columns;
	private Table table;
	private JLabel colName;
	private JLabel operationName;
	private JLabel value;
	private JLabel AndOr;
	private JTextField valueTxt;
	private JButton next;
	private JButton done;
	private StringBuilder sb;
	private boolean flag;
	private Repository rep;
	public SearchDialog(TableView tableView) {
		try {
			rep = new Repository();
			colName=new JLabel("Column name");
			operationName=new JLabel("Operation");
			value = new JLabel("Value");
			AndOr = new JLabel("AND or OR");
			valueTxt = new JTextField();
			next = new JButton("Next");
			comboBoxAndOr = new JComboBox<String>();
			comboBoxOperation = new JComboBox<String>();
			done = new JButton("Done");
			table = tableView.getTable();
			setPreferredSize(new Dimension(1000, 600));
			setTitle("Search");
			setLocationRelativeTo(null);
			pack();
			sb = new StringBuilder("SELECT * FROM " + table.getName() + " WHERE ");
			comboBoxAndOr.addItem("OR");
			comboBoxAndOr.addItem("AND");
			columns = new Vector(tableView.getTable().getChildCount());
			
			for(int i = 0; i < tableView.getTable().getChildren().size(); i++) 
				columns.add(tableView.getTable().getChildren().get(i).getName());
	
			comboBoxCol = new JComboBox<String>(columns);
			
			comboBoxCol.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					
					String chosen = comboBoxCol.getSelectedItem().toString();
					Column c = table.getChildNode(chosen);
					if(c.getType().equals(ColumnType.INT) 
							|| c.getType().equals(ColumnType.DECIMAL)
							|| c.getType().equals(ColumnType.NUMERIC)
							|| c.getType().equals(ColumnType.FLOAT)) {
						flag=true;
						comboBoxOperation.removeAllItems();
						comboBoxOperation.addItem(">");
						comboBoxOperation.addItem("<");
						comboBoxOperation.addItem("=");
						comboBoxOperation.addItem("<=");
						comboBoxOperation.addItem(">=");
					}else if(c.getType().equals(ColumnType.CHAR) 
							|| c.getType().equals(ColumnType.VARCHAR)){
						flag = false;
						comboBoxOperation.removeAllItems();
						comboBoxOperation.addItem("Pronadji sve sto pocinje sa:");
						comboBoxOperation.addItem("Pronadji sve sto se zavrsava sa");
						comboBoxOperation.addItem("Pronadji sve sto sadrzi");
						comboBoxOperation.addItem("Pronadji sve sto se krece od drugog mesta sa");
						comboBoxOperation.addItem("Pronadji sve sto se pocinje sa , a ima najmanje 3 slova");
						
					}
					else {
						comboBoxOperation.removeAllItems();
						comboBoxOperation.addItem("Nije predvidjeno za pretragu");
					}
				}
			});
			next.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag) {
						sb.append(comboBoxCol.getSelectedItem().toString()+
							comboBoxOperation.getSelectedItem().toString()+valueTxt.getText()+" "
							+comboBoxAndOr.getSelectedItem().toString()+" ");
					}else {
						sb.append(comboBoxCol.getSelectedItem().toString()+" LIKE ");
						if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto pocinje sa:"))
							sb.append("'"+valueTxt.getText()+"%' ");
						else if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto se zavrsava sa"))
							sb.append("'%"+valueTxt.getText()+"' ");
						else if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto sadrzi"))
							sb.append("'%"+valueTxt.getText()+"%' ");
						else if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto se krece od drugog mesta sa"))
							sb.append("'_"+valueTxt.getText()+"%' ");
						else if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto se pocinje sa , a ima najmanje 3 slova"))
							sb.append("'"+valueTxt.getText()+"_%_%' ");
						sb.append(comboBoxAndOr.getSelectedItem().toString()+" ");
					}
					
				}
			});
			done.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(flag) {
						sb.append(comboBoxCol.getSelectedItem().toString()+
							comboBoxOperation.getSelectedItem().toString()+valueTxt.getText());
					}else {
						sb.append(comboBoxCol.getSelectedItem().toString()+" LIKE ");
						if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto pocinje sa:"))
							sb.append("'"+valueTxt.getText()+"%' ");
						else if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto se zavrsava sa"))
							sb.append("'%"+valueTxt.getText()+"' ");
						else if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto sadrzi"))
							sb.append("'%"+valueTxt.getText()+"%' ");
						else if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto se krece od drugog mesta sa"))
							sb.append("'_"+valueTxt.getText()+"%' ");
						else if(comboBoxOperation.getSelectedItem().toString().equals("Pronadji sve sto se pocinje sa , a ima najmanje 3 slova"))
							sb.append("'"+valueTxt.getText()+"_%_%'");
					}
					setVisible(false);
					Connection connection = AppCore.startConnection();
					try {
						System.out.println(sb.toString());
						
						ResultSet rs = rep.ExcecuteBaseQuery(connection, sb.toString());
						List<Row> rows = new ArrayList<Row>();
			                while (rs.next()){

			                    Row row = new Row(tableView.getTable().getName());

			                    ResultSetMetaData resultSetMetaData = rs.getMetaData();
			                    for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
			                        row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
			                    }
			                    rows.add(row);
							}
			                SearchResultDialog srd = new SearchResultDialog(rows);
					} catch (SQLException e1) {
						e1.printStackTrace();
						OptionDialog op = new OptionDialog("Unesi validan tekst");
						return;
					}
					AppCore.CloseConnection(connection);
				}
			});
			add(colName);
			add(comboBoxCol);
			add(operationName);
			add(comboBoxOperation);
			add(value);
			add(valueTxt);
			add(AndOr);
			add(comboBoxAndOr);
			add(next);
			add(done);
			setLayout(new GridLayout(5, 2));
			setVisible(true);
		}catch (Exception e) {
			OptionDialog op = new OptionDialog("Unesi validne podatke");
		}
	}
		
}
