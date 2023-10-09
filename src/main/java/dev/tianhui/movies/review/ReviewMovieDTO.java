package dev.tianhui.movies.review;

import dev.tianhui.movies.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewMovieDTO {
    private Movie movie;
    private double ratings;
    private String body;
}
