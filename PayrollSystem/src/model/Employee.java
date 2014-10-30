package model;

public class Employee implements Comparable<Employee>{
	private Integer empId;
	private String empFname;
	private String empLname;
	private String empAddress;
	private String empType;
	
	

	public Employee(Integer empId, String empFname, String empLname,
			String empAddress, String empType) {
		super();
		this.empId = empId;
		this.empFname = empFname;
		this.empLname = empLname;
		this.empAddress = empAddress;
		this.empType = empType;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmpLname() {
		return empLname;
	}

	public void setEmpLname(String empLname) {
		this.empLname = empLname;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empFname=" + empFname
				+ ", empLname=" + empLname + ", empAddress=" + empAddress
				+ ", empType=" + empType + "]";
	}

	@Override
	public int compareTo(Employee emp2) {
		return this.getEmpId().compareTo(emp2.getEmpId());
	}
}
