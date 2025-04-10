package com.movieflix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HomePageDTO {
    private List<MovieDTO> featuredMovies;
    private List<MovieDTO> recentlyAdded;
    private List<MovieDTO> popularMovies;
    private List<MovieDTO> trendingNow;
} 