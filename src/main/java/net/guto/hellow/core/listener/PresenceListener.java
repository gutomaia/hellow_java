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
