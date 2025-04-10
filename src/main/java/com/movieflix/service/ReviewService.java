package com.movieflix.service;

import com.movieflix.dto.ReviewDTO;
import com.movieflix.entities.Movie;
import com.movieflix.entities.Review;
import com.movieflix.exceptions.ResourceNotFoundException;
import com.movieflix.repositories.MovieRepository;
import com.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private MovieRepository movieRepository;

    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        Movie movie = movieRepository.findById(reviewDTO.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + reviewDTO.getMovieId()));
        
        Review review = new Review();
        review.setMovie(movie);
        review.setContent(reviewDTO.getContent());
        review.setRating(reviewDTO.getRating());
        review.setReviewerName(reviewDTO.getReviewerName());
        
        Review savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    public List<ReviewDTO> getReviewsByMovieId(Integer movieId) {
        return reviewRepository.findByMovieMovieId(movieId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ReviewDTO convertToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setReviewId(review.getReviewId());
        dto.setMovieId(review.getMovie().getMovieId());
        dto.setContent(review.getContent());
        dto.setRating(review.getRating());
        dto.setReviewerName(review.getReviewerName());
        return dto;
    }
} 