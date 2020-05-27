package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import observer.IListener;
import observer.IObserver;
import observer.state.ObserverStates;

public class Table implements MutableTreeNode, IObserver {
	
	private Database parent;
	private List<Column> children;
	private String name;
	private List<Row> rows;
	private List<IListener> listeners;
	
	
	
	public Table(String name) {
		this.name = name;
		children = new ArrayList<Column>();
		rows = new ArrayList<Row>();
		listeners = new ArrayList<IListener>();
	}
	
	public void addColumn(Column c) {
		children.add(c);
		c.setParent(this);
	}
	
	public List<Row> getRows() {
		return rows;
	}
	
	public Column getChildNode(String s) {
		for (Column column : children) {
			if(column.getName().equals(s)) return column;
		}
		return null;
	}
	
	public void addRows(Row r) {
		rows.add(r);
		notifyObserver(ObserverStates.ADD, r);
	}
	public void removeRows(Row r) {
		rows.remove(r);
		notifyObserver(ObserverStates.REMOVE, r);
	}
	public void setName(String name) {
		this.name = name;
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
	public Database getDatabase() {
		return parent;
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

	@Override
	public void addObserver(IListener listener) {
		if(listener==null) return;
		if(this.listeners==null) 
			this.listeners = new ArrayList<>();
		if(this.listeners.contains(listener)) return;
		this.listeners.add(listener);
	}

	@Override
	public void removeObserver(IListener listener) {
		if(listener==null) return;
		if(this.listeners==null) return;
		if(!this.listeners.contains(listener)) return;
		this.listeners.remove(listener);
	}

	@Override
	public void notifyObserver(Object event, Object obj) {
		if(event==null) return;
		if(this.listeners==null) return;
		if(this.listeners.isEmpty()) return;
		for(IListener l : listeners) {
			l.update(event,obj);
		}
	}

}
