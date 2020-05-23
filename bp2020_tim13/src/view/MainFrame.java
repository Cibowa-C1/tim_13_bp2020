 package view;

import javax.swing.JFrame;

import controller.ActionManager;


public class MainFrame extends JFrame{

	private ActionManager am;
	private static MainFrame instance = null;
	
	private MainFrame() {
		am = new ActionManager();
		
	}
	
	public static MainFrame getInstance() {
		if(instance==null)
			instance = new MainFrame();
		return instance;
	}

	public ActionManager getAm() {
		return am;
	}
}
