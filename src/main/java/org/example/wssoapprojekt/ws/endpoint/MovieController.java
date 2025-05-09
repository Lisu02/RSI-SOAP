package org.example.wssoapprojekt.ws.endpoint;


import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.example.wssoapprojekt.model.Movie;

import java.util.List;

@WebService(name = "MovieService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface MovieController {

    @WebMethod
    public void addMovie(Movie movie); // Poprawiono

    @WebMethod
    public Movie getMovie(String id);

    @WebMethod
    public List<Movie> getMovieList(); // Dodano spowrotem
}
