package de.mexchange.packagingdb.domain;

import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.entity.NewsEntity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Arpine on 6/14/2016.

 * Data Transfer Object representing {@link NewsEntity}
 */
public class News extends AbstractModel{

    //@NotEmpty(message = "{err.field.news.sw.code.required}")
    @DateTimeFormat(pattern="MM/dd/YYYY")
    private Date newsDate;

    @DateTimeFormat(pattern="MM/dd/YYYY")
    private Date expirationDate;

    private String remarks;

    private byte[] newsFile;

    private String newsFilename;

    private String newsFileContentType;

    private byte[] newsLink;

    private String newsLinkFilename;

    private String newsLinkFileContentType;

    private NewsInfo currentInfo;

    @Valid
    private List<NewsInfo> infoList;

    public Date getNewsDate() { return newsDate; }

    public void setNewsDate(Date newsDate) { this.newsDate = newsDate; }

    public Date getExpirationDate() { return expirationDate; }

    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }

    public String getRemarks() { return remarks; }

    public void setRemarks(String remarks) { this.remarks = remarks; }

    public byte[] getNewsFile() { return newsFile; }

    public void setNewsFile(byte[] newsFile) { this.newsFile = newsFile; }

    public String getNewsFilename() { return newsFilename; }

    public void setNewsFilename(String newsFilename) { this.newsFilename = newsFilename; }

    public String getNewsFileContentType() { return newsFileContentType; }

    public void setNewsFileContentType(String newsFileContentType) { this.newsFileContentType = newsFileContentType; }

    public byte[] getNewsLink() { return newsLink; }

    public void setNewsLink(byte[] newsLink) { this.newsLink = newsLink; }

    public String getNewsLinkFilename() { return newsLinkFilename; }

    public void setNewsLinkFilename(String newsLinkFilename) { this.newsLinkFilename = newsLinkFilename; }

    public String getNewsLinkFileContentType() { return newsLinkFileContentType; }

    public void setNewsLinkFileContentType(String newsLinkFileContentType) { this.newsLinkFileContentType = newsLinkFileContentType; }

    public List<NewsInfo> getInfoList() { return infoList; }

    public void setInfoList(List<NewsInfo> infoList) { this.infoList = infoList; }

    public NewsInfo getInfo(Language language) {
        if (CollectionHelper.isNotEmpty(infoList)) {
            for (NewsInfo info : infoList) {
                if (info.getLanguage() == language) {
                    return info;
                }
            }
        }
        return null;
    }

    public NewsInfo getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(NewsInfo currentInfo) {
        this.currentInfo = currentInfo;
    }
}
