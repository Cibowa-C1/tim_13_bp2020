package action.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import app.AppCore;
import model.Column;
import model.ColumnLimit;
import model.ColumnLimitsEnum;
import model.ColumnType;
import model.Row;
import model.Table;
import view.AVGResultDialog;
import view.CNTResultDialog;
import view.MyTableModel;
import view.OptionDialog;
import view.TableView;

public class Repository implements Base {
	
	public Repository() {
	}
	
	


	@Override
	public void updateQuery(Connection connection,String sb) throws SQLException {
		PreparedStatement ps;
			ps = connection.prepareStatement(sb);
			ps.executeUpdate();
		
	}




	@Override
	public ResultSet ExcecuteBaseQuery(Connection connection, String query) throws SQLException{
		PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
		return rs;
	}




	@Override
	public PreparedStatement prepExecute(Connection connection, String query) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(query.toString());
		return ps;
	}


}
