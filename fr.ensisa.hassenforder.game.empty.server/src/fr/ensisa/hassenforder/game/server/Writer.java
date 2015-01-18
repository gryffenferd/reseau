package fr.ensisa.hassenforder.game.server;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class Writer extends BasicAbstractWriter implements Protocol {

	public Writer(OutputStream outputStream) {
		super(outputStream);
	}

	public void okConnect(Long id) {
		writeInt(CONNECTION);
		writeBoolean(true);
		writeLong(id);
	}

	public void koConnect() {
		writeInt(CONNECTION_FAILED);
		writeBoolean(false);
	}

	public void okDisconnect() {
		writeInt(DISCONNECTION);
		writeBoolean(true);
	}

	public void ko() {
		writeInt(FAILED);
		writeBoolean(false);
	}

	public void statistics(int cash, long lengthImage, String nameImage,
			byte[] content) {
		writeInt(STATISTICS_OK);
		writeInt(cash);
		writeLong(lengthImage);
		writeString(nameImage);
		writeBytes(content);
	}

	public void productD(int size) {
		writeInt(PRODUCT);
		writeInt(size);
	}

	public void product(int category, String name, String image, long size,
			byte[] content, int duration, long time, boolean stackable,
			int count) {
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

	public void shopD(int j) {
		writeInt(SHOP);
		writeInt(j);
	}

	public void shop(int category, String name, String image, long size,
			byte[] content, int duration, long time, boolean stackable,
			int count) {
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
