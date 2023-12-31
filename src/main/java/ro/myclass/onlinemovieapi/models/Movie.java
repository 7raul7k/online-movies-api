package ro.myclass.onlinemovieapi.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "movie_db")
@Entity(name = "Movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Movie {
    @Id
    @SequenceGenerator(name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    @Column(
            name = "id"
    )
    private long id;
    @Column(name = "movie_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;
    @Column(name = "movie_genre",
            nullable = false,
            columnDefinition = "TEXT")
    private String genre;
    @Column(name = "movie_year",
            nullable = false,
            columnDefinition = "INT")
    private int year;
    @Column(name = "movie_rating",
            nullable = false,
            columnDefinition = "INT")
    private int rating;

    @Column(name = "movie_director",
            nullable = false,
            columnDefinition = "TEXT")
    private String director;


    @Override
    public String toString(){
        return id+","+name+","+genre+","+year+","+rating+","+director;
    }

    @Override
    public boolean equals(Object obj){
        Movie m = (Movie) obj;

        if(this.name.equals(m.name)&&this.genre.equals(m.genre)&&this.year==m.year&&this.rating==m.rating&&this.director.equals(m.director)) {
            return true;
        }

        return false;
        }


}
