package view;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import model.Column;
import model.Row;
import model.Table;

public class TableView extends JTable {
	
	private Table table;
	private List<Row> rows;
	private JTable jtable;
	private MyTableModel tableModel;

	public TableView(Table t) {
		if(t instanceof Table) {
			table = (Table)t;
			this.table = t;
			rows = table.getRows();
			tableModel = new MyTableModel();
			tableModel.setRows(rows, table);
			this.setModel(tableModel);
			this.setFillsViewportHeight(true);
			}
		}

	public List<Row> getRows() {
		return rows;
	}


	
}

	
	


