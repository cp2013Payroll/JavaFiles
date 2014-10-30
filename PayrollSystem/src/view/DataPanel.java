package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import model.*;
import dao.*;
import dataCreators.CalculatePayroll;

@SuppressWarnings("serial")
public class DataPanel extends JPanel {
	//global definitions
	private Dimension size;
	private Font labelFont, textFieldFont, titleAndButtonFont;
	private Border panelBorder;
	
	//add and edit employee component definitions
	int yStart;
	private JLabel[] addEmployeeLabels, changeEmployeeLabels;
	private Component[] addEmployeeInputFields, changeEmployeeInputFields;
	private JButton[] addEmployeeButtons, changeEmployeeButtons;
	private ActionListener addEmployeeButtonListener, workTypeListener, changeEmployeeButtonListener, paymentTypeListener;
	private JLabel titleLabel, firstNameLabel, lastNameLabel, addressLabel;
	private JLabel accountNumberLabel, bsbNumberLabel, bankLabel, workTypeLabel, payrateLabel, commissionRateLabel;
	private JLabel paymentMethodLabel, unionLabel;
	private JTextField firstNameTextField, lastNameTextField, addressTextField;
	private JTextField accountNumberTextField, bsbNumberTextField, bankTextField, payrateTextField;
	private JComboBox<String> workTypeComboBox, paymentMethodComboBox;
	private JTextField commissionRateTextField;
	private JCheckBox unionTypeCheckBox;

	//delete employee, post time card, post sales receipt and post union service charge component definitions
	private int empid;
	private Employee emp;
	private JLabel searchEmployeesLabel, enterHoursLabel, enterSalesLabel, enterUnionChargeLabel;
	private JTextField searchEmployeeTextField, enterHoursTextField, enterSalesTextField, enterUnionChargeTextField;
	private ActionListener searchEmployeeListener, searchEmployeeResultsListener, postTimeCardListener, postSalesReceiptListener;
	private ActionListener postUnionChargeListener;
	private Border searchResultsBorder;
	private JButton searchResultsButton, enterHoursButton, clearHoursButton, enterSalesButton, clearSalesButton;
	private JButton enterUnionChargeButton, clearUnionChargeButton;
	
	//run payroll component definitions
	private JButton runPayrollButton;
	private ActionListener runPayrollListener;
	
