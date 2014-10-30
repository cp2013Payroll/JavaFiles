package dataCreators;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CommEmp;
import model.DirectDepositMethod;
import model.Employee;
import model.HoldMethod;
import model.HourlyEmp;
import model.MailMethod;
import model.Paycheck;
import model.SalaryEmp;
import model.SalesReciept;
import model.TimeSheetEntry;
import model.UnionCharge;

public class Factory {
	

	
	public static Employee createEmployee(ResultSet result) throws SQLException{
		Employee employee = new Employee(result.getInt("empId"), result.getString("empFname"), result.getString("empLname"), result.getString("empAddress"), result.getString("empType"));
		return employee;
	}
	
	public static Employee createEmp(ResultSet result) throws SQLException{
		String type = result.getString("empType");
		switch(type){
		case SalaryEmp.SALARY_TYPE: return createSalaryEmployee(result);
		case HourlyEmp.HOURLY_TYPE : return createHourlyEmployee(result);
		case CommEmp.COMM_TYPE : return createCommissionEmployee(result);
		}
		return null;
	}

	public static List<Employee> createEmployees(ResultSet results) throws SQLException {
		List<Employee> emps = new ArrayList<>();
		while (results.next()) {
			Employee employee = createEmployee(results);
			emps.add(employee);
		}
		return emps;
	}
	
	public static HourlyEmp createHourlyEmployee(ResultSet results) throws SQLException{
			Employee employee = createEmployee(results);
			return new HourlyEmp(employee.getEmpId(), employee.getEmpFname(), employee.getEmpLname(), employee.getEmpAddress(), results.getDouble("hourlyClassRate"));

	}

	public static List<TimeSheetEntry> createTimeSheets(ResultSet results) throws SQLException {
		List<TimeSheetEntry> ts = new ArrayList<>();
		while (results.next()){
			TimeSheetEntry timeSheet = createTimeSheet(results);
			ts.add(timeSheet);
		}
		return ts;
	}

	private static TimeSheetEntry createTimeSheet(ResultSet results) throws SQLException {
		TimeSheetEntry timeSheet = new TimeSheetEntry(results.getInt("timeSheetId"), results.getString("timeSheetDate"), results.getDouble("timeSheetHours"), results.getInt("empId"), results.getString("paid"));
		return timeSheet;
	}

	public static List<TimeSheetEntry> createTimeSheetsForEmp(ResultSet results) throws SQLException {
		List<TimeSheetEntry> ts = new ArrayList<>();
		while (results.next()){
			TimeSheetEntry timeSheet = createTimeSheet(results);
			ts.add(timeSheet);
		}
		return ts;
	}

	public static List<HourlyEmp> createHourlyEmployees(ResultSet results)throws SQLException {
		List<HourlyEmp> emps = new ArrayList<>();
		while (results.next()) {	
			emps.add(createHourlyEmployee(results));
		}
		return emps;
	}

	public static List<CommEmp> createCommissionEmployees(ResultSet results) throws SQLException {
		List<CommEmp> emps = new ArrayList<>();
		while (results.next()) {	
			emps.add(createCommissionEmployee(results));
		}
		return emps;
	}

	private static CommEmp createCommissionEmployee(ResultSet results)throws SQLException  {
		Employee employee = createEmployee(results);
		return new CommEmp(employee.getEmpId(), employee.getEmpFname(), employee.getEmpLname(), employee.getEmpAddress(), results.getDouble("commClassRate"), results.getDouble("commClassSalary"));
	}

	public static List<SalaryEmp> createSalaryEmployees(ResultSet results)throws SQLException {
		List<SalaryEmp> emps = new ArrayList<>();
		while (results.next()) {	
			emps.add(createSalaryEmployee(results));
		}
		return emps;
	}
	
	public static SalaryEmp createSalaryEmployee(ResultSet results)throws SQLException{
		Employee employee = createEmployee(results);
		return new SalaryEmp(employee.getEmpId(), employee.getEmpFname(), employee.getEmpLname(), employee.getEmpAddress(), results.getDouble("salClassSalary"));
	}

