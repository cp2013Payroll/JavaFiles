package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.CommEmp;
import model.HourlyEmp;
import model.SalaryEmp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dao.EmployeeOperations;

public class UpdateEmployeeTest {
	
	static HourlyEmp he;
	static SalaryEmp se;
	static CommEmp ce;
	static int numEmpsToAdd = 3;
	
	@BeforeClass
	public static void AddEmps() throws SQLException {
		he = EmployeeOperations.saveHourlyEmployee(new HourlyEmp(null, "John", "InaDb", "50 iminadb Street", 25));
		ce = EmployeeOperations.saveCommEmployee(new CommEmp(null, "Mick", "Black", "1 onaroad Dr", 1.0, 50000));
		se = EmployeeOperations.saveSalaryEmployee(new SalaryEmp(null, "Bob", "White", "2 somewhere crt", 90000));
	}
	
	@Test
	public void UpdateEmp() throws SQLException{
		EmployeeOperations.findEmployeeById(he.getEmpId());
		he.setEmpFname("t");
		EmployeeOperations.updateEmployee(he);
		assertEquals("t", EmployeeOperations.findEmployeeById(he.getEmpId()).getEmpFname());
		
	}
	
	@Test
	public void UpdateHourlyEmp() throws SQLException{
		EmployeeOperations.findEmployeeById(he.getEmpId());
//		System.out.println(EmployeeOperations.findEmployeeById(he.getEmpId()).toString());
		he.setEmpAddress("t");
		he.setHourlyRate(28.0);
		EmployeeOperations.updateHourly(he);
//		System.out.println(EmployeeOperations.findEmployeeById(he.getEmpId()).toString());
		assertEquals("t", ((HourlyEmp) EmployeeOperations.findEmployeeById(he.getEmpId())).getEmpAddress());
		assertEquals(26, ((HourlyEmp) EmployeeOperations.findEmployeeById(he.getEmpId())).getHourlyRate(), 26.0);
	}
	
	@Ignore
	@Test
	public void UpdateCommEmp() throws SQLException{
		EmployeeOperations.findEmployeeById(ce.getEmpId());
//		System.out.println(EmployeeOperations.findEmployeeById(ce.getEmpId()).toString());
		ce.setCommRate(5.0);
		ce.setCommSalary(100000);
		EmployeeOperations.updateCommEmp(ce);
//		System.out.println(EmployeeOperations.findEmployeeById(ce.getEmpId()).toString());
		assertEquals(5.0, ((CommEmp) EmployeeOperations.findEmployeeById(ce.getEmpId())).getCommRate(), 5.0);
		assertEquals(100000, ((CommEmp) EmployeeOperations.findEmployeeById(ce.getEmpId())).getCommSalary(), 100000);
	}
	
	@Ignore
	@Test
	public void UpdateSalaryEmp() throws SQLException{
		EmployeeOperations.findEmployeeById(se.getEmpId());
//		System.out.println(EmployeeOperations.findEmployeeById(se.getEmpId()).toString());
		se.setSalary(10);
		EmployeeOperations.updateSalaryEmp(se);
//		System.out.println(EmployeeOperations.findEmployeeById(se.getEmpId()).toString());
		assertEquals(10.0, ((SalaryEmp) EmployeeOperations.findEmployeeById(se.getEmpId())).getSalary(), 10.0);
	}
	
	@AfterClass
	public static void CleanDatabase() throws SQLException{
		int originalDbSize = EmployeeOperations.findAllEmployees().size() - numEmpsToAdd;
		EmployeeOperations.deleteEmp(he.getEmpId());
		EmployeeOperations.deleteEmp(ce.getEmpId());
		EmployeeOperations.deleteEmp(se.getEmpId());
		
		assertEquals(originalDbSize, EmployeeOperations.findAllEmployees().size());
		
	}


}
