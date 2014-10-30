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
	private JButton[] buttons;
	
	public PayrollFrame() {
		buttons = new JButton[7];
		buttonBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE);
		topPanel = new JPanel();
		sidePanel = new JPanel();
		dataPanel = new DataPanel();
		titleLabel = new JLabel("PAYROLL SYSTEM");
		buttonFont = new Font(Font.SERIF, Font.PLAIN, 20);
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
		
		buttons[0] = addEmployeeButton;
		buttons[1] = deleteEmployeeButton;
		buttons[2] = postTimeCardButton;
		buttons[3] = postSalesReceiptButton;
		buttons[4] = postUnionChargeButton;
		buttons[5] = changeEmployeeDetailsButton;
		buttons[6] = runPayrollButton;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Payroll System");
		setPreferredSize(new Dimension(1000, 600));
		setBackground(new Color(230, 230, 250));
		
		titleLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		topPanel.setBackground(Color.WHITE);
		topPanel.setPreferredSize(new Dimension(1000, 50));
		topPanel.setLayout(new BorderLayout());
		topPanel.add(titleLabel, BorderLayout.CENTER);
		
		sidePanel.setBackground(new Color(230, 230, 250));
		sidePanel.setPreferredSize(new Dimension(300, 550));
		
		for(JButton button : buttons) {
			button.setForeground(Color.BLUE);
			button.setFont(buttonFont);
			button.setBorder(buttonBorder);
			button.setPreferredSize(buttonDimension);
			button.addActionListener(buttonListener);
			sidePanel.add(button);
		}
		
		add(topPanel, BorderLayout.NORTH);
		add(sidePanel, BorderLayout.WEST);
		add(dataPanel, BorderLayout.CENTER);
	}
	
	public void changeState(GUIState state) {
		dataPanel.redraw();
		dataPanel.repaint();
		switch (state) {
			case INITIAL:
				dataPanel.drawInitialSetup();
				break;
			case ADD_EMPLOYEE:
				dataPanel.drawAddEmployee();
				break;
			case DELETE_EMPLOYEE:
				dataPanel.drawDeleteEmployee();
				break;
			case POST_TIME_CARD:
				dataPanel.drawPostTimeCard();
				break;
			case POST_SALES_RECEIPT:
				dataPanel.drawPostSalesReceipt();
				break;
			case POST_UNION_SERVICE_CHARGE:
				dataPanel.drawPostUnionServiceCharge();
				break;
			case CHANGE_EMPLOYEE_DETAILS:
				dataPanel.drawChangeEmployee();
				break;	
			case RUN_PAYROLL:
				dataPanel.drawRunPayroll();
				break;
		}
	}
}