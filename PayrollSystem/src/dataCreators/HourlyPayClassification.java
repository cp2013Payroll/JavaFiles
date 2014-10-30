package dataCreators;

import java.sql.SQLException;
import java.util.List;

import model.DateUtil;
import model.HourlyEmp;
import model.Paycheck;
import model.TimeSheetEntry;
import model.UnionCharge;
import dao.TimesheetOperations;
import dao.UnionOperations;

public class HourlyPayClassification implements PayClassification<HourlyEmp> {

	@Override
	public Paycheck calculatePay(HourlyEmp emp) throws SQLException {
		
		double totalHours = 0;
		double totalPay = 0;
		double deductions = 0;
		double netPay = 0;
		
		List<UnionCharge> uc = UnionOperations.findUnionChargeForEmp(emp.getEmpId());
		for(UnionCharge unionCharge : uc){
			deductions += unionCharge.getAffUnionDues();
		}
		
		
		List<TimeSheetEntry> ts = TimesheetOperations.findTimeSheetsForEmp(emp.getEmpId());
		
		for(TimeSheetEntry timeSheet : ts){
			if(timeSheet.getPaid().equals("F")){
			totalHours += timeSheet.getEntryHours();
			TimesheetOperations.updateTimeSheetEntrysToPAID(timeSheet.getTimeSheetId());
			
			}
			
		}
		

		
		totalPay += totalHours * emp.getHourlyRate();
		netPay += totalPay - deductions;
		
		Paycheck payChk = new Paycheck(emp.getEmpId(), DateUtil.todaysDate().toString(),totalPay, deductions, netPay);
		payChk.setGrossPay(totalPay);
		
		System.out.println(payChk.toString());
		return payChk;
	}

}
