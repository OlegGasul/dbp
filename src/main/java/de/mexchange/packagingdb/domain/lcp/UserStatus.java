package de.mexchange.packagingdb.domain.lcp;

/**
 * Created by Garik on 4/30/16.
 */
public enum UserStatus implements Labelable {

	PENDING     (1, "Pending",  "label.user.status.pending"),
	ACTIVE      (2, "Active",   "label.user.status.active"),
	DISABLED    (3, "Disabled", "label.user.status.disabled");

	UserStatus(int value, String title, String labelKey) {
		this.value = value;
		this.title = title;
		this.labelKey = labelKey;
	}

	public static UserStatus valueOf(int value) {
		for (UserStatus e : UserStatus.values()) {
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
