package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import view.FilterDialog;
import view.MainFrame;
import view.MyTableModel;
import view.TableView;

public class FilterAction extends ActionAbstract {

	private FilterDialog dialog;
	private TableView tableView;
	private JCheckBox check;
	
	public FilterAction() {
		putValue(NAME, "Filter");
		putValue(SHORT_DESCRIPTION, "Filter");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component o = MainFrame.getInstance().getDv().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			dialog = new FilterDialog(t);
			dialog.setVisible(true);
		}
		
	}
	
}
