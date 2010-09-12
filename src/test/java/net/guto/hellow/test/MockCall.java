package net.guto.hellow.test;

import net.guto.hellow.core.listener.CallListener;

public class MockCall implements CallListener {

	String call = null;
	String server = null;
	int port = -1;
	String cki = null;
	String username = null;
	String nick = null;

	@Override
	public void onRing(String call, String server, int port, String cki,
			String username, String nick) {
		this.call = call;
		this.server = server;
		this.port = port;
		this.cki = cki;
		this.username = username;
		this.nick = nick;
	}

}
