package com.movieflix.controllers;

import com.movieflix.dto.HomePageDTO;
import com.movieflix.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomePageController {
    @Autowired
    private HomePageService homePageService;

    @GetMapping
    public ResponseEntity<HomePageDTO> getHomePage() {
        HomePageDTO homePageData = homePageService.getHomePageData();
        return ResponseEntity.ok(homePageData);
    }
} 