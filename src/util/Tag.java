package util;

import java.io.Serializable;

public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	private String type;
	private String value;
	
	public Tag (String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public String getType () {
		return this.type;
	}
	
	public String getValue () {
		return this.value;
	}
	
	public void setType (String type) {
		this.type = type;
	}
	
	public void setValue (String value) {
		this.value = value;
	}
	
	public String toString () {
		return this.type + " : " + this.value;
	}
	
	public boolean equals (Tag tag) {
		if (tag == null || !(tag instanceof Tag)) {
			return false;
		}
		if (tag.getType().equals("")) {
			if (this.value.toLowerCase().equals(tag.getValue().toLowerCase())) {
				return true;
			}
		}
		if (tag.getValue().equals("")) {
			if (this.type.toLowerCase().equals(tag.getType().toLowerCase())) {
				return true;
			}
		}
		if (this.value.toLowerCase().equals(tag.getValue().toLowerCase()) && this.type.toLowerCase().equals(tag.getType().toLowerCase())) {
			return true;
		}
		return false;
	}
	
}
