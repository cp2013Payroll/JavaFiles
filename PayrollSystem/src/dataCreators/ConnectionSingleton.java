package dataCreators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
	public static final ConnectionSingleton INSTANCE = new ConnectionSingleton();
	private static final String PASSWORD = "password123";
	private static final String USERNAME = "payroll3_public";
	private static final String JDBC_MYSQL_LOCALHOST_PAYROLL = "jdbc:mysql://payrollsystem.info:3306/payroll3_database";
	
//	private static final String PASSWORD = "cp2013";
//	private static final String USERNAME = "payrolluser";
//	private static final String JDBC_MYSQL_LOCALHOST_PAYROLL = "jdbc:mysql://localhost/payroll";

	private ConnectionSingleton(){
		
	}
	public Connection create() throws SQLException{
		return DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_PAYROLL, USERNAME, PASSWORD);
	}
	
}