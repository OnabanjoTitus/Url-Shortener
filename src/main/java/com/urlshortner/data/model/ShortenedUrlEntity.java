package com.urlshortner.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShortenedUrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String shortUrl;

    @Column
    private String longUrl;

    @Column
    private Integer accessCount = 0;

    @Column
    private Integer shortenedCount = 1;

    @Column
    private Date createdDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }

    public Integer getShortenedCount() {
        return shortenedCount;
    }

    public void setShortenedCount(Integer shortenedCount) {
        this.shortenedCount = shortenedCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
