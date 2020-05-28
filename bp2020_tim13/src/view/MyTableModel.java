package view;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import app.AppCore;
import model.Row;
import model.Table;

public class MyTableModel extends DefaultTableModel {
	
	
	private List<Row> rows;
	private Table table;
	private Vector dataV;
	private Vector columnV;
	private int columnCount;

	
	private void updateModel() {
		
		columnCount = rows.get(0).getFields().keySet().size();
		
		columnV = DefaultTableModel.convertToVector(rows.get(0).getFields().keySet().toArray());
		dataV = new Vector(columnCount);
		
		for (int i = 0; i < rows.size(); i++) {
			dataV.add(DefaultTableModel.convertToVector(rows.get(i).getFields().values().toArray()));
		}
		
		setDataVector(dataV, columnV);
		
	}
	
	public void setRows(List<Row> rows, Table table) {
		this.rows = rows;
		this.table  = table;
		updateModel();
	}
	public Vector getDataV() {
		return dataV;
	}
	public Vector getColumnV() {
		return columnV;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return super.getValueAt(row, column);
	}
	@Override
	public int getColumnCount() {
		return columnCount;
	}
	public void clear() {
	    dataV.removeAllElements();
	    columnV.removeAllElements();
	    fireTableDataChanged();
	}
	

	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub
		super.removeRow(row);
	}
}
