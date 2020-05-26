package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


import model.Table;


public class FilterDialog extends JDialog {
	
	private JLabel lbl;
	private JTextField txtField;
	private JButton apply;
	private List<JCheckBox> checkBoxy;
	private JCheckBox checkychecky;
	private JLabel atrName;
	private String name;
	private  int[][] sizes;
	private TableView table;
	
		public FilterDialog(TableView t) {
			this.table = t;
			sizes = t.getSizeCol();
			checkBoxy = new ArrayList<>();
			setPreferredSize(new Dimension(600, 600));
			setLocationRelativeTo(null);
			lbl = new JLabel("Choose filter settings: ");
			apply = new JButton("Apply changes");
			apply.setPreferredSize(new Dimension(100, 20));
			add(lbl);
			lbl.setPreferredSize(new Dimension(100,20));
			MyTableModel mtm = (MyTableModel)t.getModel();
			Vector attributes = mtm.getColumnV();
			Iterator<Object> iter = attributes.iterator();
			while(iter.hasNext()) {
				checkychecky = new JCheckBox(iter.next().toString());
				checkychecky.setPreferredSize(new Dimension(120,20));
				checkBoxy.add(checkychecky);
				add(checkychecky);
			}
			add(apply);	
			pack();
			this.setLayout(new GridLayout(checkBoxy.size()+2, 1));
			int i=0;
			
			apply.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					int k=0;
					for (JCheckBox ch :checkBoxy) {
						if(!ch.isSelected()) {
							t.getColumnModel().getColumn(k).setMinWidth(0);
							t.getColumnModel().getColumn(k).setMaxWidth(0);
						}
						else {
							t.getColumnModel().getColumn(k).setWidth(sizes[k][1]);
							t.getColumnModel().getColumn(k).setMinWidth(sizes[k][0]);
							t.getColumnModel().getColumn(k).setMaxWidth(sizes[k][1]);
						}
						k++;
					}
					
					setVisible(false);
				}
			});
			
		}
}

