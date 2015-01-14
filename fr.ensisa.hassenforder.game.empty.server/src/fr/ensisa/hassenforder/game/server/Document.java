package fr.ensisa.hassenforder.game.server;

import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Account;
import fr.ensisa.hassenforder.game.model.Accounts;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.game.model.GameFactory;
import fr.ensisa.hassenforder.game.model.User;
import fr.ensisa.hassenforder.game.model.Users;

public class Document {

	private Accounts accounts;
	private Users connected;
	
	public Document() {
		super();
		accounts = new Accounts();
		connected = new Users();
		populate ();
	}

	private boolean isConnected (String name, long id) {
		if (name == null) return false;
		if (! connected.exists(name)) return false;
		User user = connected.get(name);
		user.resetTime();
		if (id != user.getId()) return false;
		return true;
	}

	private boolean populate () {
		if (accounts.exists("pierre")) return false;
		accounts.add (GameFactory.newAccount("paul", 1));
		accounts.add (GameFactory.newAccount("jean", 2));
		accounts.add (GameFactory.newAccount("marc", 3));
		accounts.add (GameFactory.newAccount("pierre", 4));
		return true;
	}

	/*
	 * Debug API
	 */
	public boolean addCash (String name, long id, int cash) {
		if (! isConnected(name, id)) return false;
		Account account = accounts.get(name);
		return account.addCash(cash);
	}
	
	public boolean clearProducts (String name, long id) {
		if (! isConnected(name, id)) return false;
		Account account = accounts.get(name);
		account.getProducts().clear();
		return true;
	}

	public boolean consumeProducts (String name, long id) {
		if (! isConnected(name, id)) return false;
		Account account = accounts.get(name);
		for (Product p : account.getProducts().values()) {
			if (p.isStackable())
				p.addCount(-1);
		}
		return true;
	}
/*
 * Release API
 */
	public User connect(String name, String password) {
		if (name == null) return null;
		if (password == null) return null;
		if (connected.exists(name)) return null;
		if (! accounts.exists(name)) return null;
		Account account = accounts.get (name);
		if (! password.equals(account.getPassword())) return null;
		User user = new User(name);
		connected.add(user);
		return user;
	}

	public boolean disconnect (String name, long id) {
		boolean ready = isConnected(name, id);
		connected.remove(name);
		return ready;
	}

	public Account getStatistics (String name, long id) {
		if (! isConnected(name, id)) return null;
		Account account = accounts.get(name);
		return account;
	}

	public Collection<Product> getProducts (String name, long id) {
		if (! isConnected(name, id)) return null;
		Account account = accounts.get(name);
		return account.getProducts().values();
	}

	public Collection<Product> getShop (String name, long id) {
		if (! isConnected(name, id)) return null;
		Account account = accounts.get(name);
		return account.getStore().getAll();
	}

	public boolean refreshShop (String name, long id) {
		if (! isConnected(name, id)) return false;
		Account account = accounts.get(name);
		return account.getStore().refresh();
	}

	public boolean buyProduct (String userName, long id, String productName) {
		if (! isConnected(userName, id)) return false;
		Account account = accounts.get(userName);
		return account.buy (productName);
	}

	public boolean sellProduct (String userName, long id, String productName) {
		if (! isConnected(userName, id)) return false;
		Account account = accounts.get(userName);
		return account.sell (productName);
	}

}
