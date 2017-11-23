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
	 * Returns the number of tags an object has
	 * @return size of ArrayList containing tags
	 */
	public int getNumTags(){
		return this.tags.size();
	}
	
	/**
	 * Returns the 
	 * @return
	 */
	public ArrayList<Tag> getTags(){
		return this.tags;
	}
	
	public String getCaption() {
		return this.Caption;
	}
	
	public boolean containsTag(Tag t) {
		for(Tag i : this.tags) {
			if(i.equals(t)) {
				return true;
			}
		}
		return false;
	}
	
	public void addTag(Tag t) {
		this.tags.add(t);
	}
	
	public void deleteTag(Tag t) {
		if(this.tags.contains(t)) {
			this.tags.remove(t);
		}
	}
	
	public File getFile() {
		return this.file;
	}
	
	public String getPath() {
		return this.file.getPath();
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String toString() {
		return this.Caption;
	}

	public String getDateString(){
		return new SimpleDateFormat("MM/dd/yy").format(this.getDate());
	}
	
	public void setCaption (String newCaption) {
		this.Caption = newCaption;
	}
}
