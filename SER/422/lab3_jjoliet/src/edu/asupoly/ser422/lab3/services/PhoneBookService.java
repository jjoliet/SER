package edu.asupoly.ser422.lab3.services;

import java.util.List;

import edu.asupoly.ser422.lab3.model.*;

public interface PhoneBookService {
	//Methods for PhoneEntry
	public List<PhoneEntry> getEntries();
	public PhoneEntry getEntry(String num);
	public boolean deleteEntry(String num);
	public int createEntry(String fname, String lname, String num);
	public int createEntry(String fname, String lname, String num, String id);
	public int createEntry(PhoneEntry entry);
	public boolean updateEntry(PhoneEntry entry);
	
	//Methods for PhoneBook
	public List<PhoneEntry> getBook(String id);
	public List<PhoneEntry> getPartial(String id, String first, String last);
	
}
