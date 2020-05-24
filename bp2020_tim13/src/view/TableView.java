package view;

import java.util.Collections;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import model.Column;
import model.Row;
import model.Table;

public class TableView extends JTable {
	
	private DefaultTableModel model;
	private Table table;
	private Vector<Row> rows;

	public TableView(Table t) {
		model = new DefaultTableModel();
		this.setModel(new DefaultTableModel());
		if(t instanceof Table) {
			table = (Table)t;
			for(Column c : table.getChildren()) {
				model.addColumn(c);
				rows = convert(c);
				model.addRow(rows);
			}
		}
	}

	private Vector<Row> convert(Column c) {
		rows = new Vector<Row>();
		//Collections.copy(rows, c.getRows());
		return rows;
	}
	
	

}
