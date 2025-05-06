package org.example.wssoapprojekt.DAO;

import org.example.wssoapprojekt.model.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO{

    HashMap<String,Movie> movieHashMap = new HashMap<>();

    @Override
    public Optional<Movie> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Movie> findAll() {
        return List.of();
    }

    public void save(Movie movie) {

    }

    public void update(Movie movie) {

    }

    @Override
    public void delete(String id) {

    }
}
