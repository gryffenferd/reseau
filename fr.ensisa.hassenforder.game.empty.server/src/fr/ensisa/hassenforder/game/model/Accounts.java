package fr.ensisa.hassenforder.game.model;

import java.util.Map;
import java.util.TreeMap;

public class Accounts {

	private Map<String, Account> accounts;
	
	public Accounts() {
		super();
	}

	private Map<String, Account> getAccounts() {
		if (accounts == null) accounts = new TreeMap<String, Account>();
		return accounts;
	}

	public boolean exists (Account account) {
		return getAccounts().containsKey(account.getName());
	}

	public boolean exists (String name) {
		return getAccounts().containsKey(name);
	}

	public Account get (String name) {
		return getAccounts().get(name);
	}

	public boolean add (Account account) {
		if (exists(account)) return false;
		getAccounts().put(account.getName(), account);
		return true;
	}

}
