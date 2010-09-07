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

public interface PresenceListener {
	void onContactAvaiable(Contact contact);

	void onContactBusy(Contact contact);

	void onContactIdle(Contact contact);

	void onContactBeRightBack(Contact contact);

	void onContactAway(Contact contact);

	void onContactOnPhone(Contact contact);

	void onContactOutLunch(Contact contact);

}
