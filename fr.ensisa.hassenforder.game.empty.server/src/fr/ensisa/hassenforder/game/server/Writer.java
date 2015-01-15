package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void okConnect(Long id){
		System.out.println("ok writer connect");
		writeInt(1);			//message connect avec discriminant 1
		writeBoolean(true);		//message true pour dire ok ou ko
		writeLong(id);			//envoie l'id de l'utilisateur
	}
	
	public void koConnect(){
		System.out.println("ko writer connect");
		writeInt(2);
		writeBoolean(false);
	}
	
	public void okDisconnect(){
		System.out.println("ok writer disconnect");
		writeInt(3);
		writeBoolean(true);
	}
	
	public void koDisconnect(){
		System.out.println("ok writer disconnect");
		writeInt(4);
		writeBoolean(false);
	}
}
