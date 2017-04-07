package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.MaterialInfoEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object representing {@link MaterialInfoEntity}
 */
public class MaterialInfo extends AbstractModel {

    @NotNull(message = "{err.field.material.language.not.specified}")
    private Language language;

    @NotEmpty(message = "{err.field.material.name.required}")
    private String name;

    @NotEmpty(message = "{err.field.material.short.name.required}")
    private String shortName;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
