package org.example.wssoapprojekt.DAO;

import org.example.wssoapprojekt.model.Movie;
import org.example.wssoapprojekt.model.Showing;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ShowingDaoImpl implements ShowingDao{

    private ShowingDaoImpl(){}

    public static ShowingDao getShowingDaoInstance() {
        if(showingDaoImpl == null){
            showingDaoImpl = new ShowingDaoImpl();
        }
        return showingDaoImpl;
    }

    private static ShowingDaoImpl showingDaoImpl;
    private HashMap<Long, Showing> database = new HashMap<>();
    private Long counter = 0L;



    @Override
    public Optional<Showing> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Showing> findAll() {
        return database.values().stream().toList();
    }

    @Override
    public Showing save(Showing showing) {
        counter++;
        showing.setShowingId(counter);
        database.put(counter,showing);
        return showing;
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }
}
