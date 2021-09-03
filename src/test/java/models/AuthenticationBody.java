package models;

public class AuthenticationBody {
	private String password;
	private String requestToken;
	private String username;

	public AuthenticationBody(String password, String requestToken, String username) {
		this.password = password;
		this.requestToken = requestToken;
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public String getRequestToken(){
		return requestToken;
	}

	public String getUsername(){
		return username;
	}
}
