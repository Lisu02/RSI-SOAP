package org.example.wssoapprojekt.ws.endpoint;


import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.List;

@WebService(name = "MovieService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface MovieController {

    public void addMovie();

    public MovieController getMovie(String id);

    public List<MovieController> getMovieList();
}
