package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import view.FilterSortDialog;
import view.MainFrame;
import view.MyTableModel;
import view.TableView;

public class FilterSortAction extends ActionAbstract {

	private FilterSortDialog dialog;
	private TableView tableView;
	private JCheckBox check;
	
	public FilterSortAction() {
		putValue(NAME, "Filter&Sort");
		putValue(SHORT_DESCRIPTION, "Filter&Sort");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component o = MainFrame.getInstance().getdV().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			dialog = new FilterSortDialog(t);
			dialog.setVisible(true);
		}
		
	}
	
}
