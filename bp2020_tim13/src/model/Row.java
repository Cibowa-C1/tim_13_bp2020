package model;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Row {
	
	private Column parent;
	private Object sadrzaj;
	
	 public Row(Object sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	 public Object getSadrzaj() {
		return sadrzaj;
	}
	 public Column getParent() {
		return parent;
	}
	 public void setParent(Column parent) {
		this.parent = parent;
	}
	 @Override
	public String toString() {
		return sadrzaj.toString();
	}
}
