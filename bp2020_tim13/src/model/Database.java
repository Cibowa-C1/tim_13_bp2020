package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Database implements MutableTreeNode {
	
	private List<Table> children;
	private String name;
	
	

	public Database(String name) {
		this.name = name;
		children = new ArrayList<Table>();
	}
	
	public String getName() {
		return name;
	}
	public void addTable(Table t) {
		children.add(t);
		t.setParent(this);
	}
	@Override
	public String toString() {
		
		return name;
	}
	public Table getChildNode(String name) {
		for (Table table : children) {
			if(table.getName().equals(name)) return table;
		}
		return null;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		
		return children.get(childIndex);
	}
	
	public List<Table> getChildren() {
		return children;
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public TreeNode getParent() {
		return null;
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
		return false;
	}

	@Override
	public Enumeration<? extends TreeNode> children() {
		return (Enumeration<? extends TreeNode>) children;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		children.add(index, (Table) child);

	}

	@Override
	public void remove(int index) {
		children.remove(index);

	}

	@Override
	public void remove(MutableTreeNode node) {
		children.add((Table) node);

	}

	@Override
	public void setUserObject(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		// TODO Auto-generated method stub

	}

}
