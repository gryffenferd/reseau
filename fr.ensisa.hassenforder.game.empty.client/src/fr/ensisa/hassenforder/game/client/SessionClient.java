package fr.ensisa.hassenforder.game.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Player;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

public class SessionClient implements Protocol {	//Voir si le protocole est utile ici

	private Socket connection;
    private String name;
    private long id;
	private String image;
	private int cash;
	private String localImage;
	private String filename;
	private byte[] content;
	
	public SessionClient (Socket connection) {
		this.connection = connection;
		this.name = null;
		this.id = 0;
	}

	public boolean connect (String username, String userpassword) throws IOException {
		
			Writer writerClient = new Writer(connection.getOutputStream());
			writerClient.writerConnect(username, userpassword);
			writerClient.send();
			Reader readerClient = new Reader(connection.getInputStream());
			readerClient.receive();
			this.id = readerClient.getUserID();
			this.name = username;
			if(readerClient.getConnectedState()){
			}
			return readerClient.getConnectedState();
	}

	public boolean disconnect () throws IOException {
		
			Writer writerClient = new Writer(connection.getOutputStream());
			writerClient.setUserID(id);
			writerClient.writerDisconnect(name,id);
			writerClient.send();
			Reader readerClient = new Reader(connection.getInputStream());
			readerClient.receive();
			return readerClient.getConnectedState();
	}

	public boolean addCash (int amount) throws IOException {
		Writer writerClient = new Writer(connection.getOutputStream());
		writerClient.writerAdd(name, id,amount);
		writerClient.send();
		Reader readerClient = new Reader(connection.getInputStream());
		readerClient.receive();
		return readerClient.getAdd();
	}

	public boolean clearProducts () throws IOException {
		Writer writerClient = new Writer(connection.getOutputStream());
		writerClient.writerClear(name, id);
		writerClient.send();
		Reader readerClient = new Reader(connection.getInputStream());
		readerClient.receive();
		return readerClient.getClear();
	}

	public boolean consumeProducts () throws IOException {
		Writer writerClient = new Writer(connection.getOutputStream());
		writerClient.writerConsume(name, id);
		writerClient.send();
		Reader readerClient = new Reader(connection.getInputStream());
		readerClient.receive();
		return readerClient.getConsume();
	}

	public Player getStatistics () throws IOException {		//
		Writer writerClient = new Writer(connection.getOutputStream());
		writerClient.writerStatisctics(name, id);
		writerClient.send();
		Reader readerClient = new Reader(connection.getInputStream());
		readerClient.receive();		
		cash = readerClient.getCash();		
		filename = readerClient.getFilename();		
		content = readerClient.getContent();		
		image = "./res/"+filename+".png";
		FileHelper.writeContent(image, content);		
		Player player1 = new Player(name,image,cash);
		return player1;	
	}

	public Collection<Product> getProducts() throws IOException {
		Writer writerClient = new Writer(connection.getOutputStream());
		writerClient.writerProducts(name, id);
		writerClient.send();
		Reader readerClient = new Reader(connection.getInputStream());
		readerClient.receive();
		Collection<Product> products = readerClient.getCollectionProducts();
		return products;
	}

	public Collection<Product> getShop () throws IOException {
		Writer writerClient = new Writer(connection.getOutputStream());
		writerClient.writerShop(name, id);
		writerClient.send();
		Reader readerClient = new Reader(connection.getInputStream());
		readerClient.receive();
		Collection<Product> shop = readerClient.getCollectionShop();
		return shop;
	}

	public boolean refreshShop () throws IOException {
		Writer writerClient = new Writer(connection.getOutputStream());
		writerClient.writerRefresh(name, id);
		writerClient.send();
		Reader readerClient = new Reader(connection.getInputStream());
		readerClient.receive();
		return readerClient.getRefresh();
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

	public String getImage (String image) {
			return image;
	}
}
