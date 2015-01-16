package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;
import java.util.Collection;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter implements Protocol {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	/* 1 */
	public void okConnect(Long id){
		writeInt(CONNECTION);			//message connect avec discriminant 1
		writeBoolean(true);		//message true pour dire ok ou ko
		writeLong(id);			//envoie l'id de l'utilisateur
	}
	
	/* 2 */
	public void koConnect(){
		writeInt(CONNECTION_FAILED);
		writeBoolean(false);
	}
	
	/* 3 */
	public void okDisconnect(){
		writeInt(DISCONNECTION);
		writeBoolean(true);
	}
	
	/* 4 */
	public void ko(){
		writeInt(DISCONNECTION_FAILED);
		writeBoolean(false);
	}
	
	/* 5 */
	public void okStatistics(int cash, String fileName, byte[] content){
		writeInt(STATISTICS_OK);
		writeInt(cash);
	}
		
			
}
