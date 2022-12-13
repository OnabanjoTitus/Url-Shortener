package com.urlshortner.data.repository;

import com.urlshortner.data.model.ShortenedUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepository extends JpaRepository<ShortenedUrlEntity, Integer> {
    boolean existsByShortUrl(String url);

    boolean existsByLongUrl(String url);

    ShortenedUrlEntity findShortenedUrlEntityByLongUrl(String longUrl);

    ShortenedUrlEntity findShortenedUrlEntityByShortUrl(String shortUrl);
}
