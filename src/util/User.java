package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	public void saveUser () {
		try {
			// Deserialize storedUsers data
			FileInputStream fileIn = new FileInputStream("accounts.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList<User> storedUsers = (ArrayList<User>) in.readObject();
			in.close();
			fileIn.close();
					
			// Traverse storedUsers and save this User
			for (User u : storedUsers) {
				if (this.equals(u)) {
					storedUsers.set(storedUsers.indexOf(u), this);
				}
			}
					
			// Serialize updated storedUsers
			FileOutputStream fileOut = new FileOutputStream("accounts.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(storedUsers);
			out.close();
			fileOut.close();
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Class not found.");
		}
		catch (IOException ex) {
			System.out.println("Error reading file.");
		}	
	}
}
