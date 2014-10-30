package model;

public class CommEmp extends Employee{
	
	private double commRate;
	private double commSalary;
	public static final String COMM_TYPE = "C";

	public CommEmp(Integer empId, String empFname, String empLname,
			String empAddress, double commRate, double commSalary) {
		
		super(empId, empFname, empLname, empAddress, COMM_TYPE);
		this.setCommRate(commRate);
		this.setCommSalary(commSalary);
	}

	public double getCommRate() {
		return commRate;
	}

	public void setCommRate(double commRate) {
		this.commRate = commRate;
	}

	public double getCommSalary() {
		return commSalary;
	}

	public void setCommSalary(double commSalary) {
		this.commSalary = commSalary;
	}

	@Override
	public String toString() {
		return "AddCommEmp [toString()=" + super.toString() + ", commRate="
				+ commRate + ", commSalary=" + commSalary + "]";
	}

	


	



	

}
