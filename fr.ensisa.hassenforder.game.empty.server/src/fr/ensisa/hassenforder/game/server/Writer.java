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
		writeInt(FAILED);
		writeBoolean(false);
	}
	
	/* 5 */
	public void statistics(int cash, long lengthImage, String nameImage, byte[] content){
		writeInt(STATISTICS_OK);
		writeInt(cash);
		writeLong(lengthImage);
		writeString(nameImage);
		writeBytes(content);
	}

	public void productD(int size){
		writeInt(PRODUCT);
		writeInt(size);
	}
	
	public void product(int category, String name, String image, long size, byte[] content, int duration, long time, boolean stackable, int count){
		writeInt(category);
		writeString(name);
		writeString(image);
		writeLong(size);
		writeBytes(content);
		writeInt(duration);
		writeLong(time);
		writeBoolean(stackable);
		writeInt(count);
	}

	public void clear() {
		writeInt(CLEAR);
		writeBoolean(true);		
	}

	
	public void consume() {
		writeInt(CONSUME);
		writeBoolean(true);		
	}

	public void shopD(int j){
		writeInt(SHOP);
		writeInt(j);
	}
	
	public void shop(int category, String name, String image, long size, byte[] content, int duration, long time, boolean stackable, int count) {
		writeInt(category);
		writeString(name);
		writeString(image);
		writeLong(size);
		writeBytes(content);
		writeInt(duration);
		writeLong(time);
		writeBoolean(stackable);
		writeInt(count);
	}

	public void sub() {
		writeInt(SUB);
		writeBoolean(true);	
	}

	public void add() {
		writeInt(ADD);
		writeBoolean(true);	
	}

	public void refresh() {
		writeInt(REFRESH);
		writeBoolean(true);		
	}
			
}
