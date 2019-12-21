package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Update {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pStatment = null;
		
		try {
			
			conn = DB.getConnection();
			
			pStatment = conn.prepareStatement("UPDATE seller " +
					"SET BaseSalary = BaseSalary + ? " +
					"WHERE departmentId = ?;");
			
			pStatment.setDouble(1, 200);
			pStatment.setInt(2, 2);
			
			int rowsAffected = pStatment.executeUpdate();
			
			System.out.println(rowsAffected);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pStatment);
			DB.closeConnection();
		}
		
	}
	
}
