package ro.myclass.onlinemovieapi.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlinemovieapi.dto.MovieDTO;
import ro.myclass.onlinemovieapi.exceptions.ListEmptyException;
import ro.myclass.onlinemovieapi.exceptions.MovieNotFoundException;
import ro.myclass.onlinemovieapi.exceptions.MovieWasFoundException;
import ro.myclass.onlinemovieapi.models.Movie;
import ro.myclass.onlinemovieapi.repo.MovieRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {

    private MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movie> getAllMovies(){
        List<Movie> movies = movieRepo.getAllMovies();

        if(movies.isEmpty()){
            throw new ListEmptyException();
        }else{
            return movies;
        }
    }

    public void addMovie(MovieDTO movieDTO) {
        Optional<Movie> movie = movieRepo.getMovieByName(movieDTO.getName());

        if (movie.isEmpty()) {

            Movie movie1 = Movie.builder()
                    .name(movieDTO.getName())
                    .genre(movieDTO.getGenre())
                    .year(movieDTO.getYear())
                    .rating(movieDTO.getRating())
                    .director(movieDTO.getDirector())
                    .build();

            this.movieRepo.save(movie1);

        } else {
            throw new MovieWasFoundException();
        }
    }

    public void deleteMovie(String name){
        Optional<Movie> movie = movieRepo.getMovieByName(name);
        if(movie.isEmpty()){
            throw new MovieNotFoundException();
        }else{
            movieRepo.delete(movie.get());
        }
    }

    public void updateMovie(MovieDTO movieDTO){
        Optional<Movie> movie = movieRepo.getMovieByName(movieDTO.getName());

        if(movie.isEmpty()){
            throw new MovieNotFoundException();
        }
        Movie movie1 = movie.get();


          if(movieDTO.getGenre()!=null){
              movie1.setGenre(movieDTO.getGenre());
        }if (movieDTO.getYear()!=0){
              movie1.setYear(movieDTO.getYear());
          }if(movieDTO.getRating()!=0){
              movie1.setRating(movieDTO.getRating());
          }if(movieDTO.getDirector()!=null){
              movie1.setDirector(movieDTO.getDirector());
          }

          movieRepo.saveAndFlush(movie1);


    }

    public Movie getMovieByName(String name){
        Optional<Movie> movie = movieRepo.getMovieByName(name);
        if(movie.isEmpty()){
            throw new MovieNotFoundException();
        }else{
            return movie.get();
        }
    }

    public List<Movie> getMovieByGenre(String genre){
        List<Movie> movies = movieRepo.getMovieByGenre(genre);
        if(movies.isEmpty()){
            throw new ListEmptyException();
        }else{
            return movies;
        }
    }

    public List<Movie> getMovieByYear(int year){
        List<Movie> movies = movieRepo.getMovieByYear(year);
        if(movies.isEmpty()){
            throw new ListEmptyException();
        }else{
            return movies;
        }
    }

    public List<Movie> getMovieByRating(int rating){
        List<Movie> movies = movieRepo.getMovieByRating(rating);
        if(movies.isEmpty()){
            throw new ListEmptyException();
        }else{
            return movies;
        }
    }

    public List<Movie> getMovieByDirector(String director){
        List<Movie> movies = movieRepo.getMovieByDirector(director);
        if(movies.isEmpty()){
            throw new ListEmptyException();
        }else{
            return movies;
        }
    }

    public Movie getMovieByNameAndGenre(String name,String genre){
        Optional<Movie> movie = movieRepo.getMovieByNameAndGenre(name,genre);
        if(movie.isEmpty()){
            throw new MovieNotFoundException();
        }else{
            return movie.get();
        }
    }

    public Movie getMovieByNameAndYear(String name,int year){
        Optional<Movie> movie = movieRepo.getMovieByNameAndYear(name,year);
        if(movie.isEmpty()){
            throw new MovieNotFoundException();
        }else{
            return movie.get();
        }
    }

    public Movie getMovieByNameAndRating(String name,int rating){
        Optional<Movie> movie = movieRepo.getMovieByNameAndRating(name,rating);
        if(movie.isEmpty()){
            throw new MovieNotFoundException();
        }else{
            return movie.get();
        }
    }

    public Movie getMovieByNameAndDirector(String name,String director){
        Optional<Movie> movie = movieRepo.getMovieByNameAndDirector(name,director);
        if(movie.isEmpty()){
            throw new MovieNotFoundException();
        }else{
            return movie.get();
        }
    }


    public List<Movie> getMovieByGenreAndYear(String genre,int year){
        List<Movie> movies = movieRepo.getMovieByGenreAndYear(genre,year);
        if(movies.isEmpty()){
            throw new ListEmptyException();
        }else{
            return movies;
        }
    }

    public List<Movie> getMovieByGenreAndRating(String genre,int rating){
        List<Movie> movies = movieRepo.getMovieByGenreAndRating(genre,rating);
        if(movies.isEmpty()){
            throw new ListEmptyException();
        }else{
            return movies;
        }
    }

    public Movie getMovieById(int id){
        Optional<Movie> movie = movieRepo.getMovieById( id);
        if(movie.isEmpty()){
            throw new MovieNotFoundException();
        }else{
            return movie.get();
        }
    }


}
