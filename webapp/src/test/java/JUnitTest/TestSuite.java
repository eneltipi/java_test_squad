package JUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import selenium.showAccountTest.InsertTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AccountDAOTest.class,
	InsertTest.class,
	Delete_Test_SirBao.class,
	Update_Test_LDA.class,
})
public class TestSuite {

}
