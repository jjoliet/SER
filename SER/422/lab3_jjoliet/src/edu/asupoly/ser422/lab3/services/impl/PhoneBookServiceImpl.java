package edu.asupoly.ser422.lab3.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import edu.asupoly.ser422.lab3.model.PhoneBook;
import edu.asupoly.ser422.lab3.model.PhoneEntry;


public class PhoneBookServiceImpl extends APhoneBookServiceImpl {
	private Set<PhoneEntry> __entries = null;
	private PhoneBook _pbook = null;
	
	public List<PhoneEntry> getEntries() {
		_pbook.readPhoneBook();
		return _pbook.getpb();
	}
	
	public int createEntry(String fname, String lname, String num){
		PhoneEntry entry = _pbook.getEntry(num);
		if(entry == null){
		_pbook.addEntry(num, new PhoneEntry(fname, lname, num));
		_pbook.savePhoneBook();
		return _pbook.getpb().size();
		}
		return -1;
	}
	
	public int createEntry(String fname, String lname, String num, String id) {		
		PhoneEntry entry = _pbook.getEntry(num);
		if(entry == null){
			_pbook.addEntry(num, new PhoneEntry(fname, lname, num, id));
		_pbook.savePhoneBook();
		return _pbook.getpb().size();
		}
		return -1;
	}
	
	public int createEntry(PhoneEntry ent) {		
		PhoneEntry entry = _pbook.getEntry(ent.getPhone());
		if(entry == null){
			_pbook.addEntry(ent.getPhone(), ent);
		_pbook.savePhoneBook();
		return _pbook.getpb().size();
		}
		return -1;
	}
	public boolean deleteEntry(String num) {
		boolean rval = _pbook.removeEntry(num);
		if(rval == true){
			_pbook.savePhoneBook();
		}
		return rval;
	}
	
	@Override
	public PhoneEntry getEntry(String num){
		
		for(PhoneEntry pe :_pbook.getpb()){
			if(pe.getPhone().equalsIgnoreCase(num)){
				return pe;
			}
		}
		return null;
	}
	
	public boolean updateEntry(PhoneEntry entry) {
		boolean rval = false;
		PhoneEntry pe = _pbook.getEntry(entry.getPhone());
		System.out.println("In update: " + pe);
		if(pe != null){
			rval = true;
			pe.setFirstname(entry.getFirstname());
			pe.setLastname(entry.getLastname());
			if(!entry.getId().equalsIgnoreCase("-1")){
				pe.setId(entry.getId());
			}
			_pbook.savePhoneBook();
		}
//		for(PhoneEntry pe : __entries){
//			if(pe.getPhone().equalsIgnoreCase(entry.getPhone())){
//				rval = true;
//				pe.setFirstname(entry.getFirstname());
//				pe.setLastname(entry.getLastname());
//			}
//		}
		return rval;
	}
	
	//PhoneBook Section
	public List<PhoneEntry> getBook(String id) {
		_pbook.readPhoneBook();
		if(!id.equalsIgnoreCase("ul")){
			return _pbook.getBook(id);
		} else {
			return _pbook.getBook("-1");
		}
	}
	
	public List<PhoneEntry> getPartial(String id, String first, String last) {
		_pbook.readPhoneBook();
		List<PhoneEntry> rval = _pbook.getBook(id);
		List<PhoneEntry> rval2 = new ArrayList<PhoneEntry>();
		for(PhoneEntry e : rval){
			System.out.println(e.getFirstname().toLowerCase().contains(first.toLowerCase()) + " firstname contains first");
			System.out.println(e.getFirstname() +" firstname " + first);
			if((e.getFirstname().toLowerCase().contains(first.toLowerCase()) || first == null) && (last == null || e.getLastname().toLowerCase().contains(last.toLowerCase()))){
				rval2.add(e);
			}
		}
		return rval2;
	}
	
	
	public PhoneBookServiceImpl() {
		__entries = new LinkedHashSet<PhoneEntry>();
		_pbook = new PhoneBook();
		
	}
}
