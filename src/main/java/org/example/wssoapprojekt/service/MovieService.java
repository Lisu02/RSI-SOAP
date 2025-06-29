package org.example.wssoapprojekt.service;

import jakarta.activation.DataHandler;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.MTOM;
import org.example.wssoapprojekt.DAO.ActorDao;
import org.example.wssoapprojekt.DAO.ActorDaoImpl;
import org.example.wssoapprojekt.DAO.MovieDao;
import org.example.wssoapprojekt.DAO.MovieDaoImpl;
import org.example.wssoapprojekt.model.Actor;
import org.example.wssoapprojekt.model.Movie;
import org.example.wssoapprojekt.controller.MovieController;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebService(endpointInterface = "org.example.wssoapprojekt.controller.MovieController")
@BindingType(value = jakarta.xml.ws.soap.SOAPBinding.SOAP11HTTP_MTOM_BINDING)
@MTOM
public class MovieService implements MovieController {

    private final MovieDao movieDao = MovieDaoImpl.getMovieDaoInstance();
    private final ActorDao actorDao = ActorDaoImpl.getActorDaoInstance();

    @Override
    @WebMethod(operationName = "addMovie")
    @WebResult(name = "addMovieResponse")
    public Movie addMovie(Movie movie) {
        // Logika dodawania filmu
        return movieDao.save(movie);
    }

    @Override
    @WebMethod(operationName = "getMovie")
    @WebResult(name = "getMovieResponse")
    public Movie getMovie(Long movieId) {
        Optional<Movie> movie = movieDao.findById(movieId);
        return movie.get();
    }

    @Override
    @WebMethod(operationName = "getMovieList")
    @WebResult(name = "getMovieListResponse")
    public List<Movie> getMovieList() {
        return movieDao.findAll();
    }

    @Override
    @WebMethod(operationName = "addActorToMovie")
    @WebResult(name = "addActorToMovieResponse")
    public Movie addActorToMovie(Long actorId,Long movieId) {
        if(movieDao.findById(movieId).isPresent()){
            Movie movie = movieDao.findById(movieId).get();
            if(actorDao.findById(actorId).isPresent()){
                movie.getActorList().add(actorId);
                movieDao.update(movie);
                return movie;
            }
        }
        return null;
    }

    @Override
    @WebMethod(operationName = "getImage")
    @XmlSchemaType(name = "base64Binary")
    @XmlMimeType("image/png")
    public DataHandler getImage(@WebParam(name = "filepath") String filepath) {
        if(filepath == null){
            Logger log = Logger.getLogger(this.getClass().getName());
            log.warning("getImage - filepath jest NULL");
            return Movie.loadImageOrDefault("");
        }
        return Movie.loadImageOrDefault(filepath);
    }

    @Override
    @WebMethod(operationName = "getMovieImage")
    @XmlSchemaType(name = "base64Binary")
    @XmlMimeType("image/png")
    public DataHandler getMovieImage(@WebParam(name = "movieId") Long movieId) {
        Optional<Movie> movieOptional = movieDao.findById(movieId);
        if(movieOptional.isPresent()){
            String imagePath = movieOptional.get().getImagePath();
            return Movie.loadImageOrDefault(imagePath);
        }else {
            return Movie.loadImageOrDefault("");
        }
        //throw new RuntimeException("No movie at provided id is present");
    }


}
