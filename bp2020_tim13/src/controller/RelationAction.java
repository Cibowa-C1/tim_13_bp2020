package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;


import view.MainFrame;
import view.TableView;

public class RelationAction extends ActionAbstract{

	public RelationAction() {
		putValue(NAME, "Relation");
		putValue(SHORT_DESCRIPTION, "Relation");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Component o = MainFrame.getInstance().getdV().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			int selRow = t.getSelectedRow();
		
	}
		
	}

	
}
