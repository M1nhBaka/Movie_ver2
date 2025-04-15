package com.movieflix.service.impl;

import com.movieflix.dto.MovieDTO;
import com.movieflix.dto.MoviePageResponse;
import com.movieflix.entities.Movie;
import com.movieflix.exceptions.MovieNotFoundException;
import com.movieflix.exceptions.ResourceNotFoundException;
import com.movieflix.repositories.MovieRepository;
import com.movieflix.service.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Value("${base.url}")
    private String baseUrl;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public MovieDTO addMovie(MovieDTO movieDto) throws IOException {

        // 3. map dto to Movie object
        Movie movie = new Movie(
                null,
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster(),
                movieDto.getVideoUrl(),
                movieDto.getDuration(),
                movieDto.getGenre(),
                movieDto.getReviews(),
                movieDto.getDescription()
        );

        // 4. save the movie object -> saved Movie object
        Movie savedMovie = movieRepository.save(movie);


        // 6. map Movie object to DTO object and return it
        MovieDTO response = new MovieDTO(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                savedMovie.getVideoUrl(),
                savedMovie.getDuration(),
                savedMovie.getGenre(),
                savedMovie.getReviews(),
                savedMovie.getDescription()
        );

        return response;
    }

    public MovieDTO getMovieByName(String title) {
        Movie movie = movieRepository.findByTitle(title);
        if(movie == null) throw new ResourceNotFoundException("Movie not found");
        MovieDTO response = new MovieDTO(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                movie.getVideoUrl(),
                movie.getDuration(),
                movie.getGenre(),
                movie.getReviews(),
                movie.getDescription()
        );
        return response;
    }

    @Override
    public MovieDTO getMovie(Integer movieId) {
        // 1. check the data in DB and if exists, fetch the data of given ID
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id = " + movieId));

        // 3. map to MovieDto object and return it
        MovieDTO response = new MovieDTO(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                movie.getVideoUrl(),
                movie.getDuration(),
                movie.getGenre(),
                movie.getReviews(),
                movie.getDescription()
        );

        return response;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        // 1. fetch all data from DB
        List<Movie> movies = movieRepository.findAll();

        List<MovieDTO> movieDtos = new ArrayList<>();

        // 2. iterate through the list, generate posterUrl for each movie obj,
        // and map to MovieDto obj
        for(Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            MovieDTO movieDto = new MovieDTO(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    movie.getVideoUrl(),
                    movie.getDuration(),
                    movie.getGenre(),
                    movie.getReviews(),
                    movie.getDescription()
            );
            movieDtos.add(movieDto);
        }

        return movieDtos;
    }

    @Override
    public void updateMovie(Integer movieId, MovieDTO movieDto) throws IOException {
        // 1. check if movie object exists with given movieId
        Movie mv = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id = " + movieId));

        // 2. if file is null, do nothing
        // if file is not null, then delete existing file associated with the record,
        // and upload the new file


        // 4. map it to Movie object
        Movie movie = new Movie(
                mv.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster(),
                movieDto.getVideoUrl(),
                movieDto.getDuration(),
                movieDto.getGenre(),
                movieDto.getReviews(),
                movieDto.getDescription()
        );

        // 5. save the movie object -> return saved movie object
        Movie updatedMovie = movieRepository.save(movie);

        // 7. map to MovieDto and return it
//        MovieDTO response = new MovieDTO(
//                movie.getMovieId(),
//                movie.getTitle(),
//                movie.getDirector(),
//                movie.getStudio(),
//                movie.getMovieCast(),
//                movie.getReleaseYear(),
//                movie.getPoster(),
//                movie.getVideoUrl(),
//                movie.getDuration()
//        );


    }

    @Override
    public String deleteMovie(Integer movieId) throws IOException {
        // 1. check if movie object exists in DB
        Movie mv = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id = " + movieId));
        Integer id = mv.getMovieId();

        // 3. delete the movie object
        movieRepository.delete(mv);

        return "Movie deleted with id = " + id;
    }

    @Override
    public MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Movie> moviePages = movieRepository.findAll(pageable);
        List<Movie> movies = moviePages.getContent();

        List<MovieDTO> movieDtos = new ArrayList<>();

        // 2. iterate through the list, generate posterUrl for each movie obj,
        // and map to MovieDto obj
        for(Movie movie : movies) {
            MovieDTO movieDto = new MovieDTO(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    movie.getVideoUrl(),
                    movie.getDuration(),
                    movie.getGenre(),
                    movie.getReviews(),
                    movie.getDescription()
            );
            movieDtos.add(movieDto);
        }


        return new MoviePageResponse(movieDtos, pageNumber, pageSize,
                                     moviePages.getTotalElements(),
                                     moviePages.getTotalPages(),
                                     moviePages.isLast());
    }

    @Override
    public MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber, Integer pageSize,
                                                                  String sortBy, String dir) {
        Sort sort = dir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                                                                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Movie> moviePages = movieRepository.findAll(pageable);
        List<Movie> movies = moviePages.getContent();

        List<MovieDTO> movieDtos = new ArrayList<>();
        
        // 2. iterate through the list, generate posterUrl for each movie obj,
        // and map to MovieDto obj
        for(Movie movie : movies) {
            MovieDTO movieDto = new MovieDTO(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    movie.getVideoUrl(),
                    movie.getDuration(),
                    movie.getGenre(),
                    movie.getReviews(),
                    movie.getDescription()
            );
            movieDtos.add(movieDto);
        }


        return new MoviePageResponse(movieDtos, pageNumber, pageSize,
                                    moviePages.getTotalElements(),
                                    moviePages.getTotalPages(),
                                    moviePages.isLast());
    }


}
