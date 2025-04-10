package com.movieflix.service;

import com.movieflix.dto.WatchListDTO;
import com.movieflix.entities.Movie;
import com.movieflix.entities.WatchList;
import com.movieflix.exceptions.ResourceNotFoundException;
import com.movieflix.repositories.MovieRepository;
import com.movieflix.repositories.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListService {
    @Autowired
    private WatchListRepository watchListRepository;
    
    @Autowired
    private MovieRepository movieRepository;

    public WatchListDTO addToWatchList(WatchListDTO watchListDTO) {
        Movie movie = movieRepository.findById(watchListDTO.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + watchListDTO.getMovieId()));
        
        if (watchListRepository.existsByMovieMovieIdAndUserName(watchListDTO.getMovieId(), watchListDTO.getUserName())) {
            throw new IllegalArgumentException("Movie is already in the watchlist");
        }
        
        WatchList watchList = new WatchList();
        watchList.setMovie(movie);
        watchList.setUserName(watchListDTO.getUserName());
        watchList.setWatched(false);
        
        WatchList savedWatchList = watchListRepository.save(watchList);
        return convertToDTO(savedWatchList);
    }

    public WatchListDTO markAsWatched(Integer watchListId) {
        WatchList watchList = watchListRepository.findById(watchListId)
                .orElseThrow(() -> new ResourceNotFoundException("WatchList entry not found with id: " + watchListId));
        
        watchList.setWatched(true);
        WatchList updatedWatchList = watchListRepository.save(watchList);
        return convertToDTO(updatedWatchList);
    }

    public List<WatchListDTO> getWatchListByUser(String userName) {
        return watchListRepository.findByUserName(userName)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<WatchListDTO> getWatchedMoviesByUser(String userName) {
        return watchListRepository.findByUserNameAndWatched(userName, true)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private WatchListDTO convertToDTO(WatchList watchList) {
        WatchListDTO dto = new WatchListDTO();
        dto.setWatchListId(watchList.getWatchListId());
        dto.setMovieId(watchList.getMovie().getMovieId());
        dto.setUserName(watchList.getUserName());
        dto.setWatched(watchList.getWatched());
        return dto;
    }
} 