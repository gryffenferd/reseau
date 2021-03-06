package fr.ensisa.hassenforder.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BasicAbstractReader {

	protected DataInputStream inputStream;
	protected int type;

	public BasicAbstractReader(InputStream inputStream2) {
		this.inputStream = new DataInputStream (inputStream2);
	}

	protected boolean readBoolean() {
		try {
			int x = inputStream.readInt();
			if (x != 0) return true;
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	protected short readShort () {
		try {
			return inputStream.readShort();
		} catch (IOException e) {
			return 0;
		}
	}

	protected int readInt() {
		try {
			return inputStream.readInt();
		} catch (IOException e) {
			return 0;
		}
	}

	protected long readLong() {
		try {
			return inputStream.readLong();
		} catch (IOException e) {
			return 0;
		}
	}

	protected String readString() {
		try {
			return inputStream.readUTF();
		} catch (IOException e) {
			return "";
		}
	}

	protected byte[] readBytes(long length){
		try{
			int l;
			byte[] input = new byte[(int) length]; 
			for(l=0; l<length ; l++)
			{
					input[l]=inputStream.readByte();
			}
			return input;
		} catch (IOException e) {
			return null;
		}
	}
	
	public int getType() {
		return type;
	}

}