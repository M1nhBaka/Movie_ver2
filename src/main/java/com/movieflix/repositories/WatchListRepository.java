package com.movieflix.repositories;

import com.movieflix.entities.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Integer> {
    List<WatchList> findByUserName(String userName);
    List<WatchList> findByUserNameAndWatched(String userName, Boolean watched);
    boolean existsByMovieMovieIdAndUserName(Integer movieId, String userName);
} 