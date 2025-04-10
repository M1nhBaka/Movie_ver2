package com.movieflix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WatchListDTO {
    private Integer watchListId;
    private Integer movieId;
    private String userName;
    private Boolean watched;
} 