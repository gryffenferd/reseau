package fr.ensisa.hassenforder.game.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Account;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.game.model.User;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

public class SessionServer {

	private Socket connection;
	private Document document;
	
	public SessionServer (Document document, Socket connection) {
		this.document = document;
		this.connection = connection;
	}
	
	public boolean operate() {
		try {
			Writer writer = new Writer (connection.getOutputStream());
			Reader reader = new Reader (connection.getInputStream());
			reader.receive ();
			
			switch (reader.getType ()) {
			case 0 : return false; // socket closed
			case 1 :
				System.out.println("I'm in the case 1 SessionServer");
				User usercurrent = document.connect(reader.getUsername(), reader.getUserpassword());
				if(usercurrent==null)
					writer.ok((long)0,false);
				else
					writer.ok(usercurrent.getId(),true);
					
				break;
			case -1 :
				break;
			default: return false; // connection jammed
			
			}
			writer.send ();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	

}
