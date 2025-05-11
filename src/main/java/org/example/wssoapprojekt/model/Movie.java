package org.example.wssoapprojekt.model;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
@XmlRootElement(name = "Movie")
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    private Long id;
    @XmlElement(required = true)
    private String title;
    private String director;
    //@XmlSchemaType(name = "dateTime")
    @XmlElement(required = true)
    private String releaseDate; //Standard od Java 8 (sama data bez godziny)
    @XmlElement(required = true)
    private String description;
    @XmlElement(required = true)
    private MovieType movieType;
    @XmlElementWrapper(name = "actorIdList")
    @XmlElement(name = "actorId")
    private List<Long> actorIdList;
    @XmlMimeType("application/octet-stream")
    private DataHandler image;

    /* Do zdjec NIEAKTUALNE NIEAKTUALNE
    * jest image a jako 'implementacja' dajemy File i potem ImageIO.read('wczytane zdjecie z pliku')
    * */

    public DataHandler loadImageOrDefault(String pathToImage) {
        File file = new File(pathToImage);
        if (!file.exists()) {
            file = new File("/path/to/default/images/no-image.png");
        }
        return new DataHandler(new FileDataSource(file));
    }


    public Movie(Long id, String title, String director, String releaseDate, String description, MovieType movieType, DataHandler image) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.description = description;
        this.movieType = movieType;
        this.image = image;
    }

    public Movie(String title, String director, String releaseDate, String description, MovieType movieType, DataHandler image) {
        this(0L,title, director, releaseDate,description,movieType,image);
    }

    public Movie(){
        this(0L,"tytul","Andrzej Tralala","26-08-2002","fajny film",MovieType.ACTION,null);
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    public List<Long> getActorList() {
        return actorIdList;
    }

    public void setActorIdList(List<Long> actorIdList) {
        this.actorIdList = actorIdList;
    }

    public DataHandler getImage() {
        return image;
    }

    public void setImage(DataHandler image) {
        this.image = image;
    }
}
