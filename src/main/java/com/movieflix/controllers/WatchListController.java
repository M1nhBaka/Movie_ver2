package com.movieflix.controllers;

import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.WatchListDTO;
import com.movieflix.service.MovieService;
import com.movieflix.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/watchlist")
public class WatchListController {
    @Autowired
    private WatchListService watchListService;
    @Autowired
    private MovieService movieService;
    @GetMapping("/user/{username}")
    public String getWatchListByUser(@PathVariable String username, Model model) {
        List<WatchListDTO> watchlist = watchListService.getWatchListByUser(username);
        List<WatchListDTO> watchedMovies = watchListService.getWatchedMoviesByUser(username);
        List<MovieDTO> movieDTOS = movieService.getAllMovies();
        model.addAttribute("movies", movieDTOS);
        model.addAttribute("username", username);
        model.addAttribute("watchlist", watchlist);
        model.addAttribute("watchedMovies", watchedMovies);
        model.addAttribute("user", username);
        return "watchlist";
    }

    @PostMapping("/user/add")
    public String addToWatchList(@RequestParam Integer movieId, Authentication authentication) {
        String username = authentication.getName();
        WatchListDTO watchListDTO = new WatchListDTO();
        watchListDTO.setMovieId(movieId);
        watchListDTO.setUserName(username);
        watchListService.addToWatchList(watchListDTO);
        return "redirect:/watchlist/user/" + username;
    }

    @PostMapping("/{watchListId}/watched")
    public String markAsWatched(@PathVariable Integer watchListId, Authentication authentication) {
        String username = authentication.getName();
        watchListService.markAsWatched(watchListId);
        return "redirect:/watchlist/user/" + username;
    }

    @GetMapping("/user/{username}/watched")
    public String getWatchedMovies(@PathVariable String username, Model model) {
        List<WatchListDTO> watchedMovies = watchListService.getWatchedMoviesByUser(username);
        model.addAttribute("username", username);
        model.addAttribute("watchedMovies", watchedMovies);
        return "watchlist";
    }
} 