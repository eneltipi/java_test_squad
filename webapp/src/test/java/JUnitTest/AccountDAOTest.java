package JUnitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dao.AccountDAO;


public class AccountDAOTest {

	private AccountDAO dao;
	
	@Before
	public void setup() {
		dao = new AccountDAO();
	}
	
	@Test
	public void checkLoginSuccess() {
		String email = "anh@gmail.com";
		String password = "123";
		assertTrue(dao.checkLogin(email, password));
	}
	
	
	  @Test 
	  public void checkLoginFalse() {
		  
	  assertFalse(dao.checkLogin("vien", "abc"));
	  
	  } 
	 
}
