package com.movieflix.service;

import com.movieflix.dto.HomePageDTO;
import com.movieflix.dto.MovieDTO;
import com.movieflix.entities.Movie;
import com.movieflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomePageService {
    @Autowired
    private MovieRepository movieRepository;

    public HomePageDTO getHomePageData() {
        HomePageDTO homePageDTO = new HomePageDTO();
        
        // Get featured movies (manually curated or based on some criteria)
        homePageDTO.setFeaturedMovies(getFeaturedMovies());
        
        // Get recently added movies
        homePageDTO.setRecentlyAdded(getRecentlyAddedMovies());
        
        // Get popular movies (based on views or ratings)
        homePageDTO.setPopularMovies(getPopularMovies());
        
        // Get trending movies (based on recent views or engagement)
        homePageDTO.setTrendingNow(getTrendingMovies());
        
        return homePageDTO;
    }

    private List<MovieDTO> getFeaturedMovies() {
        // For now, we'll get the latest 5 movies as featured
        // In a real application, you might want to have a separate table for featured movies
        return movieRepository.findAll(PageRequest.of(0, 5, Sort.by("releaseYear").descending()))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private List<MovieDTO> getRecentlyAddedMovies() {
        return movieRepository.findAll(PageRequest.of(0, 10, Sort.by("releaseYear").descending()))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private List<MovieDTO> getPopularMovies() {
        // For now, we'll get movies sorted by release year
        // In a real application, you might want to consider views, ratings, etc.
        return movieRepository.findAll(PageRequest.of(0, 10, Sort.by("releaseYear").descending()))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private List<MovieDTO> getTrendingMovies() {
        // For now, we'll get the latest 5 movies as trending
        // In a real application, you might want to consider recent views, engagement, etc.
        return movieRepository.findAll(PageRequest.of(0, 5, Sort.by("releaseYear").descending()))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MovieDTO convertToDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setMovieId(movie.getMovieId());
        dto.setTitle(movie.getTitle());
        dto.setDirector(movie.getDirector());
        dto.setStudio(movie.getStudio());
        dto.setMovieCast(movie.getMovieCast());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setPoster(movie.getPoster());
        dto.setVideoUrl(movie.getVideoUrl());
        dto.setDuration(movie.getDuration());
        return dto;
    }
} 