package de.mexchange.packagingdb.domain.lcp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Arthur on 4/28/2016.
 */
public enum Language implements Labelable {

	ENGLISH 	(1, "en", "label.lang.en"),
	GERMAN		(2, "de", "label.lang.de"),
	POLISH		(3, "pl", "label.lang.pl", Status.DISABLED),
	PORTUGUESE 	(4, "pt", "label.lang.pt", Status.DISABLED),
	;

	Language(final int value, final String language, final String labelKey) {
		this(value, language, labelKey, Status.ACTIVE);
	}

	Language(final int value, final String language, final String labelKey, Status status) {
		this.value = value;
		this.language = language;
		this.labelKey = labelKey;
		this.status = status;
	}

	public static Language valueOf(int value) {
		for (Language e : Language.values()) {
			if (e.getValue() == value) {
				return e;
			}
		}
		return null;
	}

    public static Language localeOf(Locale locale) {
        for (Language e : Language.values()) {
            if (e.getLanguage().equals(locale.getLanguage())) {
                return e;
            }
        }
        return getDefault();
    }

	public static Language[] activeLanguages(){
		List<Language> languageList = new ArrayList<>();
		for (Language e : Language.values()) {
			if (e.status == Status.ACTIVE) {
				languageList.add(e);
			}
		}

		return languageList.toArray(new Language[languageList.size()]);
	}

    public static Language getDefault() {
        return GERMAN;
    }

	public int getValue() {
		return value;
	}

	public String getLanguage() {
		return language;
	}

	public String getLabelKey() {
		return labelKey;
	}

	public Locale getLocale() {
		if (locale == null) {
			locale = new Locale(language);
		}
		return locale;
	}

	public Status getStatus() {
		return status;
	}

	private final int value;
	private final String language;
	private final String labelKey;
	private Locale locale;
	private Status status;
}
