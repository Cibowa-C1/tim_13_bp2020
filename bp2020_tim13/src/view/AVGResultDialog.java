package view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Row;
import model.Table;
import view.table.TableRenderer;

public class AVGResultDialog extends JDialog {
	
	List<Row> r;

	public AVGResultDialog(List<Row> rows, String name) {
		this.r = rows;
		setPreferredSize(new Dimension(600, 600));
		setTitle("Average table");
		setLocationRelativeTo(null);
		pack();
		//JTable table = new JTable(new DefaultTableModel());
		
		System.out.println(rows.size());
		System.out.println(r.size());
		Table t = new Table("AVG-TABLE");
		t.setRows(rows);
		TableView tableView = new TableView(t);
		tableView.getColumnModel().getColumn(0).setHeaderValue("Average("+name+")");
		tableView.setFillsViewportHeight(true);
		TableRenderer tr = new TableRenderer();
		tableView.setDefaultRenderer(Object.class, tr);
		tableView.setPreferredSize(new Dimension(600, 2000));
		JScrollPane scr = new JScrollPane(tableView);
		scr.setPreferredSize(new Dimension(600, 2000));
		add(scr);
		setVisible(true);
	}

}
