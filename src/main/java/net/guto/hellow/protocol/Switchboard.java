package net.guto.hellow.protocol;

import net.guto.hellow.core.listener.ChatListener;
import net.guto.hellow.core.pojos.Message;

public abstract class Switchboard extends Msnp {

	public static int WIN_MOBILE = 0x01;
	public static int EXPLORER_8 = 0x02;
	public static int INK_GIF = 0x04;
	public static int INK_ISF = 0x08;
	public static int WEBCAM = 0x10;
	public static int MULTIPLAK = 0x20;
	public static int DIRECTIM = 0x4000;
	public static int WINKS = 0x8000;
	public static int SIP = 0x100000;
	public static int SHARINGFOLDER = 0x400000;
	public static int MSNC1 = 0x10000000; // Msn Msgr 6.0
	public static int MSNC2 = 0x20000000; // Msn Msgr 6.1
	public static int MSNC3 = 0x30000000; // Msn Msgr 6.2
	public static int MSNC4 = 0x40000000; // Msn Msgr 7.1
	public static int MSNC5 = 0x50000000; // Msn Msgr 7.5
	public static int MSNC6 = 0x60000000; // Msn Msgr 8.0
	public static int MSNC7 = 0x70000000; // Msn Msgr 8.1
	public static int MSNC8 = 0x70000000; // Msn Msgr 8.5

	private String host;
	private int port;

	@Override
	protected void connect(String host, int port) {
		this.host = host;
		this.port = port;
		super.connect(host, port);
	}

	@Override
	String getHost() {
		return host;
	}

	@Override
	Integer getPort() {
		return port;
	}

	private ChatListener chatListener;

	public void addChatListener(ChatListener chatListener) {
		this.chatListener = chatListener;
	}

	public void onMessage(String message) {
		if (chatListener != null) {
			Message msg = new Message(message);
			chatListener.onMessage(msg);
		}
	}

	public abstract void say(String message);
}
