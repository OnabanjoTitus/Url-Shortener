package com.urlshortner.service;

import com.urlshortner.web.dtos.UrlRequestDto;
import com.urlshortner.web.dtos.UrlResponseDto;

public interface UrlShortenerService {
    UrlResponseDto shortenUrl(UrlRequestDto urlRequestDto);

    String getLongUrlFromShortUrl(String shortUrl);

    void increaseNumberOfAccessCount(String longUrl);

}
