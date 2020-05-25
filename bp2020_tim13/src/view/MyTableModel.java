package view;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

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
	    fireTableDataChanged();
	}
	public void removeRow( int i ) {
		dataV.remove(i);
	    fireTableRowsDeleted(i,i);
	}
	public void addRow(Object o[]) {
	     dataV.add(o);
	     fireTableRowsInserted(rows.size()-1,rows.size()-1);
	}
}
