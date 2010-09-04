package net.guto.hellow.core.listener;

import net.guto.hellow.core.pojos.ContactEvent;

public interface PresenceListener {
	void onContactAvaiable(ContactEvent event);

	void onContactBusy(ContactEvent event);

	void onContactIdle(ContactEvent event);

	void onContactBeRightBack(ContactEvent event);

	void onContactAway(ContactEvent event);

	void onContactOnPhone(ContactEvent event);

	void onContactOutLunch(ContactEvent event);

}
