package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.function.BiFunction;

import javax.swing.JScrollPane;

import action.repository.Repository;
import app.AppCore;
import model.Column;
import model.ColumnType;
import model.Row;
import model.Table;
import view.MainFrame;
import view.MyTableModel;
import view.OptionDialog;
import view.TableView;

public class UpdateAction extends ActionAbstract{

	public UpdateAction() {
		putValue(NAME, "Update");
		putValue(SHORT_DESCRIPTION, "Update");
	}
	 
	
	private int getColumnByName(TableView table, String name) {
	    for (int i = 0; i < table.getColumnCount(); ++i)
	        if (table.getColumnName(i).equals(name))
	            return i;
	    return -1;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Repository rep = new Repository();
		Connection connection=null;
		HashMap<String, Object> mapaKopi = new HashMap<String, Object>();
		
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
			mapaKopi.putAll(r.getFields());
			int rowIndex = t.getSelectedRow();
			
			 connection = AppCore.startConnection();
			try {
				DatabaseMetaData dmd = connection.getMetaData();
				ResultSet primKey = dmd.getPrimaryKeys(connection.getCatalog(), null, table.getName());
				String prmk = null;
				StringBuilder sb = new StringBuilder("UPDATE " + table.getName() + " SET ");
				while(primKey.next()) {
					 prmk = primKey.getString("COLUMN_NAME");
				}
				int flag = 1;
				String a = (String) r.getFields().get(prmk);
				for (Column c : table.getChildren()) {
					if(c.getName().equals(prmk)) continue;
					int colIndex = getColumnByName(t, c.getName());
					String value = (String) t.getValueAt(rowIndex, colIndex);
					if(flag==1) {
					sb.append(c.getName()+" = ");
					flag = 0;
					}else
						sb.append(", " + c.getName() + " = ");
					r.replaceValue(c.getName(), value);
					if(c.getType().equals(ColumnType.NUMERIC) || (c.getType().equals(ColumnType.INT)))
						sb.append(Integer.parseInt(value));
					else if(c.getType().equals(ColumnType.FLOAT)) {
						sb.append(Float.parseFloat(value));
					}	
					else if((c.getType().equals(ColumnType.CHAR)||(c.getType().equals(ColumnType.VARCHAR)))){
						sb.append( "'"+value+"'");

					}
					else if(c.getType().equals(ColumnType.DATETIME)){
						
						sb.append( "'"+value+"'");
					}
					else
						sb.append(value);
				}
				int colIndex = getColumnByName(t, prmk);
				String value = (String) t.getValueAt(rowIndex, colIndex);
				Column coll = table.getChildNode(prmk);
				if(coll.getType().equals(ColumnType.NUMERIC) || (coll.getType().equals(ColumnType.INT)))
					sb.append(" WHERE " + prmk + " = "+Integer.parseInt(value));
				else if(coll.getType().equals(ColumnType.FLOAT)) {
					sb.append(" WHERE " + prmk + " = "+Float.parseFloat(value));
				}	
				else if((coll.getType().equals(ColumnType.CHAR)||(coll.getType().equals(ColumnType.VARCHAR)))){
					sb.append( " WHERE " + prmk + " = "+"'"+value+"'");

				}
				else if(coll.getType().equals(ColumnType.DATETIME)){
					
					sb.append( " WHERE " + prmk + " = "+"'"+value+"'");
				}
				else
					sb.append(value);
				if(!a.equals(value)) {
					OptionDialog op = new OptionDialog("Unesi validne podatke ili ste pokusaki da promenite primary key");
					t.setValueAt(a, rowIndex, getColumnByName(t, prmk));
					return;
				}
				rep.updateQuery(connection, sb.toString());
				System.out.println(sb.toString());
			} catch (SQLException e1) {
				r.getFields().putAll(mapaKopi);
				OptionDialog op = new OptionDialog("Unesi validne podatke ili ste pokusaki da promenite primary key");
			}
		}
		AppCore.CloseConnection(connection);
	}

}
