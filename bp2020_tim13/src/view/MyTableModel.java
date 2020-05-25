package view;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.Row;
import model.Table;

public class MyTableModel extends DefaultTableModel {

	
	private List<Row> rows;
	private Table table;
	private Vector dataVec;
	private Vector columnVec;
	
	private void updateModel() {
		
		int columnCount = rows.get(0).getFields().keySet().size();
		
		
		Vector columnV = DefaultTableModel.convertToVector(rows.get(0).getFields().keySet().toArray());
		Vector dataV = new Vector(columnCount);
		
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
	@Override
	public Object getValueAt(int row, int column) {
		///////////////////////
		return super.getValueAt(row, column);
	}
	@Override
	public int getColumnCount() {
		///////////////////////
		return super.getColumnCount();
	}
	@Override
	public void addRow(Object[] rowData) {
		///////////////////////
		super.addRow(rowData);
	}
	@Override
	public void removeRow(int row) {
		///////////////////////
		super.removeRow(row);
	}
}
