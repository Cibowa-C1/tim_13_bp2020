package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import view.MainFrame;
import view.MyTableModel;
import view.TableView;

public class DeleteAction extends ActionAbstract {

	public DeleteAction() {
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Delete");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Component o = MainFrame.getInstance().getDv().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			MyTableModel mdl = (MyTableModel) t.getModel();
			int index = t.getSelectedRow();
			mdl.removeRow(index);
		}

	}

}
