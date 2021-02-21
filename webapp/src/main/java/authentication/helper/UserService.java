package authentication.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

	@Autowired
	protected JdbcTemplate jdbc2;

	public User loadUserByUsername(String email) {
		System.out.println("this is user service");
		User user = null;

		try {
			Connection connection = jdbc2.getDataSource().getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from account where email = '"+email+"'");
			ResultSet rs = pstmt.executeQuery();
			user = new User();
			if (rs.next()) {
				user.setEmail(rs.getString(1));
				user.setPassword("$2y$12$Cg4XLgtggOjN4fltMm1UDu9KVrhT.8Ho00sLB/nc72fEmSlPOdhBS");
				user.setRoles(rs.getString(5).split(","));
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}

		return user;
	}

}