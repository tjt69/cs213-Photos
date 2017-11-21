package util;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Photo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String Caption;
	private File file;
	private Date date;
	private ArrayList<Tag> tags;
	
	public Photo(File file, String cap) {
		this.Caption = cap;
		this.file = file;
		this.date = new Date(file.lastModified());
		this.tags = new ArrayList<Tag>();
	}
	
	public Photo(File file) {
		this.Caption = file.getPath();
		this.file = file;
		this.date = new Date(file.lastModified());
		this.tags = new ArrayList<Tag>();
	}
	
	public int getNumTags(){
		return this.tags.size();
	}
	
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
		// TODO Auto-generated method stub
		return null;
	}

}
