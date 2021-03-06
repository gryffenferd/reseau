package fr.ensisa.hassenforder.network;

public interface Protocol {

	static final public int GAME_PORT_ID = 5552;

	public static final Object EXIT_TEXT = "exit";

	public static final int CONNECTION = 1;
	public static final int CONNECTION_FAILED = 2;
	public static final int DISCONNECTION = 3;
	public static final int FAILED= 4;
	public static final int STATISTICS_OK = 5;
	public static final int ADD = 6;
	public static final int SUB = 7;
	public static final int PRODUCT = 8;
	public static final int CLEAR = 9;
	public static final int CONSUME = 10;
	public static final int SHOP = 11;
	public static final int REFRESH=12;
	public static final int BUY=13;
	public static final int SELL=14;
}
