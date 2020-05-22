package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	
	public final static String url = "jdbc:jtds:sqlserver://147.91.175.155/tim_13_bp2020";
	public final static String username = "tim_13_bp2020";
	public final static String pass = "5m7Apjgp";
	private static Connection connection;
	
	
	public static void initialiseConnection() {
		 try {
	             connection = DriverManager.getConnection(url,username,pass);
	            System.out.println("Uspeh");
	      } catch (Exception e) {
	            e.printStackTrace();
	        }

	}
	public static Connection getConnection() {
		return connection;
	}
}

