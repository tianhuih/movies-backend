package dev.tianhui.movies.review;

import dev.tianhui.movies.movie.Movie;
import dev.tianhui.movies.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private ObjectId id;

    private String body;

    private double ratings;
    @DocumentReference
    private Movie rMovie;
    @DocumentReference
    private User rUser;

    public Review(Movie rMovie, User rUser, String body) {
        this.rMovie = rMovie;
        this.rUser = rUser;
        this.body = body;
    }
}
