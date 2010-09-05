package net.guto.hellow.core.listener;

import net.guto.hellow.core.pojos.Contact;

public interface ContactListener {
	void onAddContact(Contact contact);

	void onRemoveContact(Contact contact);
}
