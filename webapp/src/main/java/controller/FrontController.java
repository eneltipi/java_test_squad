package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

import dao.AccountDAO;
import model.Account;

@Controller
public class FrontController {

	@GetMapping(value = "/")
	public String welcomePage() {
		return "dangnhap2";
	}
	
	@GetMapping(value = "/upimg")
	public String welcomePage2() {
		return "upimg";
	}

	@Autowired
	public AccountDAO dao;

	@Autowired
	protected JdbcTemplate jdbc;

	@RequestMapping(value = "hienthitaikhoan2")
	public String showAccount(ModelMap model) {
		String notice = (String) model.get("notice");// get attribute from redirect

		List<Account> accountList = dao.getAll();
		model.addAttribute("notice", new Gson().toJson(notice));
		model.addAttribute("accs", accountList);
		model.addAttribute("userListJSON", new Gson().toJson(accountList));
		returnColumnName(model);
		return "hienthitaikhoan2";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public RedirectView insert(ModelMap model, Account user, RedirectAttributes redirectAttributes) {
		try {
			String result = dao.insert(user);
			if (result.equalsIgnoreCase("success")) {
				redirectAttributes.addFlashAttribute("notice", "Insert successful");
			} else {
				redirectAttributes.addFlashAttribute("notice", result);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("notice", "Insert fail");
		}
		return new RedirectView("hienthitaikhoan2");
	}

//    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	@RequestMapping(value = "/updateCellValue", method = RequestMethod.POST)
	public @ResponseBody String Fetch(Account acc) {
		String json = null;
		try {
			dao.cellUpdate(acc.getUsername(), acc.getColumnName(), acc.getNewValue());
			json = new Gson().toJson(dao.getAll());
		} catch (Exception e) {
			System.out.println("Lá»—i á»Ÿ update: " + e);
		}
		return json;
	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public @ResponseBody String DeleteAccount(Account acc) {
		int result = 0;
		try {
			result = dao.removeUser(acc.getUsername());
		} catch (Exception e) {
			System.out.println("Lá»—i á»Ÿ delete: " + e);
		}
		return new Gson().toJson(result);
	}

	public void returnColumnName(ModelMap model) {
		ArrayList<String> columnNameList = new ArrayList<String>();
		try {
			Connection con = DataSourceUtils.getConnection(jdbc.getDataSource());
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM account");
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNameList.add(metadata.getColumnName(i));
			}
		} catch (Exception e) {
			columnNameList = null;
		}
		model.addAttribute("columnNameJSON", new Gson().toJson(columnNameList)); // JSON format
		model.addAttribute("columnNameJAVA", columnNameList);// JAVA
	}

}
