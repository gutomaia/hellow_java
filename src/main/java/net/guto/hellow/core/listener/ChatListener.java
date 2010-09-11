package net.guto.hellow.core.listener;

import net.guto.hellow.core.pojos.Message;

public interface ChatListener {
	public void onUserTyping();

	public void onMessage(Message message);
	
}
