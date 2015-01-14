package fr.ensisa.hassenforder.game.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Player;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

public class SessionClient {

	private Socket connection;
    private String name;
    private long id;
	
	public SessionClient (Socket connection) {
		this.connection = connection;
		this.name = null;
		this.id = 0;
	}

	public boolean connect (String username, String userpassword) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean disconnect () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean addCash (int amount) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean clearProducts () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean consumeProducts () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public Player getStatistics () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public Collection<Product> getProducts () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public Collection<Product> getShop () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public boolean refreshShop () {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean buyProduct (String productName) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean sellProduct (String productName) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public String getImage (String imageName) {
		try {
			if (true) throw new IOException ("not yet implemented");
			return "";
		} catch (IOException e) {
			return null;
		}
	}
}
