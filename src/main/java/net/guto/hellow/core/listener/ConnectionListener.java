package net.guto.hellow.core.listener;

//TODO: create a ConnectionEvent;
import javax.sql.ConnectionEvent;

public interface ConnectionListener {
	void onLogged(ConnectionEvent event);

	void onConnected(ConnectionEvent event);
}
