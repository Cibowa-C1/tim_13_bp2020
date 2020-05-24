package view.table;

import java.util.List;
import java.util.Vector;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import model.Row;

public class TableModel extends DefaultTableModel{

	private List<Row> rows;
	
	private void updateModel() {
		
		int columnCount = rows.get(1).getFields().keySet().size();
		
		Vector columnV = DefaultTableModel.convertToVector(rows.get(1).getFields().values().toArray());
		Vector dataV = new Vector(columnCount);
		
		for (int i = 0; i < rows.size(); i++) {
			dataV.add(DefaultTableModel.convertToVector(rows.get(i).getFields().values().toArray()));
		}
		setDataVector(dataVector, columnV);
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
		updateModel();
	}
	

}
