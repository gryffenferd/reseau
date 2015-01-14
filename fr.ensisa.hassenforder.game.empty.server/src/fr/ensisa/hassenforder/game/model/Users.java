package fr.ensisa.hassenforder.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Users {

	private Map<String, User> users;
	
	public Users() {
		super();
	}

	private Map<String, User> getUsers() {
		if (users == null) users = new TreeMap<String, User>();
		List<User> toRemove = new ArrayList<User> ();
		for (User u : users.values()) {
			if (u.isElapsed()) {
				toRemove.add(u);
			}
		}
		for (User u : toRemove) {
			users.remove(u.getName());
		}
		return users;
	}

	public boolean exists (User user) {
		return getUsers().containsKey(user.getName());
	}

	public boolean exists (String name) {
		return getUsers().containsKey(name);
	}

	public User get (String name) {
		return getUsers().get (name);
	}

	public boolean add (User user) {
		if (user == null) return false;
		if (exists(user)) return false;
		getUsers().put(user.getName(), user);
		return true;
	}

	public boolean remove (String name) {
		if (name == null) return false;
		if (! exists(name)) return false;
		getUsers().remove(name);
		return true;
	}

}
