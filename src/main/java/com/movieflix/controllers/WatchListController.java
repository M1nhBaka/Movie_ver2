package com.movieflix.controllers;

import com.movieflix.dto.WatchListDTO;
import com.movieflix.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {
    @Autowired
    private WatchListService watchListService;
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<WatchListDTO> addToWatchList(@RequestBody WatchListDTO watchListDTO) {
        WatchListDTO savedWatchList = watchListService.addToWatchList(watchListDTO);
        return ResponseEntity.ok(savedWatchList);
    }
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/{watchListId}/watched")
    public ResponseEntity<WatchListDTO> markAsWatched(@PathVariable Integer watchListId) {
        WatchListDTO updatedWatchList = watchListService.markAsWatched(watchListId);
        return ResponseEntity.ok(updatedWatchList);
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/{userName}")
    public ResponseEntity<List<WatchListDTO>> getWatchListByUser(@PathVariable String userName) {
        List<WatchListDTO> watchList = watchListService.getWatchListByUser(userName);
        return ResponseEntity.ok(watchList);
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/{userName}/watched")
    public ResponseEntity<List<WatchListDTO>> getWatchedMoviesByUser(@PathVariable String userName) {
        List<WatchListDTO> watchedMovies = watchListService.getWatchedMoviesByUser(userName);
        return ResponseEntity.ok(watchedMovies);
    }
} 