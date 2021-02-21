package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

import authentication.helper.CustomUserDetails;
import authentication.jwt.JwtResponse;
import authentication.jwt.JwtUtils;
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

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@RequestMapping("JWTLogin")
	public RedirectView authenticateUser(Account user, HttpServletResponse response) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		Cookie cookie = new Cookie("jwt", jwt);
//		cookie.setHttpOnly(true);
		cookie.setMaxAge(1800);
		response.addCookie(cookie);

//		return ResponseEntity.ok(new JwtResponse(userDetails.getEmail(), jwt, roles));
		return new RedirectView("concac");
	}

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
	public @ResponseBody String insert(ModelMap model, Account user, RedirectAttributes redirectAttributes) {
		String response = null;

		try {
			String result = dao.insert(user);
			if (result.equalsIgnoreCase("success")) {
//				redirectAttributes.addFlashAttribute("notice", "Insert successful");
				response = "ok";
			} else {
				response = "taken";
//				redirectAttributes.addFlashAttribute("notice", result);
			}
		} catch (Exception e) {
			response = "fail";
//			redirectAttributes.addFlashAttribute("notice", "Insert fail");
		}
		return response;
	}

//    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	@RequestMapping(value = "/updateCellValue", method = RequestMethod.POST)
	public @ResponseBody String Fetch(@RequestBody Account acc) {
		String json = null;
		try {
			dao.cellUpdate(acc.getEmail(), acc.getColumnName(), acc.getNewValue());
			json = new Gson().toJson(dao.getAll());
		} catch (Exception e) {
			System.out.println("Lỗi update cell : " + e);
		}
		return json;
	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public @ResponseBody String DeleteAccount(@RequestBody Account acc) {
		int result = 0;
		try {
			result = dao.removeUser(acc.getEmail());
		} catch (Exception e) {
			System.out.println("Lỗi delete cell: " + e);
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
