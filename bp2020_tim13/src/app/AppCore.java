package app;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import database.connection.SQLConnection;
import model.Database;

public class AppCore{

	private static Connection connection;

	public static void main(String[] args) {
		
		connection = null;
		SQLConnection.initialiseConnection(connection);
		try {
			DatabaseMetaData dmd = connection.getMetaData();
			Database database = new Database("Database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				//
			}
		});

	}


}
