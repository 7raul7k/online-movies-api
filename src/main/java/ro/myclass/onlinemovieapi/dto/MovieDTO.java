package ro.myclass.onlinemovieapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MovieDTO {

    private long id;
    private String name;
    private String genre;
    private int year;
    private int rating;
    private String director;

}
