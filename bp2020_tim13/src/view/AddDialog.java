package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.AppCore;
import model.Column;
import model.ColumnLimit;
import model.ColumnLimitsEnum;
import model.ColumnType;
import model.Row;
import model.Table;

public class AddDialog extends JDialog {
	
	List<JTextField> cells;
	Table table;
	public AddDialog(TableView t) {
		cells = new ArrayList<JTextField>();
		table = t.getTable();
		MyTableModel mtm = (MyTableModel)t.getModel();
		Vector attributes = mtm.getColumnV();
		Iterator<Object> iter = attributes.iterator();
		this.setPreferredSize(new Dimension(700,700));
		this.setLocationRelativeTo(null);
		int i=0;
		while(iter.hasNext()) {
			JLabel lbl = new JLabel(iter.next().toString());
			JTextField txt = new JTextField();
			this.add(lbl);
			this.add(txt);
			cells.add(txt);
			i++;
		}
		JButton ok = new JButton("OK");
		this.add(ok);
		this.pack();
		this.setLayout(new GridLayout(i+1,2));
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i=0;
				setVisible(false);
				StringBuilder query = new StringBuilder("INSERT INTO " + table.getName()+ " (");
				for (JTextField text : cells) {
					Column c = (Column) table.getChildAt(i);
					if(c==null) {System.out.println("NULLLLLLLLLLL"); return;}
					if(i==0) {
						query.append(c.getName());
					}else {
						query.append(", ");
						query.append(c.getName());
					}
					String sadrzaj = text.getText();
					if(sadrzaj==null) {
						for (ColumnLimit cl : c.getLimits()) {
							if(cl.getType().equals(ColumnLimitsEnum.NOT_NULL)) {
								OptionDialog op =new OptionDialog(); 
								return;
							}
						}
					}
					if(c.getType()==null) return;
					else if(c.getType().equals(ColumnType.CHAR)) if(!isAlpha(sadrzaj)) {OptionDialog op =new OptionDialog(); return;}
					else if(c.getType().equals(ColumnType.VARCHAR)) if(!isAlpha(sadrzaj)) {OptionDialog op =new OptionDialog(); return;}												  
					else if(c.getType().equals(ColumnType.DATE)) if(!isValidDate(sadrzaj)){OptionDialog op =new OptionDialog(); return;}	
					else if(c.getType().equals(ColumnType.TIME)) if(!isValidTime(sadrzaj)){OptionDialog op =new OptionDialog(); return;}	
					else if(c.getType().equals(ColumnType.DATETIME)) if(!isValidDateTime(sadrzaj)){OptionDialog op =new OptionDialog(); return;}	
					else if(c.getType().equals(ColumnType.FLOAT)) if(!isFloat(sadrzaj)){OptionDialog op =new OptionDialog(); return;} 
					else if(c.getType().equals(ColumnType.DECIMAL)) if(!isFloat(sadrzaj)){OptionDialog op =new OptionDialog(); return;}    
					else if(c.getType().equals(ColumnType.INT))  if(!isFloat(sadrzaj)){OptionDialog op =new OptionDialog(); return;}   
					i++;
				}
				query.append(") VALUES (");
				int k=0;
				while(k<i) {
					if(k==0) {
						query.append("?");
					}else {
						query.append(", ?");
					}
					k++;
				}
				query.append(")");
				System.out.println(query.toString());
				Connection connection = AppCore.startConnection();
				Row row = new Row(table.getName());
				try {
					PreparedStatement ps = connection.prepareStatement(query.toString());
					i=0;
					for (JTextField text : cells) {
						Column c = (Column) table.getChildAt(i);
						SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MMM-dd");
						 if(c.getType().equals(ColumnType.DATE))
							try {
								ps.setDate(i+1,(Date) formatter2.parse(text.getText()));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if(c.getType().equals(ColumnType.DATETIME))
							try {
								ps.setDate(i+1,(Date) formatter2.parse(text.getText()));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if(c.getType().equals(ColumnType.FLOAT)) ps.setFloat(i+1, Float.parseFloat(text.getText()));
						else if(c.getType().equals(ColumnType.DECIMAL))ps.setFloat(i+1, Float.parseFloat(text.getText())); 
						else if(c.getType().equals(ColumnType.INT))  ps.setInt(i+1, Integer.parseInt(text.getText()));  
						else ps.setString(i+1, text.getText());
						 row.addField(c.getName(), text.getText());
						 i++;
					}
					table.addRows(row);
					ps.execute();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			AppCore.CloseConnection(connection);
			}
		});
	}
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	public static boolean isInt(String s) {
		try{
	        Integer.parseInt(s);
	    }catch(NumberFormatException e){
	        return false;
	    }
		return true;
	}
	
	public static boolean isFloat(String s) {
		try{
	        Float.parseFloat(s);
	    }catch(NumberFormatException e){
	        return false;
	    }
		return true;
	}
	public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
	public static boolean isValidDateTime(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:ms");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
	public static boolean isValidTime(String s) {
	    try {
	        LocalTime.parse(s);
	    } catch (DateTimeParseException | NullPointerException e) {
	        return false;
	    }
	    return true;
	}

}
