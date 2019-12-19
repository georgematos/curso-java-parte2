package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement statment = null;
		ResultSet resultSet = null;
		
		try {
			
			conn = DB.getConnection();
			statment = conn.createStatement();
			resultSet = statment.executeQuery("select * from department;");
			
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(resultSet);
			DB.closeStatement(statment);
			DB.closeConnection();
		}
		
	}
	
}
