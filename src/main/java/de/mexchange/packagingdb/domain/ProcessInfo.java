package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.ProcessInfoEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object representing {@link ProcessInfoEntity}
 */
public class ProcessInfo {

    private Long id;

    @NotNull(message = "{err.field.process.name.language.not.specified}")
    private Language language;

    @NotEmpty(message = "{err.field.process.name.required}")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
