package util;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String accountType;
	
	public User (String userName, String password, String accountType) {
		this.userName = userName;
		this.password = password;
		this.accountType = accountType;
	}
	
	public String getUserName () {
		return userName;
	}
	
	public String getPassword () {
		return password;
	}
	
	public String getAccountType () {
		return accountType;
	}
}
