package fr.ensisa.hassenforder.game.model;

import java.util.Random;

import fr.ensisa.hassenforder.game.model.Category;
import fr.ensisa.hassenforder.game.model.Product;

public class GameFactory {

	private static Random rnd = new Random ();
	
	private static String getImage(String name, int id) {
		if (id == -1) return name;
		return name+"-"+id;
	}

	public static Product newWeapon() {
		switch (rnd.nextInt(4)) {
		case 0 : return new Product(Category.WEAPON, "rifle", GameFactory.getImage("rifle", -1), 0, false, 1);
		case 1 : return new Product(Category.WEAPON, "bow", GameFactory.getImage("bow", -1), 0, false, 1);
		case 2 : return new Product(Category.WEAPON, "blowpipe", GameFactory.getImage("blowpipe", -1), 0, false, 1);
		case 3 : return new Product(Category.WEAPON, "slingshot", GameFactory.getImage("slingshot", -1), 0, false, 1);
		}
		return null;
	}

	public static Product newAmmo() {
		switch (rnd.nextInt(4)) {
		case 0 : return new Product(Category.AMMO, "bullet", GameFactory.getImage("bullets", -1), 0, true, 5);
		case 1 : return new Product(Category.AMMO, "arrow", GameFactory.getImage("arrows", -1), 0, true, 5);
		case 2 : return new Product(Category.AMMO, "dart", GameFactory.getImage("darts", -1), 0, true, 5);
		case 3 : return new Product(Category.AMMO, "stone", GameFactory.getImage("stones", -1), 0, true, 5);
		}
		return null;
	}

	public static Product newFood() {
		switch (rnd.nextInt(4)) {
		case 0 : return new Product(Category.FOOD, "meat", GameFactory.getImage("meat", -1), 40, false, 1);
		case 1 : return new Product(Category.FOOD, "fish", GameFactory.getImage("fish", -1), 50, false, 1);
		case 2 : return new Product(Category.FOOD, "vegetable", GameFactory.getImage("vegetable", -1), 60, false, 1);
		case 3 : return new Product(Category.FOOD, "mushroom", GameFactory.getImage("mushroom", -1), 30, false, 1);
		}
		return null;
	}

	public static Product newProduct(Category category) {
		switch(category) {
		case AMMO : return newAmmo();
		case FOOD : return newFood();
		case WEAPON : return newWeapon();
		default : return null;
		}
	}

	public static Account newAccount (String name, int raceId) {
		Account account = new Account(name, GameFactory.getImage("race", raceId), 1000, "aaa");
		account.addProduct(newWeapon());
		account.addProduct(newAmmo());
		account.addProduct(newFood());
		account.getStore().add(newWeapon());
		account.getStore().add(newAmmo());
		account.getStore().add(newFood());
		return account;
	}
}
