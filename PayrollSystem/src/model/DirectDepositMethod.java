package model;

public class DirectDepositMethod implements PaymentMethod
{
	private Integer directPayId;
	private String directPayBankName;
	private String directPayBSB;
	private String	directPayAcctNum;
	private int empId;



	public DirectDepositMethod(Integer directPayId, String directPayBankName,
			String directPayBSB, String directPayAcctNum, int empId) {
		super();
		this.directPayId = directPayId;
		this.directPayBankName = directPayBankName;
		this.directPayBSB = directPayBSB;
		this.directPayAcctNum = directPayAcctNum;
		this.empId = empId;
	}

	public Integer getDirectPayId() {
		return directPayId;
	}



	public void setDirectPayId(Integer directPayId) {
		this.directPayId = directPayId;
	}



	public String getDirectPayBankName() {
		return directPayBankName;
	}



	public void setDirectPayBankName(String directPayBankName) {
		this.directPayBankName = directPayBankName;
	}



	public String getDirectPayBSB() {
		return directPayBSB;
	}



	public void setDirectPayBSB(String directPayBSB) {
		this.directPayBSB = directPayBSB;
	}



	public String getDirectPayAcctNum() {
		return directPayAcctNum;
	}



	public void setDirectPayAcctNum(String directPayAcctNum) {
		this.directPayAcctNum = directPayAcctNum;
	}



	public int getEmpId() {
		return empId;
	}



	public void setEmpId(int empId) {
		this.empId = empId;
	}



	@Override
	public String toString() {
		return "DirectDepositMethod [directPayId=" + directPayId
				+ ", directPayBankName=" + directPayBankName
				+ ", directPayBSB=" + directPayBSB + ", directPayAcctNum="
				+ directPayAcctNum + ", empId=" + empId + "]";
	}

	@Override
	public void Pay(Paycheck paycheck) {
		// TODO Auto-generated method stub
		
	}




}
