package dao;

import java.io.Serializable;
import model.Account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
    
	public AccountDAO() {
		
		System.out.println("dsadsad");
	}
    /**
     * Inject tá»« <bean class="...JdbcTemplate>
     */
    @Autowired
    protected JdbcTemplate jdbc; // khai bao doi tuong JdbcTemplate

//    @Autowired
//    Account account;
    /**
     * Truy váº¥n 1 thá»±c thá»ƒ theo mÃ£
     *
     * @param id mÃ£ thá»±c thá»ƒ cáº§n truy váº¥n
     * @return thá»±c thá»ƒ truy váº¥n Ä‘Æ°á»£c
     */
    //1. lay 1 tai khoan dua vao Username
    public Account getByUsername(Serializable Username) {
        String sql = "SELECT * FROM Account WHERE Username=?";
        return jdbc.queryForObject(sql, getRowMapper(), Username);
    }

    /**
     * Truy váº¥n táº¥t cáº£ cÃ¡c thá»±c thá»ƒ
     *
     * @return danh sÃ¡ch thá»±c thá»ƒ truy váº¥n Ä‘Æ°á»£c
     */
    //2. lay tat ca cac tai khoáº£n trong bang Account ra
    public List<Account> getAll() {
        String sql = "SELECT * FROM Account";
        return getBySql(sql); // tráº£ vá»� danh sÃ¡ch cÃ¡c tÃ i khoáº£n
    }

    /**
     * Truy váº¥n cÃ¡c thá»±c thá»ƒ theo cÃ¢u lá»‡nh sql
     *
     * @param sql cÃ¢u lá»‡nh truy váº¥n
     * @return danh sÃ¡ch thá»±c thá»ƒ truy váº¥n Ä‘Æ°á»£c
     */
    protected List<Account> getBySql(String sql) {
        return jdbc.query(sql, getRowMapper());
    }

    /**
     * Ã�nh xáº¡ cáº¥u trÃºc báº£n ghi theo thuá»™c tÃ­nh cá»§a bean
     *
     * @return Ã¡nh xáº¡ báº£n ghi theo thuá»™c tÃ­nh bean
     */
    private RowMapper<Account> getRowMapper() {
        return new BeanPropertyRowMapper<Account>(Account.class);
    }

    /**
     * ThÃªm má»›i 1 thá»±c thá»ƒ
     *
     * @param Account
     * @param acc lÃ  thá»±c thá»ƒ má»›i
     */
    public String insert(Account account) {
        try {
            String sql = "insert into account (email, password, fullname, phonenumber, roles) values (?,?,?,?,?)";
            jdbc.update(sql, account.getEmail(), account.getPassword(), account.getFullname(), account.getPhonenumber(), account.getRole()); 
            return "success";
        } catch (DataAccessException e) {         
            String[] MySQLexception = e.getCause().toString().split(":");
            return MySQLexception[1];
        }
    }

    /**
     * Cáº­p nháº­t thá»±c thá»ƒ
     *
     * @param acc lÃ  thá»±c thá»ƒ cáº§n cáº­p nháº­t
     */
    public void update(Account acc) {
        String sql = "UPDATE Account SET password=? WHERE username=?";
        jdbc.update(sql, acc.getPassword(), acc.getUsername());
    }

    public void cellUpdate(String email, String columnName, String newValue) {
        String sql = "UPDATE Account SET " + columnName + "='" + newValue + "' WHERE email='" + email + "'";
        System.out.println(sql);
        jdbc.update(sql);
    }

    /**
     * XÃ³a thá»±c thá»ƒ theo mÃ£
     *
     * @param username mÃ£ thá»±c thá»ƒ cáº§n xÃ³a
     */
    public int removeUser(String email) {
        String sql = "DELETE FROM Account WHERE email=?";
        System.out.println(sql);
        return jdbc.update(sql, email);
    }

    ///////////////////////////////
    /**
     * Truy váº¥n thá»±c thá»ƒ theo tÃªn
     *
     * @param username tÃªn cá»§a thá»±c thá»ƒ cáº§n truy váº¥n
     * @return danh sÃ¡ch thá»±c thá»ƒ truy váº¥n Ä‘Æ°á»£c
     */
    public List<Account> getByName(String username) {
        String sql = "SELECT * FROM Account WHERE username LIKE ?";
        return jdbc.query(sql, getRowMapper(), "%" + username + "%");
    }
}
