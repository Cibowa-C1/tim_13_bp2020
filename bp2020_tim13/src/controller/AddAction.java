package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import view.AddDialog;
import view.FilterDialog;
import view.MainFrame;
import view.TableView;

public class AddAction extends ActionAbstract {

	
	
	
	public AddAction() {
		putValue(NAME, "Add");
		putValue(SHORT_DESCRIPTION, "Add");

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Component o = MainFrame.getInstance().getDv().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			AddDialog dialog = new AddDialog(t);
			dialog.setVisible(true);
			
		
	}

}
}
