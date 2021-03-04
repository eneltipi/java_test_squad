package JUnitTest;





import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.springframework.core.annotation.Order;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dao.AccountDAO;

public class LoginTest {
	
	private AccountDAO dao;
	
	@BeforeClass
	public void setup() {
		dao = new AccountDAO();
	}
	
	@Test(description = "Check login ")
	@Order(1)
	public void LoginSuccess() {
		
		String email = "anh@gmail.com";
		String password = "123";
		String expected = "true";
		
		System.out.println("Email: "+email+", "+"password: "+ password + "expected: "+ expected);
		assertTrue(dao.checkLogin(email, password));
	}
	
	@Test(description = "Check login ")
	@Order(2)
	public void LoginFaile() {
		String email = "aaaaa";
		String password = "123";
		String expected = "false";
		
		System.out.println("Email: "+email+", "+"password: "+ password + "expected: "+ expected);
		assertFalse(dao.checkLogin(email, password));
	}

}
