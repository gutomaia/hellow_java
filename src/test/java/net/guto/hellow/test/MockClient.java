package net.guto.hellow.test;

import net.guto.hellow.core.listener.ConnectionListener;
import net.guto.hellow.core.listener.ContactListener;
import net.guto.hellow.core.pojos.Contact;
import net.guto.hellow.core.pojos.Group;

public class MockClient implements ConnectionListener, ContactListener {
	boolean logged = false;
	boolean connected = false;

	Group group;
	Contact contact;

	@Override
	public void onLogged() {
		logged = true;
	}

	@Override
	public void onConnected() {
		connected = true;
	}

	@Override
	public void onAddContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public void onRemoveContact(Contact contact) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAddGroup(Group group) {
		this.group = group;

	}

	@Override
	public void onRemoveGroup(Group group) {
		// TODO Auto-generated method stub

	}
}
