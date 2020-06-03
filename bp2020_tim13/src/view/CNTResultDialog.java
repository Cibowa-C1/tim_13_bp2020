package view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;

import model.Row;
import model.Table;
import view.table.TableRenderer;

public class CNTResultDialog extends JDialog {

	public CNTResultDialog(List<Row> rows, String name) {
		setPreferredSize(new Dimension(600, 600));
		setTitle("Count table");
		setLocationRelativeTo(null);
		pack();
		//JTable table = new JTable(new DefaultTableModel());
		

		Table t = new Table("CNT-TABLE");
		t.setRows(rows);
		TableView tableView = new TableView(t);
		tableView.getColumnModel().getColumn(0).setHeaderValue("Count("+name+")");
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
