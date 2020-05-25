package view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import model.Database;
import model.Table;

public class DatabaseView extends JPanel {
		
		private JScrollPane scroll;
		private TableView tableView;
		private Database dataBase;
		private JTabbedPane jtp;
		private MyTableModel model;
		private JTable table;
		private ArrayList<TableView> tables = new ArrayList<>();
	
		public DatabaseView(Database d) {
			
			if(d instanceof Database) {
				dataBase = (Database)d;
				this.dataBase = d;
				jtp = new JTabbedPane();
				jtp.setPreferredSize(new Dimension(900,700));
				jtp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				this.add(jtp);
			}
		}
		public void addTab(Table t) {
			model = new MyTableModel();
			
			tableView = new TableView(t);
			tables.add(tableView);
			jtp.addTab(t.getName(), new JScrollPane(tableView));
			//table = (Table)t;
			//rows = table.getRows();
			//tableModel = new MyTableModel();
			//tableModel.setRows(rows);
			//jtable = new JTable(tableModel);
			
			
			this.updateUI();
		}
		public ArrayList<TableView> getTables() {
			return tables;
		}
		
}
