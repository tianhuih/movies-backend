package dev.tianhui.movies.movie;

import dev.tianhui.movies.review.ReviewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewController reviewController;
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<MoviePageDTO> getMoviePage(@PathVariable String imdbId) {
        Optional<Movie> movie = movieService.singleMovie(imdbId);
        if (movie.isEmpty()) {
            throw new IllegalStateException("imdb id not found!");
        }
        MoviePageDTO result = new MoviePageDTO(movie.get(), reviewController.getReviewsByIMDBId(imdbId).getBody());
        return new ResponseEntity<MoviePageDTO>(result, HttpStatus.OK);
    }


}
