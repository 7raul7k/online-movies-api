package ro.myclass.onlinemovieapi.repo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.onlinemovieapi.OnlineMovieApiApplication;
import ro.myclass.onlinemovieapi.models.Movie;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineMovieApiApplication.class)
@Transactional
class MovieRepoTest {

    @Autowired
    MovieRepo movieRepo;

    @BeforeEach
    public void clean() {
        movieRepo.deleteAll();
    }

    @Test
    public void getAllMovies(){

       //generate 4 movies

        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Crime").description("A crime story").director("Francis Ford Coppola").rating(9).year(1972).build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Drama").description("A prison story").director("Frank Darabont").rating(9).year(1994).build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("Christopher Nolan").rating(9).year(2008).build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getAllMovies());
    }

    @Test
    public void getMovieByName(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();
        movieRepo.save(movie);

        assertEquals(movie,movieRepo.getMovieByName("Titanic").get());
    }

    @Test
    public void getMovieByGenre(){

        //generate 4 movies with same drama genre
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Drama").description("A crime story").director("Francis Ford Coppola").rating(9).year(1972).build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Drama").description("A prison story").director("Frank Darabont").rating(9).year(1994).build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Drama").description("A superhero story").director("Christopher Nolan").rating(9).year(2008).build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByGenre("Drama"));
    }

    @Test
    public void getMovieByYear(){

        //generate 4 movies with year 1997
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Crime").description("A crime story").director("Francis Ford Coppola").rating(9).year(1997).build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Drama").description("A prison story").director("Frank Darabont").rating(9).year(1997).build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("Christopher Nolan").rating(9).year(1997).build();
        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByYear(1997));
    }

    @Test
    public void getMovieByRating(){


        //generate 4 movies with rating 8
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Crime").description("A crime story").director("Francis Ford Coppola").rating(8).year(1972).build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Drama").description("A prison story").director("Frank Darabont").rating(8).year(1994).build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("Christopher Nolan").rating(8).year(2008).build();
         movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByRating(8));
    }

    @Test
    public void getMovieByDirector(){


       // generate 4 movies with same director
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Action").description("A love story").director("James Cameron").year(1997).rating(9).build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Action").description("A crime story").director("James Cameron").year(1972).rating(9).build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Action").description("A prison story").director("James Cameron").year(1994).rating(9).build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("James Cameron").year(2008).rating(9).build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByDirector("James Cameron"));
    }



    @Test
    public void getMovieByNameAndGenre(){


        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();

        movieRepo.save(movie);


        assertEquals(movie,movieRepo.getMovieByNameAndGenre("Titanic","Drama").get());

    }

    @Test
    public void getMovieByNameAndYear(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();
        movieRepo.save(movie);

        assertEquals(movie,movieRepo.getMovieByNameAndYear("Titanic",1997).get());
    }

    @Test
    public void getMovieByNameAndDirector(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();
        movieRepo.save(movie);

        assertEquals(movie,movieRepo.getMovieByNameAndDirector("Titanic","James Cameron").get());
    }

    @Test
    public void getMovieByNameAndRating(){

        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();

        movieRepo.save(movie);


        assertEquals(movie,movieRepo.getMovieByNameAndRating("Titanic",8).get());
         }




    @Test
    public void getMovieByGenreAndYear(){

        // generate 4 movies with same genre and year

        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Action").description("A love story").director("James Cameron").year(1997).rating(9).build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Action").description("A crime story").director("Francis Ford Coppola").year(1972).rating(9).build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Action").description("A prison story").director("Frank Darabont").year(1994).rating(9).build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("Christopher Nolan").year(2008).rating(9).build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

          }

    @Test
    public void getMovieByGenreAndRating(){

        //generate 4 movies with same genre and rating
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Action").description("A love story").director("James Cameron").year(1997).rating(9).build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Action").description("A crime story").director("Francis Ford Coppola").year(1972).rating(9).build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Action").description("A prison story").director("Frank Darabont").year(1994).rating(9).build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("Christopher Nolan").year(2008).rating(9).build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByGenreAndRating("Action",9));

         }


    @Test
    public void getMovieById(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").rating(8).year(1997).build();
          movieRepo.save(movie);
        assertEquals(movie,movieRepo.getMovieById(movie.getId()).get());
    }




}