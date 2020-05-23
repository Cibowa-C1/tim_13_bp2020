package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	
	public final static String url = "jdbc:jtds:sqlserver://147.91.175.155/tim_13_bp2020";
	public final static String username = "tim_13_bp2020";
	public final static String pass = "5m7Apjgp";
	
	
	public static void initialiseConnection(Connection connection) {
		 try {
	             connection = DriverManager.getConnection(url,username,pass);
	            System.out.println("Uspeh");
	      } catch (Exception e) {
	            e.printStackTrace();
	        }

	}
	
}

