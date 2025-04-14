package com.movieflix.controllers;

import com.movieflix.auth.entities.User;
import com.movieflix.dto.HomePageDTO;
import com.movieflix.service.HomePageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomePageController {
    @Autowired
    private HomePageService homePageService;

    @GetMapping
    public String getHomePage(Model model, HttpSession session) {
        // Lấy thông tin user từ session
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        
        HomePageDTO homePageData = homePageService.getHomePageData();
        model.addAttribute("homePageData", homePageData);
        return "home";
    }
} 