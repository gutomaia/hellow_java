package net.guto.hellow.core.listener;

import net.guto.hellow.core.pojos.ContactEvent;

public interface ContactListener {
	void onAddContact(ContactEvent event);

	void onRemoveContact(ContactEvent event);
}
