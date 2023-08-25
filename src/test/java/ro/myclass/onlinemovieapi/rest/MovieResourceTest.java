package ro.myclass.onlinemovieapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.myclass.onlinemovieapi.dto.MovieDTO;
import ro.myclass.onlinemovieapi.exceptions.ListEmptyException;
import ro.myclass.onlinemovieapi.exceptions.MovieWasFoundException;
import ro.myclass.onlinemovieapi.models.Movie;
import ro.myclass.onlinemovieapi.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class MovieResourceTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieResource movieResource;

    private ObjectMapper mapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(movieResource).build();
    }

    @Test
    public void getAllMoviesOk() throws Exception{
        Faker faker = new Faker();

        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Movie movie = Movie.builder().id((long) i).name(faker.name().name()).genre(faker.name().name()).build();
            movieList.add(movie);

        }

        doReturn(movieList).when(movieService).getAllMovies();

       restMockMvc.perform(get("/api/v1/movie/allMovies"))
                .andExpect(content().string(mapper.writeValueAsString(movieList))).andExpect(status().isOk());

    }

    @Test
    public void getAllMoviesBadRequest() throws Exception{
      doThrow(ListEmptyException.class).when(movieService).getAllMovies();

        restMockMvc.perform(get("/api/v1/movie/allMovies"))
                    .andExpect(status().isBadRequest());
    }

    @Test
    public void addMovieOk() throws Exception{
        MovieDTO movieDTO = MovieDTO.builder().name("test").genre("test").year(2020).rating(10).description("test").director("test").duration(120).language("test").country("test").date("test").build();

        doNothing().when(movieService).addMovie(movieDTO);

        restMockMvc.perform(post("/api/v1/movie/addMovie").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(movieDTO)))
                .andExpect(status().isOk());


    }

    @Test
    public void addMovieBadRequest() throws Exception {
        MovieDTO movieDTO = MovieDTO.builder().name("test").genre("test").year(2020).rating(10).description("test").director("test").duration(120).language("test").country("test").date("test").build();

        doThrow(MovieWasFoundException.class).when(movieService).addMovie(movieDTO);

        restMockMvc.perform(post("/api/v1/movie/addMovie").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(movieDTO)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void removeMovieOk() throws Exception{


    doNothing().when(movieService).deleteMovie("test");

    restMockMvc.perform(delete("/api/v1/movie/deleteMovie/test"))
                .andExpect(status().isOk());
    }

    @Test
    public void removeMovieBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).deleteMovie("test");

        restMockMvc.perform(delete("/api/v1/movie/deleteMovie/test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateMovieOk() throws Exception{
        MovieDTO movieDTO = MovieDTO.builder().name("test").genre("test").year(2020).rating(10).description("test").director("test").duration(120).language("test").country("test").date("test").build();

        doNothing().when(movieService).updateMovie(movieDTO);

        restMockMvc.perform(put("/api/v1/movie/updateMovie").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(movieDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMovieBadRequest() throws Exception{
        MovieDTO movieDTO = MovieDTO.builder().name("test").genre("test").year(2020).rating(10).description("test").director("test").duration(120).language("test").country("test").date("test").build();

        doThrow(MovieWasFoundException.class).when(movieService).updateMovie(movieDTO);

        restMockMvc.perform(put("/api/v1/movie/updateMovie").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(movieDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByIdOk() throws Exception{
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2020).rating(10).description("test").director("test").duration(120).language("test").country("test").date("test").build();

        doReturn(movie).when(movieService).getMovieById(1);

        restMockMvc.perform(get("/api/v1/movie/getMovieById/1"))
                .andExpect(content().string(mapper.writeValueAsString(movie))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByIdBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieById(1);

        restMockMvc.perform(get("/api/v1/movie/getMovieById/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByNameOk() throws Exception{
        Movie movie = Movie.builder().id(1L).name("test").genre("test").year(2020).rating(10).description("test").director("test").duration(120).language("test").country("test").date("test").build();

        doReturn(movie).when(movieService).getMovieByName("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByName/test"))
                .andExpect(content().string(mapper.writeValueAsString(movie))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByNameBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByName("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByName/test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByGenreOk() throws Exception{
        Faker faker = new Faker();

        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Movie movie = Movie.builder().id((long) i).name(faker.name().name()).genre(faker.name().name()).build();
            movieList.add(movie);

        }

        doReturn(movieList).when(movieService).getMovieByGenre("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByGenre/test"))
                .andExpect(content().string(mapper.writeValueAsString(movieList))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByGenreBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByGenre("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByGenre/test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByYearOk() throws Exception{
        Faker faker = new Faker();

        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Movie movie = Movie.builder().id((long) i).name(faker.name().name()).genre(faker.name().name()).year(2020).build();
            movieList.add(movie);

        }

        doReturn(movieList).when(movieService).getMovieByYear(2020);

        restMockMvc.perform(get("/api/v1/movie/getMovieByYear/2020"))
                .andExpect(content().string(mapper.writeValueAsString(movieList))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByYearBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByYear(2020);

        restMockMvc.perform(get("/api/v1/movie/getMovieByYear/2020"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByRatingOk() throws Exception{
        Faker faker = new Faker();

        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Movie movie = Movie.builder().id((long) i).name(faker.name().name()).genre(faker.name().name()).rating(10).build();
            movieList.add(movie);

        }

        doReturn(movieList).when(movieService).getMovieByRating(10);

        restMockMvc.perform(get("/api/v1/movie/getMovieByRating/10"))
                .andExpect(content().string(mapper.writeValueAsString(movieList))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByRatingBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByRating(10);

        restMockMvc.perform(get("/api/v1/movie/getMovieByRating/10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByDirectorOk() throws Exception{
        Faker faker = new Faker();

        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Movie movie = Movie.builder().id((long) i).name(faker.name().name()).genre(faker.name().name()).director("test").build();
            movieList.add(movie);

        }

        doReturn(movieList).when(movieService).getMovieByDirector("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByDirector/test"))
                .andExpect(content().string(mapper.writeValueAsString(movieList))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByDirectorBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByDirector("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByDirector/test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByLanguageOk() throws Exception{
        Faker faker = new Faker();

        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Movie movie = Movie.builder().id((long) i).name(faker.name().name()).genre(faker.name().name()).language("test").build();
            movieList.add(movie);

        }

        doReturn(movieList).when(movieService).getMovieByLanguage("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByLanguage/test"))
                .andExpect(content().string(mapper.writeValueAsString(movieList))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByLanguageBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByLanguage("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByLanguage/test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByCountryOk() throws Exception{
        Faker faker = new Faker();

        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Movie movie = Movie.builder().id((long) i).name(faker.name().name()).genre(faker.name().name()).country("test").build();
            movieList.add(movie);

        }

        doReturn(movieList).when(movieService).getMovieByCountry("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByCountry/test"))
                .andExpect(content().string(mapper.writeValueAsString(movieList))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByCountryBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByCountry("test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByCountry/test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByNameAndGenreOk() throws Exception{
        Movie movie = Movie.builder().id(1L).name("test").genre("test").build();

        doReturn(movie).when(movieService).getMovieByNameAndGenre("test","test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndGenre").param("name","test").param("genre","test"))
                .andExpect(content().string(mapper.writeValueAsString(movie))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByNameAndGenreBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByNameAndGenre("test","test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndGenre").param("name","test").param("genre","test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByNameAndYearOk() throws Exception{
        Movie movie = Movie.builder().id(1L).name("test").year(2020).build();

        doReturn(movie).when(movieService).getMovieByNameAndYear("test",2020);

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndYear").param("name","test").param("year","2020"))
                .andExpect(content().string(mapper.writeValueAsString(movie))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByNameAndYearBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByNameAndYear("test",2020);

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndYear").param("name","test").param("year","2020"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByNameAndRatingOk() throws Exception{
        Movie movie = Movie.builder().id(1L).name("test").rating(10).build();

        doReturn(movie).when(movieService).getMovieByNameAndRating("test",10);

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndRating").param("name","test").param("rating","10"))
                .andExpect(content().string(mapper.writeValueAsString(movie))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByNameAndRatingBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByNameAndRating("test",10);

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndRating").param("name","test").param("rating","10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByNameAndDirectorOk() throws Exception{
        Movie movie = Movie.builder().id(1L).name("test").director("test").build();

        doReturn(movie).when(movieService).getMovieByNameAndDirector("test","test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndDirector").param("name","test").param("director","test"))
                .andExpect(content().string(mapper.writeValueAsString(movie))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByNameAndDirectorBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByNameAndDirector("test","test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndDirector").param("name","test").param("director","test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByNameAndLanguageOk() throws Exception{
        Movie movie = Movie.builder().id(1L).name("test").language("test").build();

        doReturn(movie).when(movieService).getMovieByNameAndLanguage("test","test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndLanguage").param("name","test").param("language","test"))
                .andExpect(content().string(mapper.writeValueAsString(movie))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByNameAndLanguageBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByNameAndLanguage("test","test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndLanguage").param("name","test").param("language","test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByNameAndCountryOk() throws Exception{
        Movie movie = Movie.builder().id(1L).name("test").country("test").build();

        doReturn(movie).when(movieService).getMovieByNameAndCountry("test","test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndCountry").param("name","test").param("country","test"))
                .andExpect(content().string(mapper.writeValueAsString(movie))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByNameAndCountryBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByNameAndCountry("test","test");

        restMockMvc.perform(get("/api/v1/movie/getMovieByNameAndCountry").param("name","test").param("country","test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByGenreAndYearOk() throws Exception{
        Faker faker = new Faker();

        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Movie movie = Movie.builder().id((long) i).genre("test").year(2020).build();
            movieList.add(movie);

        }

        doReturn(movieList).when(movieService).getMovieByGenreAndYear("test",2020);

        restMockMvc.perform(get("/api/v1/movie/getMovieByGenreAndYear").param("genre","test").param("year","2020"))
                .andExpect(content().string(mapper.writeValueAsString(movieList))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByGenreAndYearBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByGenreAndYear("test",2020);

        restMockMvc.perform(get("/api/v1/movie/getMovieByGenreAndYear").param("genre","test").param("year","2020"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getMovieByGenreAndRatingOk() throws Exception{
        Faker faker = new Faker();

        List<Movie> movieList = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            Movie movie = Movie.builder().id((long) i).genre("test").rating(10).build();
            movieList.add(movie);

        }

        doReturn(movieList).when(movieService).getMovieByGenreAndRating("test",10);

        restMockMvc.perform(get("/api/v1/movie/getMovieByGenreAndRating").param("genre","test").param("rating","10"))
                .andExpect(content().string(mapper.writeValueAsString(movieList))).andExpect(status().isOk());
    }

    @Test
    public void getMovieByGenreAndRatingBadRequest() throws Exception{
        doThrow(MovieWasFoundException.class).when(movieService).getMovieByGenreAndRating("test",10);

        restMockMvc.perform(get("/api/v1/movie/getMovieByGenreAndRating").param("genre","test").param("rating","10"))
                .andExpect(status().isBadRequest());
    }
}