package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Delete {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pStatment = null;
		
		try {
			
			conn = DB.getConnection();
			
			pStatment = conn.prepareStatement("DELETE FROM department " +
					"WHERE id = ?");
			
			pStatment.setInt(1, 2);
			
			int rowsAffected = pStatment.executeUpdate();
			
			System.out.println(rowsAffected);
			
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(pStatment);
			DB.closeConnection();
		}
		
	}
	
}
