package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import view.CountDialog;
import view.MainFrame;
import view.TableView;

public class SearchAction extends ActionAbstract {
	
	public SearchAction() {
		putValue(NAME, "Add");
		putValue(SHORT_DESCRIPTION, "Add");
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
