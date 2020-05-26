 package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import controller.ActionManager;
import controller.AddAction;
import controller.DeleteAction;
import controller.UpdateAction;
import model.Database;
import model.DatabaseTreeModel;
import model.Table;
import view.treeDatabase.DatabaseTree;


public class MainFrame extends JFrame{

	private JTable jtable;
	private MyTableModel dbm;
	private JSplitPane split;
	private DatabaseView lowerTable;
	private TableView tv;
	private DatabaseView dV;
	private DatabaseTree dt;
	private DatabaseTreeModel dtm;
	private ActionManager am;
	private Toolkit toolkit;
	private Dimension dim;
	private JScrollPane left;
	private JButton add;  
	private JButton delete;
	private JButton refresh;
	private Toolbar toolbar;
	private JSplitPane right;
	
	
	private static MainFrame instance = null;
	
	private MainFrame() {}
	
	

	private void initializeTree(Database d) {
		dt = new DatabaseTree();
		dtm = new DatabaseTreeModel(d);
		dt.setModel(dtm);
		dt.addMouseListener(new MouseAdapter());
		
	}

	private void initializeGUI(Database d) {
		toolbar = new Toolbar();
		toolkit = Toolkit.getDefaultToolkit();
		dim = toolkit.getScreenSize();
		
		
		Dimension dims = new Dimension(1500,400);
		//upperView.setPreferredSize(dims);
		dV = new DatabaseView(d);
		lowerTable = new DatabaseView(d);
		right = new JSplitPane(SwingConstants.HORIZONTAL,dV,lowerTable);
		right.setEnabled(false);
		right.setResizeWeight(0.5); 
		dV.setPreferredSize(dims);
		lowerTable.setPreferredSize(dims);
		left = new JScrollPane(dt);
		pack();
		split = new JSplitPane(SwingConstants.VERTICAL,left,right);
		split.setResizeWeight(0.2);
		this.add(split);
		this.pack();
		this.setSize(1300, 830);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Database-Viewer");
		this.add(toolbar, BorderLayout.NORTH);
	}
	
	private void initialize(Database d) {
		am = new ActionManager();
		initializeTree(d);
		initializeGUI(d);
	}
	



	public ActionManager getAm() {
		return am;
	}

	class MouseAdapter extends java.awt.event.MouseAdapter{
		public void mouseClicked(MouseEvent ms) {
			if(ms.getClickCount()==2) {
				Object o = dt.getLastSelectedPathComponent();
				if(o instanceof Table) {
					Table t = (Table) o;
					dV.addTab(t);
				}
			}
		}
	}
	
	public JPanel getLowerTable() {
		return lowerTable;
	}



	public void setLowerTable(DatabaseView lowerTable) {
		this.lowerTable = lowerTable;
	}


	public DatabaseView getdV() {
		return dV;
	}



	public void setdV(DatabaseView dV) {
		this.dV = dV;
	}



	public static MainFrame getInstance(Database a) {
		if(instance==null) {
			instance = new MainFrame();
			instance.initialize(a);
		}
		return instance;
	}
	public static MainFrame getInstance() {
		if(instance==null) 
			instance = new MainFrame();
		return instance;
	}
	
}
