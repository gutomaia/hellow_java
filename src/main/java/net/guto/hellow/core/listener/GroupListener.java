package net.guto.hellow.core.listener;

import net.guto.hellow.core.pojos.Group;

public interface GroupListener {
	void onAddGroup(Group group);

	void onRemoveGroup(Group group);
}
