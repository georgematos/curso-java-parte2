package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Insert {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pStatment = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			conn = DB.getConnection();
			
			pStatment = conn.prepareStatement("INSERT INTO seller " +
					"(Name, Email, Birthdate, BaseSalary, DepartmentId) " +
					"VALUES " +
					"(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			pStatment.setString(1, "Beltrano da Silva");
			pStatment.setString(2, "beltranosilva@gmail.com");
			pStatment.setDate(3, new java.sql.Date(sdf.parse("13/04/1987").getTime()));
			pStatment.setFloat(4, 5400);
			pStatment.setInt(5, 4);
			
			int rowsAffected = pStatment.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = pStatment.getGeneratedKeys();
				while (rs.next()) {
					System.out.println("Done! Id: " + rs.getInt(1));
				}
			} else {
				System.out.println("No rows affected.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pStatment);
			DB.closeConnection();
		}
		
	}
	
}
