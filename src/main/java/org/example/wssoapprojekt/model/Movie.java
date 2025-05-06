package org.example.wssoapprojekt.model;

import jakarta.activation.DataHandler;

import java.time.LocalDate;
import java.util.List;

public class Movie {

    private Long id;
    private String title;
    private String director;
    private LocalDate releseDate; //Standard od Java 8 (sama data bez godziny)
    private String description;
    private MovieType movieType;
    private List<Actor> actorList;
    private DataHandler image;

    /* Do zdjec NIEAKTUALNE NIEAKTUALNE
    * jest image a jako 'implementacja' dajemy File i potem ImageIO.read('wczytane zdjecie z pliku')
    * */

    public Movie(Long id, String title, String director, LocalDate releseDate, String description, MovieType movieType, DataHandler image) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releseDate = releseDate;
        this.description = description;
        this.movieType = movieType;
        this.image = image;
    }

    public Movie(String title, String director, LocalDate releseDate, String description, MovieType movieType, DataHandler image) {
        this(0L,title, director,releseDate,description,movieType,image);
    }

    public Movie(){
        this(0L,"tytul","Andrzej Tralala",LocalDate.of(2002,8,26),"fajny film",MovieType.ACTION,null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleseDate() {
        return releseDate;
    }

    public void setReleseDate(LocalDate releseDate) {
        this.releseDate = releseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public DataHandler getImage() {
        return image;
    }

    public void setImage(DataHandler image) {
        this.image = image;
    }
}
