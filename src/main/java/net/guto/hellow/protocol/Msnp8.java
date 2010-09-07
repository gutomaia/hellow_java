/*  HellowJava, alpha version
 *  (c) 2005-2010 Gustavo Maia Neto (gutomaia)
 *
 *  HellowJava and all other Hellow flavors will be always
 *  freely distributed under the terms of an GPLv3 license.
 *
 *  Human Knowledge belongs to the World!
 *--------------------------------------------------------------------------*/

package net.guto.hellow.protocol;

import java.util.StringTokenizer;

public class Msnp8 extends Notification {

	private static final String MSN_HOST = "messenger.hotmail.com";
	private static final int MSN_PORT = 1863;

	private static final String PROTOCOL_VERSION = "MSNP8";
	private static final String LOCALE_ID = "0x0409";
	private static final String OS_TYPE = "win";
	private static final String OS_VERSION = "4.10"; // windows 98;
	private static final String CPU_ARCHITECTURE = "i386";
	private static final String CLIENT_NAME = "MSNMSGR";
	private static final String CLIENT_VERSION = "6.0.0602"; // 5.0.0544
	// Challenger
	private static final String CLIENT_ID = "MSMSGS";
	private static final String CLIENT_IDCODE = "msmsgs@msnmsgr.com";
	// needed for the challenger
	private static final String CLIENT_CODE = "Q1P7W2E4J9R8U3S5";

	@Override
	String getHost() {
		return MSN_HOST;
	}

	@Override
	Integer getPort() {
		return MSN_PORT;
	}

	@Override
	String getProtocolVersion() {
		return PROTOCOL_VERSION;
	}

	@Override
	String getLocale() {
		return LOCALE_ID;
	}

	@Override
	String getOSType() {
		return OS_TYPE;
	}

	@Override
	String getOSVersion() {
		return OS_VERSION;
	}

	@Override
	String getArch() {
		return CPU_ARCHITECTURE;
	}

	@Override
	String getClientName() {
		return CLIENT_NAME;
	}

	@Override
	String getClientVersion() {
		return CLIENT_VERSION;
	}

	@Override
	String getClientId() {
		return CLIENT_ID;
	}

	@Override
	String getIdCode() {
		return CLIENT_IDCODE;
	}

	@Override
	String getCode() {
		return CLIENT_CODE;
	}

	@Override
	//TODO: see diferences on PHP and Java Impl
	public void execute(String command) {
		StringTokenizer token;
		String params [] = command.trim().split(" ");
		String cmd = params[0];
		String str1, str2;
		if (cmd.equals("VER")) {
			send(cvr());
		} else if (cmd.equals("CVR")) {
			send(usr());
		} else if (cmd.equals("XFR")) {
			token = new StringTokenizer(params[3]);
			str1 = token.nextToken(":");
			str2 = token.nextToken();
			connect(str1, Integer.valueOf(str2));
		} else if (cmd.equals("USR")) {
			if (params[2].equals("TWN")) {
				authenticate(params[4]);
				send(usr());
			} else if (params[2].equals("OK")) {
				onLogged();
				send(syn());
			}
		} else if (cmd.equals("SYN")) {
			send(chg());
		} else if (cmd.equals("CHL")) {
			send(qry(params[2]));
		} else if (cmd.equals("GTC")) {
		} else if (cmd.equals("BLP")) {
		} else if (cmd.equals("PRP")) {
		} else if (cmd.equals("LSG")) {
			onAddGroup(params[1],params[2]);
		} else if (cmd.equals("LST")) {
			if (params.length == 4) {
				//user, nick, lists, groups
				//onAddContact(params[1],params[2],params[3]);
			} else if (params.length == 5) {				
				onAddContact(params[1],params[2],params[3],params[4]);
			}
		} else if (params.equals("CHG")) {
			onConnected();
		} else if (params.equals("ILN")) {
		} else if (params.equals("ADD")) {
		} else if (params.equals("FLN")) {
		} else if (params.equals("NLN")) {
		}
	}
}
