package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	
	public static void main(String[] args) {
		 
		  String url = "jdbc:jtds:sqlserver://147.91.175.155/tim_13_bp2020";
	      String username = "tim_13_bp2020";
	      String pass = "5m7Apjgp";

	      try {
	            Connection connection = DriverManager.getConnection(url,username,pass);
	            System.out.println("Uspeh");
	      } catch (Exception e) {
	            //System.out.println("Karina");
	            e.printStackTrace();
	        }


	    }
}

