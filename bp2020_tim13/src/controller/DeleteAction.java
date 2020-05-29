package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JScrollPane;

import app.AppCore;
import model.Column;
import model.ColumnType;
import model.Row;
import model.Table;
import view.MainFrame;
import view.MyTableModel;
import view.OptionDialog;
import view.TableView;

public class DeleteAction extends ActionAbstract {

	public DeleteAction() {
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Delete");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Component o = MainFrame.getInstance().getdV().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			MyTableModel mdl = (MyTableModel) t.getModel();
			
			Table table = t.getTable();
			Row r = table.getRows().get(t.getSelectedRow());
			if(r==null) {
				OptionDialog op = new OptionDialog("Izaberi red");
				return;
			}
			Connection connection = AppCore.startConnection();
			try {
				String prmk = null;
			DatabaseMetaData dmd = connection.getMetaData();
			ResultSet primKey = dmd.getPrimaryKeys(connection.getCatalog(), null, table.getName());
			while(primKey.next()) {
				 prmk = primKey.getString("COLUMN_NAME");
			}
			String value = (String) r.getFields().get(prmk);
			for(Table tbl:table.getDatabase().getChildren()) {
				if(tbl.getName().equals(table.getName())) continue;
				String query;
				if(tbl.getChildNode(prmk)!=null) {
					ColumnType ct = tbl.getChildNode(prmk).getType();
					if(ct.equals(ColumnType.INT)||ct.equals(ColumnType.FLOAT)||ct.equals(ColumnType.NUMERIC))
						 query = "UPDATE " + tbl.getName() + " SET " + tbl.getChildNode(prmk).getName() + " = NULL WHERE " + prmk +" = " +value;
					else
						 query = "UPDATE " + tbl.getName() + " SET " + tbl.getChildNode(prmk).getName() + " = NULL WHERE " + prmk +" = " +"'"+value+"'";						
					PreparedStatement ps = connection.prepareStatement(query);
					ps.executeUpdate();
					for(Row rw:tbl.getRows()) {
						rw.getFields().replace(prmk,value, null);
					}
				}
			}
			
			Column c = table.getChildNode(prmk);
			String query = null;
			System.out.println(prmk);
			System.out.println(value);
			System.out.println(c.getType());
			if(c.getType().equals(ColumnType.NUMERIC) || (c.getType().equals(ColumnType.INT)))
				query = "DELETE FROM " + table.getName() +   " WHERE " + prmk + " = "+Integer.parseInt(value);
			else if(c.getType().equals(ColumnType.FLOAT)) {
				System.out.println(Float.parseFloat(value));
				System.out.println(prmk);
				 query = "DELETE FROM " + table.getName() +   " WHERE " + prmk + " = "+Float.parseFloat(value);
			}	
			else if((c.getType().equals(ColumnType.CHAR)||(c.getType().equals(ColumnType.VARCHAR)))){
				char[] ch=value.toCharArray();  
				 query = "DELETE FROM " + table.getName() +   " WHERE " + prmk + " = " + "'"+value+"'";

			}
			else if(c.getType().equals(ColumnType.DATETIME)){
				
				 query = "DELETE FROM " + table.getName() +   " WHERE " + prmk + " = " + "'"+value+"'";

			}
			else 
				 query = "DELETE FROM " + table.getName() +   " WHERE " + prmk + " = " + value;
			System.out.println(query);
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.executeUpdate();
			int cnt = t.getSelectedRow();
			mdl.removeRow(cnt);
			table.removeRows(r);
			}
			catch(Exception es) {
				//es.printStackTrace();
				OptionDialog op = new OptionDialog("Druge tabele zavise od ovog kljuca ne mozes ga obrisati");
			}

			
			
			AppCore.CloseConnection(connection);
		}

	}

}
