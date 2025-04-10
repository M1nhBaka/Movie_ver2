package com.movieflix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieWatchingDTO {
    private Integer watchingId;
    private Integer movieId;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer lastWatchedPosition;
    private Boolean completed;
} 