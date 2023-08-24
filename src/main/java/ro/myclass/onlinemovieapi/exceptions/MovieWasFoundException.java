package ro.myclass.onlinemovieapi.exceptions;

public class MovieWasFoundException extends RuntimeException{

    public MovieWasFoundException() {
        super("Movie was found");
    }
}
