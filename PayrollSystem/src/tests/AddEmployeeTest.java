package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.CommEmp;
import model.HourlyEmp;
import model.SalaryEmp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.EmployeeOperations;

public class AddEmployeeTest {
	
	static HourlyEmp he;
	static SalaryEmp se;
	static CommEmp ce;
	static int numEmpsToAdd = 3;
	static int originalDbSize = 0;
	
	@BeforeClass
	public static void SetUpDBInfo() throws SQLException {
		originalDbSize = EmployeeOperations.findAllEmployees().size();
	
	}
	
	@Test
	public void addEmps() throws SQLException{
		he = EmployeeOperations.saveHourlyEmployee(new HourlyEmp(null, "John", "InaDb", "50 iminadb Street", 25));
		ce = EmployeeOperations.saveCommEmployee(new CommEmp(null, "Mick", "Black", "1 onaroad Dr", 1.0, 50000));
		se = EmployeeOperations.saveSalaryEmployee(new SalaryEmp(null, "Bob", "White", "2 somewhere crt", 90000));
		
		assertEquals(originalDbSize + numEmpsToAdd, EmployeeOperations.findAllEmployees().size());
	}
	
	@AfterClass
	public static void cleanDb() throws SQLException{
		EmployeeOperations.deleteEmp(he.getEmpId());
		EmployeeOperations.deleteEmp(se.getEmpId());
		EmployeeOperations.deleteEmp(ce.getEmpId());
		
		assertEquals(originalDbSize, EmployeeOperations.findAllEmployees().size());
	}


}
