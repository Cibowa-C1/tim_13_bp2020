package app;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingUtilities;

import database.connection.SQLConnection;
import model.Column;
import model.ColumnLimit;
import model.ColumnLimitsEnum;
import model.Database;
import model.Row;
import model.Table;
import view.MainFrame;

public class AppCore{

	
	public final static String url = "jdbc:jtds:sqlserver://147.91.175.155/tim_13_bp2020";
	public final static String username = "tim_13_bp2020";
	public final static String pass = "5m7Apjgp";
	public static Connection connection;
	
	public static void startConnection() {
		 try{
			 connection = DriverManager.getConnection(url,username,pass);
			 System.out.println("Uspeh");
		 }catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		try {
			startConnection();
			DatabaseMetaData dmd = connection.getMetaData();
			Database database = new Database("Database");
			String tableType[] = {"TABLE"};
	        ResultSet tables = dmd.getTables(connection.getCatalog(), null, null, tableType);

			while(tables.next()) {
				 String tableName = tables.getString("TABLE_NAME");
				 Table table = new Table(tableName);
				if(table.getName().startsWith("trace")) continue;
				 
				
				 ResultSet columns = dmd.getColumns(connection.getCatalog(), null, tableName, null);

	                while (columns.next()){

	                    String columnName = columns.getString("COLUMN_NAME");
	                    String columnType = columns.getString("TYPE_NAME");
	                    int size = Integer.parseInt(columns.getString("COLUMN_SIZE"));
	                    Column column = new Column(columnName,columnType,size);
	                    String isNull = columns.getString("IS_NULLABLE");
	                    if(isNull.contains("NO")) {
	                    	ColumnLimit cl = new ColumnLimit(ColumnLimitsEnum.NOT_NULL, column);
                    		column.addLimit(cl);
	                    }
	                    table.addColumn(column);
	                  
	                }
	               String query = "SELECT * FROM " + tableName;
	                PreparedStatement preparedStatement = connection.prepareStatement(query);
	                ResultSet rs = preparedStatement.executeQuery();

	                while (rs.next()){

	                    Row row = new Row(tableName);

	                    ResultSetMetaData resultSetMetaData = rs.getMetaData();
	                    for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
	                        row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
	                    }
	                    table.addRows(row);
	                }
	                database.addTable(table);
			}
			
			for (Table t : database.getChildren()) {
				 ResultSet foreignKeys = dmd.getImportedKeys(connection.getCatalog(), null, t.getName());
				 while(foreignKeys.next()) {
					String Fkeyname = foreignKeys.getString("FKCOLUMN_NAME");
                 	String Pkeyname = foreignKeys.getString("PKCOLUMN_NAME");
                 	for (Column c : t.getChildren()) {
						if(c.getName().equals(Fkeyname)) {
							ColumnLimit cl = new ColumnLimit(ColumnLimitsEnum.FOREIGN_KEY, c);
                    		c.addLimit(cl);
						}
					}
				 }
				 ResultSet primaryKeys = dmd.getPrimaryKeys(connection.getCatalog(), null, t.getName());
				 while(primaryKeys.next()) {
                 	String keyname = primaryKeys.getString("COLUMN_NAME");
                 	for (Column c : t.getChildren()) {
                 		if(c.getName().equals(keyname)) {
                 			ColumnLimit cl = new ColumnLimit(ColumnLimitsEnum.PRIMARY_KEY, c);
                 			c.addLimit(cl);
                 		}
                 	}
                 }
			}
			
			for (Table t : database.getChildren()) {
				 ResultSet foreignKeys = dmd.getImportedKeys(connection.getCatalog(), null, t.getName());
				 while(foreignKeys.next()) {
					 Table forT = database.getChildNode(foreignKeys.getString("FKTABLE_NAME"));
					 if(forT!=null) {
						 Column forCol = forT.getChildNode(foreignKeys.getString("FKCOLUMN_NAME"));
						 if(forCol!=null) {
							 Table pointT = database.getChildNode(foreignKeys.getString("PKTABLE_NAME"));
							 if(pointT!=null) {
								 Column pointCol = pointT.getChildNode(foreignKeys.getString("PKCOLUMN_NAME"));
								 if(pointCol!=null)
									 forCol.setInRelation(pointCol);
							 }
						 }
					 }
				 }
			}
			
			
		CloseConnection();
		MainFrame.getInstance(database).setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	public static void CloseConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
