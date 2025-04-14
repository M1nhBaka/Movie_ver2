package com.movieflix.controllers;

import com.movieflix.dto.MovieDTO;
import com.movieflix.service.MovieService;
import com.movieflix.service.VideoStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/video")
public class VideoStreamingController {
    @Autowired
    private VideoStreamingService videoStreamingService;
    @Autowired
    private MovieService movieService;
    @GetMapping("/stream/{movieId}")
    public String streamVideo(
            @PathVariable Integer movieId, Model model) {
        MovieDTO movie = movieService.getMovie(movieId);

        model.addAttribute("movie", movie);
        return "video/stream";
    }
} 