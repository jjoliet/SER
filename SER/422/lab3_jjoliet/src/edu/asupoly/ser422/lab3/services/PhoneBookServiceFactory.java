package edu.asupoly.ser422.lab3.services;

import java.io.IOException;
import java.util.Properties;

import edu.asupoly.ser422.lab3.services.impl.PhoneBookServiceImpl;

public class PhoneBookServiceFactory {
	private static PhoneBookService __pbookservice;
	private PhoneBookServiceFactory() {}
	
	public static PhoneBookService getInstance(){
		if(__pbookservice == null){
			__pbookservice = new PhoneBookServiceImpl();			
		}
		return __pbookservice;
	}
	
	static {
		try {
			//Properties dbProperties = new Properties();
			Class<?> initClass = null;
//			dbProperties.load(PhoneBookServiceFactory.class.getClassLoader().getResourceAsStream("/phonebook.properties"));
//			String serviceImpl = dbProperties.getProperty("serviceImpl");
//			if (serviceImpl != null) {
//				initClass = Class.forName(serviceImpl);
//			} else {
				initClass = Class.forName("edu.asupoly.ser422.lab3.services.impl.PhoneBookService");
			//}
			__pbookservice = (PhoneBookService)initClass.newInstance();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}
	}
}
