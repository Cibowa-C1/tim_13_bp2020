package view;

import java.awt.Dimension;
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
import observer.IListener;
import observer.state.ObserverStates;

public class TableView extends JTable implements IListener {
	
	private Table table;
	private List<Row> rows;
	private JTable jtable;
	private MyTableModel tableModel;
	private int sizeCol[][];

	public TableView(Table t) {
		if(t instanceof Table) {
			table = (Table)t;
			this.table = t;
			rows = table.getRows();
			tableModel = new MyTableModel();
			tableModel.setRows(rows, table);
			this.setModel(tableModel);
			this.setFillsViewportHeight(true);
			sizeCol = new int[100][100];
			populatesize();
			}
		
		}

	public List<Row> getRows() {
		return rows;
	}

	public int[][] getSizeCol() {
		return sizeCol;
	}
	
	public MyTableModel getTableModel() {
		return tableModel;
	}
	
	
	public Table getTable() {
		return table;
	}
	
	public void populatesize() {
		int i=0;
		for(Column c :table.getChildren()) {
		sizeCol[i][0] = this.getColumnModel().getColumn(i).getMinWidth();
		sizeCol[i][1] = this.getColumnModel().getColumn(i).getMaxWidth();
		i++;
		}
	}

	@Override
	public void update(Object event, Object obj) {
		if(obj instanceof Row) {
			Row row =(Row)obj;
			
			if(event==ObserverStates.ADD) {
				rows.add(row);
				tableModel.fireTableRowsInserted(rows.size()-1,rows.size()-1);
				this.repaint();

			} 
			else if(event==ObserverStates.REMOVE){
				rows.remove(row);
				//tableModel.fireTableRowsDeleted(getSelectedRow(), getSelectedRow());
				this.repaint();
			}
			else if(event==ObserverStates.UPDATE) {
				
			}
		}
		MainFrame.getInstance().getdV().updateUI();
	}
}

	
	


