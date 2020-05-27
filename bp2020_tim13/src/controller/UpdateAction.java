package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.swing.JScrollPane;

import app.AppCore;
import model.Row;
import model.Table;
import view.MainFrame;
import view.MyTableModel;
import view.TableView;

public class UpdateAction extends ActionAbstract{

	public UpdateAction() {
		putValue(NAME, "Update");
		putValue(SHORT_DESCRIPTION, "Update");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Component o = MainFrame.getInstance().getdV().getJtp().getSelectedComponent();
		if(o instanceof JScrollPane) {
			JScrollPane scrl =(JScrollPane)o;
			TableView t = (TableView) scrl.getViewport().getComponents()[0];
			MyTableModel mdl = (MyTableModel) t.getModel();
			
			Table table = t.getTable();
			Row r = table.getRows().get(t.getSelectedRow());
			
			
			Connection connection = AppCore.startConnection();
			try {
				DatabaseMetaData dmd = connection.getMetaData();
			}catch (Exception es) {
				// TODO: handle exception
			}
		}
	}

}
