package ro.myclass.onlinemovieapi.exceptions;

public class ListEmptyException extends RuntimeException{

    public ListEmptyException() {
        super("List is empty");
    }
}
