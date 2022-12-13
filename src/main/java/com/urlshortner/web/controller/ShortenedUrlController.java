package com.urlshortner.web.controller;

import com.urlshortner.service.UrlShortenerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("")
public class ShortenedUrlController {

    private final UrlShortenerService urlShortenerService;

    public ShortenedUrlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("{key}")
    public RedirectView accessLongUrlFromShortUrl(@PathVariable("key") String key, HttpServletResponse resp) throws IOException {
        try {
            String longUrl = urlShortenerService.getLongUrlFromShortUrl(key);
            if (!(longUrl == null)) {
                urlShortenerService.increaseNumberOfAccessCount(longUrl);
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl(longUrl);
                return redirectView;
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
