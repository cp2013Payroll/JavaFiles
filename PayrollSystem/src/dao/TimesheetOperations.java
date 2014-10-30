package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.TimeSheetEntry;
import dataCreators.ConnectionSingleton;
import dataCreators.Factory;

public class TimesheetOperations {
	
	public static List<TimeSheetEntry> findAllTimeSheets() throws SQLException {
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.timesheet";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createTimeSheets(results);
		}
	}
	
	public static List<TimeSheetEntry> findTimeSheetsForEmp(int empId) throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
			String queryStr = "SELECT * FROM payroll3_database.timesheet WHERE empId =" + empId;
			Statement s = c.createStatement();
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createTimeSheetsForEmp(results);
		}
	}
	
	public static void saveTimeSheetEntry(TimeSheetEntry entry) throws SQLException{
		PreparedStatement dbStatement;
		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
		
		String insertQuery = "INSERT INTO payroll3_database.timesheet (timeSheetId, timeSheetDate, timeSheetHours, empId, paid) VALUES (null, ?, ?, ?, ?)";
		
		dbStatement = c.prepareStatement(insertQuery);
		dbStatement.setString(1, entry.getEntryDate());
		dbStatement.setDouble(2, entry.getEntryHours());
		dbStatement.setInt(3, entry.getEmpId());
		dbStatement.setString(4, entry.getPaid());
		
		dbStatement.executeUpdate();
		}
		
		
	}
	
	public static void updateTimeSheetEntrysToPAID(int timeSheetId) throws SQLException{
		PreparedStatement dbStatement;
		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
		
		String updateQuery = "UPDATE payroll3_database.timesheet t SET t.paid = 'T' WHERE t.timeSheetId = " + timeSheetId;
		
		dbStatement = c.prepareStatement(updateQuery);
		
		dbStatement.executeUpdate();
		}
	}

}
