package util;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String accountType;
	public ArrayList<Album> albums;
	
	public User (String userName, String password, String accountType) {
		this.userName = userName;
		this.password = password;
		this.accountType = accountType;
		this.albums = new ArrayList<Album>();
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
	
	public ArrayList<Album> getAlbums(){
		return this.albums;
	}
	
	public void addAlbums(Album a) {
		System.out.println("adding: "+a.getName()+" to: "+albums.toString());
		this.albums.add(a);
	}
	
	public void deleteAlbum(Album a) {
		this.albums.remove(a);
	}
	
	public boolean equals(User u){
		if (!(u instanceof User) || u == null) return false;
		return this.userName.equals(u.getUserName()) && this.password.equals(u.password);
	}
}
