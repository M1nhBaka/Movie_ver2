package com.movieflix.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.MoviePageResponse;
import com.movieflix.exceptions.EmptyFileException;
import com.movieflix.service.MovieService;
import com.movieflix.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/movie")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/add")
    public String showAddMoviePage(Model model) {
        model.addAttribute("movie", new MovieDTO());
        return "movies/admin";
    }

    @PostMapping("/add")
    public String addMovieHandler(@ModelAttribute MovieDTO movieDto, Model model) throws IOException, EmptyFileException {
        MovieDTO savedMovie = movieService.addMovie(movieDto);
        model.addAttribute("movie", savedMovie);
        model.addAttribute("message", "Movie added successfully");
        return "movies/admin";
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovieHandler(@PathVariable Integer movieId) {
        return ResponseEntity.ok(movieService.getMovie(movieId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAllMoviesHandler() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/edit/{movieId}")
    public String showEditMoviePage(@PathVariable Integer movieId, Model model) {
        MovieDTO movie = movieService.getMovie(movieId);
        model.addAttribute("movie", movie);
        return "movies/edit";
    }

    @PostMapping("/update/{movieId}")
    public String updateMovieHandler(@PathVariable Integer movieId, 
                                   @ModelAttribute MovieDTO movieDto, 
                                   Model model) throws IOException {
        movieService.updateMovie(movieId, movieDto);
        model.addAttribute("message", "Movie updated successfully");
        return "movies/edit";
    }

    @PostMapping("/delete/{movieId}")
    public String deleteMovieHandler(@PathVariable Integer movieId, Model model) throws IOException {
        movieService.deleteMovie(movieId);
        model.addAttribute("message", "Movie deleted successfully");
        return "redirect:/movie/all";
    }

    @GetMapping("/allMoviesPage")
    public ResponseEntity<MoviePageResponse> getMoviesWithPagination(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize
    ) {
        return ResponseEntity.ok(movieService.getAllMoviesWithPagination(pageNumber, pageSize));
    }

    @GetMapping("/allMoviesPageSort")
    public ResponseEntity<MoviePageResponse> getMoviesWithPaginationAndSorting(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String dir
    ) {
        return ResponseEntity.ok(movieService.getAllMoviesWithPaginationAndSorting(pageNumber, pageSize, sortBy, dir));
    }
}
