package model;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class ColumnLimit implements MutableTreeNode {
	
	private ColumnLimitsEnum type;
	
	Column col;

	public ColumnLimit(ColumnLimitsEnum type,Column col) {
		this.type = type;
		this.col = col;
	}
	@Override
	public String toString() {
	return type.toString();
	}	
	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return col;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub

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
		col.remove(this);
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		col = (Column) newParent;
	}

}
