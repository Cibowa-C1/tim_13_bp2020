package view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import model.Database;
import model.Table;

public class DatabaseView extends JPanel {
		
		private JScrollPane scroll;
		private TableView tableView;
		private Database dataBase;
		private JTabbedPane jtp;
		private ArrayList<TableView> tables = new ArrayList<>();
	
		public DatabaseView(Database d) {
			
			if(d instanceof Database) {
				dataBase = (Database)d;
				this.dataBase = d;
				jtp = new JTabbedPane();
				for(Table t : dataBase.getChildren()) {
					tableView = new TableView(t);
					tableView.setPreferredScrollableViewportSize(new Dimension(500, 400));
					tableView.setFillsViewportHeight(true);
					tables.add(tableView);
					scroll = new JScrollPane();
					scroll.add(tableView);
					jtp.addTab(t.getName(), scroll);
				}
				jtp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				this.add(jtp);
			}
		}

		public ArrayList<TableView> getTables() {
			return tables;
		}
		
}
