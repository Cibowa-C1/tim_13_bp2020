package model;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Row {
	
	private Column parent;
	private String sadrzaj;
	
	 public Row(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	 public String getSadrzaj() {
		return sadrzaj;
	}
	 public Column getParent() {
		return parent;
	}
	 public void setParent(Column parent) {
		this.parent = parent;
	}
}
