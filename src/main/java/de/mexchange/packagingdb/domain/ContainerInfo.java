package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.Language;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Serozh on 7/10/2016.
 */
public class ContainerInfo {

    private Long id;

    @NotNull(message = "{err.field.container.language.not.specified}")
    private Language language;

    @NotEmpty(message = "{err.field.container.comments.required}")
    private String comments;

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
