package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import app.AppCore;
import model.Row;
import model.Table;
import view.MainFrame;
import view.TableView;

public class RelationAction extends ActionAbstract{

	public RelationAction() {
		putValue(NAME, "Relation");
		putValue(SHORT_DESCRIPTION, "Relation");
	}
	private int getColumnByName(TableView table, String name) {
	    for (int i = 0; i < table.getColumnCount(); ++i)
	        if (table.getColumnName(i).equals(name))
	            return i;
	    return -1;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getLowerTable().getJtp().removeAll();
		MainFrame.getInstance().getLowerTable().getJtp().updateUI();
		List<Table> tables = new ArrayList<Table>();
		Component o = MainFrame.getInstance().getdV().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			Table table = t.getTable();
			Row r = table.getRows().get(t.getSelectedRow());
			int rowIndex = t.getSelectedRow();
			
			Connection connection = AppCore.startConnection();
			try {
				String prmk = null;
				DatabaseMetaData dmd = connection.getMetaData();
				ResultSet primKey = dmd.getPrimaryKeys(connection.getCatalog(), null, table.getName());
				while(primKey.next()) {
					prmk = primKey.getString("COLUMN_NAME");
			}
				String value = (String) t.getValueAt(rowIndex, getColumnByName(t, prmk));
				System.out.println("Potreban value je "+value);
				for (Table tblMod : table.getDatabase().getChildren()) {
					if(tblMod.getName().equals(table.getName())) continue;
					if(tblMod.getChildNode(prmk)!=null) {
						tables.add(tblMod);
					}
				}
				for (Table tblRel : tables) {
					MainFrame.getInstance().getLowerTable().addTab(tblRel);
					TableView tabView = MainFrame.getInstance().getLowerTable().getTableView();
					int colindex = getColumnByName(tabView, prmk);
					for (int i = 0; i < tabView.getRowCount(); i++) {
						String val = (String) tabView.getValueAt(i, colindex);
						if(val==null) {
							tabView.getTableModel().removeRow(i);
							 i=-1;
						}
						else if(!val.equals(value)) {
							 tabView.getTableModel().removeRow(i);
							 i=-1;
						}
					}
					
				}
			}catch(Exception es) {
				es.printStackTrace();
			}
			AppCore.CloseConnection(connection);
	}
		
	}

	
}
