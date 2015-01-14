package fr.ensisa.hassenforder.game.client;

import java.io.InputStream;
import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {
	
	private boolean connected;
	private long userID;

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0:				//Si 0, risque de valoir la valeur d'une var non initialisée
			break;
		case 1: 			//connect
			readerConnect();
			break;

//		case 3:				//disconnect
//			readerDisconnect();
//			break;
		default:
			break;
		}
	}
	
	public void readerConnect(){
		connected = readBoolean();
		userID = readLong();
	}
	

	
//	public void readerDisconnect(){
//		//
//	}
	
	public boolean getConnectedState(){	//can't set: you shall not pass.
		return this.connected;
	}
	
	public long getUserID(){
		return this.userID;
	}
	
}