	public static List<SalesReciept> createSalesRecipts(ResultSet results) throws SQLException{
		List<SalesReciept> sr = new ArrayList<>();
		while (results.next()){
			SalesReciept salesReciept = createSalesReciept(results);
			sr.add(salesReciept);
		}
		return sr;
	}

	private static SalesReciept createSalesReciept(ResultSet results) throws SQLException {
		SalesReciept salesReciept = new SalesReciept(results.getInt("salesReciptId"), results.getDouble("salesReciptAmount"), results.getString("salesReciptDate"), results.getInt("empId"), results.getString("paid"));
		return salesReciept;
	}
	

	public static List<MailMethod> createMailMethods(ResultSet results) throws SQLException {
		List<MailMethod> mm = new ArrayList<>();
		while (results.next()){
			MailMethod mailMethod = createMailMethod(results);
			mm.add(mailMethod);
		}
		return mm;
	}
	
	private static MailMethod createMailMethod(ResultSet results) throws SQLException {
		MailMethod mailMethod = new MailMethod(results.getInt("mailPayId"), results.getString("mailPayAddress"), results.getInt("empId"));
		return mailMethod;
	}

	public static List<HoldMethod> createHoldMethods(ResultSet results) throws SQLException {
		List<HoldMethod> hm = new ArrayList<>();
		while (results.next()){
			HoldMethod holdMethod = createHoldMethod(results);
			hm.add(holdMethod);
		}
		return hm;
	}
	
	private static HoldMethod createHoldMethod(ResultSet results) throws SQLException {
		HoldMethod holdMethod = new HoldMethod(results.getInt("holdPayId"), results.getInt("empId"));
		return holdMethod;
	}

	public static List<DirectDepositMethod> createDirectMethods(
			ResultSet results) throws SQLException {
		List<DirectDepositMethod> dm = new ArrayList<>();
		while (results.next()){
			DirectDepositMethod directMethod = createDirectMethod(results);
			dm.add(directMethod);
		}
		return dm;
	}
	
	private static DirectDepositMethod createDirectMethod(ResultSet results) throws SQLException {
		DirectDepositMethod directDepositMethod = new DirectDepositMethod(results.getInt("directPayId"), results.getString("directPayBankName"), results.getString("directPayBSB"), results.getString("directPayAcctNum"), results.getInt("empId"));
		return directDepositMethod;
	}

	public static List<UnionCharge> createUnionCharges(ResultSet results) throws SQLException {
		List<UnionCharge> uc = new ArrayList<>();
		while (results.next()){
			UnionCharge unionCharge = createUnionCharge(results);
			uc.add(unionCharge);
		}
		return uc;
	}

	private static UnionCharge createUnionCharge(ResultSet results) throws SQLException {
		UnionCharge unionCharge = new UnionCharge(results.getInt("affId"), results.getString("affUnionName"), results.getDouble("affUnionDues"), results.getInt("empId"));
		return unionCharge;
	}

	public static List<UnionCharge> createUnionChargeForEmp(ResultSet results) throws SQLException {
		List<UnionCharge> uc = new ArrayList<>();
		while (results.next()){
		UnionCharge unionCharge = new UnionCharge(results.getInt("affId"), results.getString("affUnionName"), results.getDouble("affUnionDues"), results.getInt("empId"));
		uc.add(unionCharge);
		}
		return uc;
	}

	public static List<SalesReciept> createSalesReciptsForEmp(ResultSet results) throws SQLException {
		List<SalesReciept> sr = new ArrayList<>();
		while (results.next()){
			SalesReciept salesReciept = createSalesReciept(results);
			sr.add(salesReciept);
		}
		return sr;
	}

	public static List<Paycheck> createPaycheckForEmp(ResultSet results) throws SQLException {
		List<Paycheck> sr = new ArrayList<>();
		while (results.next()){
			Paycheck paycheck = createPayckeck(results);
			sr.add(paycheck);
		}
		return sr;
	}

	private static Paycheck createPayckeck(ResultSet results) throws SQLException {
		Paycheck paycheck = new Paycheck(results.getInt("empId"), results.getString("payDate"), results.getDouble("grossPay"), results.getDouble("deductions"), results.getDouble("netPay"));
		return paycheck;
	}
	

}
