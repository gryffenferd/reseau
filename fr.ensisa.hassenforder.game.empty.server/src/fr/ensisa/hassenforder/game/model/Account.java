package fr.ensisa.hassenforder.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.ensisa.hassenforder.game.model.Player;
import fr.ensisa.hassenforder.game.model.Product;

public class Account extends Player {

	private String password;
	private Store store;
	private Map<String, Product> products;

	public Account(String name, String image, int cash, String password) {
		super(name, image, cash);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public Store getStore() {
		if (store == null) store =  new Store();
		return store;
	}

	public boolean addCash (int cash) {
		if (this.cash + cash >= 0) {
			this.cash += cash;
			return true;
		}
		return false;
	}

	public Map<String, Product> getProducts() {
		if (products == null) products = new TreeMap<String, Product>();
		List<Product> toRemove = new ArrayList<Product> ();
		for (Product p : products.values()) {
			if (p.isElapsed() || p.getCount() <= 0) {
				toRemove.add(p);
			}
		}
		for (Product p : toRemove) {
			products.remove(p.getName());
		}
		return products;
	}
	
	private boolean add (Product product) {
		if (product == null) return false;
		if (getProducts().containsKey(product.getName())) {
			Product original = getProducts().get(product.getName());
			if (original.isStackable()) {
				original.addCount (product.getCount());
				return true;
			} else {
				return false;
			}
		} else {
			Product copy = new Product (product);
			getProducts().put(product.getName(), copy);
			return true;
		}
	}

	private boolean remove (Product product) {
		if (getProducts().containsKey(product.getName())) {
			Product original = getProducts().get(product.getName());
			if (original.isStackable()) {
				original.addCount (-1);
				return true;
			} else {
				original.addCount (- original.getCount());
				return true;
			}
		} else {
			return false;
		}
	}

	public boolean exists (String productName) {
		return getProducts().containsKey(productName);
	}

	public Product get (String productName) {
		return getProducts().get (productName);
	}

	public boolean buy (String productName) {
		if (! getStore().exists(productName)) return false;
		Product product = getStore().get(productName);
		int price = getStore().getBuyPrice(product.getCategory());
		if (cash < price) return false;
		if (! add(product)) return false;
		addCash(-price);
		return true;
	}

	public boolean sell (String productName) {
		if (! exists(productName)) return false;
		Product product = get(productName);
		int price = getStore().getSellPrice(product.getCategory());
		if (! remove(product)) return false;
		addCash(price);
		return true;
	}

	// DEBUG only
	public void addProduct (Product p)  {
		add(p);
	}
}
