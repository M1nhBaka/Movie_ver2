package com.movieflix.controllers;

import com.movieflix.service.VideoStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api/v1/video")
public class VideoStreamingController {
    @Autowired
    private VideoStreamingService videoStreamingService;

    @GetMapping("/stream/{movieId}")
    public ResponseEntity<Resource> streamVideo(
            @PathVariable Integer movieId) {
        return videoStreamingService.streamVideo(movieId);
    }
} 