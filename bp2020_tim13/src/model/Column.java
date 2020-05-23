package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Column implements MutableTreeNode {

	private Table parent;
	private List<Row> rows;
	private String name;
	
	
	
	public Column(String name) {
		this.name = name;
		rows = new ArrayList<Row>();
	}
	
	public void addRow(Row r) {
		rows.add(r);
	}
	
	public String getName() {
		return name;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return rows.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return rows.size();
	}
	public List<Row> getRows() {
		return rows;
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration<? extends TreeNode> children() {
		return (Enumeration<? extends TreeNode>) rows;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int index) {
		rows.remove(index);
		
	}

	@Override
	public void remove(MutableTreeNode node) {
		// TODO Auto-generated method stub
		
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
		parent = (Table) newParent;
		
	}

}
