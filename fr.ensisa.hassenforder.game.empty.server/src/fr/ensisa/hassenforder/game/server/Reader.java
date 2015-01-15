package fr.ensisa.hassenforder.game.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {

	private String userName;
	private String userPassword;
	private Long userId;
	
	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0 :
			break;
		case 1:
			connect();
			break;
		case 3:
			disconnect();
		default:
			break;
		}
	}
	
	public void connect(){	
		System.out.println("connect Reader");
		userName = readString();
		userPassword = readString();
	}
	
	public void disconnect(){
		System.out.println("disconnect Reader");
		userName = readString();
		userId = readLong();
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getUserPassword(){
		return this.userPassword;
	}
	
	public Long getUserId(){
		return this.userId;
	}
	
}
