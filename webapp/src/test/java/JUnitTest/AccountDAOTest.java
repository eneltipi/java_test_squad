package JUnitTest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import dao.AccountDAO;

@RunWith(Parameterized.class)
public class AccountDAOTest {
	private String email;
	private String password;
	private boolean expected;
	private AccountDAO dao;
	
	public AccountDAOTest(String email, String password, boolean expected) {
		this.email = email;
		this.password = password;
		this.expected = expected;
	}
	
	@Before
	public void setup() {
		dao = new AccountDAO();
	}
	
	@Test
	public void checkLogin() {
		System.out.println("email: " + email + ", " + "password: " + password + ", " + "expected: " + expected);
		assertEquals(expected,dao.checkLogin(email, password));
	}
		  
	  @Parameterized.Parameters
	  public static Collection input() {
		  return Arrays.asList(new Object [][] {
		  {"anh@gmail.com","123",true},
		  {"khoi@gmail.com","123",true},
		  {"khoi","123",false}
		  });
	  }
 	 
}
