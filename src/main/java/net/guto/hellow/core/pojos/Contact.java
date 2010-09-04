package net.guto.hellow.core.pojos;

public class Contact {
	private String name;
	private String displayName;
	private String guidDisplayPicture; // only for MSNP9;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getGuidDisplayPicture() {
		return guidDisplayPicture;
	}

	public void setGuidDisplayPicture(String guidDisplayPicture) {
		this.guidDisplayPicture = guidDisplayPicture;
	}
}
