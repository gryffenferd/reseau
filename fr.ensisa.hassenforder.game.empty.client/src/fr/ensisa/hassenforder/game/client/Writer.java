package fr.ensisa.hassenforder.game.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

}
