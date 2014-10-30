package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.CommEmp;
import model.DateUtil;
import model.DirectDepositMethod;
import model.HoldMethod;
import model.HourlyEmp;
import model.MailMethod;
import model.Paycheck;
import model.SalaryEmp;
import model.SalesReciept;
import model.TimeSheetEntry;
import model.UnionCharge;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.EmployeeOperations;
import dao.PaycheckOperations;
import dao.PaymentOperations;
import dao.SalesRecieptOperations;
import dao.TimesheetOperations;
import dao.UnionOperations;
import dataCreators.CalculatePayroll;

public class RunPayrollTest {
	static HourlyEmp he;
	static SalaryEmp se;
	static CommEmp ce;
	static int numEmpsToAdd = 3;

	@BeforeClass
	public static void SetUpDB() throws SQLException {
		he = EmployeeOperations.saveHourlyEmployee(new HourlyEmp(null, "John", "InaDb", "50 iminadb Street", 25));
		ce = EmployeeOperations.saveCommEmployee(new CommEmp(null, "Mick", "Black", "1 onaroad Dr", 1.0, 50000));
		se = EmployeeOperations.saveSalaryEmployee(new SalaryEmp(null, "Bob", "White", "2 somewhere crt", 90000));
		
		SalesRecieptOperations.saveSalesRecipt(new SalesReciept(null, 100, DateUtil.todaysDate().toString(), ce.getEmpId(), "F"));
		TimesheetOperations.saveTimeSheetEntry(new TimeSheetEntry(null, DateUtil.todaysDate().toString(), 5.0, he.getEmpId(), "F"));
		
		PaymentOperations.addDirectMethod(new DirectDepositMethod(null, "Test Bank name", "111-111", "00000000", he.getEmpId()));
		PaymentOperations.addHoldMethod(new HoldMethod(null, ce.getEmpId()));
		PaymentOperations.addMailMethod(new MailMethod(null, "Test Address", se.getEmpId()));
		
		UnionOperations.saveUnionCharge(new UnionCharge(null, "Test1", 2.5, ce.getEmpId()));
		UnionOperations.saveUnionCharge(new UnionCharge(null, "Test2", 2.5, he.getEmpId()));
		UnionOperations.saveUnionCharge(new UnionCharge(null, "Test3", 2.5, se.getEmpId()));
	}
	
	
	@Test	
	public void FindEmpByEmpId() throws SQLException{
//		System.out.println( EmployeeOperations.findEmployeeById(he.getEmpId()).toString());
		assertEquals(he.getEmpId(), EmployeeOperations.findEmployeeById(he.getEmpId()).getEmpId());
	}
	@Test
	public void RunPayroll() throws SQLException{
		
		CalculatePayroll.CalcPay();
		
		assertEquals(1, PaycheckOperations.findAllPayChecksForEmp(he.getEmpId()).size());
		assertEquals(1, PaycheckOperations.findAllPayChecksForEmp(ce.getEmpId()).size());
		assertEquals(1, PaycheckOperations.findAllPayChecksForEmp(se.getEmpId()).size());
		
		for(Paycheck paycheck : PaycheckOperations.findAllPayChecksForEmp(he.getEmpId())){
			assertEquals(122.5, paycheck.getNetPay(), 122.5);
		}
		for(Paycheck paycheck : PaycheckOperations.findAllPayChecksForEmp(se.getEmpId())){
			assertEquals(244.0, paycheck.getNetPay(), 244.1);
		}
		for(Paycheck paycheck : PaycheckOperations.findAllPayChecksForEmp(ce.getEmpId())){
			assertEquals(135.4, paycheck.getNetPay(), 135.5);
		}
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
