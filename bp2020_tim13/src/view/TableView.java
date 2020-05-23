package view;

import javax.swing.JTable;

import model.Column;
import model.Row;
import model.Table;

public class TableView extends JTable {
	
	Table table;
	Row data;
	Column dataName;

	public TableView(Table t) {
		if(t instanceof Table) {
			table = (Table)t;
			
		}
	}

}
