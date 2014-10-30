package dataCreators;

import java.sql.SQLException;
import java.util.List;

import model.CommEmp;
import model.DateUtil;
import model.Paycheck;
import model.SalesReciept;
import model.UnionCharge;
import dao.SalesRecieptOperations;
import dao.UnionOperations;

public class CommissionPayClassification implements PayClassification<CommEmp> {

	@Override
	public Paycheck calculatePay(CommEmp emp) throws SQLException {
//		System.out.println("Paying Commission Employee " + emp.getEmpFname() + " " + emp.getEmpLname() + "(" + emp.getEmpId() + ")");
		double totalSales = 0;
		double totalCommission = 0;
		double totalPay = 0;
		double commRate = emp.getCommRate();
		double weeklyPay = emp.getCommSalary() / 365; //Payroll is run daily
		double deductions = 0;
		double netPay= 0;
		
		List<UnionCharge> uc = UnionOperations.findUnionChargeForEmp(emp.getEmpId());
		for(UnionCharge unionCharge : uc){
			deductions += unionCharge.getAffUnionDues();
		}
		
		List<SalesReciept> sr = SalesRecieptOperations.findSalesRecieptForEmp(emp.getEmpId());
		for(SalesReciept salesReciept : sr){
			if(salesReciept.getPaid().equals("F")){
			totalSales += salesReciept.getSalesReciptAmount();
			SalesRecieptOperations.updateSalesReciptsToPAID(salesReciept.getSalesReciptId());
			}
		}
		totalCommission += (commRate * totalSales) / 100; 
		totalPay += totalCommission + weeklyPay;
		netPay += totalPay - deductions;
		
		Paycheck payChk = new Paycheck(emp.getEmpId(), DateUtil.todaysDate().toString(),totalPay, deductions, netPay);
		payChk.setGrossPay(totalPay);
		
		System.out.println(payChk.toString());
		return payChk;
	}

}
