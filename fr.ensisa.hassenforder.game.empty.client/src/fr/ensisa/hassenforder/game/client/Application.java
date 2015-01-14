package fr.ensisa.hassenforder.game.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import fr.ensisa.hassenforder.game.model.Player;
import fr.ensisa.hassenforder.game.model.Product;
import fr.ensisa.hassenforder.network.Protocol;

/**
 *
 * @author michel
 */
public class Application {
    
	private Socket socket;
    private SessionClient network;
    private GUI gui;
    
    public Application() throws Exception {
        gui = new GUI (this);
		socket = null;
		network = null;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application app;
		try {
			app = new Application();
	        app.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    void start () {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.setVisible(true);
            }
        });
    }

/*
 * DEBUG API
 */
    private int count = 0;
    public void passThrough (String id) {
    	++count;
    	System.out.println("Pass Through "+id+" count "+count);
    }

    public boolean doAddCash(int amount) {
    	passThrough ("doAddCash");
    	return network.addCash(amount);
    }

    public boolean doSubCash(int amount) {
    	passThrough ("doSubCash");
    	return network.addCash(-amount);
    }

    public boolean doClearProducts() {
    	passThrough ("doClearProducts");
    	return network.clearProducts();
    }

    public boolean doConsumeProducts() {
    	passThrough ("doConsumeProducts");
    	return network.consumeProducts();
    }

    /*
     * REALEASE API
     */

    public boolean doConnect(String name, String password) {
    	passThrough ("doConnect");
		try {
			if (socket != null) return false;
			if (network != null) return false;
			socket = new Socket("localhost", Protocol.GAME_PORT_ID);
	        network = new SessionClient(socket);
	    	return network.connect(name, password);
		} catch (Exception e) {
			socket = null;
			network = null;
			return false;
		}
    }

    public boolean doDisconnect() {
    	passThrough ("doDisconnect");
		if (socket == null) return false;
		if (network == null) return false;
		boolean result = network.disconnect();
		network = null;
		try {
			socket.close();
		} catch (IOException e) {
		}
		socket = null;
		return result;
    }

    public Player doGetStatistics () {
    	passThrough ("doGetStatistics");
    	return network.getStatistics();
    }

    public Collection<Product> doGetProducts () {
    	passThrough ("doGetProducts");
    	return network.getProducts();
    }

    public Collection<Product> doGetShop () {
    	passThrough ("doGetShop");
    	return network.getShop();
    }

    public boolean doRefreshShop() {
    	passThrough ("doRefreshShop");
    	return network.refreshShop();
    }

    public boolean doBuyProduct (String productName) {
    	passThrough ("doBuyProduct");
    	return network.buyProduct (productName);
    }

    public boolean doSellProduct (String productName) {
    	passThrough ("doSellProduct");
    	return network.sellProduct (productName);
    }

    public String doGetImage (String name) {
    	passThrough ("doGetImage");
    	return network.getImage(name);
    }

}
