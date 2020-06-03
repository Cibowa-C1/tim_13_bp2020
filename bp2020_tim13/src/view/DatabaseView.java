package view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import model.Database;
import model.Table;
import view.table.TableRenderer;

public class DatabaseView extends JPanel {
		
		private JScrollPane scroll;
		private TableView tableView;
		private Database dataBase;
		private JTabbedPane jtp;
		private TableRenderer tr;
		private MyTableModel model;
		private JTable table;
		private ArrayList<TableView> tables = new ArrayList<>();
	
		public DatabaseView(Database d) {
			
			if(d instanceof Database) {
				dataBase = (Database)d;
				this.dataBase = d;
				jtp = new JTabbedPane();
				jtp.setPreferredSize(new Dimension(1000,367));
				jtp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				this.add(jtp);
			}
		}
		
		public Component getSelectedTab() {
			return jtp.getSelectedComponent();
		}
		public void addTab(Table t) {
			model = new MyTableModel();
			tableView = new TableView(t);
			tableView.setPreferredSize(new Dimension(1000,2000));
			tables.add(tableView);
			tr = new TableRenderer();
			tableView.setDefaultRenderer(Object.class, tr);
			JScrollPane scr = new JScrollPane(tableView);
			scr.setPreferredSize(new Dimension(1000,1000));
			jtp.addTab(t.getName(), scr);

			this.updateUI();
		}
		public ArrayList<TableView> getTables() {
			return tables;
		}
		public JTabbedPane getJtp() {
			return jtp;
		}
		public TableView getTableView() {
			return tableView;
		}
}
