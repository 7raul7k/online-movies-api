package ro.myclass.onlinemovieapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.onlinemovieapi.dto.MovieDTO;
import ro.myclass.onlinemovieapi.models.Movie;
import ro.myclass.onlinemovieapi.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@Slf4j
public class MovieResource {

    private MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/allMovies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movieList = this.movieService.getAllMovies();

        log.info("REST request to get all movies {}", movieList);

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(MovieDTO movie) {
        this.movieService.addMovie(movie);

        log.info("REST request to add a movie {}", movie);

        return new ResponseEntity<>("Movie was added", HttpStatus.OK);
    }

    @DeleteMapping("/deleteMovie/{name}")
    public ResponseEntity<String> deleteMovie(@PathVariable String name) {
        this.movieService.deleteMovie(name);

        log.info("REST request to delete a movie by name {}", name);

        return new ResponseEntity<>("Movie was deleted", HttpStatus.OK);
    }

    @GetMapping("/getMovieById/{id}")
    public ResponseEntity<Movie> getMovieById(int id) {
        Movie movie = this.movieService.getMovieById(id);

        log.info("REST request to get a movie by id {}", id);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getMovieByName/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
        Movie movie = this.movieService.getMovieByName(name);

        log.info("REST request to get a movie by name {}", name);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PutMapping("/updateMovie")
    public ResponseEntity<String> updateMovie(MovieDTO movie) {
        this.movieService.updateMovie(movie);

        log.info("REST request to update a movie {}", movie);

        return new ResponseEntity<>("Movie was updated", HttpStatus.OK);
    }

    @GetMapping("/getMovieByGenre/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {

        List<Movie> movieList = this.movieService.getMovieByGenre(genre);

        log.info("REST request to get a movie by genre {}", genre);

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/getMovieByYear/{year}")
    public ResponseEntity<List<Movie>> getMovieByYear(@PathVariable int year) {

        List<Movie> movieList = this.movieService.getMovieByYear(year);

        log.info("REST request to get a movie by year {}", year);

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/getMovieByRating/{rating}")
    public ResponseEntity<List<Movie>> getMovieByRating(@PathVariable int rating) {

        List<Movie> movieList = this.movieService.getMovieByRating(rating);

        log.info("REST request to get a movie by rating {}", rating);

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/getMovieByDirector/{director}")
    public ResponseEntity<List<Movie>> getMovieByDirector(@PathVariable String director) {

        List<Movie> movieList = this.movieService.getMovieByDirector(director);

        log.info("REST request to get a movie by director {}", director);

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/getMovieByLanguage/{language}")
    public ResponseEntity<List<Movie>> getMovieByLanguage(@PathVariable String language) {

        List<Movie> movieList = this.movieService.getMovieByLanguage(language);

        log.info("REST request to get a movie by language {}", language);

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/getMovieByCountry/{country}")
    public ResponseEntity<List<Movie>> getMovieByCountry(@PathVariable String country) {

        List<Movie> movieList = this.movieService.getMovieByCountry(country);

        log.info("REST request to get a movie by country {}", country);

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/getMovieByNameAndGenre")
    public ResponseEntity<Movie> getMovieByNameAndGenre(@RequestParam String name,@RequestParam String genre) {

        Movie movie = this.movieService.getMovieByNameAndGenre(name,genre);

        log.info("REST request to get a movie by name and genre {}", name,genre);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getMovieByNameAndYear")
    public ResponseEntity<Movie> getMovieByNameAndYear(@RequestParam String name,@RequestParam int year) {

        Movie movie = this.movieService.getMovieByNameAndYear(name,year);

        log.info("REST request to get a movie by name and year {}", name,year);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getMovieByNameAndRating")
    public ResponseEntity<Movie> getMovieByNameAndRating(@RequestParam String name,@RequestParam int rating) {

        Movie movie = this.movieService.getMovieByNameAndRating(name,rating);

        log.info("REST request to get a movie by name and rating {}", name,rating);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getMovieByNameAndDirector")
    public ResponseEntity<Movie> getMovieByNameAndDirector(@RequestParam String name,@RequestParam String director) {

        Movie movie = this.movieService.getMovieByNameAndDirector(name,director);

        log.info("REST request to get a movie by name and director {}", name,director);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getMovieByNameAndLanguage")
    public ResponseEntity<Movie> getMovieByNameAndLanguage(@RequestParam String name,@RequestParam String language) {

        Movie movie = this.movieService.getMovieByNameAndLanguage(name,language);

        log.info("REST request to get a movie by name and language {}", name,language);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getMovieByNameAndCountry")
    public ResponseEntity<Movie> getMovieByNameAndCountry(@RequestParam String name,@RequestParam String country) {

        Movie movie = this.movieService.getMovieByNameAndCountry(name,country);

        log.info("REST request to get a movie by name and country {}", name,country);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/getMovieByGenreAndYear")
    public ResponseEntity<List<Movie>> getMovieByGenreAndYear(@RequestParam String genre,@RequestParam int year) {

        List<Movie> movieList = this.movieService.getMovieByGenreAndYear(genre,year);

        log.info("REST request to get a movie by genre and year {}", genre,year);

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/getMovieByGenreAndRating")
    public ResponseEntity<List<Movie>> getMovieByGenreAndRating(@RequestParam String genre,@RequestParam int rating) {

        List<Movie> movieList = this.movieService.getMovieByGenreAndRating(genre,rating);

        log.info("REST request to get a movie by genre and rating {}", genre,rating);

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    





}
