package de.mexchange.packagingdb.domain;


import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.UnTestMediumInfoEntity;

/**
 * Data UnTestMediumInfo Object representing {@link UnTestMediumInfoEntity}
 */
public class UnTestMediumInfo {

    private Long id;

    private Language language;

    private String medium;

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}