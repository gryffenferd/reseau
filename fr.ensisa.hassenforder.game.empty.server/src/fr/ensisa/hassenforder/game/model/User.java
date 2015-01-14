package fr.ensisa.hassenforder.game.model;

public class User {

	static final private int DURATION = 120;
	
	private String name;
	private long time;
	private long id;
	
	public User(String name) {
		super();
		this.name = name;
		this.id = time;
		resetTime();
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public boolean isElapsed() {
		return time + DURATION * 1000 < System.currentTimeMillis();
	}

	public void resetTime() {
		this.time = System.currentTimeMillis();
	}

}
