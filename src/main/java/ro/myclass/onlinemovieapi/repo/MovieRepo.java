package ro.myclass.onlinemovieapi.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.onlinemovieapi.models.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {

    @Query("select m from Movie m where m.name = ?1")
    Optional<Movie> getMovieByName(String name);

    @Query("select m from Movie m where m.genre = ?1")
    List<Movie> getMovieByGenre(String genre);

    @Query("select m from Movie m where m.year = ?1")
    List<Movie> getMovieByYear(int year);

    @Query("select m from Movie m where m.rating = ?1")
    List<Movie> getMovieByRating(int rating);

    @Query("select m from Movie m where m.director = ?1")
    List<Movie> getMovieByDirector(String director);

    @Query("select m from Movie m where m.language = ?1")
    List<Movie> getMovieByLanguage(String language);


    @Query("select m from Movie m where m.country = ?1")
    List<Movie> getMovieByCountry(String country);
    @Query("select m from Movie m where m.name = ?1 AND m.genre = ?2")
    Optional<Movie> getMovieByNameAndGenre(String name,String genre);

    @Query("select m from Movie m where m.name = ?1 AND m.year = ?2")
    Optional<Movie> getMovieByNameAndYear(String name,int year);

    @Query("select m from Movie m where m.name = ?1 AND m.rating = ?2")
    Optional<Movie> getMovieByNameAndRating(String name,int rating);

    @Query("select m from Movie m where m.name = ?1 AND m.director = ?2")
    Optional<Movie> getMovieByNameAndDirector(String name,String director);


    @Query("select m from Movie m where m.name = ?1 AND m.language = ?2")
    Optional<Movie> getMovieByNameAndLanguage(String name,String language);

    @Query("select m from Movie m where m.name = ?1 AND m.country = ?2")
    Optional<Movie> getMovieByNameAndCountry(String name,String country);

    @Query("select m from Movie m where m.genre = ?1 AND m.year = ?2")
    List<Movie> getMovieByGenreAndYear(String genre,int year);

    @Query("select m from Movie m where m.genre = ?1 AND m.rating = ?2")
    List<Movie> getMovieByGenreAndRating(String genre,int rating);


    @Query("select m from Movie m where m.date = ?1")
    List<Movie> getMovieByDate(String date);

    @Query("select m from Movie m ")
    List<Movie> getAllMovies();

    @Query("select m from Movie m where m.id = ?1")
    Optional<Movie> getMovieById(long id);




}
