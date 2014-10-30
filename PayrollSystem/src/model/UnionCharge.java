package model;

public class UnionCharge {
	
	Integer affId;
	String affUnionName;
	double affUnionDues;
	int empId;
	
	public UnionCharge(Integer affId, String affUnionName, double affUnionDues,
			int empId) {
		
		this.affId = affId;
		this.affUnionName = affUnionName;
		this.affUnionDues = affUnionDues;
		this.empId = empId;
	}

	public Integer getAffId() {
		return affId;
	}

	public void setAffId(Integer affId) {
		this.affId = affId;
	}

	public String getAffUnionName() {
		return affUnionName;
	}

	public void setAffUnionName(String affUnionName) {
		this.affUnionName = affUnionName;
	}

	public double getAffUnionDues() {
		return affUnionDues;
	}

	public void setAffUnionDues(double affUnionDues) {
		this.affUnionDues = affUnionDues;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "UnionCharge [affId=" + affId + ", affUnionName=" + affUnionName
				+ ", affUnionDues=" + affUnionDues + ", empId=" + empId + "]";
	}
	
}
