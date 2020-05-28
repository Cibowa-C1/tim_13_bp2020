package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import model.Table;
import view.CountDialog;
import view.MainFrame;
import view.TableView;

public class CountAction extends ActionAbstract {
	
	public CountAction() {
		putValue(NAME, "COUNT");
		putValue(SHORT_DESCRIPTION, "COUNT");
	}

	
	private int getColumnByName(TableView table, String name) {
	    for (int i = 0; i < table.getColumnCount(); ++i)
	        if (table.getColumnName(i).equals(name))
	            return i;
	    return -1;
	}
	private String getColumnByInd(TableView table, int ind) {
	        
	    return table.getColumnName(ind);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Component o = MainFrame.getInstance().getdV().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			CountDialog cd = new CountDialog(t);
		}	
	}

}
