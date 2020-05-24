package view.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {

	
	public Component getTableCellRendererComponent(JTable table, java.lang.Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	      if( !isSelected ) {
	         Color c = table.getBackground();
	         if( (row%2)==0 && c.getRed()>20 && c.getGreen()>20 && c.getBlue()>20 )
	            setBackground(new Color(c.getRed()-20, c.getGreen()-20, c.getBlue()-20));
	         else
	            setBackground(c);
	      }
	      return super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
	   }
}
