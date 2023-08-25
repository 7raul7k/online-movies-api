package ro.myclass.onlinemovieapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException() {
        super("Movie not found");
    }
}
