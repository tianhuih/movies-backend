package dev.tianhui.movies.review;

import dev.tianhui.movies.movie.Movie;
import dev.tianhui.movies.movie.MovieService;
import dev.tianhui.movies.user.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MovieService movieService;

    public Review createReview(String reviewBody, String imdbId, String userId) {
        User currUser = mongoTemplate.findById(new ObjectId(userId), User.class);
        Optional<Movie> currMovie = movieService.singleMovie(imdbId);
        if (!currMovie.isPresent()) {
            throw new IllegalStateException("imdb id not found!");
        }
        Review review = reviewRepository.insert(new Review(currMovie.get(), currUser, reviewBody));
        return review;
    }

    public List<Review> getByIMDBId(String imdbId) {
        Optional<Movie> currMovie = movieService.singleMovie(imdbId);
        if (!currMovie.isPresent()) {
            throw new IllegalStateException("imdb id not found!");
        }
        return reviewRepository.findByrMovie(currMovie.get());
    }

    public List<Review> getByUserId(String userId) {
        User currUser = mongoTemplate.findById(new ObjectId(userId), User.class);
        return reviewRepository.findByrUser(currUser);
    }

}
