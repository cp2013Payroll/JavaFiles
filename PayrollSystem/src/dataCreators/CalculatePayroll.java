package dataCreators;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import model.Employee;
import dao.EmployeeOperations;
import dao.PaycheckOperations;

public class CalculatePayroll {
	
//	Handler for pay calculations based on employee base class type.
	
	@SuppressWarnings("unchecked")
	public static void CalcPay() throws SQLException{
		List<Employee> employees = EmployeeOperations.findEmployees2();
		Collections.sort(employees);

		for(Employee emp : employees){
			@SuppressWarnings("rawtypes")
			PayClassification classification = null;
			switch(emp.getClass().getSimpleName()){
			
	        	case "SalaryEmp": classification= new SalaryPayClassification();
	        		break;
	        		
	        	case "HourlyEmp": classification = new HourlyPayClassification();
	        		break;
	        		
	        	case "CommEmp": classification = new CommissionPayClassification();
	        		break;
	        	
	        }
	        PaycheckOperations.addPaycheck(classification.calculatePay(emp));
	    
		}
	}
}
