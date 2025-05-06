package org.example.wssoapprojekt.model;

import java.time.LocalDateTime;

public class Showing {

    private CinemaRoom room;
    private Integer[][] stateOfSeats;
    private Movie movie;
    private LocalDateTime showingTime;

    public Showing(CinemaRoom room, Movie movie, LocalDateTime showingTime){
        this.room = room;
        this.stateOfSeats = room.getSeating().getSitsCopy();
        this.movie = movie;
        this.showingTime = showingTime;
    }




}
