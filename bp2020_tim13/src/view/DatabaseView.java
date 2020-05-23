package view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Database;
import model.Table;

public class DatabaseView extends JPanel {
		
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
					tables.add(tableView);
					jtp.addTab(t.getName(), tableView);
				}
				jtp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				this.add(jtp);
			}
		}
}
