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
    @Column(name = "movie_description",
            nullable = false,
            columnDefinition = "TEXT")
    private String description;
    @Column(name = "movie_director",
            nullable = false,
            columnDefinition = "TEXT")
    private String director;
    @Column(name = "movie_duration",
            nullable = false,
            columnDefinition = "INT")
    private int duration;
    @Column(name = "movie_language",
            nullable = false,
            columnDefinition = "TEXT")
    private String language;
    @Column(name = "movie_country",
            nullable = false,
            columnDefinition = "TEXT")
    private String country;
    @Column(name = "movie_age",
            nullable = false,
            columnDefinition = "INT")
    private int age;
    @Column(name = "movie_views",
            nullable = false,
            columnDefinition = "INT")
    private int views;
    @Column(name = "movie_date",
            nullable = false,
            columnDefinition = "TEXT")
    private String date;
    @Column(name = "movie_time",
            nullable = false,
            columnDefinition = "TEXT")
    private String time;

    @Override
    public String toString(){
        return id+","+name+","+genre+","+year+","+rating+","+description+","+director+","+duration+","+language+","+country+","+age+","+views+","+date+","+time;
    }

    @Override
    public boolean equals(Object obj){
        Movie m = (Movie) obj;

        if(this.name.equals(m.name)&&this.genre.equals(m.genre)&&this.year==m.year&&this.rating==m.rating&&this.description.equals(m.description)&&this.director.equals(m.director)&&this.duration==m.duration&&this.language.equals(m.language)&&this.country.equals(m.country)&&this.age==m.age&&this.views==m.views&&this.date.equals(m.date)&&this.time.equals(m.time)) {
            return true;
        }

        return false;
        }


}
