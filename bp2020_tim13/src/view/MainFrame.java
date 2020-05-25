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
	private JPanel upperView;
	private JPanel lowerView;
	private TableView tv;
	private DatabaseView dv;
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
		dv = new DatabaseView(d);
		
		Dimension dims = new Dimension(830,740);
		//upperView.setPreferredSize(dims);
		dv.setPreferredSize(dims);
		left = new JScrollPane(dt);
		pack();
		split = new JSplitPane(SwingConstants.VERTICAL,left,dv);
		this.add(split);
		this.pack();
		this.setSize(1300, 800);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Database-Viewer");
		this.add(toolbar, BorderLayout.NORTH);
	}
	
	private void initialize(Database d) {
		am = new ActionManager();
		initializeTree(d);
		initializeGUI(d);
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

	public DatabaseView getDv() {
		return dv;
	}
	class MouseAdapter extends java.awt.event.MouseAdapter{
		public void mouseClicked(MouseEvent ms) {
			if(ms.getClickCount()==2) {
				Object o = dt.getLastSelectedPathComponent();
				if(o instanceof Table) {
					Table t = (Table) o;
					dv.addTab(t);
				}
			}
		}
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
