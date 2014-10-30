package model;

import model.Paycheck;
import model.PaymentMethod;

public class HoldMethod implements PaymentMethod{
	
	private Integer holdPayId;
	private int empId;
	
	public HoldMethod(Integer holdPayId, int empId){
		this.holdPayId = holdPayId;
		this.empId = empId;
		
	}
	public void Pay(Paycheck paycheck)
	{
	}

	
	public int getHoldPayId() {
		return holdPayId;
	}
	public void setHoldPayId(int holdPayId) {
		this.holdPayId = holdPayId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	@Override
	public String toString() {
		return "HoldMethod [holdPayId=" + holdPayId + ", empId=" + empId + "]";
	}
	
	

}
