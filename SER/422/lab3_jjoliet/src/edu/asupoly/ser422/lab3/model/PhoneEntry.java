package edu.asupoly.ser422.lab3.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PhoneEntry {
	private String firstname;
	private String lastname;
	private String phone;
	private String id;
	
	public PhoneEntry() {
		this.id="-1";
	};
	
	public PhoneEntry(String name, String lname, String phone){
		this.firstname = name;
		this.lastname = lname;
		this.phone = phone;
		this.id = "-1";
	}
	
	public PhoneEntry(String name, String lname, String phone, String id){
		this.firstname = name;
		this.lastname = lname;
		this.phone = phone;
		this.id = id;
	}
	
	public void changeName(String newfname, String newlname){
		this.firstname = newfname;
		this.lastname = newlname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String toString()
    { return firstname + "\n" + lastname + "\n" + phone + "\n" + id; }
	
}
