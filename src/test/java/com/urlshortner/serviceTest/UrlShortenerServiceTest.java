package com.urlshortner.serviceTest;

import com.urlshortner.service.UrlShortenerService;
import com.urlshortner.web.dtos.UrlRequestDto;
import com.urlshortner.web.dtos.UrlResponseDto;
import com.urlshortner.web.exceptions.UrlRequestDTOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;


@SpringBootTest
public class UrlShortenerServiceTest {
    @Autowired
    private UrlShortenerService urlShortenerService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void testThatUrlRequestDtoCannotBeNull() {
        //Given
        UrlRequestDto urlRequestDto = null;
        //Assert That
        Assertions.assertThrows(UrlRequestDTOException.class, () -> urlShortenerService.shortenUrl(urlRequestDto));
    }

    @Test
    @Transactional
    void testThatLongUrlCannotBeEmptyOrNull() {
        //Given
        UrlRequestDto urlRequestDto = new UrlRequestDto();
        urlRequestDto.setLongUrl("");
        //Assert That
        Assertions.assertThrows(UrlRequestDTOException.class, () -> urlShortenerService.shortenUrl(urlRequestDto));
        //Given
        urlRequestDto.setLongUrl(null);
        //Assert That
        Assertions.assertThrows(UrlRequestDTOException.class, () -> urlShortenerService.shortenUrl(urlRequestDto));
    }

    @Test
    @Transactional
    void testThatWeCanGenerateShortUrl() {
        //Given
        UrlRequestDto urlRequestDto = new UrlRequestDto();
        urlRequestDto.setLongUrl("https://www.google.com/");
        //Assert That
        UrlResponseDto urlResponseDto = urlShortenerService.shortenUrl(urlRequestDto);
        Assertions.assertNotEquals(urlRequestDto.getLongUrl(), urlResponseDto.getShortUrl());
    }

    @Test
    @Transactional
    void testThatUrlOfLengthFourOrLessIsNotShortened() {
        //Given
        UrlRequestDto urlRequestDto = new UrlRequestDto();
        urlRequestDto.setLongUrl("t.ly");
        //When
        UrlResponseDto urlResponseDto = urlShortenerService.shortenUrl(urlRequestDto);
        //AssertThat
        Assertions.assertEquals(urlRequestDto.getLongUrl(), urlResponseDto.getShortUrl());
    }

    @Test
    @Transactional
    void testThatShortUrlOfLongUrlRemainsTheSame() {
        //Given
        UrlRequestDto urlRequestDto = new UrlRequestDto();
        urlRequestDto.setLongUrl("https://www.fb.com/");
        //When
        UrlResponseDto urlResponseDto1 = urlShortenerService.shortenUrl(urlRequestDto);
        UrlResponseDto urlResponseDto2 = urlShortenerService.shortenUrl(urlRequestDto);
        //AssertThat
        Assertions.assertEquals(urlResponseDto1.getShortUrl(), urlResponseDto2.getShortUrl());

    }

    @Test
    @Transactional
    void testThatUrlShorteningCountIncreases() {
        //Given
        UrlRequestDto urlRequestDto = new UrlRequestDto();
        urlRequestDto.setLongUrl("https://www.fb.com/");
        //When
        UrlResponseDto urlResponseDto1 = urlShortenerService.shortenUrl(urlRequestDto);
        //AssertThat
        Assertions.assertEquals(urlResponseDto1.getNumberOfTimesShortened(), 1);
        //When
        UrlResponseDto urlResponseDto2 = urlShortenerService.shortenUrl(urlRequestDto);
        //AssertThat
        Assertions.assertEquals(urlResponseDto2.getNumberOfTimesShortened(), 2);

    }

    @Test
    @Transactional
    void testThatWeCanGetLongUrlFromShortUrl() {
        //Given
        UrlRequestDto urlRequestDto = new UrlRequestDto();
        urlRequestDto.setLongUrl("https://www.fb.com/");
        //When
        UrlResponseDto urlResponseDto1 = urlShortenerService.shortenUrl(urlRequestDto);
        String shortUrl = urlResponseDto1.getShortUrl().substring(15);
        //AssertThat
        Assertions.assertEquals(urlRequestDto.getLongUrl(), urlShortenerService.getLongUrlFromShortUrl(shortUrl));

    }

    @Test
    @Transactional
    void testThatWeCanIncreaseAccessCount() {
        //Given
        UrlRequestDto urlRequestDto = new UrlRequestDto();
        urlRequestDto.setLongUrl("https://www.fb.com/");
        //When
        UrlResponseDto urlResponseDto1 = urlShortenerService.shortenUrl(urlRequestDto);
        //AssertThat
        Assertions.assertEquals(urlResponseDto1.getNumberOfTimesAccessed(), 0);
        //When
        urlShortenerService.increaseNumberOfAccessCount(urlRequestDto.getLongUrl());
        urlResponseDto1 = urlShortenerService.shortenUrl(urlRequestDto);
        //AssertThat
        Assertions.assertEquals(urlResponseDto1.getNumberOfTimesAccessed(), 1);

    }

}
