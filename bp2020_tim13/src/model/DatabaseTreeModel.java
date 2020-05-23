package model;

import javax.swing.tree.DefaultTreeModel;

public class DatabaseTreeModel extends DefaultTreeModel{
	public DatabaseTreeModel(Database d) {
		super(d);
	}
	
	public void addTable(Table t) {
		((Database)getRoot()).addTable(t);
	}
}
