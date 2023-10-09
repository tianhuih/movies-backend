package dev.tianhui.movies.movie;

import dev.tianhui.movies.review.ReviewUsernameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MoviePageDTO {
    private Movie movie;
    private List<ReviewUsernameDTO> reviews;
}
