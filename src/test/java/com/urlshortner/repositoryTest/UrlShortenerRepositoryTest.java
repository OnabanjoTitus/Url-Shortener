package com.urlshortner.repositoryTest;

import com.urlshortner.data.model.ShortenedUrlEntity;
import com.urlshortner.data.repository.UrlShortenerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootTest
public class UrlShortenerRepositoryTest {
    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void testThatUrlExistsByLongUrl() {
        //Given
        ShortenedUrlEntity shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setLongUrl("test-url");
        shortenedUrlEntity.setShortUrl("tet");
        shortenedUrlEntity.setCreatedDate(new Date());
        shortenedUrlEntity.setAccessCount(1);
        shortenedUrlEntity.setShortenedCount(1);
        //When
        urlShortenerRepository.save(shortenedUrlEntity);
        //Assert That
        Assertions.assertTrue(urlShortenerRepository.existsByLongUrl("test-url"));
    }

    @Test
    @Transactional
    void testThatUrlExistsByShortUrl() {
        //Given
        ShortenedUrlEntity shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setLongUrl("test-url");
        shortenedUrlEntity.setShortUrl("tet");
        shortenedUrlEntity.setCreatedDate(new Date());
        shortenedUrlEntity.setAccessCount(1);
        shortenedUrlEntity.setShortenedCount(1);
        //When
        urlShortenerRepository.save(shortenedUrlEntity);
        //Assert That
        Assertions.assertTrue(urlShortenerRepository.existsByShortUrl("tet"));
    }

    @Test
    @Transactional
    void testThatFindEntityByShortUrl() {
        //Given
        ShortenedUrlEntity shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setLongUrl("test-url");
        shortenedUrlEntity.setShortUrl("tet");
        shortenedUrlEntity.setCreatedDate(new Date());
        shortenedUrlEntity.setAccessCount(1);
        shortenedUrlEntity.setShortenedCount(1);
        //When
        urlShortenerRepository.save(shortenedUrlEntity);
        //Assert That
        Assertions.assertNotNull(urlShortenerRepository.findShortenedUrlEntityByShortUrl(shortenedUrlEntity.getShortUrl()));
    }

    @Test
    @Transactional
    void testThatFindEntityByLongUrl() {
        //Given
        ShortenedUrlEntity shortenedUrlEntity = new ShortenedUrlEntity();
        shortenedUrlEntity.setLongUrl("test-url");
        shortenedUrlEntity.setShortUrl("tet");
        shortenedUrlEntity.setCreatedDate(new Date());
        shortenedUrlEntity.setAccessCount(1);
        shortenedUrlEntity.setShortenedCount(1);
        //When
        urlShortenerRepository.save(shortenedUrlEntity);
        //Assert That
        Assertions.assertNotNull(urlShortenerRepository.findShortenedUrlEntityByLongUrl(shortenedUrlEntity.getLongUrl()));
    }
}
