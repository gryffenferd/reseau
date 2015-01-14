package fr.ensisa.hassenforder.game.model;

public class Product {

	private Category category;
	private String name;
	private String image;
	private int duration;
	private long time;
	private boolean stackable;
	private int count;
	
	public Product(Category category, String name, String image,
			int duration, boolean stackable, int count) {
		super();
		this.category = category;
		this.name = name;
		this.image = image;
		this.duration = duration;
		this.time = 0;
		this.stackable = stackable;
		this.count = count;
	}

	public Product(Category category, String name, String image,
			int duration, boolean stackable, int count, long time) {
		super();
		this.category = category;
		this.name = name;
		this.image = image;
		this.duration = duration;
		this.time = time;
		this.stackable = stackable;
		this.count = count;
	}

	public Product(Product product) {
		this.category = product.category;
		this.name = product.name;
		this.image = product.image;
		this.duration = product.duration;
		this.time = System.currentTimeMillis();
		this.stackable = product.stackable;
		this.count = product.count;
	}

	public Category getCategory() {
		return category;
	}
	
	public String getName() {
		return name;
	}
	
	public String getImage() {
		return image;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public boolean isStackable() {
		return stackable;
	}
	
	public long getTime() {
		return time;
	}

	public int getCount() {
		return count;
	}
	
	public void addCount (int count) {
		this.count += count;
	}

	public boolean isElapsed () {
		if (duration == 0) return false;
		if (time == 0) return false;
		return getRemainingTime() < 0;
	}

	public long getRemainingTime() {
		if (duration == 0) return Long.MAX_VALUE;
		if (time == 0) return Long.MAX_VALUE;
		return time + duration * 1000 - System.currentTimeMillis();
	}

}
