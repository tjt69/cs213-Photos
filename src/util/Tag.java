package util;

import java.io.Serializable;

/**
 * Class Representation of a Tag object
 * @author Justin Valeroso
 * @author Travis Thiel
 *
 */
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	private String type;
	private String value;
	/**
	 * Constructor for a Tag Instance
	 * @param type String to set the type parameter of tag 
	 * @param value String to set the value parameter of tag
	 */
	public Tag (String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Returns the type of tag
	 * @return type parameter of tag
	 */
	public String getType () {
		return this.type;
	}
	
	/**
	 * Returns the value of tag
	 * @return value parameter of tag
	 */
	public String getValue () {
		return this.value;
	}
	
	/**
	 * Sets the type parameter of the tag 
	 * @param type String to set the Tag type to
	 */
	public void setType (String type) {
		this.type = type;
	}
	
	/**
	 * Sets the value parameter of the tag 
	 * @param vlaue String to set the Tag value to
	 */
	public void setValue (String value) {
		this.value = value;
	}
	
	/**
	 * Overwrites the toString method to print the Tag's type:value pair
	 * @return "type : value"
	 */
	public String toString () {
		return this.type + " : " + this.value;
	}
	
	/**
	 * Overwrites the equals method to compare the current tag to another. If there is no type specified for tag then values are compared. If there is no value specified for tag then types are compared. If both type and value exist then both are compared.
	 * @param tag The Tag that is being compared to.
	 * @return True if the tags are equal. False if they are not equal, tag is not a instance of Tag, or tag is null
	 */
	public boolean equals (Tag tag) {
		if (tag == null || !(tag instanceof Tag)) {
			return false;
		}
		if (tag.getType().equals("")) {
			if (this.value.toLowerCase().trim().equals(tag.getValue().toLowerCase().trim())) {
				return true;
			}
		}
		if (tag.getValue().equals("")) {
			if (this.type.toLowerCase().trim().equals(tag.getType().toLowerCase().trim())) {
				return true;
			}
		}
		if (this.value.toLowerCase().trim().equals(tag.getValue().toLowerCase().trim()) && this.type.toLowerCase().trim().equals(tag.getType().toLowerCase().trim())) {
			return true;
		}
		return false;
	}
	
}
