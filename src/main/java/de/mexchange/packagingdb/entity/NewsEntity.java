package de.mexchange.packagingdb.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Serozh on 6/11/2016.
 */
@Entity
@Table(name = "news")
public class NewsEntity extends AbstractEntity {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "news_date")
    private Date newsDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "remarks")
    private String remarks;

    @Lob
    @Column(name = "news_file")
    private byte[] newsFile;

    @Column(name = "process_filename")
    private String newsFilename;

    @Column(name = "process_file_content_type")
    private String newsFileContentType;

    @Lob
    @Column(name = "news_link")
    private byte[] newsLink;

    @Column(name = "news_link_filename")
    private String newsLinkFilename;

    @Column(name = "news_link_file_content_type")
    private String newsLinkFileContentType;

    @OneToMany(mappedBy="news", cascade = CascadeType.ALL)
    private Set<NewsInfoEntity> infoList;

    /**
     * Initializes a new instance of the class.
     */
    public NewsEntity() {
    }

    // region <GET/SET>

    public Long getId() {
        return id;
    }

    public NewsEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public NewsEntity setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
        return this;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public NewsEntity setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public NewsEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public byte[] getNewsFile() {
        return newsFile;
    }

    public NewsEntity setNewsFile(byte[] newsFile) {
        this.newsFile = newsFile;
        return this;
    }

    public String getNewsFilename() {
        return newsFilename;
    }

    public NewsEntity setNewsFilename(String newsFilename) {
        this.newsFilename = newsFilename;
        return this;
    }

    public String getNewsFileContentType() {
        return newsFileContentType;
    }

    public NewsEntity setNewsFileContentType(String newsFileContentType) {
        this.newsFileContentType = newsFileContentType;
        return this;
    }

    public byte[] getNewsLink() {
        return newsLink;
    }

    public NewsEntity setNewsLink(byte[] newsLink) {
        this.newsLink = newsLink;
        return this;
    }

    public String getNewsLinkFilename() {
        return newsLinkFilename;
    }

    public NewsEntity setNewsLinkFilename(String newsLinkFilename) {
        this.newsLinkFilename = newsLinkFilename;
        return this;
    }

    public String getNewsLinkFileContentType() {
        return newsLinkFileContentType;
    }

    public NewsEntity setNewsLinkFileContentType(String newsLinkFileContentType) {
        this.newsLinkFileContentType = newsLinkFileContentType;
        return this;
    }

    public Set<NewsInfoEntity> getInfoList() {
        return infoList;
    }

    public NewsEntity setInfoList(Set<NewsInfoEntity> infoList) {
        this.infoList = infoList;
        return this;
    }

    //endregion

    // region <OBJECT>
    /**
     *
     * @return string representation of <code>NewsEntity</code> object.
     */
    @Override
    public String toString() {
        return "NewsEntity{" +
                "id=" + id +
                ", newsDate=" + newsDate +
                ", expirationDate=" + expirationDate +
                ", remarks='" + remarks + '\'' +
                ", newsFilename='" + newsFilename + '\'' +
                ", newsFileContentType='" + newsFileContentType + '\'' +
                ", newsLinkFilename='" + newsLinkFilename + '\'' +
                ", newsLinkFileContentType='" + newsLinkFileContentType + '\'' +
                ", infoList=" + infoList +
                '}';
    }
    // endregion
}
