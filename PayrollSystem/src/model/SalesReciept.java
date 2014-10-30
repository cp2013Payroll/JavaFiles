package model;

public class SalesReciept {
	
	Integer salesReciptId;
	double salesReciptAmount;
	String salesReciptDate;
	int empId;
	private String paid;
	
	public SalesReciept(Integer salesReciptId, double salesReciptAmount, String salesReciptDate, int empId, String paid){
		this.salesReciptId = salesReciptId;
		this.salesReciptAmount = salesReciptAmount;
		this.salesReciptDate = salesReciptDate;
		this.empId = empId;
		this.setPaid(paid);
	}
	
	

	public Integer getSalesReciptId() {
		return salesReciptId;
	}



	public void setSalesReciptId(Integer salesReciptId) {
		this.salesReciptId = salesReciptId;
	}



	public double getSalesReciptAmount() {
		return salesReciptAmount;
	}

	public void setSalesReciptAmount(double salesReciptAmount) {
		this.salesReciptAmount = salesReciptAmount;
	}

	public String getSalesReciptDate() {
		return salesReciptDate;
	}

	public void setSalesReciptDate(String salesReciptDate) {
		this.salesReciptDate = salesReciptDate;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public String getPaid() {
		return paid;
	}



	public void setPaid(String paid) {
		this.paid = paid;
	}



	@Override
	public String toString() {
		return "SalesReciept [salesReciptId=" + salesReciptId
				+ ", salesReciptAmount=" + salesReciptAmount
				+ ", salesReciptDate=" + salesReciptDate + ", empId=" + empId
				+ ", paid=" + paid + "]";
	}
	
	

}
