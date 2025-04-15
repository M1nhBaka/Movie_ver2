package com.movieflix.controllers;

import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.ReviewDTO;
import com.movieflix.service.MovieService;
import com.movieflix.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MovieService movieService;
    @PostMapping
    public String addReview(@ModelAttribute ReviewDTO reviewDTO, Authentication authentication) {
        String username = authentication.getName();
        reviewDTO.setReviewerName(username);
        reviewService.addReview(reviewDTO);
        return "redirect:/movie/" + reviewDTO.getMovieId();
    }

    @GetMapping("/movie/{movieId}")
    public String getReviewsByMovieId(@PathVariable Integer movieId, Model model) {

        MovieDTO movieDTO = movieService.getMovie(movieId) ;
        model.addAttribute("movie", movieDTO);
        List<ReviewDTO> reviews = reviewService.getReviewsByMovieId(movieId);
        model.addAttribute("reviews", reviews);
        return "movies/detail";
    }
} 