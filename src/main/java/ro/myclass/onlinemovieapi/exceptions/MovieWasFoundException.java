package ro.myclass.onlinemovieapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MovieWasFoundException extends RuntimeException{

    public MovieWasFoundException() {
        super("Movie was found");
    }
}
