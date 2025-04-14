package com.movieflix.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDTO {

    private Integer movieId;

    @NotBlank(message = "Please provide movie's title!")
    private String title;

    @NotBlank(message = "Please provide movie's director!")
    private String director;

    @NotBlank(message = "Please provide movie's studio!")
    private String studio;

    private Set<String> movieCast;

    private Integer releaseYear;

    @NotBlank(message = "Please provide movie's poster!")
    private String poster;

    @Column(nullable = false, name = "video_url")
    @NotBlank(message = "Please provide movie's video URL!")
    private String videoUrl;

    @Column(nullable = false)
    private Integer duration; // in seconds

    private String genre;

    private Double rating;

    private String description;
}
