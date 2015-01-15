package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	/* 1 */
	public void okConnect(Long id){
		writeInt(1);			//message connect avec discriminant 1
		writeBoolean(true);		//message true pour dire ok ou ko
		writeLong(id);			//envoie l'id de l'utilisateur
	}
	
	/* 2 */
	public void koConnect(){
		writeInt(2);
		writeBoolean(false);
	}
	
	/* 3 */
	public void okDisconnect(){
		writeInt(3);
		writeBoolean(true);
	}
	
	/* 4 */
	public void koDisconnect(){
		writeInt(4);
		writeBoolean(false);
	}
}
