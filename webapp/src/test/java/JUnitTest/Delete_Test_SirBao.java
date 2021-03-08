package JUnitTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import dao.AccountDAO;
import model.Account;

@RunWith(Parameterized.class)
public class Delete_Test_SirBao {
	private String email; boolean expected;
	private AccountDAO dao;

	public Delete_Test_SirBao(String email, boolean expected) {
		this.email = email;
		this.expected = expected;
	}

	@Before
	public void setup() {
		dao = new AccountDAO();
	}

	@Test
	public void checkDelete() {

		StringBuilder StrRs = new StringBuilder();
		
		StrRs.append(String.format("delete account %s |",email));
		
		boolean result = dao.deleteAccount(email);
	
		if(result) {
			StrRs.append(" this email: "+email+" deleted");
			Assert.assertEquals(StrRs.toString(), expected, true);
		}else {
			StrRs.append(" this email: "+email+" can't delete");
			Assert.assertEquals(StrRs.toString(), expected, false);
		}
	}

	@Parameterized.Parameters
	public static Collection input() {
		  return Arrays.asList(new Object [][] {
			  {"anh@gmail.com",true},
			  {"khoi@gmail.com",true},
			  {"khoi",true},
			  {"khoi",false}
			  });
	}

}
