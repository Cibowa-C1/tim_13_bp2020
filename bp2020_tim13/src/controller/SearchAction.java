package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import view.CountDialog;
import view.MainFrame;
import view.SearchDialog;
import view.TableView;

public class SearchAction extends ActionAbstract {
	
	public SearchAction() {
		putValue(NAME, "Search");
		putValue(SHORT_DESCRIPTION, "Search");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Component o = MainFrame.getInstance().getdV().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			SearchDialog cd = new SearchDialog(t);
		}	
	}


}
