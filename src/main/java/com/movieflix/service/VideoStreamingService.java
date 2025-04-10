package com.movieflix.service;

import com.movieflix.entities.Movie;
import com.movieflix.exceptions.ResourceNotFoundException;
import com.movieflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VideoStreamingService {
    @Autowired
    private MovieRepository movieRepository;

    public ResponseEntity<StreamingResponseBody> streamVideo(Integer movieId, String range) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));

        try {
            URL videoUrl = new URL(movie.getVideoUrl());
            StreamingResponseBody responseBody = outputStream -> {
                try (InputStream inputStream = videoUrl.openStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            };

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "video/mp4");
            headers.add("Accept-Ranges", "bytes");
            headers.add("Content-Length", String.valueOf(movie.getDuration()));

            if (range != null) {
                String[] ranges = range.replace("bytes=", "").split("-");
                long start = Long.parseLong(ranges[0]);
                long end = ranges.length > 1 ? Long.parseLong(ranges[1]) : movie.getDuration();
                headers.add("Content-Range", String.format("bytes %d-%d/%d", start, end, movie.getDuration()));
                return new ResponseEntity<>(responseBody, headers, HttpStatus.PARTIAL_CONTENT);
            }

            return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("Error streaming video", e);
        }
    }
} 