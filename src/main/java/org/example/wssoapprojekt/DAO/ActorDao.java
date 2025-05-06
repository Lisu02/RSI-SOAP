package org.example.wssoapprojekt.DAO;

import org.example.wssoapprojekt.model.Actor;
import org.example.wssoapprojekt.model.Movie;

import java.util.List;
import java.util.Optional;

public interface ActorDao {

    public Actor save(Actor actor);
    public Actor update(Actor actor);
    public void delete(Actor actor);
    public void delete(Long id);
    public Optional<Actor> findById(Long id);
    public List<Actor> findAll();
}
