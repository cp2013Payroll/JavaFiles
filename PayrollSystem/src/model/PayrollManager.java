package model;

public class PayrollManager {
	private static PayrollManager manager;
	
	public static PayrollManager getManager() {
		if(manager == null) {
			manager = new PayrollManager();
		}
		return manager;
	}
}