package model;

public class SalaryEmp extends Employee{
	
	private double salary;
	public static final String SALARY_TYPE = "S";

	public SalaryEmp(Integer empId, String empFname, String empLname,
			String empAddress, double salary) {
		
		super(empId, empFname, empLname, empAddress, SALARY_TYPE);
		this.setSalary(salary);
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "AddSalaryEmp [toString()="
				+ super.toString() + ", salary=" + salary + "]";
	}
	
	

}
