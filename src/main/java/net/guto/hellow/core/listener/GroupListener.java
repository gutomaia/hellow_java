package net.guto.hellow.core.listener;

import net.guto.hellow.core.pojos.GroupEvent;

public interface GroupListener {
	void onAddGroup(GroupEvent event);

	void onRemoveGroup(GroupEvent event);
}
