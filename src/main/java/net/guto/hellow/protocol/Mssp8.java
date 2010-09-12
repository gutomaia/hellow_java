/*  HellowJava, alpha version
 *  (c) 2005-2010 Gustavo Maia Neto (gutomaia)
 *
 *  HellowJava and all other Hellow flavors will be always
 *  freely distributed under the terms of an GPLv3 license.
 *
 *  Human Knowledge belongs to the World!
 *--------------------------------------------------------------------------*/

package net.guto.hellow.protocol;

import net.guto.hellow.core.pojos.Message;

public class Mssp8 extends Switchboard {

	private String _username;
	private String _cki;
	private String _call;
	private String host;
	private int port;

	public Mssp8(String username, String call, String host, int port,
			String cki, String caller, String nick) {
		_username = username;
		this.host = host;
		this.port = port;
		_cki = cki;
		_call = call;
	}
	
	public void start(){
		super.connect(host, port);
		send(ans());
		listen();
	}

	@Override
	public void execute(String command) {
		String params[] = command.trim().split(" ");
		String cmd = params[0];
		if (cmd.equals("IRO")) {
			// IRO 1 1 2 luke@rebels.org Luke\r\n
			// IRO 1 2 2 emperor@empire.com Emperor\r\n
		} else if (cmd.equals("ANS")) {
			// ANS 1 OK\r\n
		} else if (cmd.equals("USR")) {
		} else if (cmd.equals("CAL")) {
		} else if (cmd.equals("JOI")) {
		} else if (cmd.equals("MSG")) {
			onMessage(command);
		} else if (cmd.equals("ACK")) {
		} else if (cmd.equals("NAK")) {
		} else if (cmd.equals("BYE")) {
		} else if (cmd.equals("OUT")) {
		}
	}

	public String ans() {
		return "ANS " + _trid + " " + _username + " " + _cki + " " + _call + EL;
	}

	public String usr() {
		// if (this._passport != null)
		String username = null;
		return "USR " + _trid + username + " " + _passport + EL;
	}

	public String cal(String contact) {
		return "CAL " + _trid + " " + contact + EL;
	}
	
	public void say(String message){
		Message msg = new Message(message, 5);
		send(msg.send());
	}
}