package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.UnionCharge;
import dataCreators.ConnectionSingleton;
import dataCreators.Factory;

public class UnionOperations {
	
	public static List<UnionCharge> findUnionChargeForEmp(int empId) throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
			String queryStr = "SELECT * FROM payroll3_database.affiliation WHERE empId =" + String.valueOf(empId);
			Statement s = c.createStatement();
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createUnionChargeForEmp(results);
		}
	}
	
	public static List<UnionCharge> findAllUnionCharges() throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.affiliation;";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createUnionCharges(results);
		}
	}
	
public static void saveUnionCharge(UnionCharge unionCharge) throws SQLException{
		
		PreparedStatement dbStatement;
		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
		String insertQuery = "INSERT INTO payroll3_database.affiliation (affId, affUnionName, affUnionDues, empId) VALUES (null, ?, ?, ?)";
		
		dbStatement = c.prepareStatement(insertQuery);
		dbStatement.setString(1, unionCharge.getAffUnionName());
		dbStatement.setDouble(2, unionCharge.getAffUnionDues());
		dbStatement.setInt(3, unionCharge.getEmpId());
		
		dbStatement.executeUpdate();
		}
		
	}

}
