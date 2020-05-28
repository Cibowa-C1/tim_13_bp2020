package controller;

import java.awt.event.ActionEvent;

import view.TableView;

public class CountAction extends ActionAbstract {

	
	private int getColumnByName(TableView table, String name) {
	    for (int i = 0; i < table.getColumnCount(); ++i)
	        if (table.getColumnName(i).equals(name))
	            return i;
	    return -1;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
