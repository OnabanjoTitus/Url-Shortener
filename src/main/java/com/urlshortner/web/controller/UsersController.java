package com.urlshortner.web.controller;

import com.urlshortner.data.model.Status;
import com.urlshortner.service.UserService;
import com.urlshortner.web.dtos.LoginRequestDto;
import com.urlshortner.web.dtos.RegisterRequestDto;
import com.urlshortner.web.dtos.RegisterResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String menu(Model model) {
        return "menu";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerRequest", new RegisterRequestDto());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequestDto());
        //Todo: differentiate user and admin
        return "login-user";
    }

    @PostMapping("/register-user")
    public String registerUser(RegisterRequestDto registerRequestDto) {
        RegisterResponseDto registerResponseDto = userService.registerUser(registerRequestDto);
        if (registerResponseDto.getStatus().equals(Status.SUCCESS)) {
            return "registered";
        }
        if (registerResponseDto.getStatus().equals(Status.ALREADY_REGISTERED)) {
            return "already-registered";
        }
        return "registered";
    }

    @GetMapping("/coming-soon")
    public String register() {

        return "coming-soon";
    }
}
