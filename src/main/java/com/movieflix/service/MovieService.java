package com.movieflix.service;

import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.MoviePageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    MovieDTO addMovie(MovieDTO movieDto) throws IOException;

    MovieDTO getMovie(Integer movieId);

    List<MovieDTO> getAllMovies();

    void updateMovie(Integer movieId, MovieDTO movieDto) throws IOException;

    String deleteMovie(Integer movieId) throws IOException;

    MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize);

    MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber, Integer pageSize,
                                                           String sortBy, String dir);
    MovieDTO getMovieByName(String title) ;

}
