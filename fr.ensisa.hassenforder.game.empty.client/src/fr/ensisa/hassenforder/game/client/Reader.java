package fr.ensisa.hassenforder.game.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Category;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.FileHelper;
import fr.ensisa.hassenforder.network.Protocol;

public class Reader extends BasicAbstractReader implements Protocol {
	
	private boolean connected;
	private long userID;
	private int cash;
	private long length;
	private String filename;
	private byte[] content;
	private boolean add;
	private int type=0;
	private Collection<Product> prod = null;
	private Collection<Product> shop = null;
	private boolean clear;
	private boolean consume;
	private boolean refresh;
	private boolean buy;
	private boolean sell;

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
		case 0:
			break;
		case CONNECTION:
			readerConnect();
			break;
		case CONNECTION_FAILED:
			readerConnectFailed();
			break;
		case DISCONNECTION:	
			readerDisconnect();
			break;
		case FAILED:
			readerDisconnect();
			break;
		case STATISTICS_OK:	
			readerStatistics();
			break;
		case ADD:
			readerAdd();
			break;
		case PRODUCT:
			readerProducts();
			break;
		case CLEAR:
			readerClear();
			break;
		case CONSUME:
			readerConsume();
			break;
		case SHOP:
			readerGetShop();
			break;
		case REFRESH:
			readerRefresh();
			break;
		case BUY:
			readerBuy();
			break;
		case SELL:
			readerSell();
			break;
		default:
			break;
		}
	}
	
	public void readerConnect(){
		connected = readBoolean();
		userID = readLong();
	}
	
	public void readerConnectFailed(){
		connected = readBoolean();
	}
	
	public void readerDisconnect(){
		connected = readBoolean();
	}
	
	public boolean getConnectedState(){
		return this.connected;
	}
	
	public void readerAdd(){
		add = readBoolean();
	}
	
	public void readerStatistics(){
		cash = readInt();
		length = readLong();
		filename = readString();
		content = readBytes(length);	
	}
	
	public void readerProducts(){
		int taille = readInt();
		readerProd(taille);	
	}
	
	public void readerProd(int taille){
		ArrayList<Product> products = new ArrayList<Product>();
		for (int i = 0; i<taille;i++){
			
			int category1 = readInt();//
			
			Category category = null;
			switch(category1){
			case 0:
				category = Category.WEAPON;
				break;
			case 1:
				category = Category.AMMO;
				break;
			case 2:
				category = Category.FOOD;
				break;
			}
			String nameP = readString();
			String imageTMP = readString();
			String imageP = "./res/"+imageTMP+".png";
			long sizeP = readLong();
			byte[] contentP = readBytes(sizeP);
			FileHelper.writeContent(imageP, contentP);	
			int duration = readInt();
			long timeP = readLong();
			boolean stackable = readBoolean();
			int countP = readInt();
			Product p = new Product(category,nameP,imageP,duration,stackable,countP,timeP);
			products.add(p);
		}
		prod = products;
	}
	
	public void readerGetShop(){
		int taille = readInt();
		readerShop(taille);	
	}
	
	public void readerShop(int taille){
		ArrayList<Product> shoptmp = new ArrayList<Product>();
		for (int i = 0; i<taille;i++){
			
			int category1 = readInt();
			Category category = null;
			switch(category1){
			case 0:
				category = Category.WEAPON;
				break;
			case 1:
				category = Category.AMMO;
				break;
			case 2:
				category = Category.FOOD;
				break;
			}
			String nameP = readString();
			String imageTMP = readString();
			String imageP = "./res/"+imageTMP+".png";
			long sizeP = readLong();
			byte[] contentP = readBytes(sizeP);
			FileHelper.writeContent(imageP, contentP);	
			int duration = readInt();
			long timeP = readLong();
			boolean stackable = readBoolean();
			int countP = readInt();
			Product p = new Product(category,nameP,imageP,duration,stackable,countP,timeP);
			shoptmp.add(p);
		}
		shop = shoptmp;
	}
	
	public void readerClear(){
		clear = readBoolean();
	}
	
	public void readerConsume(){
		consume = readBoolean();
	}
	
	public void readerRefresh(){
		refresh = readBoolean();
	}
	
	public void readerBuy(){
		buy = readBoolean();
	}
	
	public void readerSell(){
		sell = readBoolean();
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
	
	public String getFilename(){
		return this.filename;
	}
	
	public byte[] getContent(){
		return this.content;
	}
	
	public Collection<Product> getCollectionProducts(){
		return this.prod;
	}
	
	public Collection<Product> getCollectionShop(){
		return this.shop;
	}
	
	public boolean getClear(){
		return this.clear;
	}
	
	public boolean getConsume(){
		return this.consume;
	}
	
	public boolean getRefresh(){
		return this.refresh;
	}
	
	public boolean getBuy(){
		return this.buy;
	}
	
	public boolean getSell(){
		return this.sell;
	}
	
}
