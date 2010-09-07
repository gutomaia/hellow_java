/*  HellowJava, alpha version
 *  (c) 2005-2010 Gustavo Maia Neto (gutomaia)
 *
 *  HellowJava and all other Hellow flavors will be always
 *  freely distributed under the terms of an GPLv3 license.
 *
 *  Human Knowledge belongs to the World!
 *--------------------------------------------------------------------------*/

package net.guto.hellow.core.pojos;

public class Contact {
	private String user;
	private String nick;
	private int lists;
	private int groups[];

	public Contact() {
	}

	public Contact(String user, String nick, int lists, int groups[]) {
		this.user = user;
		this.nick = nick;
		this.lists = lists;
		this.groups = groups;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getLists() {
		return lists;
	}

	public void setLists(int lists) {
		this.lists = lists;
	}

	public int[] getGroups() {
		return groups;
	}

	public void setGroups(int[] groups) {
		this.groups = groups;
	}

}
