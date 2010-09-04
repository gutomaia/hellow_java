/*  HellowJava, alpha version
 *  (c) 2005-2010 Gustavo Maia Neto (gutomaia)
 *
 *  HellowJava and all other Hellow flavors will be always
 *  freely distributable under the terms of an GPLv3 licence.
 *
 *  Human Knowledge belongs to the World!
 *--------------------------------------------------------------------------*/

package net.guto.hellow.protocol;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.guto.hellow.core.Authentication;
import net.guto.hellow.core.listener.ConnectionListener;
import net.guto.hellow.core.listener.ContactListener;
import net.guto.hellow.core.listener.PresenceListener;

public abstract class Notification extends Msnp {

	abstract String getProtocolVersion();

	abstract String getLocale();

	abstract String getOSType();

	abstract String getOSVersion();

	abstract String getArch();

	abstract String getClientName();

	abstract String getClientVersion();

	abstract String getClientId();

	abstract String getIdCode();

	abstract String getCode();
	
	private Authentication _authenticationHandle;

	public void setAuthenticationHandle(Authentication authenticationHandle){
		_authenticationHandle = authenticationHandle;
	}
	
	public void authenticate(String lc){
		_passport = _authenticationHandle.authenticate(_username, _password, lc);
	}
	
	private String _username;
	private String _password;
	
	

	protected final String getUsername() {
		return _username;
	}

	protected final String getPassword() {
		return _password;
	}

	@Override
	protected void connect(String host, int port) {
		super.connect(host, port);
		send(ver());
		listen();
	}

	public void login(String username, String password) {
		_username = username;
		_password = password;
		connect(getHost(), getPort());
	}
	
	public void logout(){
		send(out());
		disconnect();
	}

	public String ver() {
		return getProtocolVersion() + EL;
	}

	public String cvr() {
		_trid = 1;
		return "CVR " + _trid + " " + getLocale() + " " + getOSType() + " "
				+ getOSVersion() + " " + getArch() + " " + getClientName()
				+ " " + getClientVersion() + " " + getClientId() + " "
				+ getUsername() + EL;
	}
	
	private ConnectionListener connectionListener = null;
	private ContactListener contactListener = null;
	private PresenceListener presenceListener = null;
	
	public final void addConnectionListener(ConnectionListener connectionListener){
		this.connectionListener = connectionListener;
	}
	
	public final void addContactListener(ContactListener contactListener){
		this.contactListener = contactListener;
	}
	
	public final void addPresenceListener(PresenceListener presenceListener){
		this.presenceListener = presenceListener;
	}
	
	//Connection
	protected final void onLogged(){
		//if (connectionListener != null) connectionListener.onLogged(event);
		
	}
	
	protected final void onConnected(){
		//if (connectionListener != null) connectionListener.onConnected(event)
	}
	
	public String usr() {
		if (_passport == null) {
			return "USR " + _trid + " TWN I " + _username + EL;
		}
		return "USR " + _trid + " TWN S " + _passport + EL;

	}

	public String syn() {
		return "SYN 1 0" + EL;// get List Version
	}

	public String chg() {
		return "CHG " + _trid + " NLN 0" + EL;// get Initial Presence
	}

	public String challenger(String chl) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(chl.concat(getCode()).getBytes());
			byte test[] = md.digest();
			BigInteger hash = new BigInteger(1, test);
			digest = hash.toString(16);
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
		}
		return digest;
	}

	public String qry(String code) {
		String digest = challenger(code);
		return "QRY " + _trid + " " + getIdCode() + " 32" + EL + digest;
	}

	public String out() {
		return "OUT" + EL;
	}

}
