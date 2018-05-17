package edu.asupoly.ser422.lab3.services.impl;

import java.util.Random;

import edu.asupoly.ser422.lab3.services.PhoneBookService;

public abstract class APhoneBookServiceImpl implements PhoneBookService{
	private static Random __r = new Random();
	
	protected int generateKey(int lb, int ub) {
		return __r.nextInt(Math.abs(ub-lb)) + ((ub>lb) ? lb : ub);
	}
}
