/*  HellowJava, alpha version
 *  (c) 2005-2010 Gustavo Maia Neto (gutomaia)
 *
 *  HellowJava and all other Hellow flavors will be always
 *  freely distributable under the terms of an GPLv3 licence.
 *
 *  Human Knowledge belongs to the World!
 *--------------------------------------------------------------------------*/

package net.guto.hellow.protocol;

import java.util.StringTokenizer;

public class Msnc1 extends Switchboard {

	// USR 1 guto_n@hotmail.com 700432717.4772207.1043557
	// USR 1 OK guto_n@hotmail.com guto
	// CAL 2 buddy@msn.com
	// CAL 2 RINGING 700432717
	// JOI buddy@msn.com buddy
	// MSG 3 D 798

	@Override
	void execute(String cmd, String... params) {
		StringTokenizer token;
		String str1, str2;
		if (cmd.equals("USR")) {
		} else if (cmd.equals("CAL")) {
		} else if (cmd.equals("JOI")) {
		} else if (cmd.equals("MSG")) {
		} else if (cmd.equals("ACK")) {
		} else if (cmd.equals("BYE")) {

		} else if (cmd.equals("OUT")) {

		}
	}

	public String usr() {
		// if (this._passport != null)
		String username = null;
		return "USR " + _trid + username + " " + _passport + EL;
	}

	public String cal(String contact) {
		return "CAL " + _trid + " " + contact + EL;
	}

}