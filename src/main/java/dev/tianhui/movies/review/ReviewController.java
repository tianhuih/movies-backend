package dev.tianhui.movies.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.postReview(
                payload.get("reviewBody"), payload.get("imdbId"), payload.get("userId")),
                HttpStatus.CREATED
        );
    }

    @GetMapping(params = "imdbId")
    public ResponseEntity<List<ReviewUsernameDTO>> getReviewsByIMDBId(@RequestParam String imdbId) {
        List<Review> reviews = reviewService.getByIMDBId(imdbId);
        List<ReviewUsernameDTO> result = new ArrayList<>();
        for (Review review : reviews) {
            result.add(new ReviewUsernameDTO(review.getUser().getUsername(), review.getRatings(), review.getBody()));
        }
        return new ResponseEntity<List<ReviewUsernameDTO>>(result, HttpStatus.OK);
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<ReviewMovieDTO>> getReviewsByUserId(@RequestParam String userId) {
        return new ResponseEntity<List<ReviewMovieDTO>>(reviewService.getByUserId(userId), HttpStatus.OK);
    }
}
