package fr.ensisa.hassenforder.game.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter implements Protocol {
	private long userID;

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}
	
	public void setUserID(long userID){
		this.userID = userID;
	}

	public void writerConnect (String username, String userpassword){
		writeInt(CONNECTION);
		writeString(username);
		writeString(userpassword);
	}
	
	public void writerDisconnect (String name, long id){
		writeInt(DISCONNECTION);
		writeString(name);
		writeLong(id);
	}
	
	public void writerStatisctics(String name, long id){
		writeInt(STATISTICS_OK);
		writeString(name);
		writeLong(id);
	}
	
	public void writerProducts(String name, long id){
		writeInt(PRODUCT);
		writeString(name);
		writeLong(id);
	}
	
	public void writerClear(String name, long id){
		writeInt(CLEAR);
		writeString(name);
		writeLong(id);
	}
	
	public void writerShop(String name, long id){
		writeInt(SHOP);
		writeString(name);
		writeLong(id);
	}
	
	public void writerAdd(String name, long id,int amount){
		writeInt(ADD);
		writeString(name);
		writeLong(id);
		writeInt(amount);
	}
	
	public void writerConsume(String name, long id){
		writeInt(CONSUME);
		writeString(name);
		writeLong(id);
	}
	
	public void writerRefresh(String name, long id){
		writeInt(REFRESH);
		writeString(name);
		writeLong(id);
	}
	
	public void writerBuy(String name, long id, String productName){
		writeInt(BUY);
		writeString(name);
		writeLong(id);
		writeString(productName);
	}
	
	public void writerSell(String name, long id, String productName){
		writeInt(SELL);
		writeString(name);
		writeLong(id);
		writeString(productName);
	}
	
	
}
