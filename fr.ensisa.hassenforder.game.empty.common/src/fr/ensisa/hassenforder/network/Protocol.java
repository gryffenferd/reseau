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
	
}
