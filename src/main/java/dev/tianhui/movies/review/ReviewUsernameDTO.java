package dev.tianhui.movies.review;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewUsernameDTO {
    private String username;
    private double ratings;
    private String body;
}
