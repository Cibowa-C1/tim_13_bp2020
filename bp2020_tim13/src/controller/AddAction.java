package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import view.MainFrame;

public class AddAction extends ActionAbstract {

	
	
	
	public AddAction() {
		putValue(NAME, "Add");
		putValue(SHORT_DESCRIPTION, "Add");

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Component o = MainFrame.getInstance().getDv().getJtp().getSelectedComponent();
		
		
	}

}
