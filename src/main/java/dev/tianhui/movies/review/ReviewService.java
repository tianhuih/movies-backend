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
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MovieService movieService;

    public Review postReview(String reviewBody, String imdbId, String userId) {
        User currUser = mongoTemplate.findById(new ObjectId(userId), User.class);
        Optional<Movie> currMovie = movieService.singleMovie(imdbId);
        if (currUser == null) {
            throw new IllegalStateException("user not found!");
        }
        if (!currMovie.isPresent()) {
            throw new IllegalStateException("imdb id not found!");
        }
        List<Review> userReviewsForMovie = getByUserId(userId).stream()
                .filter(review -> review.getMovie().equals(currMovie.get()))
                .collect(Collectors.toList());
        if (!userReviewsForMovie.isEmpty()) {
            Review oldReview = userReviewsForMovie.get(0);
            oldReview.setBody(reviewBody);
            return reviewRepository.save(oldReview);
        } else {
            Review review = new Review(currMovie.get(), currUser, reviewBody);
            return reviewRepository.insert(review);
        }
    }

    public List<Review> getByIMDBId(String imdbId) {
        Optional<Movie> currMovie = movieService.singleMovie(imdbId);
        if (!currMovie.isPresent()) {
            throw new IllegalStateException("imdb id not found!");
        }
        return reviewRepository.findByMovie(currMovie.get());
    }

    public List<Review> getByUserId(String userId) {
        User currUser = mongoTemplate.findById(new ObjectId(userId), User.class);
        return reviewRepository.findByUser(currUser);
    }

}
