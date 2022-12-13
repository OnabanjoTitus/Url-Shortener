package com.urlshortner.web.controller;

import com.urlshortner.service.UrlShortenerService;
import com.urlshortner.web.dtos.UrlRequestDto;
import com.urlshortner.web.dtos.UrlResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shortener")
public class ShortenerController {
    private final UrlShortenerService urlShortenerService;

    public ShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }


    @GetMapping("")
    public String homepage(Model model) {
        model.addAttribute("UrlRequest", new UrlRequestDto());
        return "shorten";
    }

    @PostMapping("/shorten")
    public String shorten(Model model, UrlRequestDto urlRequestDto) {
        UrlResponseDto urlResponseDto = urlShortenerService.shortenUrl(urlRequestDto);
        model.addAttribute("urlResponse", urlResponseDto);
        return "shortened";
    }
}
