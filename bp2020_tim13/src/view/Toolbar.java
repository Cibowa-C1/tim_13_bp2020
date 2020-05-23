package view;

import javax.swing.JToolBar;

public class Toolbar extends JToolBar{
	
	public Toolbar() {
		add(MainFrame.getInstance().getAm().getRefresh());
		add(MainFrame.getInstance().getAm().getDelete());
		add(MainFrame.getInstance().getAm().getAdd());
	}

}
