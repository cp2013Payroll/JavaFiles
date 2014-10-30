package dataCreators;

import java.sql.SQLException;
import java.util.List;

import model.DateUtil;
import model.Paycheck;
import model.SalaryEmp;
import model.UnionCharge;
import dao.UnionOperations;

public class SalaryPayClassification implements PayClassification<SalaryEmp> {

	@Override
	public Paycheck calculatePay(SalaryEmp emp) throws SQLException {
//		System.out.println("Paying Salary Employee " + emp.getEmpFname() + " " + emp.getEmpLname() + "(" + emp.getEmpId() + ")");
		
		double totalPay = emp.getSalary() / 365; //Payroll is run daily 
		double deductions = 0;
		double netPay = 0;
		List<UnionCharge> uc = UnionOperations.findUnionChargeForEmp(emp.getEmpId());
		for(UnionCharge unionCharge : uc){
			deductions += unionCharge.getAffUnionDues();
		}
		netPay += totalPay - deductions;
		
		Paycheck payChk = new Paycheck(emp.getEmpId(), DateUtil.todaysDate().toString(),totalPay, deductions, netPay);
		
		payChk.setGrossPay(totalPay);
		
		System.out.println(payChk.toString());
		return payChk;
	}

}
