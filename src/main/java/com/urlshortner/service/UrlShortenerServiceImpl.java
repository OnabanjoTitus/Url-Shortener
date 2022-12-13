package com.urlshortner.service;

import com.urlshortner.data.model.ShortenedUrlEntity;
import com.urlshortner.data.repository.UrlShortenerRepository;
import com.urlshortner.web.dtos.UrlRequestDto;
import com.urlshortner.web.dtos.UrlResponseDto;
import com.urlshortner.web.exceptions.UrlRequestDTOException;
import com.urlshortner.web.exceptions.UrlShortenerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.security.SecureRandom;
import java.util.Date;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final UrlShortenerRepository urlShortenerRepository;
    @Value("${request.path}")
    String requestDomainPath;

    public UrlShortenerServiceImpl(UrlShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    @Override
    public UrlResponseDto shortenUrl(UrlRequestDto urlRequestDto) {
        checkParameters(urlRequestDto);
        UrlResponseDto urlResponseDto = new UrlResponseDto();
        String shortUrl = generateShortUrl(urlRequestDto.getLongUrl());
        urlResponseDto.setShortUrl(shortUrl);
        if (StringUtils.isEmpty(shortUrl)) {
            return urlResponseDto;
        }
        ShortenedUrlEntity shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setShortUrl(shortUrl);
        shortenedUrlEntity.setCreatedDate(new Date());
        shortenedUrlEntity.setLongUrl(urlRequestDto.getLongUrl());
        if (!urlShortenerRepository.existsByLongUrl(urlRequestDto.getLongUrl())) {
            urlResponseDto.setNumberOfTimesShortened(addOne(0));
            urlResponseDto.setNumberOfTimesAccessed(0);
            urlShortenerRepository.save(shortenedUrlEntity);
        } else {
            shortenedUrlEntity = urlShortenerRepository.findShortenedUrlEntityByShortUrl(shortUrl);
            urlResponseDto.setNumberOfTimesShortened(shortenedUrlEntity.getShortenedCount());
            urlResponseDto.setNumberOfTimesAccessed(shortenedUrlEntity.getAccessCount());
        }
        return urlResponseDto;
    }

    @Override
    public String getLongUrlFromShortUrl(String shortUrl) {
        StringBuilder shortUrlLink = new StringBuilder(requestDomainPath);
        shortUrlLink.append(shortUrl);
        boolean shortUrlExist = urlShortenerRepository.existsByShortUrl(shortUrlLink.toString());
        if (!shortUrlExist) {
            throw new UrlShortenerException("Short Url Does Not Exists");
        }
        String longUrl = urlShortenerRepository.findShortenedUrlEntityByShortUrl(shortUrlLink.toString()).getLongUrl();
        return longUrl.trim();
    }

    @Override
    public void increaseNumberOfAccessCount(String longUrl) {
        ShortenedUrlEntity shortenedUrlEntity = urlShortenerRepository.findShortenedUrlEntityByLongUrl(longUrl);
        shortenedUrlEntity.setAccessCount(addOne(shortenedUrlEntity.getAccessCount()));
        urlShortenerRepository.save(shortenedUrlEntity);
    }

    private String generateShortUrl(String longUrl) {
        if (longUrl.length() <= 4) {
            return longUrl;
        }
        if (urlShortenerRepository.existsByLongUrl(longUrl)) {
            ShortenedUrlEntity shortenedUrlEntity = urlShortenerRepository.findShortenedUrlEntityByLongUrl(longUrl);
            shortenedUrlEntity.setShortenedCount(addOne(shortenedUrlEntity.getShortenedCount()));
            urlShortenerRepository.save(shortenedUrlEntity);
            return shortenedUrlEntity.getShortUrl();
        }
        String shortUrlKey = generateActualUrl(longUrl);
        return shortUrlKey;
    }

    private String generateActualUrl(String longUrl) {
        String websiteName = extractWebsiteNameFromUrl(longUrl);
        StringBuilder shortUrl = new StringBuilder(requestDomainPath);
        shortUrl.append((websiteName.substring(0, 2) + websiteName.charAt(websiteName.length() - 2)).toLowerCase());
        SecureRandom random = new SecureRandom();
        int numberOfScramble = random.nextInt(3);
        for (int i = 0; i < numberOfScramble; i++) {
            shortUrl.append(random.nextInt(10));
        }
        return shortUrl.toString();
    }

    private String extractWebsiteNameFromUrl(String longUrl) {
        longUrl = longUrl.toLowerCase();
        if (longUrl.startsWith("http") || longUrl.startsWith("www") || longUrl.startsWith("com")) {
            longUrl = longUrl.substring(longUrl.indexOf(".") + 1);
        }
        return longUrl;
    }

    private void checkParameters(UrlRequestDto urlRequestDto) {
        if (urlRequestDto == null) {
            throw new UrlRequestDTOException("Url Request Cannot Be Null");
        }
        if (StringUtils.isEmpty(urlRequestDto.getLongUrl())) {
            throw new UrlRequestDTOException("Long Url Cannot Be Empty");
        }
    }

    private Integer addOne(Integer value) {
        return value + 1;
    }
}
