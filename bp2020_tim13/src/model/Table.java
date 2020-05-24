package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Table implements MutableTreeNode {
	
	private Database parent;
	private List<Column> children = new ArrayList<Column>();
	private String name;
	private List<Row> rows = new ArrayList<Row>();
	
	

	public Table(String name) {
		this.name = name;
		children = new ArrayList<Column>();
	}
	
	public void addColumn(Column c) {
		children.add(c);
		c.setParent(this);
	}
	
	public List<Row> getRows() {
		return rows;
	}
	
	public void addRows(Row r) {
		rows.add(r);
	}
	
	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}
	@Override
	public TreeNode getChildAt(int childIndex) {
		return children.get(childIndex);
	}

	public List<Column> getChildren() {
		return children;
	}
	
	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public int getIndex(TreeNode node) {
		return children.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration<? extends TreeNode> children() {
		
		return (Enumeration<? extends TreeNode>) children;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		children.add(index, (Column) child);

	}

	@Override
	public void remove(int index) {
		children.remove(index);

	}

	@Override
	public void remove(MutableTreeNode node) {
		children.remove(node);

	}

	@Override
	public void setUserObject(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFromParent() {
		parent.remove(this);

	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		parent = (Database) newParent;

	}

}
