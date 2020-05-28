package view;

import javax.swing.JToolBar;

public class Toolbar extends JToolBar{
	
	public Toolbar() {
		
		add(MainFrame.getInstance().getAm().getAdd());
		add(MainFrame.getInstance().getAm().getDelete());
		add(MainFrame.getInstance().getAm().getUpdate());
		add(MainFrame.getInstance().getAm().getFilter());
		add(MainFrame.getInstance().getAm().getRelation());
		add(MainFrame.getInstance().getAm().getCount());
		add(MainFrame.getInstance().getAm().getAverage());

	}

}
