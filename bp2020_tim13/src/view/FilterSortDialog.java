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
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import model.Table;


public class FilterSortDialog extends JDialog {
	
	private JLabel lbl;
	private TableRowSorter<MyTableModel> sorter;
	private JTextField txtField;
	private JButton filter;
	private JButton sort;
	private List<JCheckBox> checkBoxy;
	private JCheckBox checkychecky;
	private JLabel atrName;
	private String name;
	private  int[][] sizes;
	private TableView table;
	private JRadioButton ascending;
	private JRadioButton descending;
	
		public FilterSortDialog(TableView t) {
			this.table = t;
			sizes = t.getSizeCol();
			checkBoxy = new ArrayList<>();
			setPreferredSize(new Dimension(600, 600));
			setLocationRelativeTo(null);
			lbl = new JLabel("Choose filter settings: ");
			sort = new JButton("Sort");
			sort.setPreferredSize(new Dimension(100, 20));
			filter = new JButton("Filter");
			filter.setPreferredSize(new Dimension(100, 20));
			add(lbl);
			ascending = new JRadioButton("Ascending");
			ascending.setPreferredSize(new Dimension(120,20));
			descending = new JRadioButton("Descending");
			descending.setPreferredSize(new Dimension(120,20));
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
			add(ascending);
			add(descending);
			add(sort);
			add(filter);	
			pack();
			this.setLayout(new GridLayout(checkBoxy.size()+2, 1));
			int i=0;
			sort.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					sorter = new TableRowSorter<MyTableModel>((MyTableModel) table.getModel());
					table.setRowSorter(sorter);
					List<RowSorter.SortKey> sortKeys = new ArrayList<>();
					for (int a = 0; a<checkBoxy.size();a++) {
						if(checkBoxy.get(a).isSelected()) {
							if(ascending.isSelected() || !(ascending.isSelected() && descending.isSelected())) {
								sortKeys.add(new RowSorter.SortKey(a, SortOrder.ASCENDING));
								sorter.setSortKeys(sortKeys);
								sorter.sort();
							}
							else if(descending.isSelected()) {
								sortKeys.add(new RowSorter.SortKey(a, SortOrder.DESCENDING));
								sorter.setSortKeys(sortKeys);
								sorter.sort();
							}
						}
					}
					setVisible(false);
				}
			});
			filter.addActionListener(new ActionListener() {
				
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

