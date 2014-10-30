package dataCreators;

import java.sql.SQLException;

import model.Employee;
import model.Paycheck;

//COMMAND PATTERN
public interface PayClassification<T extends Employee> {
//	T uses generic's principles.
	public Paycheck calculatePay(T emp) throws SQLException;
}
