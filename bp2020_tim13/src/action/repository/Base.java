package action.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.Row;
import model.Table;
import view.MyTableModel;
import view.TableView;

public interface Base {

	
	public void updateQuery(Connection connection,String query) throws SQLException;
	public ResultSet ExcecuteBaseQuery(Connection connection,String query)throws SQLException; 
	public PreparedStatement prepExecute(Connection connection,String query) throws SQLException;
}
