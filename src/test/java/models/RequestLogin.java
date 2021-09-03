package models;

public class RequestLogin{
	private String username;
	private String password;
	private String request_token;

	public RequestLogin(String username,String password, String requestToken) {
		this.username = username;
		this.password = password;
		this.request_token = requestToken;
	}

	public String getPassword(){
		return password;
	}

	public String getRequestToken(){
		return request_token;
	}

	public String getUsername(){
		return username;
	}
}
