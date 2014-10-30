package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.DateUtil;
import model.HourlyEmp;
import model.TimeSheetEntry;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.EmployeeOperations;
import dao.TimesheetOperations;

public class AddTimeSheetTest {
	static HourlyEmp he;
	static int numEmpsToAdd = 1;
	static int originalNumEmps = 0;
	static int originalNumTimesheets = 0;

	@BeforeClass
	public static void SetUpDB() throws SQLException {
		he = EmployeeOperations.saveHourlyEmployee(new HourlyEmp(null, "John", "InaDb", "50 iminadb Street", 25));
		originalNumEmps = EmployeeOperations.findAllEmployees().size();
		originalNumTimesheets = TimesheetOperations.findAllTimeSheets().size();
		
		
	}
	
	@Test
	public void addTimesheet() throws SQLException{
		TimesheetOperations.saveTimeSheetEntry(new TimeSheetEntry(null, DateUtil.todaysDate().toString(), 5.0, he.getEmpId(), "F"));
		
	}
	
	@AfterClass
	public static void CleanDatabase() throws SQLException{
		EmployeeOperations.deleteEmp(he.getEmpId());
		assertEquals(originalNumEmps - numEmpsToAdd, EmployeeOperations.findAllEmployees().size());
		
	}
}
