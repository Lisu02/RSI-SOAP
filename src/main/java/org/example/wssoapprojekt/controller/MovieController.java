package org.example.wssoapprojekt.controller;


import jakarta.activation.DataHandler;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import org.example.wssoapprojekt.model.Actor;
import org.example.wssoapprojekt.model.Movie;

import java.util.List;

@WebService(name = "MovieService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface MovieController {

    @WebMethod
    Movie addMovie(Movie movie); // Poprawiono

    @WebMethod
    Movie getMovie(@WebParam(name = "movieId")Long movieId);

    @WebMethod
    List<Movie> getMovieList(); // Dodano spowrotem

    @WebMethod
    Movie addActorToMovie(
            @WebParam(name = "actorId")Long actorId,
            @WebParam(name = "movieId")Long movieId
    );

    @WebMethod
    @XmlSchemaType(name = "base64Binary")
    @XmlMimeType("image/jpg")
    DataHandler getImage(@WebParam(name="filepath") String filepath);

    @WebMethod
    @XmlSchemaType(name = "base64Binary")
    @XmlMimeType("image/jpg")
    DataHandler getMovieImage(@WebParam(name = "movieId") Long movieId);
}
