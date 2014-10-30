package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddEmployeeTest.class, AddTimeSheetTest.class, RunPayrollTest.class,
		UpdateEmployeeTest.class })
public class AllPayrollSystemTests {

}
