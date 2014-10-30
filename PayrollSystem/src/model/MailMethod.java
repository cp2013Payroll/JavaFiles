package model;

public class MailMethod implements PaymentMethod
{
	private Integer mailPayId;
	private String address;
	private int empId;

	public MailMethod(Integer mailPayId, String address, int empId)
	{
		this.mailPayId = mailPayId;
		this.address = address;
		this.empId = empId;
	}

	public void Pay(Paycheck paycheck)
	{
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getMailPayId() {
		return mailPayId;
	}

	public void setMailPayId(Integer mailPayId) {
		this.mailPayId = mailPayId;
	}
	
	public int getEmpId(){
		return empId;
	}
	
	public void setEmpId(int empId){
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "MailMethod [mailPayId=" + mailPayId + ", address=" + address
				+ "empId=" + empId + "]";
	}
	
	

}
