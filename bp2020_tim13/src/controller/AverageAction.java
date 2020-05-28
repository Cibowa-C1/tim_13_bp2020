package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import view.AverageDialog;
import view.MainFrame;
import view.TableView;

public class AverageAction extends ActionAbstract {

	private AverageDialog dialog;
	
	public AverageAction() {
			putValue(NAME, "Average");
			putValue(SHORT_DESCRIPTION, "Average");
	}
	////
	@Override
	public void actionPerformed(ActionEvent e) {
		Component o = MainFrame.getInstance().getdV().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			dialog = new AverageDialog(t);
		}
	}

}
