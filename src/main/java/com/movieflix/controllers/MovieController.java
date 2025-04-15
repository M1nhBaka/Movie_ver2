package com.movieflix.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.MoviePageResponse;
import com.movieflix.exceptions.EmptyFileException;
import com.movieflix.exceptions.ResourceNotFoundException;
import com.movieflix.service.MovieService;
import com.movieflix.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movie")
@CrossOrigin(origins = "*")

public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/show")
    public String showAddMoviePage(Model model) {
        MovieDTO movieDTO = new MovieDTO();
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("movie", movieDTO);
        return "movies/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addMovieHandler(@ModelAttribute MovieDTO movieDto, Model model) throws IOException, EmptyFileException {
        MovieDTO savedMovie = movieService.addMovie(movieDto);
        model.addAttribute("movie", savedMovie);
        model.addAttribute("message", "Movie added successfully");
        return "movies/admin";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovieHandler(@PathVariable Integer movieId) {
        return ResponseEntity.ok(movieService.getMovie(movieId));
    }

    @GetMapping("/all")
    public String getMoviePages(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String dir,
            @RequestParam(required = false) String search,
            Model model) {

        MoviePageResponse moviePageResponse;
        if (search != null && !search.isEmpty()) {
            // If search parameter is provided, get movies by title
            try {
                MovieDTO movie = movieService.getMovieByName(search);
                List<MovieDTO> movieList = new ArrayList<>();
                if (movie != null) {
                    movieList.add(movie);
                }
                moviePageResponse = new MoviePageResponse(movieList, pageNumber, pageSize, movieList.size(), 1, true);
            } catch (ResourceNotFoundException e) {
                model.addAttribute("message", "Movie not found");
                return "redirect:/movie/list";
            }
        } else {
            // Otherwise get all movies with pagination and sorting
            moviePageResponse = movieService.getAllMoviesWithPaginationAndSorting(pageNumber, pageSize, sortBy, dir);
        }

        model.addAttribute("movies", moviePageResponse);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("dir", dir);
        model.addAttribute("search", search);
        model.addAttribute("totalPages", moviePageResponse.totalPages());
        return "movies/list";
    }
    @GetMapping("/query")
    public String getMoviePagesQuery(@RequestParam(defaultValue = "", required = false) String search, Model model){
        MovieDTO movieDTO = movieService.getMovieByName(search);
        model.addAttribute("movieQuery", movieDTO);
        return "movies/list";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{movieId}")
    public String showEditMoviePage(@PathVariable Integer movieId, Model model) {
        MovieDTO movie = movieService.getMovie(movieId);
        model.addAttribute("movie", movie);
        return "movies/edit";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update/{movieId}")
    public String updateMovieHandler(@PathVariable Integer movieId,
                                   @ModelAttribute MovieDTO movieDto, 
                                   Model model) throws IOException {
        movieService.updateMovie(movieId, movieDto);
        model.addAttribute("message", "Movie updated successfully");
        return "redirect:/movie/show";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<String> deleteMovieHandler(@PathVariable Integer movieId, Model model) throws IOException {
        movieService.deleteMovie(movieId);
        model.addAttribute("message", "Movie deleted successfully");
        return ResponseEntity.ok("Movie deleted successfully");
    }

//    @GetMapping("/allMoviesPage")
//    public String getMoviesWithPagination(
//            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
//            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
//            @RequestParam(defaultValue = "", required = false) String query,
//            Model model) {
//
//        MoviePageResponse moviePageResponse = movieService.getAllMoviesWithPagination(pageNumber, pageSize);
//
//        model.addAttribute("movies", moviePageResponse.getContent());
//        model.addAttribute("currentPage", pageNumber);
//        model.addAttribute("totalPages", moviePageResponse.getTotalPages());
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("query", query);
//        model.addAttribute("genres", movieService.getAllGenres());
//
//        return "movies/list";
//    }
//
//    @GetMapping("/allMoviesPageSort")
//    public String getMoviesWithPaginationAndSorting(
//            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
//            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
//            @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
//            @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String dir,
//            @RequestParam(defaultValue = "", required = false) String query,
//            Model model) {
//
//        MoviePageResponse moviePageResponse = movieService.getAllMoviesWithPaginationAndSorting(pageNumber, pageSize, sortBy, dir);
//
//        model.addAttribute("movies", moviePageResponse.getContent());
//        model.addAttribute("currentPage", pageNumber);
//        model.addAttribute("totalPages", moviePageResponse.getTotalPages());
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("sortBy", sortBy);
//        model.addAttribute("dir", dir);
//        model.addAttribute("query", query);
//        model.addAttribute("genres", movieService.getAllGenres());
//
//        return "movies/list";
//    }
}
