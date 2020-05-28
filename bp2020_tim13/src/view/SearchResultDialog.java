package view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;

import model.Row;
import model.Table;
import view.table.TableRenderer;

public class SearchResultDialog extends JDialog {

	public SearchResultDialog(List<Row> rows) {
		setPreferredSize(new Dimension(1200, 600));
		setTitle("Search table");
		setLocationRelativeTo(null);
		pack();
		

		Table t = new Table("SRCH-TABLE");
		t.setRows(rows);
		TableView tableView = new TableView(t);
		tableView.setFillsViewportHeight(true);
		TableRenderer tr = new TableRenderer();
		tableView.setDefaultRenderer(Object.class, tr);
		add(new JScrollPane(tableView));
		setVisible(true);
	}
}
