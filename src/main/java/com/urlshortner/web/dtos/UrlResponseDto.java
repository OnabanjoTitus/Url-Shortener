package com.urlshortner.web.dtos;

public class UrlResponseDto {
    private String shortUrl;
    private Integer numberOfTimesShortened;
    private Integer numberOfTimesAccessed;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Integer getNumberOfTimesShortened() {
        return numberOfTimesShortened;
    }

    public void setNumberOfTimesShortened(Integer numberOfTimesShortened) {
        this.numberOfTimesShortened = numberOfTimesShortened;
    }

    public Integer getNumberOfTimesAccessed() {
        return numberOfTimesAccessed;
    }

    public void setNumberOfTimesAccessed(Integer numberOfTimesAccessed) {
        this.numberOfTimesAccessed = numberOfTimesAccessed;
    }
}
