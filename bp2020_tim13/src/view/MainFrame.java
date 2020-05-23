 package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ActionManager;
import model.Database;
import model.DatabaseTreeModel;
import model.Table;
import view.treeDatabase.DatabaseTree;


public class MainFrame extends JFrame{

	private JPanel upperView;
	private JPanel lowerView;
	private TableView tv;
	private DatabaseView dv;
	private DatabaseTree dt;
	private DatabaseTreeModel dtm;
	private ActionManager am;
	
	private static MainFrame instance = null;
	
	private MainFrame() {}
		
	private void initializeTree(Database d) {
		dt = new DatabaseTree();
		dtm = new DatabaseTreeModel(d);
		dt.setModel(dtm);
		dt.addMouseListener(new MouseAdapter());
		
	}

	private void initializeGUI() {
		
		
	}
	
	private void initialize(Database d) {
		am = new ActionManager();
		initializeTree(d);
		initializeGUI();
	}
	
	public JPanel getUpperView() {
		return upperView;
	}

	public void setUpperView(JPanel upperView) {
		this.upperView = upperView;
	}

	public JPanel getLowerView() {
		return lowerView;
	}

	public void setLowerView(JPanel lowerView) {
		this.lowerView = lowerView;
	}

	public ActionManager getAm() {
		return am;
	}
	
	class MouseAdapter extends java.awt.event.MouseAdapter{
		public void mouseClicked(MouseEvent ms) {
			if(ms.getClickCount()==2) {
				Object o = dt.getLastSelectedPathComponent();
				if(o instanceof Table) {
					
				}
			}
		}
	}
	
	public static MainFrame getInstance() {
		if(instance==null)
			instance = new MainFrame();
		return instance;
	}
	
}
