package util;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class Representation of a Photo object
 * @author Justin Valeroso
 * @author Travis Thiel
 *
 */
public class Photo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String Caption;
	private File file;
	private Date date;
	private ArrayList<Tag> tags;
	
	/**
	 * Constructor for a Photo Instance
	 * @param file File object that contains the photo
	 * @param cap String caption
	 */
	public Photo(File file, String cap) {
		this.Caption = cap;
		this.file = file;
		this.date = new Date(file.lastModified());
		this.tags = new ArrayList<Tag>();
	}
	
	/**
	 * Constructor for a Photo Instance
	 * @param file File object that contains the photo
	 */
	public Photo(File file) {
		this.Caption = file.getPath();
		this.file = file;
		this.date = new Date(file.lastModified());
		this.tags = new ArrayList<Tag>();
	}
	
	/**
	 * Returns the number of tags a photo has
	 * @return size of ArrayList containing tags
	 */
	public int getNumTags(){
		return this.tags.size();
	}
	
	/**
	 * Returns the ArrayList containing the tags a photo has 
	 * @return ArrayList containing tags
	 */
	public ArrayList<Tag> getTags(){
		return this.tags;
	}
	
	/**
	 * Returns the caption of the photo
	 * @return String caption of the photo
	 */
	public String getCaption() {
		return this.Caption;
	}
	
	/**
	 * Checks to see if Tag t is contained in the photo's list of tags
	 * @param t Tag to be checked
	 * @return True if found, false if otherwise
	 */
	public boolean containsTag(Tag t) {
		for(Tag i : this.tags) {
			if(i.equals(t)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds tag to ArrayList of Tags
	 * @param t Tag to be added
	 */
	public void addTag(Tag t) {
		this.tags.add(t);
	}
	
	/**
	 * Removes tag from ArrayList of Tags
	 * @param t Tag to be removed
	 */
	public void deleteTag(Tag t) {
		if(this.tags.contains(t)) {
			this.tags.remove(t);
		}
	}
	
	/**
	 * Returns file of photo
	 * @return File of photo
	 */
	public File getFile() {
		return this.file;
	}
	
	/**
	 * Returns the path of the photo file
	 * @return String path name
	 */
	public String getPath() {
		return this.file.getPath();
	}
	
	/**
	 * Returns the date of the photo
	 * @return Data object containing the date of the photo
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * Overwrites the toString method to print the photo's caption
	 * @return String caption
	 */
	public String toString() {
		return this.Caption;
	}

	/**
	 * Converts the Date object into a formatted string
	 * @return String "MM/dd/yy"
	 */
	public String getDateString(){
		return new SimpleDateFormat("MM/dd/yy").format(this.getDate());
	}
	
	/**
	 * Changes the caption of the photo to newCaption
	 * @param newCaption String of the new caption 
	 */
	public void setCaption (String newCaption) {
		this.Caption = newCaption;
	}
}
