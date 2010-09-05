package net.guto.hellow.core.listener;

import net.guto.hellow.core.pojos.Contact;
import net.guto.hellow.core.pojos.Group;

public interface ContactListener {
	void onAddContact(Contact contact);

	void onRemoveContact(Contact contact);

	void onAddGroup(Group group);

	void onRemoveGroup(Group group);
}
