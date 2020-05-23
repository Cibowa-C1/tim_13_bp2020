package view.treeDatabase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.Database;
import model.DatabaseTreeModel;
import model.Table;


public class DatabaseTree extends JTree {
	
	public DatabaseTree() {
		addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setCellEditor(new DatabaseTreeCellEditor(this,new DefaultTreeCellRenderer()));
		setCellRenderer(new DatabaseTreeCellRenderer());
		setEditable(true);
	}
	public void addTable(Table t) {
		((DatabaseTreeModel)getModel()).addTable(t);
		SwingUtilities.updateComponentTreeUI(this);
	}
}
