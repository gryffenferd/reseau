package fr.ensisa.hassenforder.game.client;

import java.io.InputStream;
import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader {
	
	private boolean connected;
	private long userID;
	private String name;

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0:						//Si 0, risque de valoir la valeur d'une var non initialisée
			break;
		case 1: 					//connect si marche (réponse du serveur = ok)
			readerConnect();
			break;
		case 2:
			readerConnectFailed();	//connect si ne marche pas (réponse du serveur = ko)
			break;
		case 3:						//disconnect ok
			readerDisconnect();
			break;
		case 4:
			readerDisconnect();		//disconnect ko
			break;
		default:
			break;
		}
	}
	
	public void readerConnect(){
		connected = readBoolean();
		userID = readLong();
		//System.out.println("OK");
	}
	
	public void readerConnectFailed(){
		connected = readBoolean();
		//System.out.println("KO");
	}

	
	public void readerDisconnect(){
		connected = !readBoolean();
		//
	}
	
	public boolean getConnectedState(){	//can't set: you shall not pass.
		return this.connected;
	}
	
	public long getUserID(){
		return this.userID;
	}
	
	public String getName(){
		return this.name = name;
	}
	
}
