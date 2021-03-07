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
	private String email;
	private String password;
	private boolean expected;
	private AccountDAO dao;

	public Delete_Test_SirBao(String email, String password, boolean expected) {
		this.email = email;
		this.password = password;
		this.expected = expected;
	}

	@Before
	public void setup() {
		dao = new AccountDAO();
	}

	@Test
	public void checkDeleteSuccess() {
		ArrayList<Account> all1 = (ArrayList<Account>) dao.getAllAccount();
		boolean result = dao.deleteAccount("vien@gmail.com");
		ArrayList<Account> all2 = (ArrayList<Account>) dao.getAllAccount();
		Assert.assertFalse(result == true && all1.size() == all2.size() + 1);
		System.out.println(all2.size() + " " + result + ": " + all1.size());
	}

	@Parameterized.Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] { { "hello", "123", true }, });
	}

}
