package org.example.model;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "movie")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Movie", propOrder = {
    "title",
    "director",
    "actors",
    "description",
    "poster",
    "screeningDate",
    "screeningTime"
})
public class Movie {

    public Movie() {
    }

    @XmlElement(required = true)
    private String title;
    
    @XmlElement(required = true)
    private String director;
    
    @XmlElementWrapper(name = "actors", required = true)
    @XmlElement(name = "actor", required = true)
    private List<String> actors = new ArrayList<>();
    
    @XmlElement(required = true)
    private String description;
    
    @XmlElement(required = true)
    private byte[] poster;
    
    @XmlElement(required = true)
    private String screeningDate;
    
    @XmlElement(required = true)
    private String screeningTime;

    // Getters and Setters
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

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors != null ? actors : new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }

    public String getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(String screeningDate) {
        this.screeningDate = screeningDate;
    }

    public String getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(String screeningTime) {
        this.screeningTime = screeningTime;
    }
} 