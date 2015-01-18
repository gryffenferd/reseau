package fr.ensisa.hassenforder.game.server;

import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader implements Protocol {

	private String userName;
	private String userPassword;
	private Long userId;
	private int amount;
	private String productName;

	public Reader(InputStream inputStream) {
		super(inputStream);
	}

	public void receive() {
		type = readInt();
		switch (type) {
		case 0:
			break;
		case CONNECTION:
			connect();
			break;
		case DISCONNECTION:
			nameId();
			break;
		case STATISTICS_OK:
			nameId();
			break;
		case ADD:
			cash();
			break;
		case SUB:
			cash();
			break;
		case PRODUCT:
			nameId();
			break;
		case SHOP:
			nameId();
			break;
		case CLEAR:
			nameId();
			break;
		case CONSUME:
			nameId();
			break;
		case REFRESH:
			nameId();
			break;
		case SELL:
			shop();
			break;
		case BUY:
			shop();
			break;
		default:
			break;
		}
	}

	private void shop() {
		userName = readString();
		userId = readLong();
		productName = readString();		
	}

	private void cash() {
		userName = readString();
		userId = readLong();
		amount = readInt();
	}

	private void nameId() {
		userName = readString();
		userId = readLong();
	}

	public void connect() {
		userName = readString();
		userPassword = readString();
	}

	public String getUserName() {
		return this.userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public Long getUserId() {
		return this.userId;
	}

	public int getAmount() {
		return this.amount;
	}

	public String getProductName() {
		return productName;
	}

}
