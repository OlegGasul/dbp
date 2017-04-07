package de.mexchange.packagingdb.entity;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "news_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"news_id", "language_id"})
})
public class NewsInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="news_id", foreignKey =  @ForeignKey(name="FK_news"))
    private NewsEntity news;

    @ManyToOne
    @JoinColumn(name="language_id", foreignKey =  @ForeignKey(name="FK_news_lang"))
    private LanguageEntity language;

    //news title
    @NotEmpty
    @Column(name = "title")
    private String title;

    //news text
    @Basic(optional = false)
    @NotEmpty
    @Lob
    @Size(min = 1, max = 65535)
    @Type(type = "text")
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    /**
     * Initializes a new instance of the class.
     */
    public NewsInfoEntity() {}

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public NewsInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public NewsEntity getNews() {
        return news;
    }

    public NewsInfoEntity setNews(NewsEntity news) {
        this.news = news;
        return this;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public NewsInfoEntity setLanguage(LanguageEntity language) {
        this.language = language;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsInfoEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public NewsInfoEntity setText(String text) {
        this.text = text;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>NewsInfoEntity</code> object.
     */
    @Override
    public String toString() {
        return "NewsInfoEntity{" +
                "id=" + id +
                ", title ='" + title + '\'' +
                ", text ='" + text + '\'' +
                ", language='" + language+ '\'' +
                '}';
    }
    // endregion
}
