package com.movieflix.controllers;

import com.movieflix.dto.ReviewDTO;
import com.movieflix.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public String addReview(@ModelAttribute ReviewDTO reviewDTO, Model model) {
        ReviewDTO savedReview = reviewService.addReview(reviewDTO);
        model.addAttribute("review", savedReview);
        return "movies/detail";
    }
    @GetMapping("/movie/{movieId}")
    public String getReviewsByMovieId(@PathVariable Integer movieId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByMovieId(movieId);
        return "#";
    }
} 