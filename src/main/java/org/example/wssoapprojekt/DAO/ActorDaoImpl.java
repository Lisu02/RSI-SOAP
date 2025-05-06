package org.example.wssoapprojekt.DAO;

import org.example.wssoapprojekt.model.Actor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ActorDaoImpl implements ActorDao{

    private HashMap<Long,Actor> database = new HashMap<>();
    private Long counter = 0L;

    @Override
    public Actor save(Actor actor) {
        counter++;
        actor.setId(counter);
        return database.put(counter,actor);
    }

    @Override
    public Actor update(Actor newActor) {
        Long actorId = newActor.getId();
        if(actorId == null){throw new RuntimeException("Updated actor's id is null");}
        Actor databaseActor = database.get(actorId);
        if(databaseActor == null){throw new RuntimeException("Actor for update doesn't exist");}
        return database.replace(actorId,newActor);
    }

    @Override
    public void delete(Actor actor) {
        if(actor != null && actor.getId() != null){
            database.remove(actor.getId());
        }
    }

    @Override
    public void delete(Long id) {
        if(id != null){
            database.remove(id);
        }
    }

    @Override
    public Optional<Actor> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Actor> findAll() {
        return new LinkedList<>(database.values());
    }
}
