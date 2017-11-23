package util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class representation of an Album Object
 * @author Justin Valeroso
 * @author Travis Thiel
 *
 */
public class Album implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Date oldestDate=null;
	private Date newestDate=null;
	private ArrayList<Photo> myPhotos;
	
	/**
	 * Constructor for a Album Instance
	 * @param name String name to set the album name to
	 */
	public Album(String name) {
		this.name = name;
		myPhotos = new ArrayList<Photo>();
	}
	/**
	 * Constructor for a Album Instance
	 * @param name String name to set the album name to
	 * @param results ArrayList of Photo objects that are contained in the album
	 */
	public Album(String name, ArrayList<Photo>results) {
		this.name = name;
		myPhotos = results;
	}
	
	/**
	 * Adds Photo p to the Album
	 * @param p Photo to be added to the album
	 */
	public void addPhoto(Photo p) {
		myPhotos.add(p);
		if (oldestDate == null || p.getDate().compareTo(oldestDate) < 0)
			oldestDate=p.getDate();
	
		if (newestDate == null || p.getDate().compareTo(newestDate) > 0)
			newestDate=p.getDate();
		
		
	}

	/**
	 * Removes a Photo p from the album
	 * @param p Photo to be removed from the album
	 */
	public void deletePhoto(Photo p) {
		if(myPhotos.contains(p)) {
			myPhotos.remove(p);
			updateDates();
		}
		
	}

	/**
	 * Updates the Range of Photo dates to find the newest and oldest date in the album
	 */
	public void updateDates() {
		if(myPhotos.size()==0) {
			this.newestDate=null;
			this.oldestDate=null;
		}else {
			for(Photo p:myPhotos) {
				if (oldestDate == null || p.getDate().compareTo(oldestDate) < 0)
					oldestDate=p.getDate();
			
				if (newestDate == null || p.getDate().compareTo(newestDate) > 0)
					newestDate=p.getDate();
			}
		}
	}
	
	
	/**
	 * Returns the number of Photos in the album
	 * @return size of the ArrayList of Photo objects in the album
	 */
	public int getNumPhoto() {
		return myPhotos.size();
	}
	
	/**
	 * Returns the name of the album
	 * @return Name of the album
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of the album
	 * @param name String to set the name of the album to
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the photos in an album
	 * @return ArrayList of Photo objects in the album
	 */
	public ArrayList<Photo> getPhotos() {
		return this.myPhotos;
	}
	
	/**
	 * Overwrites the toString method to return the name of the album
	 * @return name of the album
	 */
	public String toString() {
		return this.name;
	}
	/**
	 * Returns the oldest date of the object
	 * @return String representation of the Date object oldestDate
	 */
	public String getOldestDateString(){
		if (this.oldestDate == null){
			return "";
		}
		return new SimpleDateFormat("MM/dd/yy").format(this.oldestDate);
	}
	
	/**
	 * Returns the newest date of the object
	 * @return String representation of the Date object newestDate
	 */
	public String getNewestDateString(){
		if (this.newestDate == null){
			return "";
		}
		return new SimpleDateFormat("MM/dd/yy").format(this.newestDate);
	}
	
	
}
