package fr.ensisa.hassenforder.game.model;

import fr.ensisa.hassenforder.game.model.Category;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.game.model.Shop;

public class Store extends Shop {

	private static final int PRICE = 100;
	private static final int RATE = 10;

	public int getSellPrice (Category category) {
		return (int) (PRICE * (100.0 - RATE) / 100.0);
	}

	public int getBuyPrice (Category category) {
		return (int) (PRICE * (100.0 + RATE) / 100.0);
	}

	public boolean refresh () {
		getProducts().clear();
		int count = Category.values().length;
		for (int i=0; i < count; ++i) {
			Product p1 = GameFactory.newProduct(Category.values()[i]);
			add(p1);
			while (true) {
				Product p2 = GameFactory.newProduct(Category.values()[i]);
				if (! p2.getName().equals(p1.getName())) {
					add(p2);
					break;
				}
			}
		}
		return true;
	}

}
