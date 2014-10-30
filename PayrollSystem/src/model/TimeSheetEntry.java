package model;

public class TimeSheetEntry {

	private Integer timeSheetId;
	private String entryDate;
	private Double entryHours;
	private int empId;
	private String paid;
	
	
	
	public TimeSheetEntry(Integer timeSheetId, String entryDate, Double entryHours, Integer empId, String paid){
		this.timeSheetId = timeSheetId;
		this.entryDate = entryDate;
		this.entryHours = entryHours;
		this.empId = empId;
		this.paid = paid;
		
	}
	
	public Integer getTimeSheetId() {
		return timeSheetId;
	}

	public void setTimeSheetId(Integer timeSheetId) {
		this.timeSheetId = timeSheetId;
	}
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public Double getEntryHours() {
		return entryHours;
	}
	public void setEntryHours(Double entryHours) {
		this.entryHours = entryHours;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
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
		return "TimeSheetEntry [timeSheetId=" + timeSheetId + ", entryDate="
				+ entryDate + ", entryHours=" + entryHours + ", empId=" + empId
				+ ", paid=" + paid + "]";
	}
	
	
	
	
	

}
