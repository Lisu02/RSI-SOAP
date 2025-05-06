package org.example.wssoapprojekt.service;

import jakarta.jws.WebService;
import org.example.wssoapprojekt.ws.endpoint.Movie;

import java.util.List;

@WebService(endpointInterface = "org.example.wssoapprojekt.ws.endpoint")
public class MovieService implements Movie {

    @Override
    public void addMovie() {

    }

    @Override
    public Movie getMovie(String id) {
        return null;
    }

    @Override
    public List<Movie> getMovieList() {
        return List.of();
    }


}
