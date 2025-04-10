package com.movieflix.controllers;

import com.movieflix.service.VideoStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api/video")
public class VideoStreamingController {
    @Autowired
    private VideoStreamingService videoStreamingService;

    @GetMapping("/stream/{movieId}")
    public ResponseEntity<StreamingResponseBody> streamVideo(
            @PathVariable Integer movieId,
            @RequestHeader(value = "Range", required = false) String range) {
        return videoStreamingService.streamVideo(movieId, range);
    }
} 