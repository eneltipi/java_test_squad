package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;

import authentication.helper.CustomUserDetails;
import authentication.helper.User;
import authentication.jwt.JwtResponse;
import authentication.jwt.JwtUtils;
import model.Account;
import model.Laptop;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:3000/")	
@RestController
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;	

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	protected JdbcTemplate jdbc;
//	
//	@RequestMapping("JWTLogin")
//	public ResponseEntity<?> authenticateUser(Account user, HttpServletResponse response) {		
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
//		
//		SecurityContextHolder.getContext().setAuthentication(authentication);		
//		String jwt = jwtUtils.generateJwtToken(authentication);
//		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//		
//		List<String> roles = userDetails.getAuthorities().stream()
//				.map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//		
//		Cookie cookie = new Cookie("jwt", jwt);
////		cookie.setHttpOnly(true);
//		cookie.setMaxAge(1800);
//		response.addCookie(cookie);
//		
//		return ResponseEntity.ok(new JwtResponse(userDetails.getEmail(), jwt, roles));
//	}
	
	
	
//	@CrossOrigin(origins = "http://localhost:3000/")	
	@RequestMapping(value = "/getLaptopList")
	public List<Laptop> getLaptopList(@CookieValue(value = "jwt") String jwt) {		
		List<Laptop> laptopList = new ArrayList<Laptop>();
		try {
			Connection connection = jdbc.getDataSource().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("Call  getLaptopList();");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				laptopList.add(new Laptop(
						rs.getString(1), // name
						rs.getString(2), // cpu
						rs.getString(3), // ram

						rs.getString(4), // storage
						rs.getString(5), // gpu
						rs.getString(6), // monitor
						rs.getString(7), // alias

						rs.getFloat(10), // price
						rs.getString(8), // brand
						rs.getInt(9), 	 // quanity
						rs.getString(11) // photo
				));
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}

		return laptopList;
//		return "hienthitaikhoan2";
	}

}
