package model;

public class HourlyEmp extends Employee{
	
	private double hourlyRate;
	public static final String HOURLY_TYPE = "H";

	public HourlyEmp(Integer empId, String empFname, String empLname,
			String empAddress, double hourlyRate) {
		
		super(empId, empFname, empLname, empAddress, HOURLY_TYPE);
		this.setHourlyRate(hourlyRate);
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	@Override
	public String toString() {
		return "AddHourlyEmp [toString()=" + super.toString() + ", hourlyRate="
				+ hourlyRate + "]";
	}

}
