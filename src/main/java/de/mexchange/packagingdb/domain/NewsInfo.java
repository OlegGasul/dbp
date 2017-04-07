package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.NewsInfoEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Arpine on 6/14/2016.
 *
 * Data Transfer Object representing {@link NewsInfoEntity}
 */
public class NewsInfo {

    private Long id;

    @NotNull(message = "{err.field.news.description.language.not.specified}")
    private Language language;

    @NotEmpty(message = "{err.field.news.title.required}")
    private String title;

    @NotEmpty(message = "{err.field.news.text.required}")
    private String text;

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

    public String getTitle() {  return title; }

    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }
}


