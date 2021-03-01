package JUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import selenium.showAccountTest.InsertTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AccountDAOTest.class,
	InsertTest.class
})
public class TestSuite {

}
