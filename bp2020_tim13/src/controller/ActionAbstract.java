package controller;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class ActionAbstract extends AbstractAction{

	public Icon loadIcon(String filename) {
		Icon icon = new ImageIcon(filename);
		return icon;
	}
}
