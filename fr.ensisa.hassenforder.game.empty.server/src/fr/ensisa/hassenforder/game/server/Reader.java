package fr.ensisa.hassenforder.game.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader implements Protocol {

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
		case CONNECTION:
			connect();
		break;
		case DISCONNECTION:
			disconnect();
		break;
		case STATISTICS_OK:
			statistics();			
		break;
		case ADD:
			cash();
		break;
		case SUB:
			cash();
		break;
		default:
			break;
		}
	}
	
	private void cash() {
		userName=readString();
		userId=readLong();		
	}

	private void statistics() {
		userName=readString();
		userId=readLong();
	}

	public void connect(){	
		userName = readString();
		userPassword = readString();
	}
	
	public void disconnect(){
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
