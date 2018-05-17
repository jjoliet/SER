package edu.asupoly.ser422.lab3.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PhoneBook {	
	public static final String DEFAULT_FILENAME = "phonebook.txt";
	private Map<String, PhoneEntry> _pbook = new HashMap<String, PhoneEntry>();
	public PhoneBook(){
//		File f = new File(DEFAULT_FILENAME);
//		if(!f.exists()){
//			try{
//			PrintWriter pw = new PrintWriter(new FileOutputStream(DEFAULT_FILENAME));
//			pw.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
		
		this.readPhoneBook();
	}
	
//	public PhoneBook(String fname) throws IOException {	
//		this(new BufferedReader(new FileReader(fname)));
//	}
//	
//	public PhoneBook(InputStream is) throws IOException {
//		this(new BufferedReader(new InputStreamReader(is)));
//	}
//	
//	private PhoneBook(BufferedReader br) throws IOException {
//		String name = null;
//		String lname = null;
//		String phone = null;
//		String id = null;
//		try{
//			String nextLine = null;
//			while( (nextLine=br.readLine()) != null){
//				name = nextLine;
//				lname=br.readLine();
//				phone = br.readLine();
//				id = br.readLine();
//				addEntry(name, lname, phone, id);
//			}
//	
//			br.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Error processing phonebook");
//			throw new IOException("Could not process phonebook");
//		}
//	}
	
	public void readPhoneBook(){
		File f = new File(DEFAULT_FILENAME);
		if(f.exists()){
			String name = null;
			String lname = null;
			String phone = null;
			String id = null;
			try{
				BufferedReader br = new BufferedReader(new FileReader(DEFAULT_FILENAME));
				String nextLine = null;
				int i = 0;
				while( (nextLine=br.readLine()) != null){
					name = nextLine;
					lname=br.readLine();
					phone = br.readLine();
					id = br.readLine();
					addEntry(name, lname, phone, id);
					i++;
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error processing phonebook");
			}
		}
	}
	
	public List<PhoneEntry> getpb(){
		PhoneEntry nextEntry = null;
		List<PhoneEntry> al = new ArrayList<PhoneEntry>();
		for(Iterator<PhoneEntry> iter = _pbook.values().iterator(); iter.hasNext();){
			nextEntry = iter.next();
			al.add(nextEntry);
		}
		System.out.println("al: " + al.size());
		return al; 
	}
	
	public boolean removeEntry(String num){
		PhoneEntry entry = _pbook.remove(num);
		boolean rval = (entry == null) ? true : false;
		return rval;
	}
	
	public List<PhoneEntry> getBook(String id){
		List<PhoneEntry> al = new ArrayList<PhoneEntry>();
		PhoneEntry nextEntry = null;
		
		for(Iterator<PhoneEntry> iter = _pbook.values().iterator(); iter.hasNext();){
			nextEntry = iter.next();
			if(nextEntry.getId().equalsIgnoreCase(id)){
				al.add(nextEntry);
			}
		}
		return al;
	}
	
	public void savePhoneBook(){
		try {
			System.out.println("Opening " + DEFAULT_FILENAME);
			PrintWriter pw = new PrintWriter(new FileOutputStream(DEFAULT_FILENAME));
			System.out.println("...done");
			//String[] entries = listEntries();
			PhoneEntry nextEntry = null;
			for(Iterator<PhoneEntry> iter = _pbook.values().iterator(); iter.hasNext();) {
				nextEntry = iter.next();
				pw.println(nextEntry.getFirstname());
				pw.println(nextEntry.getLastname());
				pw.println(nextEntry.getPhone());
				pw.println(nextEntry.getId());
			}
//			for (int i = 0; i < entries.length; i++)
//				pw.println(entries[i]);

			pw.close();
		}
		catch (Exception exc)
		{ 
			exc.printStackTrace(); 
			System.out.println("Error saving phone book");
		}
	}
	
	public void editEntry(String phone, String fname, String lname) {
		PhoneEntry pentry = _pbook.get(phone);
		pentry.changeName(fname, lname);
	}
	
	public PhoneEntry getEntry(String phone){
		return _pbook.get(phone);
	}
	public void addEntry(String fname, String lname, String phone, String id)
	{ 
		addEntry(phone, new PhoneEntry(fname, lname, phone, id));
	}

	public void addEntry(String number, PhoneEntry entry)
	{ _pbook.put(number, entry); }

	public String[] listEntries()
	{
		String[] rval = new String[_pbook.size()];
		int i = 0;
		PhoneEntry nextEntry = null;
		for (Iterator<PhoneEntry> iter = _pbook.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			rval[i++] = nextEntry.toString();
		}
		return rval;
	}
}
