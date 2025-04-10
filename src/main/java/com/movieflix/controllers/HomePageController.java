package com.movieflix.controllers;

import com.movieflix.dto.HomePageDTO;
import com.movieflix.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/home")
public class HomePageController {
    @Autowired
    private HomePageService homePageService;

    @RequestMapping
    public String getHomePage(Model model) {
        HomePageDTO homePageData = homePageService.getHomePageData();
        model.addAttribute("homePageData", homePageData);
        return "homePage";
    }
} 