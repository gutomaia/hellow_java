package net.guto.hellow.core.listener;

import javax.sql.ConnectionEvent;

public interface OnlineListener {
	void onContactOnline(ConnectionEvent event);
	void onContactOffline(ConnectionEvent event);
}
