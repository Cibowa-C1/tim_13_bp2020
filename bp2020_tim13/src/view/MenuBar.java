package view;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar{

	public MenuBar() {
		
		JMenu file = new JMenu("File");
		JMenu analyze = new JMenu("Analyze");
		JMenu window = new JMenu("Window");
		JMenu help = new JMenu("Help");
		window.setForeground(Color.BLACK);
		file.setForeground(Color.BLACK);
		help.setForeground(Color.BLACK);
		analyze.setForeground(Color.BLACK);
	}
}
