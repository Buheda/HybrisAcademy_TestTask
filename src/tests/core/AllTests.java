package tests.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Util_TestSuite.class, DAO_TestSuite.class, Commands_TestSuite.class})
public class AllTests {

}
