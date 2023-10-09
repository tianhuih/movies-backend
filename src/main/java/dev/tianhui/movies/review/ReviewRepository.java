package dev.tianhui.movies.review;

import dev.tianhui.movies.movie.Movie;
import dev.tianhui.movies.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    List<Review> findByrMovie(Movie rMovie);
    List<Review> findByrUser(User rUser);
}
