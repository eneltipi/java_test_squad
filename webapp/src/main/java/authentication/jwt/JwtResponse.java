package authentication.jwt;

import java.util.List;

public class JwtResponse {
	private String email;
	private String jwt;
	private List<String> roles;
	final String tokenType = "Bearer";
	
	public String getTokenType() {
		return tokenType;
	}

	public JwtResponse(String email, String jwt, List<String> roles) {
		super();
		this.email = email;
		this.jwt = jwt;
		this.roles = roles;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


}


