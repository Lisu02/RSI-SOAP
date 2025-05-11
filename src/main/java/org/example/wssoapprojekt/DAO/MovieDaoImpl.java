package org.example.wssoapprojekt.DAO;

import org.example.wssoapprojekt.model.Movie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {

    private MovieDaoImpl(){}

    public static MovieDaoImpl getMovieDaoInstance(){
        if(movieDao == null){
            movieDao = new MovieDaoImpl();
        }
        return movieDao;
    }

    private static MovieDaoImpl movieDao;
    private HashMap<Long, Movie> database = new HashMap<>();
    private Long counter = 0L;

    @Override
    public Movie save(Movie movie) {
        if(movie.getActorList() == null){
            if(movie.getActorList().isEmpty()){
                throw new RuntimeException(
                        this.getClass().getName() +": Actor list for a movie is null or empty"
                );
            }
        }
        counter++;
        movie.setId(counter);
        database.put(counter,movie);
        return movie;
    }

    @Override
    public Movie update(Movie newMovie) {
        Long movieId = newMovie.getId();
        if(movieId == null){
            throw new RuntimeException("Updated movie id is null");
        }
        Movie databaseMovie = database.get(movieId);
        if(databaseMovie == null){
            throw new RuntimeException("Movie for update does not exist");
        }
        database.replace(movieId, newMovie);
        return newMovie;
    }

    @Override
    public void delete(Movie movie) {
        database.remove(movie.getId());
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Movie> findAll() {
        return new LinkedList<>(database.values());
    }


}
