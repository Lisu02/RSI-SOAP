package org.example.wssoapprojekt.service;

import jakarta.jws.WebService;
import org.example.wssoapprojekt.ws.endpoint.MovieController;

import java.util.List;

@WebService(endpointInterface = "org.example.wssoapprojekt.ws.endpoint")
public class MovieService implements MovieController {

    @Override
    public void addMovie() {

    }

    @Override
    public MovieController getMovie(String id) {
        return null;
    }

    @Override
    public List<MovieController> getMovieList() {
        return List.of();
    }


}
