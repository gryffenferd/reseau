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
	private long userId;

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
			case 1:
				User usercurrent = document.connect(reader.getUserName(),
						reader.getUserPassword());
				if (usercurrent == null)
					writer.koConnect();
				else
					writer.okConnect(usercurrent.getId());
				this.userId = usercurrent.getId();
				break;
			case 3:
				if (this.userId != reader.getUserId())
					writer.ko();
				else {
					if (document.disconnect(reader.getUserName(),
							reader.getUserId()))
						writer.okDisconnect();
					else
						writer.ko();
				}
				break;
			case 4:
				Account account = document.getStatistics("Paul", userId);
				int cash = account.getCash();
				System.out.println(cash);
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
