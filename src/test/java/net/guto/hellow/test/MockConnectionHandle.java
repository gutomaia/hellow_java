package net.guto.hellow.test;

import net.guto.hellow.core.ConnectionHandle;

public class MockConnectionHandle implements ConnectionHandle {

	protected String host = null;
	protected int port = -1;
	protected String sended;
	protected String received;

	@Override
	public void connect(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void disconnect() {
		this.host = null;
		this.port = -1;
	}
	
	
	@Override
	public void send(String command) {
		this.sended = command;
	}
	
	public void receive(String command){
		this.received = command;
	}

	@Override
	public String nextCommand() {
		return null;
	}

	@Override
	public boolean hasMoreCommands() {
		return false;
	}

}
