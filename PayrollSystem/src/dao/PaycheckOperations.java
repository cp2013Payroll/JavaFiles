package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Paycheck;
import dataCreators.ConnectionSingleton;
import dataCreators.Factory;

public class PaycheckOperations {
	
	public static List<Paycheck> findAllPayChecksForEmp(int empId) throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
			String queryStr = "SELECT * FROM payroll3_database.paycheck WHERE empId =" + empId;
			Statement s = c.createStatement();
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createPaycheckForEmp(results);
		}
	}
	
	public static List<Paycheck> findAllPayChecks() throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
			String queryStr = "SELECT * FROM payroll3_database.paycheck";
			Statement s = c.createStatement();
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createPaycheckForEmp(results);
		}
	}
	
	/**
	* @param date
	* //Pass in a partial date e.g. Oct 29, it compares the payDate attribute in the DB to return those
	//with dates containing that. Here is the DB attributes actual Date string.
	//Wed Oct 29 16:49:23 EST 2014.
	//This method can be used in any combination for the above.
	* @return
	* A list of paychecks
	* @throws SQLException
	*/
	public static List<Paycheck> findAllPayChecksForPayDay(String date) throws SQLException {
		PreparedStatement dbStatement;
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			String queryStr = "SELECT * FROM payroll3_database.paycheck p WHERE p.payDate LIKE ?";
			dbStatement = c.prepareStatement(queryStr);
			dbStatement.setString(1, "%" + date + "%");
			ResultSet results = dbStatement.executeQuery();
			return Factory.createPaycheckForEmp(results);
		}
	}

	public static void addPaycheck(Paycheck paycheck) throws SQLException{
		PreparedStatement dbStatement;
		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
		String insertQuery = "INSERT INTO payroll3_database.paycheck (id, empId, payDate, grossPay, netPay, deductions) VALUES (null, ?, ? ,?, ?, ?)";
		
		dbStatement = c.prepareStatement(insertQuery);
		dbStatement.setInt(1, paycheck.getEmpId());
		dbStatement.setString(2, paycheck.getPayDate());
		dbStatement.setDouble(3, paycheck.getGrossPay());
		dbStatement.setDouble(4, paycheck.getNetPay());
		dbStatement.setDouble(5, paycheck.getDeductions());
		dbStatement.executeUpdate();
		}
	}
}