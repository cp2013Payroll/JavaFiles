package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.DirectDepositMethod;
import model.HoldMethod;
import model.MailMethod;
import dataCreators.ConnectionSingleton;
import dataCreators.Factory;

public class PaymentOperations {
	
	public static List<MailMethod> findAllMailMethods() throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.mailmethod;";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createMailMethods(results);
		}
	}
	
	public static List<HoldMethod> findAllHoldMethods() throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.holdmethod;";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createHoldMethods(results);
		}
	}
	
	public static List<DirectDepositMethod> findAllDirectMethods() throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.directmethod;";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createDirectMethods(results);
		}
	}
	
public static void addHoldMethod(HoldMethod holdMethod) throws SQLException{
		
		PreparedStatement dbStatement;
		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
		String insertQuery = "INSERT INTO payroll3_database.holdmethod (holdPayId, empId) VALUES (null, ?)";
		
		dbStatement = c.prepareStatement(insertQuery);
		dbStatement.setInt(1, holdMethod.getEmpId());
		
		dbStatement.executeUpdate();
		}
	}
	
	public static void addMailMethod(MailMethod mailMethod) throws SQLException{
		
		PreparedStatement dbStatement;
		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
		String insertQuery = "INSERT INTO payroll3_database.mailmethod (mailPayId, mailPayAddress, empId) VALUES (null, ? ,?)";
		
		dbStatement = c.prepareStatement(insertQuery);
		dbStatement.setString(1, mailMethod.getAddress());
		dbStatement.setInt(2, mailMethod.getEmpId());
		
		dbStatement.executeUpdate();
		}
	}
	
	public static void addDirectMethod(DirectDepositMethod directDepositMethod) throws SQLException{
		
		PreparedStatement dbStatement;
		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
		String insertQuery = "INSERT INTO payroll3_database.directmethod (directPayId, directPayBankName, directPayBSB, directPayAcctNum, empId) VALUES (null, ?, ?, ? ,?)";
		
		dbStatement = c.prepareStatement(insertQuery);
		dbStatement.setString(1, directDepositMethod.getDirectPayBankName());
		dbStatement.setString(2, directDepositMethod.getDirectPayBSB());
		dbStatement.setString(3, directDepositMethod.getDirectPayAcctNum());
		dbStatement.setInt(4, directDepositMethod.getEmpId());
		
		dbStatement.executeUpdate();
		}
	}

}
