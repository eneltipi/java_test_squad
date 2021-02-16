package authentication.helper;

public class User {
	
	private long id;
	private String email;
	private String password;
	private String[] roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(String email, String password, long id) {
		super();
		this.email = email;
		this.password = password;
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public User() {
		super();
	}

//	public List<GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		for (String role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role));
//		}
//		return authorities;
//	}

}
