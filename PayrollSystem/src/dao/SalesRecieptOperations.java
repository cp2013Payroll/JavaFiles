package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.SalesReciept;
import dataCreators.ConnectionSingleton;
import dataCreators.Factory;

public class SalesRecieptOperations {
	
	public static List<SalesReciept> findAllSalesReciepts() throws SQLException {
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.salesrecipt";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createSalesRecipts(results);
		}
	}
	
	public static List<SalesReciept> findSalesRecieptForEmp(int empId) throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
			String queryStr = "SELECT * FROM payroll3_database.salesrecipt WHERE empId =" + empId;
			Statement s = c.createStatement();
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createSalesReciptsForEmp(results);
		}
	}
	
	public static void saveSalesRecipt(SalesReciept salesReciept) throws SQLException{
		
		PreparedStatement dbStatement;
		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
		String insertQuery = "INSERT INTO payroll3_database.salesrecipt (salesReciptId, salesReciptAmount, salesReciptDate, empId, paid) VALUES (null, ?, ?, ?, ?)";
		
		dbStatement = c.prepareStatement(insertQuery);
		dbStatement.setDouble(1, salesReciept.getSalesReciptAmount());
		dbStatement.setString(2, salesReciept.getSalesReciptDate());
		dbStatement.setInt(3, salesReciept.getEmpId());
		dbStatement.setString(4, salesReciept.getPaid());
		
		dbStatement.executeUpdate();
		}
	}
		
	public static void updateSalesReciptsToPAID(int salesReciptId) throws SQLException{
			PreparedStatement dbStatement;
			
			try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
			String updateQuery = "UPDATE payroll3_database.salesrecipt s SET s.paid = 'T' WHERE s.salesReciptId = " + salesReciptId;
			
			dbStatement = c.prepareStatement(updateQuery);
			
			dbStatement.executeUpdate();
			}
		
	}

}
