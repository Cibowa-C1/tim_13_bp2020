package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Column implements MutableTreeNode {

	private Table parent;
	private String name;
	private ColumnType type;
	private List<ColumnLimit> limits;
	
	
	public Column(String name,String type) {
		this.name = name;
		if(type.equals(ColumnType.CHAR)) this.type = ColumnType.CHAR;
		else if(type.equals(ColumnType.VARCHAR)) this.type = ColumnType.VARCHAR;													
		else if(type.equals(ColumnType.TEXT)) this.type = ColumnType.TEXT; 
		else if(type.equals(ColumnType.DATE)) this.type = ColumnType.DATE;  
		else if(type.equals(ColumnType.TIME)) this.type = ColumnType.TIME; 
		else if(type.equals(ColumnType.DATETIME)) this.type = ColumnType.DATETIME; 
		else if(type.equals(ColumnType.FLOAT)) this.type = ColumnType.FLOAT; 
		else if(type.equals(ColumnType.REAL)) this.type = ColumnType.REAL;   
		else if(type.equals(ColumnType.BIT)) this.type = ColumnType.BIT; 
		else if(type.equals(ColumnType.BIGINT)) this.type = ColumnType.BIGINT; 
		else if(type.equals(ColumnType.NUMERIC)) this.type = ColumnType.NUMERIC; 
		else if(type.equals(ColumnType.DECIMAL)) this.type = ColumnType.DECIMAL;    
		else if(type.equals(ColumnType.INT)) this.type = ColumnType.INT; 
		else if(type.equals(ColumnType.IMAGE)) this.type = ColumnType.IMAGE; 
		else if(type.equals(ColumnType.SMALLINT)) this.type = ColumnType.SMALLINT; 
		else if(type.equals(ColumnType.NVARCHAR)) this.type = ColumnType.NVARCHAR; 
		limits = new ArrayList<ColumnLimit>();
	}
	
	public List<ColumnLimit> getLimits() {
		return limits;
	}
	public ColumnType getType() {
		return type;
	}
	public void addLimit(ColumnLimit c) {
		limits.add(c);
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
		return limits.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return limits.size();
	}
	
	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public int getIndex(TreeNode node) {
		return limits.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public Enumeration<? extends TreeNode> children() {
		return (Enumeration<? extends TreeNode>) limits;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		limits.add(index, (ColumnLimit) child);
		
	}

	@Override
	public void remove(int index) {
		limits.remove(index);
	}

	@Override
	public void remove(MutableTreeNode node) {
		limits.remove(node);
	}

	@Override
	public void setUserObject(Object object) {
		
		
	}

	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub
		parent.remove(this);
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		parent = (Table) newParent;
		
	}

}
