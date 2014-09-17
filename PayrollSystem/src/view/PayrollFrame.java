package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.GUIState;


@SuppressWarnings("serial")
public class PayrollFrame extends JFrame{
	private JPanel topPanel;
	private JPanel sidePanel;
	private DataPanel dataPanel;
	private JLabel titleLabel;
	private Font buttonFont;
	private Dimension buttonDimension;
	private JButton addEmployeeButton;
	private JButton deleteEmployeeButton;
	private JButton postTimeCardButton;
	private JButton postSalesReceiptButton;
	private JButton postUnionChargeButton;
	private JButton changeEmployeeDetailsButton;
	private JButton runPayrollButton;
	private ActionListener buttonListener;
	private Border buttonBorder;
	
	public PayrollFrame() {
		buttonBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE);
		topPanel = new JPanel();
		sidePanel = new JPanel();
		dataPanel = new DataPanel();
		titleLabel = new JLabel("PAYROLL SYSTEM");
		buttonFont = new Font(Font.SERIF, Font.PLAIN, 18);
		buttonDimension = new Dimension(250, 50);
		addEmployeeButton = new JButton("ADD EMPLOYEE");
		deleteEmployeeButton = new JButton("DELETE EMPLOYEE");
		postTimeCardButton = new JButton("POST A TIME CARD");
		postSalesReceiptButton = new JButton("POST A SALES RECEIPT");
		postUnionChargeButton = new JButton("<html><center>POST A UNION SERVICE CHARGE</center></html>");
		changeEmployeeDetailsButton = new JButton("<html><center>CHANGE EMPLOYEE DETAILS</center></html>");
		runPayrollButton = new JButton("RUN PAYROLL");
		buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "ADD EMPLOYEE":
					changeState(GUIState.ADD_EMPLOYEE);
					break;
				case "DELETE EMPLOYEE":
					changeState(GUIState.DELETE_EMPLOYEE);
					break;
				case "POST A TIME CARD":
					changeState(GUIState.POST_TIME_CARD);
					break;
				case "POST A SALES RECEIPT":
					changeState(GUIState.POST_SALES_RECEIPT);
					break;
				case "<html><center>POST A UNION SERVICE CHARGE</center></html>":
					changeState(GUIState.POST_UNION_SERVICE_CHARGE);
					break;
				case "<html><center>CHANGE EMPLOYEE DETAILS</center></html>":
					changeState(GUIState.CHANGE_EMPLOYEE_DETAILS);
					break;
				case "RUN PAYROLL":
					changeState(GUIState.RUN_PAYROLL);
					break;
				}
			}
		};
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Payroll System");
		setPreferredSize(new Dimension(1000, 600));
		setBackground(new Color(230, 230, 250));
		
		titleLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		topPanel.setBackground(Color.WHITE);
		topPanel.setPreferredSize(new Dimension(1000, 50));
		topPanel.setLayout(new BorderLayout());
		topPanel.add(titleLabel, BorderLayout.CENTER);
				
		addEmployeeButton.setForeground(Color.BLUE);
		deleteEmployeeButton.setForeground(Color.BLUE);
		postTimeCardButton.setForeground(Color.BLUE);
		postSalesReceiptButton.setForeground(Color.BLUE);
		postUnionChargeButton.setForeground(Color.BLUE);
		changeEmployeeDetailsButton.setForeground(Color.BLUE);
		runPayrollButton.setForeground(Color.BLUE);
		
		addEmployeeButton.setFont(buttonFont);
		deleteEmployeeButton.setFont(buttonFont);
		postTimeCardButton.setFont(buttonFont);
		postSalesReceiptButton.setFont(buttonFont);
		postUnionChargeButton.setFont(buttonFont);
		changeEmployeeDetailsButton.setFont(buttonFont);
		runPayrollButton.setFont(buttonFont);
		
		addEmployeeButton.setBorder(buttonBorder);
		deleteEmployeeButton.setBorder(buttonBorder);
		postTimeCardButton.setBorder(buttonBorder);
		postSalesReceiptButton.setBorder(buttonBorder);
		postUnionChargeButton.setBorder(buttonBorder);
		changeEmployeeDetailsButton.setBorder(buttonBorder);
		runPayrollButton.setBorder(buttonBorder);
		
		addEmployeeButton.setPreferredSize(buttonDimension);
		deleteEmployeeButton.setPreferredSize(buttonDimension);
		postTimeCardButton.setPreferredSize(buttonDimension);
		postSalesReceiptButton.setPreferredSize(buttonDimension);
		postUnionChargeButton.setPreferredSize(buttonDimension);
		changeEmployeeDetailsButton.setPreferredSize(buttonDimension);
		runPayrollButton.setPreferredSize(buttonDimension);
		
		addEmployeeButton.addActionListener(buttonListener);
		deleteEmployeeButton.addActionListener(buttonListener);
		postTimeCardButton.addActionListener(buttonListener);
		postSalesReceiptButton.addActionListener(buttonListener);
		postUnionChargeButton.addActionListener(buttonListener);
		changeEmployeeDetailsButton.addActionListener(buttonListener);
		runPayrollButton.addActionListener(buttonListener);
		
		sidePanel.setBackground(new Color(230, 230, 250));
		sidePanel.setPreferredSize(new Dimension(300, 550));
		
		sidePanel.add(addEmployeeButton);
		sidePanel.add(deleteEmployeeButton);
		sidePanel.add(postTimeCardButton);
		sidePanel.add(postSalesReceiptButton);
		sidePanel.add(postUnionChargeButton);
		sidePanel.add(changeEmployeeDetailsButton);
		sidePanel.add(runPayrollButton);
		
		add(topPanel, BorderLayout.NORTH);
		add(sidePanel, BorderLayout.WEST);
		add(dataPanel, BorderLayout.CENTER);
	}
	
	public void changeState(GUIState state) {
		switch (state) {
			case INITIAL:
				System.out.println("System Start");
				dataPanel.drawInitialSetup();
				break;
			case ADD_EMPLOYEE:
				System.out.println("Add Employee");
				break;
			case DELETE_EMPLOYEE:
				System.out.println("Delete Employee");
				break;
			case POST_SALES_RECEIPT:
				System.out.println("Post Sales Receipt");
				break;
			case POST_TIME_CARD:
				System.out.println("Post Time Card");
				break;
			case POST_UNION_SERVICE_CHARGE:
				System.out.println("Post Union Service Charge");
				break;
			case CHANGE_EMPLOYEE_DETAILS:
				System.out.println("Change Employee Details");
				break;	
			case RUN_PAYROLL:
				System.out.println("Run Payroll");
				break;
		}
	}
}