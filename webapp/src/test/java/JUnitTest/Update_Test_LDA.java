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
public class Update_Test_LDA {
	private String email;
	private String password;
	private boolean expected;
	private AccountDAO dao;

	public Update_Test_LDA(String email, String password, boolean expected) {
		this.email = email;
		this.password = password;
		this.expected = expected;
	}

	@Before
	public void setup() {
		dao = new AccountDAO();
	}

	@Test
	public void checkUpdateSuccess() {
		String email = "vien@gmail.com", username = "asdaf", fullname = "asfasg", address = "2355",
				phonenumber = "463226235";
		boolean result = dao.updateAccount(email, username, fullname, address, phonenumber);
		ArrayList<Account> all = (ArrayList<Account>) dao.getAllAccount();
		int idx = dao.findIndex(email);
		if (username == all.get(idx).getEmail() && fullname == all.get(idx).getFullname()
				&& address == all.get(idx).getAddress() && phonenumber == all.get(idx).getPhonenumber()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@Parameterized.Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] { { "hello", "123", true }, });
	}
}
