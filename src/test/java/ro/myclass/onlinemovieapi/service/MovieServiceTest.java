package ro.myclass.onlinemovieapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.onlinemovieapi.dto.MovieDTO;
import ro.myclass.onlinemovieapi.exceptions.ListEmptyException;
import ro.myclass.onlinemovieapi.exceptions.MovieNotFoundException;
import ro.myclass.onlinemovieapi.exceptions.MovieWasFoundException;
import ro.myclass.onlinemovieapi.models.Movie;
import ro.myclass.onlinemovieapi.repo.MovieRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepo movieRepo;

    @InjectMocks
    private MovieService movieService;

    @Captor
    private ArgumentCaptor<Movie> argumentCaptor;

    @Test
    public void getAllMovies(){

        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").build();
        Movie movie1 = Movie.builder().id(2L).name("test1").genre("test1").year(2001).rating(4).director("test1").language("test1").country("test1").build();
        Movie movie2 = Movie.builder().id(3L).name("test2").genre("test2").year(2002).rating(3).director("test2").language("test2").country("test2").build();
        Movie movie3 = Movie.builder().id(4L).name("test3").genre("test3").year(2003).rating(2).director("test3").language("test3").country("test3").build();


        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);


        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        doReturn(movieList).when(movieRepo).getAllMovies();

        assertEquals(movieList, movieService.getAllMovies());

    }

    @Test
    public void getAllMoviesException(){
        List<Movie> movieList = new ArrayList<>();

        doReturn(movieList).when(movieRepo).getAllMovies();

        assertThrows(ListEmptyException.class, () -> movieService.getAllMovies());
    }

    @Test
    public void addMovie(){
        MovieDTO movieDTO = MovieDTO.builder().name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();

        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();

        doReturn(Optional.empty()).when(movieRepo).getMovieByName(movie.getName());


        movieService.addMovie(movieDTO);

        verify(movieRepo,times(1)).save(argumentCaptor.capture());



        assertEquals(movie, argumentCaptor.getValue());
    }

    @Test
    public void addMovieException() {
        MovieDTO movieDTO = MovieDTO.builder().name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();

        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByName(movie.getName());

        assertThrows(MovieWasFoundException.class, () -> movieService.addMovie(movieDTO));
    }

    @Test
    public void deleteMovie(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByName(movie.getName());

        movieService.deleteMovie(movie.getName());

        verify(movieRepo,times(1)).delete(argumentCaptor.capture());

        assertEquals(movie,argumentCaptor.getValue());
    }

    @Test
    public void deleteMovieException(){

        doReturn(Optional.empty()).when(movieRepo).getMovieByName("test");

        assertThrows(MovieNotFoundException.class,()->movieService.deleteMovie("test"));
    }

    @Test
    public void updateMovie(){
        MovieDTO movieDTO = MovieDTO.builder().name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();

        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByName(movie.getName());

        movieService.updateMovie(movieDTO);

        verify(movieRepo,times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(movie,argumentCaptor.getValue());
    }

    @Test
    public void updateMovieException(){
        MovieDTO movieDTO = MovieDTO.builder().name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();

        doReturn(Optional.empty()).when(movieRepo).getMovieByName("test");

        assertThrows(MovieNotFoundException.class,()->movieService.updateMovie(movieDTO));
    }

    @Test
    public void getMovieByName(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByName(movie.getName());

        assertEquals(movie,movieService.getMovieByName(movie.getName()));
    }

    @Test
    public void getMovieByNameException(){

        doReturn(Optional.empty()).when(movieRepo).getMovieByName("test");

        assertThrows(MovieNotFoundException.class,()->movieService.getMovieByName("test"));
    }

    @Test
    public void getMovieByGenre(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();
        Movie movie1 = Movie.builder().id(2L).name("test1").genre("test1").year(2001).rating(4).director("test1").language("test1").country("test1").description("test1").date("test1").build();
        Movie movie2 = Movie.builder().id(3L).name("test2").genre("test2").year(2002).rating(3).director("test2").language("test2").country("test2").description("test2").date("test2").build();
        Movie movie3 = Movie.builder().id(4L).name("test3").genre("test3").year(2003).rating(2).director("test3").language("test3").country("test3").description("test3").date("test3").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        doReturn(movieList).when(movieRepo).getMovieByGenre(movie.getGenre());

        assertEquals(movieList,movieService.getMovieByGenre(movie.getGenre()));
    }

    @Test
    public void getMovieByGenreException(){
        List<Movie> movieList = new ArrayList<>();

        doReturn(movieList).when(movieRepo).getMovieByGenre("test");

        assertThrows(ListEmptyException.class,()->movieService.getMovieByGenre("test"));
    }

    @Test
    public void getMovieByYear(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();
        Movie movie1 = Movie.builder().id(2L).name("test1").genre("test1").year(2000).rating(4).director("test1").language("test1").country("test1").description("test1").date("test1").build();
        Movie movie2 = Movie.builder().id(3L).name("test2").genre("test2").year(2000).rating(3).director("test2").language("test2").country("test2").description("test2").date("test2").build();
        Movie movie3 = Movie.builder().id(4L).name("test3").genre("test3").year(2000).rating(2).director("test3").language("test3").country("test3").description("test3").date("test3").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        doReturn(movieList).when(movieRepo).getMovieByYear(movie.getYear());

        assertEquals(movieList,movieService.getMovieByYear(movie.getYear()));
    }

    @Test
    public void getMovieByYearException(){
        List<Movie> movieList = new ArrayList<>();

        doReturn(movieList).when(movieRepo).getMovieByYear(2000);

        assertThrows(ListEmptyException.class,()->movieService.getMovieByYear(2000));
    }

    @Test
    public void getMovieByRating(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();
        Movie movie1 = Movie.builder().id(2L).name("test1").genre("test1").year(2001).rating(5).director("test1").language("test1").country("test1").description("test1").date("test1").build();
        Movie movie2 = Movie.builder().id(3L).name("test2").genre("test2").year(2002).rating(5).director("test2").language("test2").country("test2").description("test2").date("test2").build();
        Movie movie3 = Movie.builder().id(4L).name("test3").genre("test3").year(2003).rating(5).director("test3").language("test3").country("test3").description("test3").date("test3").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        doReturn(movieList).when(movieRepo).getMovieByRating(movie.getRating());

        assertEquals(movieList,movieService.getMovieByRating(movie.getRating()));
    }

    @Test
    public void getMovieByRatingException(){
        List<Movie> movieList = new ArrayList<>();

        doReturn(movieList).when(movieRepo).getMovieByRating(5);

        assertThrows(ListEmptyException.class,()->movieService.getMovieByRating(5));
    }

    @Test
    public void getMovieByDirector(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("test").country("test").description("test").date("test").build();
        Movie movie1 = Movie.builder().id(2L).name("test1").genre("test1").year(2001).rating(4).director("test").language("test1").country("test1").description("test1").date("test1").build();
        Movie movie2 = Movie.builder().id(3L).name("test2").genre("test2").year(2002).rating(3).director("test").language("test2").country("test2").description("test2").date("test2").build();
        Movie movie3 = Movie.builder().id(4L).name("test3").genre("test3").year(2003).rating(2).director("test").language("test3").country("test3").description("test3").date("test3").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        doReturn(movieList).when(movieRepo).getMovieByDirector(movie.getDirector());

        assertEquals(movieList,movieService.getMovieByDirector(movie.getDirector()));
    }

    @Test
    public void getMovieByDirectorException(){
        List<Movie> movieList = new ArrayList<>();

        doReturn(movieList).when(movieRepo).getMovieByDirector("test");

        assertThrows(ListEmptyException.class,()->movieService.getMovieByDirector("test"));
    }

    @Test
    public void getMovieByLanguage(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("english").country("test").description("test").date("test").build();
        Movie movie1 = Movie.builder().id(2L).name("test1").genre("test1").year(2001).rating(4).director("test1").language("english").country("test1").description("test1").date("test1").build();
        Movie movie2 = Movie.builder().id(3L).name("test2").genre("test2").year(2002).rating(3).director("test2").language("english").country("test2").description("test2").date("test2").build();
        Movie movie3 = Movie.builder().id(4L).name("test3").genre("test3").year(2003).rating(2).director("test3").language("english").country("test3").description("test3").date("test3").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        doReturn(movieList).when(movieRepo).getMovieByLanguage(movie.getLanguage());

        assertEquals(movieList,movieService.getMovieByLanguage(movie.getLanguage()));
    }

    @Test
    public void getMovieByLanguageException(){
        List<Movie> movieList = new ArrayList<>();

        doReturn(movieList).when(movieRepo).getMovieByLanguage("english");

        assertThrows(ListEmptyException.class,()->movieService.getMovieByLanguage("english"));
    }

    @Test
    public void getMovieByCountry(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").language("english").country("test").description("test").date("test").build();
        Movie movie1 = Movie.builder().id(2L).name("test1").genre("test1").year(2001).rating(4).director("test1").language("english").country("test").description("test1").date("test1").build();
        Movie movie2 = Movie.builder().id(3L).name("test2").genre("test2").year(2002).rating(3).director("test2").language("english").country("test").description("test2").date("test2").build();
        Movie movie3 = Movie.builder().id(4L).name("test3").genre("test3").year(2003).rating(2).director("test3").language("english").country("test").description("test3").date("test3").build();

        movieRepo.save(movie);
        movieRepo.save(movie1);
        movieRepo.save(movie2);
        movieRepo.save(movie3);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        doReturn(movieList).when(movieRepo).getMovieByCountry(movie.getCountry());

        assertEquals(movieList,movieService.getMovieByCountry(movie.getCountry()));
    }

    @Test
    public void getMovieByCountryException(){
        List<Movie> movieList = new ArrayList<>();

        doReturn(movieList).when(movieRepo).getMovieByCountry("test");

        assertThrows(ListEmptyException.class,()->movieService.getMovieByCountry("test"));
    }

    @Test
    public void getMovieByNameAndGenre(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").description("test").date("test").language("english").country("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByNameAndGenre(movie.getName(),movie.getGenre());

        assertEquals(movie,movieService.getMovieByNameAndGenre(movie.getName(),movie.getGenre()));
    }

    @Test
    public void getMovieByNameAndGenreException(){
        doReturn(Optional.empty()).when(movieRepo).getMovieByNameAndGenre("test","test");

        assertThrows(MovieNotFoundException.class,()->movieService.getMovieByNameAndGenre("test","test"));
    }

    @Test
    public void getMovieByNameAndYear(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").description("test").date("test").language("english").country("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByNameAndYear(movie.getName(),movie.getYear());

        assertEquals(movie,movieService.getMovieByNameAndYear(movie.getName(),movie.getYear()));
    }

    @Test
    public void getMovieByNameAndYearException(){
        doReturn(Optional.empty()).when(movieRepo).getMovieByNameAndYear("test",2000);

        assertThrows(MovieNotFoundException.class,()->movieService.getMovieByNameAndYear("test",2000));
    }

    @Test
    public void getMovieByNameAndRating(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").description("test").date("test").language("english").country("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByNameAndRating(movie.getName(),movie.getRating());

        assertEquals(movie,movieService.getMovieByNameAndRating(movie.getName(),movie.getRating()));
    }

    @Test
    public void getMovieByNameAndRatingException(){
        doReturn(Optional.empty()).when(movieRepo).getMovieByNameAndRating("test",5);

        assertThrows(MovieNotFoundException.class,()->movieService.getMovieByNameAndRating("test",5));
    }

    @Test
    public void getMovieByNameAndLanguage(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").description("test").date("test").language("english").country("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByNameAndLanguage(movie.getName(),movie.getLanguage());

        assertEquals(movie,movieService.getMovieByNameAndLanguage(movie.getName(),movie.getLanguage()));
    }

    @Test
    public void getMovieByNameAndLanguageException(){
        doReturn(Optional.empty()).when(movieRepo).getMovieByNameAndLanguage("test","english");

        assertThrows(MovieNotFoundException.class,()->movieService.getMovieByNameAndLanguage("test","english"));
    }

    @Test
    public void getMovieByNameAndCountry(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).director("test").description("test").date("test").language("english").country("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByNameAndCountry(movie.getName(),movie.getCountry());

        assertEquals(movie,movieService.getMovieByNameAndCountry(movie.getName(),movie.getCountry()));

    }

    @Test
    public void getMovieByNameAndCountryException(){
        doReturn(Optional.empty()).when(movieRepo).getMovieByNameAndCountry("test","test");

        assertThrows(MovieNotFoundException.class,()->movieService.getMovieByNameAndCountry("test","test"));
    }

    @Test
    public void getMovieByGenreAndYear(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).description("test").date("test").director("test").language("english").country("test").build();
        Movie movie1 = Movie.builder().id(2L).name("test1").genre("test").year(2000).rating(4).description("test1").date("test1").director("test1").language("english").country("test").build();
        Movie movie2 = Movie.builder().id(3L).name("test2").genre("test").year(2000).rating(3).description("test2").date("test2").director("test2").language("english").country("test").build();
        Movie movie3 = Movie.builder().id(4L).name("test3").genre("test").year(2000).rating(2).description("test3").date("test3").director("test3").language("english").country("test").build();

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
    public void getMovieByGenreAndYearException(){
        List<Movie> movieList = new ArrayList<>();

        doReturn(movieList).when(movieRepo).getMovieByGenreAndYear("test",2000);

        assertThrows(ListEmptyException.class,()->movieService.getMovieByGenreAndYear("test",2000));
    }

    @Test
    public void getMovieByGenreAndRating(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).description("test").date("test").director("test").language("english").country("test").build();
        Movie movie1 = Movie.builder().id(2L).name("test1").genre("test").year(2001).rating(5).description("test1").date("test1").director("test1").language("english").country("test").build();
        Movie movie2 = Movie.builder().id(3L).name("test2").genre("test").year(2002).rating(5).description("test2").date("test2").director("test2").language("english").country("test").build();
        Movie movie3 = Movie.builder().id(4L).name("test3").genre("test").year(2003).rating(5).description("test3").date("test3").director("test3").language("english").country("test").build();

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
    public void getMovieByGenreAndRatingException(){
        List<Movie> movieList = new ArrayList<>();

        doReturn(movieList).when(movieRepo).getMovieByGenreAndRating("test",5);

        assertThrows(ListEmptyException.class,()->movieService.getMovieByGenreAndRating("test",5));
    }


    @Test
    public void getMovieById(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).description("test").date("test").director("test").language("english").country("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieById(movie.getId());



        assertEquals(movie,this.movieService.getMovieById(1));

    }

    @Test
    public void getMovieByIdException(){
        doReturn(Optional.empty()).when(movieRepo).getMovieById(1L);

        assertThrows(MovieNotFoundException.class,()->movieService.getMovieById(1));
    }

    @Test
    public void getMovieByNameAndDirector(){
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2000).rating(5).description("test").date("test").director("test").language("english").country("test").build();

        doReturn(Optional.of(movie)).when(movieRepo).getMovieByNameAndDirector(movie.getName(),movie.getDirector());

        assertEquals(movie,movieService.getMovieByNameAndDirector(movie.getName(),movie.getDirector()));
    }

    @Test
    public void getMovieByNameAndDirectorException(){
        doReturn(Optional.empty()).when(movieRepo).getMovieByNameAndDirector("test","test");

        assertThrows(MovieNotFoundException.class,()->movieService.getMovieByNameAndDirector("test","test"));
    }





}