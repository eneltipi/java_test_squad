package JUnitTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.core.annotation.Order;

import dao.AccountDAO;
import model.Account;

@RunWith(Parameterized.class)
public class Update_Test_LDA {
	private String email;
	private String password;
	private String username;
	private String fullname;
	private String address;
	private String phonenumber;
	private boolean expected;

	private AccountDAO dao;

	public Update_Test_LDA(String email, String password, String username, String fullname, String address,
			String phonenumber, boolean expected) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.fullname = fullname;
		this.address = address;
		this.phonenumber = phonenumber;
		this.expected = expected;
	}

	@Before
	public void setup() {
		dao = new AccountDAO();
	}

	@Test
	public void checkUpdate() {
		StringBuilder StrRs = new StringBuilder();
		StrRs.append(String.format("update account %s with value userName: %s |",email,username));
	
		boolean result = dao.updateAccount(email,password, username, fullname, address, phonenumber);

		if(result == false) {
			StrRs.append(" cant find email: "+email+" in db");
			Assert.assertEquals(StrRs.toString(), expected, false);
			return;
		}
		List<Account> all = dao.getAllAccount();
		int idx = dao.findIndex(email);

		if (username == all.get(idx).getUsername().trim() && fullname == all.get(idx).getFullname().trim()
				&& address == all.get(idx).getAddress().trim() && phonenumber == all.get(idx).getPhonenumber().trim()) {
			StrRs.append(" this email: "+email+" is updated");
			Assert.assertEquals(StrRs.toString(), expected, true);
		} else {
			StrRs.append(" this email: "+email+" cant update");
			Assert.assertEquals(StrRs.toString(), expected, false);
		}
	}

	@Parameterized.Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] 
		{ 
			{ "vien@gmail.com", "111","vien_SIEU_nhan","mai ky vien","long an","113", true }, 
			{ "vien@gmai12313l.com", "111","vien_SIEU_nhan","mai ky vien","long an","113", true },
			{ "vien@gmai12313l.com", "111","vien_SIEU_nhan","mai ky vien","long an","113", false },
			{ "bao@gmail.com", "321","bao_bu","bao 8 nut","hcm","101", true },
			{ "bao8nut@gmail.com", "321","bao_bu","bao 8 nut","hcm","101", true }	
		});
	}

}
