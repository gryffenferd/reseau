package fr.ensisa.hassenforder.game.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;

import fr.ensisa.hassenforder.game.model.Account;
import fr.ensisa.hassenforder.game.model.Category;
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
				Account account = document.getStatistics(reader.getUserName(),
						reader.getUserId());
				int cash = account.getCash();
				String race = account.getImage();
				byte[] content = FileHelper.readContent("./res/" + race
						+ ".png");
				long size = FileHelper.getFileSize("./res/" + race + ".png");
				System.out.println("cash stat: " + cash);
				System.out.println("race: " + race);
				System.out.println("size: " + size);
				writer.statistics(cash, size, race, content);
			break;

			case ADD:
				if (document.addCash(
						reader.getUserName(),
						reader.getUserId(),reader.getAmount())) {
					writer.add();
				} else
					writer.ko();
			break;

			case SUB:
				if (document.addCash(
						reader.getUserName(),
						reader.getUserId(),
						- reader.getAmount())) {
					writer.sub();
				} else
					writer.ko();
			break;
			
			case PRODUCT:
				Collection<Product> p;
				p=document.getProducts(reader.getUserPassword(), reader.getUserId());
				int i=p.size();
				writer.productD(i);				
				Iterator<Product> itp = p.iterator() ;
				 while (itp.hasNext()) {
				    Product prod = itp.next();
				    writer.product(prod.getCategory().ordinal(), prod.getName(), prod.getImage(), prod.getDuration(), prod.getTime(), prod.isStackable(), prod.getCount());
				    System.out.println(prod.getCategory().ordinal()+","+prod.getName()+"," +prod.getImage()+","+ prod.getDuration()+","+ prod.getTime()+","+ prod.isStackable()+","+ prod.getCount());
				 }	   	
			break;				
			
			case CLEAR:
				if(document.clearProducts(reader.getUserName(), reader.getUserId()))
					writer.clear();
				else
					writer.ko();
			break;
			
			case CONSUME:
				if(document.consumeProducts(reader.getUserPassword(), reader.getUserId()))
					writer.consume();
				else
					writer.ko();
			break;
			
			case SHOP:
				Collection<Product> s;
				s=document.getShop(reader.getUserPassword(), reader.getUserId());
				writer.shopD();
				Iterator<Product> its = s.iterator() ;
				 while (its.hasNext()) {
				    Product prod = its.next();
				    writer.shop(prod.getCategory().ordinal(), prod.getName(), prod.getImage(), prod.getDuration(), prod.getTime(), prod.isStackable(), prod.getCount());
				   
				 }	
			break;
			
			case REFRESH:
				if(document.refreshShop(reader.getUserPassword(), reader.getUserId()))
					writer.refresh();
				else
					writer.ko();
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
