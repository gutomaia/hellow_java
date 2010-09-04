package net.guto.hellow.core.listener;

public interface CommandListener {
	void reveivedCommand(String cmd);

	void sendedCommand(String cmd);
}
