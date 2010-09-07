/*  HellowJava, alpha version
 *  (c) 2005-2010 Gustavo Maia Neto (gutomaia)
 *
 *  HellowJava and all other Hellow flavors will be always
 *  freely distributed under the terms of an GPLv3 license.
 *
 *  Human Knowledge belongs to the World!
 *--------------------------------------------------------------------------*/

package net.guto.hellow.core.listener;

import net.guto.hellow.core.pojos.Contact;
import net.guto.hellow.core.pojos.Group;

public interface ContactListener {
	void onAddContact(Contact contact);

	void onRemoveContact(Contact contact);

	void onAddGroup(Group group);

	void onRemoveGroup(Group group);
}
