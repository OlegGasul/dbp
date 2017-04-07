package de.mexchange.packagingdb.domain.lcp;

public enum UserRole implements Labelable {

	ROLE_READER (1, "User Reader", "label.user.profile.reader"),
	ROLE_WRITER (2, "User Writer", "label.user.profile.writer"),
	ROLE_ADMIN  (3, "Administrator", "label.user.profile.admin");

	UserRole(int value, String title, String labelKey) {
		this.value = value;
		this.title = title;
		this.labelKey = labelKey;
	}

	public static UserRole valueOf(int value) {
		for (UserRole e : UserRole.values()) {
			if (e.value == value) {
				return e;
			}
		}
		return null;
	}

	public int getValue() {
		return value;
	}

	public String getTitle() {
		return title;
	}

	public String getLabelKey() {
		return labelKey;
	}

	private final int value;

	private final String title;

	private final String labelKey;
}