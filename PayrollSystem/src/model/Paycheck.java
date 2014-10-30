package model;

public class Paycheck {
	
	private int empId;
	private String payDate;
	private double grossPay;
	private double deductions;
	private double netPay;
	
	public Paycheck(int empId, String payDate, double grossPay,
			double deductions, double netPay) {
		super();
		this.empId = empId;
		this.payDate = payDate;
		this.grossPay = grossPay;
		this.deductions = deductions;
		this.netPay = netPay;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	public double getDeductions() {
		return deductions;
	}


	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	@Override
	public String toString() {
		return "Paycheck [empId=" + empId + ", payDate=" + payDate
				+ ", grossPay=" + grossPay + ", deductions=" + deductions + ", netPay=" + netPay
				+ "]";
	}

}
