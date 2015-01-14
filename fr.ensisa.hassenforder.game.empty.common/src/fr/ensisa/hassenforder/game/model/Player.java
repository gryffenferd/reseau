package fr.ensisa.hassenforder.game.model;

public class Player {

	private String name;
	private String image;
	protected int cash;

	public Player(String name, String image, int cash) {
		super();
		this.name = name;
		this.image = image;
		this.cash = cash;
	}

	public String getName() {
		return name;
	}
	
	public String getImage() {
		return image;
	}
	
	public int getCash() {
		return cash;
	}
	
}
