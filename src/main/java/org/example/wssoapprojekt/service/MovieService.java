package org.example.wssoapprojekt.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.example.wssoapprojekt.model.Movie;
import org.example.wssoapprojekt.ws.endpoint.MovieController;

import java.util.List;

@WebService(endpointInterface = "org.example.wssoapprojekt.ws.endpoint.MovieController")
public class MovieService implements MovieController {

    @Override
    @WebMethod(operationName = "addMovie")
    @WebResult(name = "addMovieResponse")
    public void addMovie(Movie movie) {
        // Logika dodawania filmu
    }

    @Override
    @WebMethod(operationName = "getMovie")
    @WebResult(name = "getMovieResponse")
    public Movie getMovie(String id) {
        return new Movie();
    }

    @Override
    @WebMethod(operationName = "getMovieList")
    @WebResult(name = "getMovieListResponse")
    public List<Movie> getMovieList() {
        return List.of();
    }
}
