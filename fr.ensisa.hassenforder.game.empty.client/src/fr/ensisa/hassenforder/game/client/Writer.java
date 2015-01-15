package fr.ensisa.hassenforder.game.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {
	private long userID;
	private String username;

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void writerConnect (String username, String userpassword){
		writeInt(1);
		writeString(username);
		this.username = username;
		writeString(userpassword);
	}
	
	public void writerDisconnect (){
		writeInt(3);
		writeString(username);
		writeLong(userID);
	}
	
	public void setUserID(long userID){
		this.userID = userID;
	}

}
