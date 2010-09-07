/*  HellowJava, alpha version
 *  (c) 2005-2010 Gustavo Maia Neto (gutomaia)
 *
 *  HellowJava and all other Hellow flavors will be always
 *  freely distributed under the terms of an GPLv3 license.
 *
 *  Human Knowledge belongs to the World!
 *--------------------------------------------------------------------------*/

package net.guto.hellow.core;

public interface ConnectionHandle {
	public void connect(String host, int port);

	public void disconnect();

	public void send(String command);

	public String nextCommand();

	public boolean hasMoreCommands();
}
