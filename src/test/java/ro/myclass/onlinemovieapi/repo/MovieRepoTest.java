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

        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(8).year(1997).country("USA").date("1997-12-19").build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Crime").description("A crime story").director("Francis Ford Coppola").duration(180).language("English").rating(9).year(1972).country("USA").date("1972-03-24").build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Drama").description("A prison story").director("Frank Darabont").duration(142).language("English").rating(9).year(1994).country("USA").date("1994-10-14").build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("Christopher Nolan").duration(152).language("English").rating(9).year(2008).country("USA").date("2008-07-18").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList= new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getAllMovies());
    }

    @Test
    public void getMovieByName(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(8).year(1997).country("USA").date("1997-12-19").build();

        movieRepo.save(movie);

        assertEquals(movie,movieRepo.getMovieByName("Titanic").get());
    }

    @Test
    public void getMovieByGenre(){


        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(8).year(1997).country("USA").date("1997-12-19").build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Drama").description("A crime story").director("Francis Ford Coppola").duration(180).language("English").rating(9).year(1972).country("USA").date("1972-03-24").build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Drama").description("A prison story").director("Frank Darabont").duration(142).language("English").rating(9).year(1994).country("USA").date("1994-10-14").build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Drama").description("A superhero story").director("Christopher Nolan").duration(152).language("English").rating(9).year(2008).country("USA").date("2008-07-18").build();

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


        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(8).year(1997).country("USA").date("1997-12-19").build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Crime").description("A crime story").director("Francis Ford Coppola").duration(180).language("English").rating(9).year(1997).country("USA").date("1972-03-24").build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Drama").description("A prison story").director("Frank Darabont").duration(142).language("English").rating(9).year(1997).country("USA").date("1994-10-14").build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("Christopher Nolan").duration(152).language("English").rating(9).year(1997).country("USA").date("2008-07-18").build();

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


        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(8).year(1997).country("USA").date("1997-12-19").build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Crime").description("A crime story").director("Francis Ford Coppola").duration(180).language("English").rating(8).year(1972).country("USA").date("1972-03-24").build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Drama").description("A prison story").director("Frank Darabont").duration(142).language("English").rating(8).year(1994).country("USA").date("1994-10-14").build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("Christopher Nolan").duration(152).language("English").rating(8).year(2008).country("USA").date("2008-07-18").build();

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


        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(8).year(1997).country("USA").date("1997-12-19").build();
        Movie movie1 = Movie.builder().name("The Godfather").description("A crime story").genre("Crime").description("A crime story").director("James Cameron").duration(180).language("English").rating(8).year(1972).country("USA").date("1972-03-24").build();
        Movie movie2 = Movie.builder().name("The Shawshank Redemption").description("A prison story").genre("Drama").description("A prison story").director("James Cameron").duration(142).language("English").rating(8).year(1994).country("USA").date("1994-10-14").build();
        Movie movie3 = Movie.builder().name("The Dark Knight").description("A superhero story").genre("Action").description("A superhero story").director("James Cameron").duration(152).language("English").rating(8).year(2008).country("USA").date("2008-07-18").build();

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
    public void getMovieByLanguage(){

        Movie movie = Movie.builder().name("Transformers").description("A robot story").genre("Action").description("A robot story").director("Michael Bay").duration(144).language("English").rating(8).year(2007).country("USA").date("2007-07-03").build();
        Movie movie1 = Movie.builder().name("Transformers: Revenge of the Fallen").description("A robot story").genre("Action").description("A robot story").director("Michael Bay").duration(150).language("English").rating(8).year(2009).country("USA").date("2009-06-19").build();
        Movie movie2 = Movie.builder().name("Transformers: Dark of the Moon").description("A robot story").genre("Action").description("A robot story").director("Michael Bay").duration(154).language("English").rating(8).year(2011).country("USA").date("2011-06-29").build();
        Movie movie3 = Movie.builder().name("Transformers: Age of Extinction").description("A robot story").genre("Action").description("A robot story").director("Michael Bay").duration(165).language("English").rating(8).year(2014).country("USA").date("2014-06-25").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByLanguage("English"));

    }

    @Test
    public void getMoviesByCountry(){

        Movie movie = Movie.builder().name("Morometii").description("A romanian story").genre("Drama").description("A romanian story").director("Sterea").duration(144).language("Romanian").rating(8).year(1987).country("Romania").date("1987-07-03").build();
        Movie movie1 = Movie.builder().name("Pistruiatul").description("A romanian story").genre("Drama").description("A romanian story").director("Sergiu Nicolaescu").duration(150).language("Romanian").rating(8).year(1973).country("Romania").date("1973-06-19").build();
        Movie movie2 = Movie.builder().name("Balanța").description("A romanian story").genre("Drama").description("A romanian story").director("Lucian Pintilie").duration(154).language("Romanian").rating(8).year(1992).country("Romania").date("1992-06-29").build();
        Movie movie3 = Movie.builder().name("Filantropica").description("A romanian story").genre("Drama").description("A romanian story").director("Nae Caranfil").duration(165).language("Romanian").rating(8).year(2002).country("Romania").date("2002-06-25").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByCountry("Romania"));



    }

    @Test
    public void getMovieByNameAndGenre(){


        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(8).year(1997).country("USA").date("1997-12-19").build();

        movieRepo.save(movie);


        assertEquals(movie,movieRepo.getMovieByNameAndGenre("Titanic","Drama").get());

    }

    @Test
    public void getMovieByNameAndYear(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(8).year(1997).country("USA").date("1997-12-19").build();

        movieRepo.save(movie);

        assertEquals(movie,movieRepo.getMovieByNameAndYear("Titanic",1997).get());
    }

    @Test
    public void getMovieByNameAndDirector(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(8).year(1997).country("USA").date("1997-12-19").build();

        movieRepo.save(movie);

        assertEquals(movie,movieRepo.getMovieByNameAndDirector("Titanic","James Cameron").get());
    }

    @Test
    public void getMovieByNameAndRating(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("English").rating(9).year(1997).country("USA").date("1997-12-19").build();

        movieRepo.save(movie);

        assertEquals(movie,movieRepo.getMovieByNameAndRating("Titanic",9).get());
    }

    @Test
    public void getMovieByNameAndLanguage(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("Romanian").rating(8).year(1997).country("USA").date("1997-12-19").build();

        movieRepo.save(movie);

        assertEquals(movie,movieRepo.getMovieByNameAndLanguage("Titanic","Romanian").get());
    }

    @Test
    public void getMovieByNameAndCountry(){
        Movie movie = Movie.builder().name("Titanic").description("A love story").genre("Drama").description("A love story").director("James Cameron").duration(210).language("Romanian").rating(8).year(1997).country("Romania").date("1997-12-19").build();

        movieRepo.save(movie);

        assertEquals(movie,movieRepo.getMovieByNameAndCountry("Titanic","Romania").get());
    }

    @Test
    public void getMovieByGenreAndYear(){


        Movie movie = Movie.builder().name("The Matrix 4").description("A sci-fi story").genre("Action").description("A sci-fi story").director("Lana Wachowski").duration(144).language("English").rating(8).year(2022).country("USA").date("2022-12-22").build();
        Movie movie1 = Movie.builder().name("Thor: Love and Thunder").description("A superhero story").genre("Action").description("A superhero story").director("Taika Waititi").duration(150).language("English").rating(8).year(2022).country("USA").date("2022-05-06").build();
        Movie movie2 = Movie.builder().name("Doctor Strange in the Multiverse of Madness").description("A superhero story").genre("Action").description("A superhero story").director("Sam Raimi").duration(154).language("English").rating(8).year(2022).country("USA").date("2022-03-25").build();
        Movie movie3 = Movie.builder().name("Black Panther II").description("A superhero story").genre("Action").description("A superhero story").director("Ryan Coogler").duration(165).language("English").rating(8).year(2022).country("USA").date("2022-07-08").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByGenreAndYear("Action",2022));
    }

    @Test
    public void getMovieByGenreAndRating(){


        Movie movie = Movie.builder().name("The Matrix 4").description("A sci-fi story").genre("Action").description("A sci-fi story").director("Lana Wachowski").duration(144).language("English").rating(8).year(2020).country("USA").date("2020-12-22").build();
        Movie movie1 = Movie.builder().name("Thor: Love and Thunder").description("A superhero story").genre("Action").description("A superhero story").director("Taika Waititi").duration(150).language("English").rating(8).year(2020).country("USA").date("2020-05-06").build();
        Movie movie2 = Movie.builder().name("Doctor Strange in the Multiverse of Madness").description("A superhero story").genre("Action").description("A superhero story").director("Sam Raimi").duration(154).language("English").rating(8).year(2020).country("USA").date("2020-03-25").build();
        Movie movie3 = Movie.builder().name("Black Panther II").description("A superhero story").genre("Action").description("A superhero story").director("Ryan Coogler").duration(165).language("English").rating(8).year(2020).country("USA").date("2020-07-08").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByGenreAndRating("Action",8));
    }

    @Test
    public void getMovieByDate(){
        //generate 4 movies from 2019 with real proprietes with same date
        //save them in the database
        //create a arraylist and save them

        Movie movie = Movie.builder().name("The Matrix 4").description("A sci-fi story").genre("Action").description("A sci-fi story").director("Lana Wachowski").duration(144).language("English").rating(8).year(2020).country("USA").date("2020-12-22").build();
        Movie movie1 = Movie.builder().name("Thor: Love and Thunder").description("A superhero story").genre("Action").description("A superhero story").director("Taika Waititi").duration(150).language("English").rating(8).year(2020).country("USA").date("2020-12-22").build();
        Movie movie2 = Movie.builder().name("Doctor Strange in the Multiverse of Madness").description("A superhero story").genre("Action").description("A superhero story").director("Sam Raimi").duration(154).language("English").rating(8).year(2020).country("USA").date("2020-12-22").build();
        Movie movie3 = Movie.builder().name("Black Panther II").description("A superhero story").genre("Action").description("A superhero story").director("Ryan Coogler").duration(165).language("English").rating(8).year(2020).country("USA").date("2020-12-22").build(); 


        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        assertEquals(movieList,movieRepo.getMovieByDate("2020-12-22"));
    }




}