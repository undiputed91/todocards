package com.sparta.todocards.controller;

import com.sparta.todocards.dto.LoginRequestDto;
import com.sparta.todocards.dto.SignupRequestDto;
import com.sparta.todocards.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(@ModelAttribute SignupRequestDto requestDto){
        userService.signup(requestDto);
        return "redirect:/api/user/login-page";
    }
    @PostMapping("/user/login")
    public String login(@ModelAttribute LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto,res);
        } catch (Exception e) {
            return "redirect:/api/user/login-page?error";
        }
        return "redirect:/";
    }
}
