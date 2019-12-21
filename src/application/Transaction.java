package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Transaction {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement statment = null;
		ResultSet resultSet = null;
		
		try {
			
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			statment = conn.createStatement();
			int operation1 = statment.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			System.out.println("operation1: " + operation1);
			
//			if (true) {
//				throw new SQLException("Fake error.");
//			}

			int operation2 = statment.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("operation2: " + operation2);
			
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back. Cause by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying roll back: " + e.getMessage());
			}
		} finally {
			DB.closeResultSet(resultSet);
			DB.closeStatement(statment);
			DB.closeConnection();
		}
		
	}
	
}
