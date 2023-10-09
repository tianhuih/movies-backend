package dev.tianhui.movies.user;

import dev.tianhui.movies.review.ReviewMovieDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserHomepageDTO {
    private String username;
    private String email;
    private boolean isEnabled;
    private List<ReviewMovieDTO> reviews;
}
