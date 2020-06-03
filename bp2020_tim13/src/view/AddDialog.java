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

import action.repository.Repository;
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
		for(Column c:table.getChildren()) {
			JLabel lbl = new JLabel(c.getName());
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
				
				setVisible(false);
				Repository rep = new Repository();
				int i=0;
				StringBuilder query = new StringBuilder("INSERT INTO " + table.getName()+ " (");
				Iterator<JTextField> iterText = cells.iterator();
				Iterator<Column> iterColumns = table.getChildren().iterator();
				while(iterColumns.hasNext() && iterText.hasNext()) {
					Column c = iterColumns.next();
					String sadrzaj = iterText.next().getText();
					if(i==0) {
						query.append(c.getName());
					}else {
						query.append(", ");
						query.append(c.getName());
					}
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
					PreparedStatement ps = rep.prepExecute(connection, query.toString());
					int u=1;
					Iterator<JTextField> iterText2 = cells.iterator();
					Iterator<Column> iterColumns2 = table.getChildren().iterator();
					while(iterText2.hasNext() && iterColumns2.hasNext()) {
						Column c = iterColumns2.next();
						String sadrzaj = iterText2.next().getText();
						System.out.println(sadrzaj);
						SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
						 if(c.getType().equals(ColumnType.DATE))
							try {
								//String strDate = formatter2.format(new Date());
								System.out.println(sadrzaj);
								java.util.Date d = formatter2.parse(sadrzaj);
								Date date = new Date(d.getTime());
								ps.setDate(u,date);
							} catch (ParseException e1) {
								System.out.println("ipak ovde");
								OptionDialog op =new OptionDialog(); return;
							}
						else if(c.getType().equals(ColumnType.DATETIME)) {
							try {
								java.util.Date d = formatter.parse(sadrzaj);
								Date date = new Date(d.getTime());
								ps.setDate(u,date);
							} catch (ParseException e1) {
								System.out.println("OVDE E");
								OptionDialog op =new OptionDialog(); return;
							}
						}
						else if(c.getType().equals(ColumnType.FLOAT)) {ps.setFloat(u, Float.parseFloat(sadrzaj));System.out.println("Uso");}
						else if(c.getType().equals(ColumnType.DECIMAL)) {ps.setFloat(u, Float.parseFloat(sadrzaj));System.out.println("Uso"); }
						else if(c.getType().equals(ColumnType.INT))  ps.setInt(u, Integer.parseInt(sadrzaj));  
						else {System.out.println("Stavljam " + sadrzaj + " na mesto " + u); ps.setString(u, sadrzaj);}
						row.addField(c.getName(), sadrzaj);
						u++;
					}
					ps.execute();
					table.addRows(row);
					int columnCount = table.getRows().get(0).getFields().keySet().size();
					Vector data = new Vector(columnCount);
					for (Object value : row.getFields().values()) {
						data.add(value);
					}
					mtm.addRow(data);
					
				} catch (SQLException e1) {
					OptionDialog op = new OptionDialog("Niste uneli postojeci foreign key ili ste uneli postojaci primary key");
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
	public static boolean isValidDateTime(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
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