	public DataPanel() {
		labelFont = new Font(Font.SERIF, Font.PLAIN, 18);
		textFieldFont = new Font(Font.SERIF, Font.PLAIN, 16);
		titleAndButtonFont = new Font(Font.SERIF, Font.PLAIN, 20);
		panelBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE);
		setPreferredSize(new Dimension(700, 550));
		setLayout(null);
		setBackground(new Color(230, 230, 250));
		setBorder(panelBorder);
	}
	
	public void redraw() {
		removeAll();
	}
	
	public void drawInitialSetup() {
		titleLabel = new JLabel("PAYROLL SYSTEM v1.0");
		titleLabel.setFont(titleAndButtonFont);
		titleLabel.setForeground(Color.BLUE);
		add(titleLabel);
		
		size = titleLabel.getPreferredSize();
		titleLabel.setBounds(275, 10, size.width, size.height);
		
		JLabel infoLabelPart1 = new JLabel("Payroll System \u00a9 made for CP2013 by Suart Garrigan, Benjamin Wallace,");
		JLabel intoLabelPart2 = new JLabel("Jake Fleming, Joshua Fraser and Justin Osbaldiston");
		infoLabelPart1.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
		intoLabelPart2.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
		infoLabelPart1.setForeground(Color.BLUE);
		intoLabelPart2.setForeground(Color.BLUE);
		add(infoLabelPart1);
		add(intoLabelPart2);
		
		size = infoLabelPart1.getPreferredSize();
		infoLabelPart1.setBounds(10, 450, size.width, size.height);
		size = intoLabelPart2.getPreferredSize();
		intoLabelPart2.setBounds(10, 470, size.width, size.height);
	}
	
	public void drawAddEmployee() {
		addEmployeeButtonListener = new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "SUBMIT":
					switch (workTypeComboBox.getSelectedItem().toString()) {
					case "Hourly":
						HourlyEmp he = new HourlyEmp(1, firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(), Double.parseDouble(payrateTextField.getText()));
						try {
							he = EmployeeOperations.saveHourlyEmployee(he);
							if(checkUnionEmployee(he)) {
								UnionCharge uc = new UnionCharge(1, "Union", 25, he.getEmpId());
								UnionOperations.saveUnionCharge(uc);
							}
							addPaymentOption(he);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						break;
					case "Salary":
						SalaryEmp se = new SalaryEmp(1, firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(), Double.parseDouble(payrateTextField.getText()));
						try {
							se = EmployeeOperations.saveSalaryEmployee(se);
							if(checkUnionEmployee(se)) {
								UnionCharge uc = new UnionCharge(1, "Union", 25, se.getEmpId());
								UnionOperations.saveUnionCharge(uc);
							}
							addPaymentOption(se);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
					case "Salary + Commission":
						CommEmp ce = new CommEmp(1, firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(), 25, Double.parseDouble(payrateTextField.getText()));
						try {
							ce = EmployeeOperations.saveCommEmployee(ce);
							if(checkUnionEmployee(ce)) {
								UnionCharge uc = new UnionCharge(1, "Union", 25, ce.getEmpId());
								UnionOperations.saveUnionCharge(uc);
							}
							addPaymentOption(ce);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						break;
					}
					break;
				case "CLEAR":
					for(Component inputField : addEmployeeInputFields) {
						if(inputField.getClass().getName() == "javax.swing.JTextField") {
							((JTextComponent) inputField).setText("");
						} else if (inputField.getClass().getName() == "javax.swing.JCheckBox") {
							((JCheckBox) inputField).setSelected(false);
						} else if (inputField.getClass().getName() == "javax.swing.JComboBox"){
							((JComboBox<String>) inputField).setSelectedItem("");
						}
					}
					break;
				}
			}
		};
		workTypeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (workTypeComboBox.getSelectedItem().toString()) {
				case "":
					payrateLabel.setText("Payrate");
					unfocusCommissionRateLabel();
					break;
				case "Hourly":
					payrateLabel.setText("Hourly Rate");
					unfocusCommissionRateLabel();
					break;
				case "Salary":
					payrateLabel.setText("Salary Rate");
					unfocusCommissionRateLabel();
					break;
				case "Salary + Commission":
					payrateLabel.setText("Salary Rate");
					focusCommissionRateLabel();
					break;
				}
			}
		};
		paymentTypeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch (paymentMethodComboBox.getSelectedItem().toString()) {
				case "":
					unfocusBankDetails();
					break;
				case "Mailed In Post":
					unfocusBankDetails();
					break;
				case "Pickup By Paymaster":
					unfocusBankDetails();
					break;
				case "Bank Deposit":
					focusBankDetails();
					break;
				}
			}
		};
		addEmployeeLabels = new JLabel[11];
		addEmployeeInputFields = new Component[11];
		addEmployeeButtons = new JButton[2];
		yStart = 45;
		
		titleLabel = new JLabel("ADD EMPLOYEE");
		titleLabel.setFont(titleAndButtonFont);
		titleLabel.setForeground(Color.BLUE);
		add(titleLabel);
		
		size = titleLabel.getPreferredSize();
		titleLabel.setBounds(275, 10, size.width, size.height);
		
		firstNameLabel = new JLabel("First Name");
		lastNameLabel = new JLabel("Last Name");
		addressLabel = new JLabel("Address");
		accountNumberLabel = new JLabel("Account Number");
		bsbNumberLabel = new JLabel("BSB");
		bankLabel = new JLabel("Bank");
		workTypeLabel = new JLabel("Work Type");
		payrateLabel = new JLabel("Payrate");
		commissionRateLabel = new JLabel("Commission Rate");
		paymentMethodLabel = new JLabel("Payment Method");
		unionLabel = new JLabel("Union");
		
		addEmployeeLabels[0] = firstNameLabel;
		addEmployeeLabels[1] = lastNameLabel;
		addEmployeeLabels[2] = addressLabel;
		addEmployeeLabels[3] = accountNumberLabel;
		addEmployeeLabels[4] = bsbNumberLabel;
		addEmployeeLabels[5] = bankLabel;
		addEmployeeLabels[6] = workTypeLabel;
		addEmployeeLabels[7] = payrateLabel;
		addEmployeeLabels[8] = commissionRateLabel;
		addEmployeeLabels[9] = paymentMethodLabel;
		addEmployeeLabels[10] = unionLabel;
		
		firstNameTextField = new JTextField();
		lastNameTextField = new JTextField();
		addressTextField = new JTextField();
		accountNumberTextField = new JTextField();
		bsbNumberTextField = new JTextField();
		bankTextField = new JTextField();
		workTypeComboBox = new JComboBox<>();
		workTypeComboBox.addItem("");
		workTypeComboBox.addItem("Hourly");
		workTypeComboBox.addItem("Salary");
		workTypeComboBox.addItem("Salary + Commission");
		payrateTextField = new JTextField();
		commissionRateTextField = new JTextField();
		paymentMethodComboBox = new JComboBox<>();
		paymentMethodComboBox.addItem("");
		paymentMethodComboBox.addItem("Mailed In Post");
		paymentMethodComboBox.addItem("Pickup By Paymaster");
		paymentMethodComboBox.addItem("Bank Deposit");
		unionTypeCheckBox = new JCheckBox();
		
		addEmployeeInputFields[0] = firstNameTextField;
		addEmployeeInputFields[1] = lastNameTextField;
		addEmployeeInputFields[2] = addressTextField;
		addEmployeeInputFields[3] = accountNumberTextField;
		addEmployeeInputFields[4] = bsbNumberTextField;
		addEmployeeInputFields[5] = bankTextField;
		addEmployeeInputFields[6] = workTypeComboBox;
		addEmployeeInputFields[7] = payrateTextField;
		addEmployeeInputFields[8] = commissionRateTextField;
		addEmployeeInputFields[9] = paymentMethodComboBox;
		addEmployeeInputFields[10] = unionTypeCheckBox;
		
		workTypeComboBox.addActionListener(workTypeListener);
		paymentMethodComboBox.addActionListener(paymentTypeListener);
		
		for(JLabel label : addEmployeeLabels) {
			label.setFocusable(false);
			label.setFont(labelFont);
			label.setForeground(Color.BLUE);
			size = label.getPreferredSize();
			label.setBounds(30, yStart, 200, size.height);
			add(label);
			yStart += 27;
		}
		
		yStart = 50;
		for(Component inputField : addEmployeeInputFields) {
			inputField.setFont(textFieldFont);
			inputField.setForeground(Color.BLUE);
			inputField.setBounds(220, yStart, 360, 20);
			add(inputField);
			yStart += 27;
		}
		
		unfocusCommissionRateLabel();
		unfocusBankDetails();
		
		JButton clearButton = new JButton("CLEAR");
		JButton submitButton = new JButton("SUBMIT");
		
		addEmployeeButtons[0] = clearButton;
		addEmployeeButtons[1] = submitButton;
		
		submitButton.setBounds(400, 460, 125, 25);
		clearButton.setBounds(200, 460, 125, 25);
		
		for(JButton button : addEmployeeButtons) {
			button.setBackground(Color.BLUE);
			button.setFont(titleAndButtonFont);
			button.setForeground(Color.WHITE);
			button.addActionListener(addEmployeeButtonListener);
			add(button);
		}
	}
	
	public void drawDeleteEmployee() {
		searchResultsBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE);
		searchEmployeeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchResultsButton != null)
					remove(searchResultsButton);
				try {
					emp = EmployeeOperations.findEmployeeById(Integer.parseInt(e.getActionCommand()));
					if(emp != null) {
						searchResultsButton = new JButton(e.getActionCommand());
						searchResultsButton.setFont(titleAndButtonFont);
						searchResultsButton.setForeground(Color.BLUE);
						searchResultsButton.setSize(searchResultsButton.getPreferredSize());
						size = searchResultsButton.getPreferredSize();
						searchResultsButton.setBounds(30, 125, size.width + 10, size.height);
						searchResultsButton.setBorder(searchResultsBorder);
						searchResultsButton.addActionListener(searchEmployeeResultsListener);
						add(searchResultsButton);
						validate();
						repaint();
					} else
						JOptionPane.showMessageDialog(null, "Cannot find that employee in the database");
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				searchEmployeeTextField.setText("");
			}
		};
		searchEmployeeResultsListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Delete Employee?", "Delete?", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					remove(searchResultsButton);
					validate();
					repaint();
					try {
						EmployeeOperations.deleteEmp(68);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Employee Deleted!");
				}
			}
		};
		
		titleLabel = new JLabel("DELETE EMPLOYEE");
		titleLabel.setFont(titleAndButtonFont);
		titleLabel.setForeground(Color.BLUE);
		size = titleLabel.getPreferredSize();
		titleLabel.setBounds(275, 10, size.width, size.height);
		add(titleLabel);
		
		searchEmployeesLabel = new JLabel("Enter Employee ID to search for:");
		searchEmployeesLabel.setFont(labelFont);
		searchEmployeesLabel.setForeground(Color.BLUE);
		size = searchEmployeesLabel.getPreferredSize();
		searchEmployeesLabel.setBounds(30, 70, 250, size.height);
		add(searchEmployeesLabel);
		
		searchEmployeeTextField = new JTextField();
		searchEmployeeTextField.setFont(textFieldFont);
		searchEmployeeTextField.setForeground(Color.BLUE);
		searchEmployeeTextField.setBounds(300, 75, 280, 20);
		searchEmployeeTextField.addActionListener(searchEmployeeListener);
		add(searchEmployeeTextField);
	}
	
	public void drawPostTimeCard() {
		searchResultsBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE);
		searchEmployeeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchResultsButton != null)
					remove(searchResultsButton);
				try {
					emp = EmployeeOperations.findEmployeeById(Integer.parseInt(e.getActionCommand()));
					if(emp != null) {
						searchResultsButton = new JButton(e.getActionCommand());
						searchResultsButton.setFont(titleAndButtonFont);
						searchResultsButton.setForeground(Color.BLUE);
						searchResultsButton.setSize(searchResultsButton.getPreferredSize());
						size = searchResultsButton.getPreferredSize();
						searchResultsButton.setBounds(30, 125, size.width + 10, size.height);
						searchResultsButton.setBorder(searchResultsBorder);
						searchResultsButton.addActionListener(searchEmployeeResultsListener);
						add(searchResultsButton);
						validate();
						repaint();
					} else
						JOptionPane.showMessageDialog(null, "Cannot find that employee in the database");
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				searchEmployeeTextField.setText("");
			}
		};
		searchEmployeeResultsListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterHoursLabel = new JLabel("Enter hours worked today:");
				enterHoursLabel.setFont(labelFont);
				enterHoursLabel.setForeground(Color.BLUE);
				size = enterHoursLabel.getPreferredSize();
				enterHoursLabel.setBounds(30, 180, size.width, size.height);
				
				enterHoursTextField = new JTextField();
				enterHoursTextField.setFont(textFieldFont);
				enterHoursTextField.setForeground(Color.BLUE);
				enterHoursTextField.setBounds(230, 185, 100, 20);
				
				enterHoursButton = new JButton();
				enterHoursButton.setText("SUBMIT");
				enterHoursButton.setBackground(Color.BLUE);
				enterHoursButton.setFont(titleAndButtonFont);
				enterHoursButton.setForeground(Color.WHITE);
				enterHoursButton.setBounds(200, 220, 125, 25);
				enterHoursButton.addActionListener(postTimeCardListener);
				
				clearHoursButton = new JButton();
				clearHoursButton.setText("CLEAR");
				clearHoursButton.setBackground(Color.BLUE);
				clearHoursButton.setFont(titleAndButtonFont);
				clearHoursButton.setForeground(Color.WHITE);
				clearHoursButton.setBounds(30, 220, 125, 25);
				clearHoursButton.addActionListener(postTimeCardListener);

				add(enterHoursButton);
				add(clearHoursButton);
				add(enterHoursLabel);
				add(enterHoursTextField);
				
				validate();
				repaint();
			}
		};
		postTimeCardListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "SUBMIT":
					TimeSheetEntry tse = new TimeSheetEntry(1, DateUtil.todaysDate().toString(), Double.parseDouble(enterHoursTextField.getText()), emp.getEmpId(), "F");
					try {
						TimesheetOperations.saveTimeSheetEntry(tse);
						JOptionPane.showMessageDialog(null, "Hours recorded");
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error! Hours not recorded");
					}
					break;
				case "CLEAR":
					enterHoursTextField.setText("");
					break;
				}
			}
		};
		
		titleLabel = new JLabel("POST TIME CARD");
		titleLabel.setFont(titleAndButtonFont);
		titleLabel.setForeground(Color.BLUE);
		size = titleLabel.getPreferredSize();
		titleLabel.setBounds(275, 10, size.width, size.height);
		add(titleLabel);
		
		searchEmployeesLabel = new JLabel("Enter Employee ID to search for:");
		searchEmployeesLabel.setFont(labelFont);
		searchEmployeesLabel.setForeground(Color.BLUE);
		size = searchEmployeesLabel.getPreferredSize();
		searchEmployeesLabel.setBounds(30, 70, 250, size.height);
		add(searchEmployeesLabel);
		
		searchEmployeeTextField = new JTextField();
		searchEmployeeTextField.setFont(textFieldFont);
		searchEmployeeTextField.setForeground(Color.BLUE);
		searchEmployeeTextField.setBounds(300, 75, 280, 20);
		searchEmployeeTextField.addActionListener(searchEmployeeListener);
		add(searchEmployeeTextField);
	}
	
	public void drawPostSalesReceipt() {
		searchResultsBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE);
		searchEmployeeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchResultsButton != null)
					remove(searchResultsButton);
				try {
					emp = EmployeeOperations.findEmployeeById(Integer.parseInt(e.getActionCommand()));
					if(emp != null) {
						searchResultsButton = new JButton(e.getActionCommand());
						searchResultsButton.setFont(titleAndButtonFont);
						searchResultsButton.setForeground(Color.BLUE);
						searchResultsButton.setSize(searchResultsButton.getPreferredSize());
						size = searchResultsButton.getPreferredSize();
						searchResultsButton.setBounds(30, 125, size.width + 10, size.height);
						searchResultsButton.setBorder(searchResultsBorder);
						searchResultsButton.addActionListener(searchEmployeeResultsListener);
						add(searchResultsButton);
						validate();
						repaint();
					} else
						JOptionPane.showMessageDialog(null, "Cannot find that employee in the database");
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				
				searchEmployeeTextField.setText("");
			}
		};
		searchEmployeeResultsListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterSalesLabel = new JLabel("Enter total sales for today:");
				enterSalesLabel.setFont(labelFont);
				enterSalesLabel.setForeground(Color.BLUE);
				size = enterSalesLabel.getPreferredSize();
				enterSalesLabel.setBounds(30, 180, size.width, size.height);
				
				enterSalesTextField = new JTextField();
				enterSalesTextField.setFont(textFieldFont);
				enterSalesTextField.setForeground(Color.BLUE);
				enterSalesTextField.setBounds(230, 185, 100, 20);
				
				enterSalesButton = new JButton();
				enterSalesButton.setText("SUBMIT");
				enterSalesButton.setBackground(Color.BLUE);
				enterSalesButton.setFont(titleAndButtonFont);
				enterSalesButton.setForeground(Color.WHITE);
				enterSalesButton.setBounds(200, 220, 125, 25);
				enterSalesButton.addActionListener(postSalesReceiptListener);
				
				clearSalesButton = new JButton();
				clearSalesButton.setText("CLEAR");
				clearSalesButton.setBackground(Color.BLUE);
				clearSalesButton.setFont(titleAndButtonFont);
				clearSalesButton.setForeground(Color.WHITE);
				clearSalesButton.setBounds(30, 220, 125, 25);
				clearSalesButton.addActionListener(postSalesReceiptListener);

				add(enterSalesButton);
				add(clearSalesButton);
				add(enterSalesLabel);
				add(enterSalesTextField);
				
				validate();
				repaint();
			}
		};
		postSalesReceiptListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "SUBMIT":
					SalesReciept sr = new SalesReciept(1, Double.parseDouble(enterSalesTextField.getText()), DateUtil.todaysDate().toString(), emp.getEmpId(), "F");
					try {
						SalesRecieptOperations.saveSalesRecipt(sr);
						JOptionPane.showMessageDialog(null, "Sales recorded");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Error! Sales not recorded");
						e1.printStackTrace();
					}
					break;
				case "CLEAR":
					enterSalesTextField.setText("");
					break;
				}
			}
		};
		
		titleLabel = new JLabel("POST SALES RECEIPT");
		titleLabel.setFont(titleAndButtonFont);
		titleLabel.setForeground(Color.BLUE);
		size = titleLabel.getPreferredSize();
		titleLabel.setBounds(275, 10, size.width, size.height);
		add(titleLabel);
		
		searchEmployeesLabel = new JLabel("Enter Employee ID to search for:");
		searchEmployeesLabel.setFont(labelFont);
		searchEmployeesLabel.setForeground(Color.BLUE);
		size = searchEmployeesLabel.getPreferredSize();
		searchEmployeesLabel.setBounds(30, 70, 250, size.height);
		add(searchEmployeesLabel);
		
		searchEmployeeTextField = new JTextField();
		searchEmployeeTextField.setFont(textFieldFont);
		searchEmployeeTextField.setForeground(Color.BLUE);
		searchEmployeeTextField.setBounds(300, 75, 280, 20);
		searchEmployeeTextField.addActionListener(searchEmployeeListener);
		add(searchEmployeeTextField);
	}
	
	public void drawPostUnionServiceCharge() {
		searchResultsBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE);
		searchEmployeeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchResultsButton != null)
					remove(searchResultsButton);
				try {
					emp = EmployeeOperations.findEmployeeById(Integer.parseInt(e.getActionCommand()));
					if(emp != null) {
						searchResultsButton = new JButton(e.getActionCommand());
						searchResultsButton.setFont(titleAndButtonFont);
						searchResultsButton.setForeground(Color.BLUE);
						searchResultsButton.setSize(searchResultsButton.getPreferredSize());
						size = searchResultsButton.getPreferredSize();
						searchResultsButton.setBounds(30, 125, size.width + 10, size.height);
						searchResultsButton.setBorder(searchResultsBorder);
						searchResultsButton.addActionListener(searchEmployeeResultsListener);
						add(searchResultsButton);
						validate();
						repaint();
					} else
						JOptionPane.showMessageDialog(null, "Cannot find that employee in the database");
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				
				searchEmployeeTextField.setText("");
			}
		};
		searchEmployeeResultsListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterUnionChargeLabel = new JLabel("Enter union charge:");
				enterUnionChargeLabel.setFont(labelFont);
				enterUnionChargeLabel.setForeground(Color.BLUE);
				size = enterUnionChargeLabel.getPreferredSize();
				enterUnionChargeLabel.setBounds(30, 180, size.width, size.height);
				
				enterUnionChargeTextField = new JTextField();
				enterUnionChargeTextField.setFont(textFieldFont);
				enterUnionChargeTextField.setForeground(Color.BLUE);
				enterUnionChargeTextField.setBounds(230, 185, 100, 20);
				
				enterUnionChargeButton = new JButton();
				enterUnionChargeButton.setText("SUBMIT");
				enterUnionChargeButton.setBackground(Color.BLUE);
				enterUnionChargeButton.setFont(titleAndButtonFont);
				enterUnionChargeButton.setForeground(Color.WHITE);
				enterUnionChargeButton.setBounds(200, 220, 125, 25);
				enterUnionChargeButton.addActionListener(postUnionChargeListener);
				
				clearUnionChargeButton = new JButton();
				clearUnionChargeButton.setText("CLEAR");
				clearUnionChargeButton.setBackground(Color.BLUE);
				clearUnionChargeButton.setFont(titleAndButtonFont);
				clearUnionChargeButton.setForeground(Color.WHITE);
				clearUnionChargeButton.setBounds(30, 220, 125, 25);
				clearUnionChargeButton.addActionListener(postUnionChargeListener);

				add(enterUnionChargeButton);
				add(clearUnionChargeButton);
				add(enterUnionChargeLabel);
				add(enterUnionChargeTextField);
				
				validate();
				repaint();
			}
		};
		postUnionChargeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "SUBMIT":
					UnionCharge uc = new UnionCharge(1, "Union", Double.parseDouble(enterUnionChargeTextField.getText()), emp.getEmpId());
					try {
						UnionOperations.saveUnionCharge(uc);
						JOptionPane.showMessageDialog(null, "Union Charge recorded");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Error! Union Charge not recorded");
						e1.printStackTrace();
					}
					break;
				case "CLEAR":
					enterUnionChargeTextField.setText("");
					break;
				}
			}
		};
		
		titleLabel = new JLabel("POST UNION CHARGE");
		titleLabel.setFont(titleAndButtonFont);
		titleLabel.setForeground(Color.BLUE);
		size = titleLabel.getPreferredSize();
		titleLabel.setBounds(275, 10, size.width, size.height);
		add(titleLabel);
		
		searchEmployeesLabel = new JLabel("Enter Employee ID to search for:");
		searchEmployeesLabel.setFont(labelFont);
		searchEmployeesLabel.setForeground(Color.BLUE);
		size = searchEmployeesLabel.getPreferredSize();
		searchEmployeesLabel.setBounds(30, 70, 250, size.height);
		add(searchEmployeesLabel);
		
		searchEmployeeTextField = new JTextField();
		searchEmployeeTextField.setFont(textFieldFont);
		searchEmployeeTextField.setForeground(Color.BLUE);
		searchEmployeeTextField.setBounds(300, 75, 280, 20);
		searchEmployeeTextField.addActionListener(searchEmployeeListener);
		add(searchEmployeeTextField);
	}
	
	public void drawChangeEmployee() {
		yStart = 45;
		searchResultsBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE);
		searchEmployeeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					emp = EmployeeOperations.findEmployeeById(Integer.parseInt(e.getActionCommand()));
					if(emp != null) {
						empid = emp.getEmpId();
						firstNameLabel = new JLabel("First Name");
						lastNameLabel = new JLabel("Last Name");
						addressLabel = new JLabel("Address");
						accountNumberLabel = new JLabel("Account Number");
						bsbNumberLabel = new JLabel("BSB");
						bankLabel = new JLabel("Bank");
						workTypeLabel = new JLabel("Work Type");
						payrateLabel = new JLabel("Payrate");
						commissionRateLabel = new JLabel("Commission Rate");
						paymentMethodLabel = new JLabel("Payment Method");
						unionLabel = new JLabel("Union");
						
						changeEmployeeLabels[0] = firstNameLabel;
						changeEmployeeLabels[1] = lastNameLabel;
						changeEmployeeLabels[2] = addressLabel;
						changeEmployeeLabels[3] = accountNumberLabel;
						changeEmployeeLabels[4] = bsbNumberLabel;
						changeEmployeeLabels[5] = bankLabel;
						changeEmployeeLabels[6] = workTypeLabel;
						changeEmployeeLabels[7] = payrateLabel;
						changeEmployeeLabels[8] = commissionRateLabel;
						changeEmployeeLabels[9] = paymentMethodLabel;
						changeEmployeeLabels[10] = unionLabel;
						
						firstNameTextField = new JTextField();
						lastNameTextField = new JTextField();
						addressTextField = new JTextField();
						accountNumberTextField = new JTextField();
						bsbNumberTextField = new JTextField();
						bankTextField = new JTextField();
						workTypeComboBox = new JComboBox<>();
						workTypeComboBox.addItem("");
						workTypeComboBox.addItem("Hourly");
						workTypeComboBox.addItem("Salary");
						workTypeComboBox.addItem("Salary + Commission");
						payrateTextField = new JTextField();
						commissionRateTextField = new JTextField();
						paymentMethodComboBox = new JComboBox<>();
						paymentMethodComboBox.addItem("");
						paymentMethodComboBox.addItem("Mailed In Post");
						paymentMethodComboBox.addItem("Pickup By Paymaster");
						paymentMethodComboBox.addItem("Bank Deposit");
						unionTypeCheckBox = new JCheckBox();
						
						firstNameTextField.setText(emp.getEmpFname());
						lastNameTextField.setText(emp.getEmpLname());
						addressTextField.setText(emp.getEmpAddress());
						
						switch (emp.getEmpType()) {
						case "H":
							for (HourlyEmp hourly : EmployeeOperations.findHourlyEmployees()) {
								if(emp.getEmpId() == hourly.getEmpId()){
									HourlyEmp he = hourly;
									workTypeComboBox.setSelectedItem("Hourly");
									payrateLabel.setText("Hourly Rate");
									payrateTextField.setText(String.valueOf(he.getHourlyRate()));
									unfocusCommissionRateLabel();
									break;
								}
							}
							break;
						case "S":
							for (SalaryEmp salary : EmployeeOperations.findSalaryEmployees()) {
								if(emp.getEmpId() == salary.getEmpId()){
									SalaryEmp se = salary;
									workTypeComboBox.setSelectedItem("Salary");
									payrateLabel.setText("Salary Rate");
									payrateTextField.setText(String.valueOf(se.getSalary()));
									unfocusCommissionRateLabel();
									break;
								}
							}
							break;
						case "C":
							for (CommEmp commission : EmployeeOperations.findCommissionEmployees()) {
								if(emp.getEmpId() == commission.getEmpId()){
									CommEmp ce = commission;
									workTypeComboBox.setSelectedItem("Salary + Commission");
									payrateTextField.setText(String.valueOf(ce.getCommSalary()));
									payrateLabel.setText("Salary Rate");
									focusCommissionRateLabel();
									commissionRateTextField.setText(String.valueOf(ce.getCommRate()));
									break;
								}
							}
							break;
						}
						
						for(DirectDepositMethod ddm : PaymentOperations.findAllDirectMethods()) {
							if(emp.getEmpId() == ddm.getEmpId()) {
								paymentMethodComboBox.setSelectedItem("Bank Deposit");
								focusBankDetails();
								bankTextField.setText(ddm.getDirectPayBankName());
								accountNumberTextField.setText(ddm.getDirectPayAcctNum());
								bsbNumberTextField.setText(ddm.getDirectPayBSB());
								break;
							}
						}
						
						for(MailMethod mm : PaymentOperations.findAllMailMethods()) {
							if(emp.getEmpId() == mm.getEmpId()) {
								paymentMethodComboBox.setSelectedItem("Mailed In Post");
								unfocusBankDetails();
								break;
							}
						}
						
						for(HoldMethod hm : PaymentOperations.findAllHoldMethods()) {
							if(emp.getEmpId() == hm.getEmpId()) {
								paymentMethodComboBox.setSelectedItem("Pickup By Paymaster");
								unfocusBankDetails();
								break;
							}
						}
						
						for(UnionCharge uc : UnionOperations.findAllUnionCharges()) {
							if(emp.getEmpId() == uc.getEmpId()) {
								unionTypeCheckBox.setSelected(true);
							}
						}
						
						changeEmployeeInputFields[0] = firstNameTextField;
						changeEmployeeInputFields[1] = lastNameTextField;
						changeEmployeeInputFields[2] = addressTextField;
						changeEmployeeInputFields[3] = accountNumberTextField;
						changeEmployeeInputFields[4] = bsbNumberTextField;
						changeEmployeeInputFields[5] = bankTextField;
						changeEmployeeInputFields[6] = workTypeComboBox;
						changeEmployeeInputFields[7] = payrateTextField;
						changeEmployeeInputFields[8] = commissionRateTextField;
						changeEmployeeInputFields[9] = paymentMethodComboBox;
						changeEmployeeInputFields[10] = unionTypeCheckBox;
						
						workTypeComboBox.addActionListener(workTypeListener);
						paymentMethodComboBox.addActionListener(paymentTypeListener);
						
						for(JLabel label : changeEmployeeLabels) {
							label.setFocusable(false);
							label.setFont(labelFont);
							label.setForeground(Color.BLUE);
							size = label.getPreferredSize();
							label.setBounds(30, yStart, 200, size.height);
							add(label);
							yStart += 27;
						}
						
						yStart = 50;
						for(Component inputField : changeEmployeeInputFields) {
							inputField.setFont(textFieldFont);
							inputField.setForeground(Color.BLUE);
							inputField.setBounds(220, yStart, 360, 20);
							add(inputField);
							yStart += 27;
						}
						
						JButton clearButton = new JButton("CLEAR");
						JButton submitButton = new JButton("SUBMIT");
						
						changeEmployeeButtons[0] = clearButton;
						changeEmployeeButtons[1] = submitButton;
						
						submitButton.setBounds(400, 460, 125, 25);
						clearButton.setBounds(200, 460, 125, 25);
						
						for(JButton button : changeEmployeeButtons) {
							button.setBackground(Color.BLUE);
							button.setFont(titleAndButtonFont);
							button.setForeground(Color.WHITE);
							button.addActionListener(changeEmployeeButtonListener);
							add(button);
						}
						
						remove(searchEmployeeTextField);
						remove(searchEmployeesLabel);
						validate();
						repaint();
					} else
						JOptionPane.showMessageDialog(null, "Cannot find that employee in the database");
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Cannot find that employee in the database");
				}
				
			}
		};
		changeEmployeeButtonListener = new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "SUBMIT":
					try {
						switch (workTypeComboBox.getSelectedItem().toString()) {
						case "Hourly":
							HourlyEmp he = new HourlyEmp(empid, firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(), Double.parseDouble(payrateTextField.getText()));
							EmployeeOperations.updateHourly(he);
							break;
						case "Salary":
							SalaryEmp se = new SalaryEmp(empid, firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(), Double.parseDouble(payrateTextField.getText()));
							EmployeeOperations.updateSalaryEmp(se);
							break;
						case "Salary + Commission":
							CommEmp ce = new CommEmp(empid, firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(), 25, Double.parseDouble(payrateTextField.getText()));
							EmployeeOperations.updateCommEmp(ce);
							break;
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					break;
				case "CLEAR":
					for(Component inputField : changeEmployeeInputFields) {
						if(inputField.getClass().getName() == "javax.swing.JTextField") {
							((JTextComponent) inputField).setText("");
						} else if (inputField.getClass().getName() == "javax.swing.JCheckBox") {
							((JCheckBox) inputField).setSelected(false);
						} else if (inputField.getClass().getName() == "javax.swing.JComboBox"){
							((JComboBox<String>) inputField).setSelectedItem("");
						}
					}
					break;
				}
			}
		};
		workTypeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (workTypeComboBox.getSelectedItem().toString()) {
				case "":
					payrateLabel.setText("Payrate");
					unfocusCommissionRateLabel();
					break;
				case "Hourly":
					payrateLabel.setText("Hourly Rate");
					unfocusCommissionRateLabel();
					break;
				case "Salary":
					payrateLabel.setText("Salary Rate");
					unfocusCommissionRateLabel();
					break;
				case "Salary + Commission":
					payrateLabel.setText("Salary Rate");
					focusCommissionRateLabel();
					break;
				}
			}
		};
		paymentTypeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch (paymentMethodComboBox.getSelectedItem().toString()) {
				case "":
					unfocusBankDetails();
					break;
				case "Mailed In Post":
					unfocusBankDetails();
					break;
				case "Pickup By Paymaster":
					unfocusBankDetails();
					break;
				case "Bank Deposit":
					focusBankDetails();
					break;
				}
			}
		};
		changeEmployeeLabels = new JLabel[11];
		changeEmployeeInputFields = new Component[11];
		changeEmployeeButtons = new JButton[2];
		
		titleLabel = new JLabel("CHANGE EMPLOYEE DETAILS");
		titleLabel.setFont(titleAndButtonFont);
		titleLabel.setForeground(Color.BLUE);
		add(titleLabel);
		
		size = titleLabel.getPreferredSize();
		titleLabel.setBounds(250, 10, size.width, size.height);
		
		searchEmployeesLabel = new JLabel("Enter Employee ID to search for:");
		searchEmployeesLabel.setFont(labelFont);
		searchEmployeesLabel.setForeground(Color.BLUE);
		size = searchEmployeesLabel.getPreferredSize();
		searchEmployeesLabel.setBounds(30, 70, 250, size.height);
		add(searchEmployeesLabel);
		
		searchEmployeeTextField = new JTextField();
		searchEmployeeTextField.setFont(textFieldFont);
		searchEmployeeTextField.setForeground(Color.BLUE);
		searchEmployeeTextField.setBounds(300, 75, 280, 20);
		searchEmployeeTextField.addActionListener(searchEmployeeListener);
		add(searchEmployeeTextField);
	}
	
	public void drawRunPayroll() {
		runPayrollListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CalculatePayroll.CalcPay();
					JOptionPane.showMessageDialog(null, "Payroll has been ran!");
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error! Payroll has not been ran");
				}
			}
		};
		titleLabel = new JLabel("RUN PAYROLL");
		titleLabel.setFont(titleAndButtonFont);
		titleLabel.setForeground(Color.BLUE);
		size = titleLabel.getPreferredSize();
		titleLabel.setBounds(275, 10, size.width, size.height);
		add(titleLabel);
		
		runPayrollButton = new JButton();
		runPayrollButton.setText("RUN");
		runPayrollButton.setFont(new Font(Font.SERIF, Font.PLAIN, 100));
		runPayrollButton.setBackground(Color.BLUE);
		runPayrollButton.setForeground(Color.WHITE);
		runPayrollButton.setBounds(150, 50, 400, 400);
		runPayrollButton.addActionListener(runPayrollListener);
		add(runPayrollButton);
	}
	
	private void focusCommissionRateLabel() {
		commissionRateLabel.setFocusable(true);
		commissionRateLabel.setForeground(Color.BLUE);
		commissionRateTextField.setFocusable(true);
		commissionRateTextField.setBackground(Color.WHITE);
		commissionRateTextField.setForeground(Color.BLUE);
	}
	
	private void unfocusCommissionRateLabel() {
		commissionRateLabel.setForeground(Color.GRAY);
		commissionRateLabel.setFocusable(false);
		commissionRateTextField.setBackground(Color.GRAY);
		commissionRateTextField.setFocusable(false);
		commissionRateTextField.setForeground(Color.WHITE);
	}
	
	private void focusBankDetails() {
		accountNumberLabel.setFocusable(true);
		accountNumberLabel.setForeground(Color.BLUE);
		accountNumberTextField.setFocusable(true);
		accountNumberTextField.setBackground(Color.WHITE);
		accountNumberTextField.setForeground(Color.BLUE);
		
		bsbNumberLabel.setFocusable(true);
		bsbNumberLabel.setForeground(Color.BLUE);
		bsbNumberTextField.setFocusable(true);
		bsbNumberTextField.setBackground(Color.WHITE);
		bsbNumberTextField.setForeground(Color.BLUE);
		
		bankLabel.setFocusable(true);
		bankLabel.setForeground(Color.BLUE);
		bankTextField.setFocusable(true);
		bankTextField.setBackground(Color.WHITE);
		bankTextField.setForeground(Color.BLUE);
	}
	
	private void unfocusBankDetails() {
		accountNumberLabel.setFocusable(false);
		accountNumberLabel.setForeground(Color.GRAY);
		accountNumberTextField.setFocusable(false);
		accountNumberTextField.setBackground(Color.GRAY);
		accountNumberTextField.setForeground(Color.WHITE);
		
		bsbNumberLabel.setFocusable(false);
		bsbNumberLabel.setForeground(Color.GRAY);
		bsbNumberTextField.setFocusable(false);
		bsbNumberTextField.setBackground(Color.GRAY);
		bsbNumberTextField.setForeground(Color.WHITE);
		
		bankLabel.setFocusable(false);
		bankLabel.setForeground(Color.GRAY);
		bankTextField.setFocusable(false);
		bankTextField.setBackground(Color.GRAY);
		bankTextField.setForeground(Color.WHITE);
	}

	private boolean checkUnionEmployee(Employee emp) {
		return unionTypeCheckBox.isSelected() ? true : false;
	}

	private String checkPaymentOption() {
		return paymentMethodComboBox.getSelectedItem().toString();
	}
	
	private void addPaymentOption(Employee emp) {
		try {
		switch (checkPaymentOption()) {
			case "Mailed In Post":
				MailMethod mm = new MailMethod(1, emp.getEmpAddress(), emp.getEmpId());
				PaymentOperations.addMailMethod(mm);
				break;
			case "Pickup By Paymaster":
				HoldMethod hm = new HoldMethod(1, emp.getEmpId());
				PaymentOperations.addHoldMethod(hm);
				break;
			case "Bank Deposit":
				DirectDepositMethod ddm = new DirectDepositMethod(1, bankTextField.getText(), bsbNumberTextField.getText(), accountNumberTextField.getText(), emp.getEmpId());
				PaymentOperations.addDirectMethod(ddm);
				break;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
