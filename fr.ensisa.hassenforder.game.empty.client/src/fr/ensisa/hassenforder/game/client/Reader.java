package fr.ensisa.hassenforder.game.client;

import java.io.InputStream;
import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader implements Protocol {
	
	private boolean connected;
	private long userID;
	private int cash;
	private long length;
	private String filename;
	private byte[] content;
	private boolean add;

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0:						//Si 0, risque de valoir la valeur d'une var non initialisée
			break;
		case CONNECTION: 					//connect si marche (réponse du serveur = ok)
			readerConnect();
			break;
		case CONNECTION_FAILED:
			readerConnectFailed();	//connect si ne marche pas (réponse du serveur = ko)
			break;
		case DISCONNECTION:						//disconnect ok
			readerDisconnect();
			break;
		case FAILED:
			readerDisconnect();		//disconnect ko
			break;
		case STATISTICS_OK:			//Cas où getStatistics est ok
			readerStatistics();
			break;
		case ADD:
			readerAdd();
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
		connected = readBoolean();
		//
	}
	
	public boolean getConnectedState(){	//can't set: you shall not pass.
		return this.connected;
	}
	
	public void readerAdd(){
		add = readBoolean();
	}
	
	
	public void readerStatistics(){	//Take cash + image
		cash = readInt();
		length = readLong();
		filename = readString();
		content = readBytes((int)length);
		
	}
	
	public long getUserID(){
		return this.userID;
	}
	
	public int getCash(){
		return this.cash;
	}
	
	public boolean getAdd(){
		return this.add;
	}
	
}
