package fr.ensisa.hassenforder.game.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Account;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.game.model.User;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

public class SessionServer implements Protocol {

	private Socket connection;
	private Document document;

	public SessionServer(Document document, Socket connection) {
		this.document = document;
		this.connection = connection;
	}

	public boolean operate() {
		try {
			Writer writer = new Writer(connection.getOutputStream());
			Reader reader = new Reader(connection.getInputStream());
			reader.receive();

			switch (reader.getType()) {
			case 0:
				return false; // socket closed
			case CONNECTION:
				User usercurrent = document.connect(reader.getUserName(),
						reader.getUserPassword());
				if (usercurrent == null)
					writer.koConnect();
				else
					writer.okConnect(usercurrent.getId());
			break;
			case DISCONNECTION:
					if (document.disconnect(reader.getUserName(),
							reader.getUserId()))
						writer.okDisconnect();
					else
						writer.ko();
			break;
			case STATISTICS_OK:
				Account account = document.getStatistics(reader.getUserName(), reader.getUserId());
				int cash = account.getCash();
				String race = account.getImage();
				byte[] content = FileHelper.readContent("./res/"+race+".png");
				long size = FileHelper.getFileSize("./res/"+race+".png");
				System.out.println("cash: "+cash);
				System.out.println("race: "+race);					
				System.out.println("size: "+size);				
				writer.statistics(cash,size,race,content);		
			break;
			case -1:
				break;
			default:
				return false; // connection jammed

			}
			writer.send();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
