package model;

import java.util.Date;

public class PayPeriod {
	
	private Date startDate;
	private Date endDate;
	public PayPeriod(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "PayPeriod [startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
	
	

}
