package fr.ensisa.hassenforder.game.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Shop {

	private Map<String, Product> products;

	public Shop() {
		super();
	}

	protected Map<String, Product> getProducts() {
		if (products == null) products = new TreeMap<String, Product>();
		return products;
	}

	public Collection<Product> getAll () {
		return getProducts().values();
	}

	public void add (Product product) {
		getProducts().put (product.getName(), product);
	}

	public boolean exists (String name) {
		return getProducts().containsKey(name);
	}

	public Product get (String name) {
		return getProducts().get (name);
	}

}
