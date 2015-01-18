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
		case 0:						//Si 0, risque de valoir la valeur d'une var non initialisée
			break;
		case CONNECTION: 					//connect si marche (réponse du serveur = ok)
			readerConnect();
			//System.out.println("connection");
			break;
		case CONNECTION_FAILED:
			readerConnectFailed();	//connect si ne marche pas (réponse du serveur = ko)
			//System.out.println("failed connexion");
			break;
		case DISCONNECTION:						//disconnect ok
			//System.out.println("deconnexion");
			readerDisconnect();
			break;
		case FAILED:
			readerDisconnect();		//disconnect ko
			//System.out.println("KO");
			break;
		case STATISTICS_OK:			//Cas où getStatistics est ok
			readerStatistics();
			//System.out.println("stats");
			//System.out.println("type stats"+type);
			break;
		case ADD:
			readerAdd();
			//System.out.println("add");
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
		//System.out.println("OK");
	}
	
	public void readerConnectFailed(){
		connected = readBoolean();
		//System.out.println("KO");
	}
	
	public void readerDisconnect(){
		connected = readBoolean();
		//
	}
	
	public boolean getConnectedState(){	//can't set: you shall not pass.
		return this.connected;
	}
	
	public void readerAdd(){
		add = readBoolean();
	}
	
	public void readerStatistics(){	//Take cash + image
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
			System.out.println(p.getCategory()+p.getName()+p.getImage()+p.getDuration());
			shoptmp.add(p);
			
			
			System.out.println(shoptmp);
		}
		shop = shoptmp;
		//System.out.println("ici"+shop);
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
