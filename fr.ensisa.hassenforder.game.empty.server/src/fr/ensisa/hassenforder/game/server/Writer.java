package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void ok(Long id){
		System.out.println("ok writer");
		writeInt(1);			//message connect avec discriminant 1
		writeBoolean(true);		//message true pour dire ok
		writeLong(id);			//envoie l'id de l'utilisateur
	}
	
	public void ko(){
		System.out.println("ko writer");
		writeInt(2);
		writeBoolean(false);
	}
}
