package fr.ensisa.hassenforder.game.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {

	private String username;
	private String userpassword;
	
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
		default:
			break;
		}
	}
	
	public void connect(){	
		username = readString();
		userpassword = readString();
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getUserpassword(){
		return this.userpassword;
	}
	
}
