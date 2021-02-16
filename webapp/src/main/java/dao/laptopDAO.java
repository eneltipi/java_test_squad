package dao;

import model.Laptop;

//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class laptopDAO {

	public laptopDAO() {
	}

	@Autowired
	protected JdbcTemplate jdbc;

	public List<Laptop> getAll() {
		try {
			String sql = "select * from laptopinfo";
			return getBySql(sql);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	protected List<Laptop> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<Laptop> getRowMapper() {
		return new BeanPropertyRowMapper<Laptop>(Laptop.class);
	}

//	public List<Laptop> getAlll() {
//
//		List<Laptop> lapList = jdbc.query("select cpu from laptopinfo", new ResultSetExtractor<List<Laptop>>() {
//
//			public List<Laptop> extractData(ResultSet rs) {
//				List<Laptop> list = new ArrayList<Laptop>();
//				try {
//					while (rs.next()) {
//						System.out.println(rs.getString(1));
//					}
//				} catch (SQLException e) {
//					System.out.println(e);
//				}
//				return list;
//			}
//		});
//		return null;
//	}

}
