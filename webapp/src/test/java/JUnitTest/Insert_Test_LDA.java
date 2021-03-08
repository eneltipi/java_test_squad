package JUnitTest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import dao.AccountDAO;


@RunWith(Parameterized.class)
public class Insert_Test_LDA {
	private String email;
	private String password;
	private String username;
	private String fullname;
	private String address;
	private String phonenumber;
	private boolean expected;

	private AccountDAO dao;

	public Insert_Test_LDA(String email, String password, String username, String fullname, String address,
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
	public void checkInsert() {
		boolean result = dao.insertAccount(email, password, username, fullname, address, phonenumber);
		if(result == true)Assert.assertEquals("insert successed", expected, true);
		else Assert.assertEquals("insert failed some of parameters is null", expected, false);
	}

	@Parameterized.Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] 
		{ 
			{ null, "111","vien_SIEU_nhan","mai ky vien","long an","113", true }, 
			{ null, "111","vien_SIEU_nhan","mai ky vien","long an","113", false },
			{ "bao@gmail.com", "321","bao_bu","bao 8 nut","hcm","101", true },
			{ "bao8nut@gmail.com", "321","bao_bu","bao 8 nut","hcm","101", true }	
		});
	}

}
