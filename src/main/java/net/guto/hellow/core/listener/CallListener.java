package net.guto.hellow.core.listener;

public interface CallListener {

	public void onRing(String call, String server, int port, String cki,
			String username, String nick);
}
