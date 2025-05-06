package org.example.wssoapprojekt.ws.endpoint;


import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.List;

@WebService(name = "MovieService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface Movie {

    public void addMovie();

    public Movie getMovie(String id);

    public List<Movie> getMovieList();
}
