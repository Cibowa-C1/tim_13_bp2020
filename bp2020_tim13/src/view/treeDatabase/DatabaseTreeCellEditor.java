package view.treeDatabase;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

public class DatabaseTreeCellEditor extends DefaultTreeCellEditor{

	private Object stavka = null;
	private JTextField edit = null;
	
	public DatabaseTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
		super(tree, renderer);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Component getTreeCellEditorComponent(
			JTree tree, 
			Object value, 
			boolean isSelected, 
			boolean expanded,
			boolean leaf, 
			int row) {
		super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
		stavka = value;
		edit = new JTextField(value.toString());
		edit.addActionListener(this);
		return edit;
	}
	
	@Override
	public boolean isCellEditable(EventObject anEvent) {
		
				return false;
	}
}
