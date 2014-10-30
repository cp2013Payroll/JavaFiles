package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CommEmp;
import model.Employee;
import model.HourlyEmp;
import model.SalaryEmp;
import dataCreators.ConnectionSingleton;
import dataCreators.Factory;

public class EmployeeOperations {
	
	public static List<Employee> findAllEmployees() throws SQLException {
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.employee;";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createEmployees(results);
		}
	}
	
	public static List<HourlyEmp> findHourlyEmployees() throws SQLException {
		try(Connection c = ConnectionSingleton.INSTANCE.create()){
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.employee e, payroll3_database.hourlyclass h WHERE e.empId = h.empId;";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createHourlyEmployees(results);
		}
	}
	
	public static List<CommEmp> findCommissionEmployees() throws SQLException {
		try(Connection c = ConnectionSingleton.INSTANCE.create()){
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.employee e, payroll3_database.commissionclass c WHERE e.empId = c.empId;";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createCommissionEmployees(results);
		}
	}
	
	public static List<SalaryEmp> findSalaryEmployees() throws SQLException {
		try(Connection c = ConnectionSingleton.INSTANCE.create()){
			Statement s = c.createStatement();
			String queryStr = "SELECT * FROM payroll3_database.employee e, payroll3_database.salaryclass s WHERE e.empId = s.empId;";
			ResultSet results = s.executeQuery(queryStr);
			return Factory.createSalaryEmployees(results);
		}
	}
	
	public static List<Employee> findEmployees2() throws SQLException{
		List<Employee> emps = new ArrayList<>();
		emps.addAll(findHourlyEmployees());
		emps.addAll(findCommissionEmployees());
		emps.addAll(findSalaryEmployees());
		return emps;
	}
	
	public static CommEmp saveCommEmployee(CommEmp commEmp) throws SQLException {
		PreparedStatement dbStatement;

		try (Connection c = ConnectionSingleton.INSTANCE.create()) {

			int generatedKey = saveEmployee(c, commEmp.getEmpFname(), commEmp.getEmpLname(), commEmp.getEmpAddress(), commEmp.getEmpType());

			
			String insertCommQuery = "INSERT INTO payroll3_database.commissionclass (commClassId, commClassRate, commClassSalary, empId) VALUES (null , ?, ?, ?)";
			dbStatement = c.prepareStatement(insertCommQuery);
			dbStatement.setDouble(1, commEmp.getCommRate());
			dbStatement.setDouble(2, commEmp.getCommSalary());
			dbStatement.setInt(3, generatedKey);

			dbStatement.executeUpdate();
			commEmp.setEmpId(generatedKey);
			return commEmp;

		}

	}
	
	public static HourlyEmp saveHourlyEmployee(HourlyEmp hourlyEmp) throws SQLException{
		PreparedStatement dbStatement;
		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			int generatedKey = saveEmployee(c, hourlyEmp.getEmpFname(), hourlyEmp.getEmpLname(), hourlyEmp.getEmpAddress(), hourlyEmp.getEmpType());

			String insertHourlyQuery = "INSERT INTO payroll3_database.hourlyclass (hourlyClassId, hourlyClassRate, empId) VALUES (null, ?, ?)";
			dbStatement = c.prepareStatement(insertHourlyQuery);
			dbStatement.setDouble(1, hourlyEmp.getHourlyRate());
			dbStatement.setInt(2, generatedKey);

			dbStatement.executeUpdate();
			hourlyEmp.setEmpId(generatedKey);
			return hourlyEmp;

		}
	}
	
	public static SalaryEmp saveSalaryEmployee(SalaryEmp salaryEmp) throws SQLException{

		PreparedStatement dbStatement;

		
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {

			int generatedKey = saveEmployee(c, salaryEmp.getEmpFname(), salaryEmp.getEmpLname(), salaryEmp.getEmpAddress(), salaryEmp.getEmpType());
			String insertSlaryQuery = "INSERT INTO payroll3_database.salaryclass (salClassId, salClassSalary, empId) VALUES (null, ?, ?)";
			dbStatement = c.prepareStatement(insertSlaryQuery);
			dbStatement.setDouble(1, salaryEmp.getSalary());
			dbStatement.setInt(2, generatedKey);

			dbStatement.executeUpdate();
			salaryEmp.setEmpId(generatedKey);
			return salaryEmp;
		}
	}
	
	private static int saveEmployee(Connection c, String fName, String lName, String address, String empType) throws SQLException{
		PreparedStatement dbStatement;
		int generatedKey = 0;
		String insertQuery = "INSERT INTO payroll3_database.employee (empId, empFname, empLname, empAddress, empType) VALUES (null, ?, ?, ?, ?)";

		dbStatement = c.prepareStatement(insertQuery,
				Statement.RETURN_GENERATED_KEYS);
		dbStatement.setString(1, fName);
		dbStatement.setString(2, lName);
		dbStatement.setString(3, address);
		dbStatement.setString(4, empType);

		dbStatement.executeUpdate();

		ResultSet rs = dbStatement.getGeneratedKeys();
		if (rs.next()) {
			generatedKey = rs.getInt(1);
//			System.out
//					.println("Auto Generated Primary Key " + generatedKey);
		}
		return generatedKey;
	}
	
	public static void deleteEmp(int id) throws SQLException{
		try(Connection c = ConnectionSingleton.INSTANCE.create()){
		
		String deleteQuery = "DELETE FROM payroll3_database.employee WHERE empId= "+ String.valueOf(id);
		
		PreparedStatement dbStatement = c.prepareStatement(deleteQuery);
		dbStatement.executeUpdate(deleteQuery);
		}
	}
	
	public static Employee findEmployeeById(int i) throws SQLException {
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			Statement s = c.createStatement();
			//Dramas need to work out a way to determine what type of emp were searching for.
//			String queryStr = "SELECT * FROM payroll3_database.employee e LEFT JOIN payroll3_database.hourlyclass h on e.empId = h.empId " +
//					"LEFT JOIN payroll3_database.salaryclass s on e.empId = s.empId " +
//					"Left JOIN payroll3_database.commissionclass c on e.empId = c.empId WHERE e.empId = " + i;
			
			String queryStr = "SELECT * FROM payroll3_database.employee e, payroll3_database.hourlyclass h, payroll3_database.salaryclass s, payroll3_database.commissionclass c WHERE e.empId = " + i;
			
			ResultSet results = s.executeQuery(queryStr);
			
			
			if(results.next()){
				return Factory.createEmp(results);
			}
			return null;
			
		}
		
	}
	
	private static void update(Employee emp) throws SQLException{
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
		
		String updateQuery = "UPDATE payroll3_database.employee e SET e.empFname = ?, e.empLname = ?, e.empAddress = ?, e.empType = ?  WHERE e.empId = " + emp.getEmpId();

		PreparedStatement dbStatement = c.prepareStatement(updateQuery);
		
		dbStatement.setString(1, emp.getEmpFname());
		dbStatement.setString(2, emp.getEmpLname());
		dbStatement.setString(3, emp.getEmpAddress());
		dbStatement.setString(4, emp.getEmpType());

		dbStatement.executeUpdate();
		}
	}
	
	public static void updateHourly(HourlyEmp emp) throws SQLException{
		update(emp);
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
			String updateQuery = "UPDATE payroll3_database.hourlyclass h SET h.hourlyClassRate = ? WHERE h.empId = " + emp.getEmpId();

			PreparedStatement dbStatement = c.prepareStatement(updateQuery);
			
			dbStatement.setDouble(1, emp.getHourlyRate());

			dbStatement.executeUpdate();
			}
		
	}
	
	public static void updateSalaryEmp(SalaryEmp emp) throws SQLException{
		update(emp);
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
			String updateQuery = "UPDATE payroll3_database.salaryclass s SET s.salClassSalary = ? WHERE s.empId = " + emp.getEmpId();

			PreparedStatement dbStatement = c.prepareStatement(updateQuery);
			
			dbStatement.setDouble(1, emp.getSalary());

			dbStatement.executeUpdate();
			}
	}
	
	public static void updateCommEmp(CommEmp emp) throws SQLException{
		update(emp);
		try (Connection c = ConnectionSingleton.INSTANCE.create()) {
			
			String updateQuery = "UPDATE payroll3_database.commissionclass c SET c.commClassRate = ?, c.commClassSalary = ? WHERE c.empId = " + emp.getEmpId();

			PreparedStatement dbStatement = c.prepareStatement(updateQuery);
			
			dbStatement.setDouble(1, emp.getCommRate());
			dbStatement.setDouble(2, emp.getCommSalary());

			dbStatement.executeUpdate();
			}
	}
	
	public static void updateEmployee(Employee emp) throws SQLException{
		update(emp);
		switch(emp.getEmpType()){
		case SalaryEmp.SALARY_TYPE: updateSalaryEmp((SalaryEmp)emp);
			break;
		case HourlyEmp.HOURLY_TYPE: updateHourly((HourlyEmp)emp);
			break;
		case CommEmp.COMM_TYPE: updateCommEmp((CommEmp)emp);
			break;
		}
	}

}
