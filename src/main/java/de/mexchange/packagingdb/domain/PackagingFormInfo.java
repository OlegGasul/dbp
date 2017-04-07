package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.Language;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * User: Garik
 * Date: 5/18/16
 * Time: 12:43 AM
 */
public class PackagingFormInfo {

    private Long id;
    @NotNull(message = "{err.field.packaging.form.language.not.specified}")
    private Language language;

    @NotEmpty(message = "{err.field.packaging.form.required}")
    private String form;

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

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}
