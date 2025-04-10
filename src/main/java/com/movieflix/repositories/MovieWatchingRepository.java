package com.movieflix.repositories;

import com.movieflix.entities.MovieWatching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieWatchingRepository extends JpaRepository<MovieWatching, Integer> {
    List<MovieWatching> findByUserName(String userName);
    List<MovieWatching> findByUserNameAndCompleted(String userName, Boolean completed);
    Optional<MovieWatching> findByMovieMovieIdAndUserNameAndCompleted(Integer movieId, String userName, Boolean completed);
} 