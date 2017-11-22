package util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Album implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Date oldestDate=null;
	private Date newestDate=null;
	private ArrayList<Photo> myPhotos;
	
	public Album(String name) {
		this.name = name;
		myPhotos = new ArrayList<Photo>();
	}
	
	public Album(String name, ArrayList<Photo>results) {
		this.name = name;
		myPhotos = results;
	}
	
	public void addPhoto(Photo p) {
		myPhotos.add(p);
		if (oldestDate == null || p.getDate().compareTo(oldestDate) < 0)
			oldestDate=p.getDate();
	
		if (newestDate == null || p.getDate().compareTo(newestDate) > 0)
			newestDate=p.getDate();
		
		
	}

	public void deletePhoto(Photo p) {
		if(myPhotos.contains(p)) {
			myPhotos.remove(p);
			updateDates();
		}
		
	}

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
	
	
	
	public int getNumPhoto() {
		return myPhotos.size();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Photo> getPhotos() {
		return this.myPhotos;
	}
	
	public String toString() {
		return this.name;
	}
	
	public String getOldestDateString(){
		if (this.oldestDate == null){
			return "";
		}
		return new SimpleDateFormat("MM/dd/yy").format(this.oldestDate);
	}
	
	public String getNewestDateString(){
		if (this.newestDate == null){
			return "";
		}
		return new SimpleDateFormat("MM/dd/yy").format(this.newestDate);
	}
	
	
}
