package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.PayrollFrame;

public class App {
	private static PayrollFrame payrollFrame;
	private static ActionListener menuListener;
	
	public static void main(String[] args) {
		payrollFrame = new PayrollFrame();
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem addEmployee = new JMenuItem("Add a New Employee");
		JMenuItem deleteEmployee = new JMenuItem("Delete an Employee");
		JMenuItem postTimeCard = new JMenuItem("Post a Time Card");
		JMenuItem postSalesReceipt = new JMenuItem("Post a Sales Receipt");
		JMenuItem postUnionServiceCharge = new JMenuItem("Post a Union Service Charge");
		JMenuItem changeEmployee = new JMenuItem("Change Employee Details");
		JMenuItem runPayroll = new JMenuItem("Run Payroll");
		JMenuItem quit = new JMenuItem("Quit");
		
		menuListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "Add a New Employee":
					payrollFrame.changeState(GUIState.ADD_EMPLOYEE);
					break;
				case "Delete an Employee":
					payrollFrame.changeState(GUIState.DELETE_EMPLOYEE);
					break;
				case "Post a Time Card":
					payrollFrame.changeState(GUIState.POST_TIME_CARD);
					break;
				case "Post a Sales Receipt":
					payrollFrame.changeState(GUIState.POST_SALES_RECEIPT);
					break;
				case "Post a Union Service Charge":
					payrollFrame.changeState(GUIState.POST_UNION_SERVICE_CHARGE);
					break;
				case "Change Employee Details":
					payrollFrame.changeState(GUIState.CHANGE_EMPLOYEE_DETAILS);
					break;
				case "Run Payroll":
					payrollFrame.changeState(GUIState.RUN_PAYROLL);
					break;
				case "Quit":
					System.exit(0);
					break;
				}
			}
		};
		
		menu.add(addEmployee);
		menu.add(deleteEmployee);
		menu.add(postTimeCard);
		menu.add(postSalesReceipt);
		menu.add(postUnionServiceCharge);
		menu.add(changeEmployee);
		menu.add(runPayroll);
		menu.add(quit);
		menuBar.add(menu);
		payrollFrame.setJMenuBar(menuBar);
		
		addEmployee.addActionListener(menuListener);
		deleteEmployee.addActionListener(menuListener);
		postTimeCard.addActionListener(menuListener);
		postSalesReceipt.addActionListener(menuListener);
		postUnionServiceCharge.addActionListener(menuListener);
		changeEmployee.addActionListener(menuListener);
		runPayroll.addActionListener(menuListener);
		quit.addActionListener(menuListener);
		
		payrollFrame.setResizable(false);
		payrollFrame.pack();
		payrollFrame.setLocationRelativeTo(null);
		payrollFrame.setVisible(true);
		payrollFrame.changeState(GUIState.INITIAL);
	}
}
