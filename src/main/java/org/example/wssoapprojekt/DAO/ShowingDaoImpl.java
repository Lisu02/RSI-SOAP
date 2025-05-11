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

            MovieDaoImpl movieDao = MovieDaoImpl.getMovieDaoInstance();
            List<Movie> movies = movieDao.findAll();

            showingDaoImpl.save(new Showing(movies.get(0), "2025-05-12 poniedziałek 13:20"));
            showingDaoImpl.save(new Showing(movies.get(0), "2025-05-13 wtorek 16:00"));

            showingDaoImpl.save(new Showing(movies.get(1), "2025-05-14 środa 12:15"));
            showingDaoImpl.save(new Showing(movies.get(1), "2025-05-15 czwartek 18:45"));

            showingDaoImpl.save(new Showing(movies.get(2), "2025-05-16 piątek 20:00"));
            showingDaoImpl.save(new Showing(movies.get(2), "2025-05-17 sobota 14:30"));

            showingDaoImpl.save(new Showing(movies.get(3), "2025-05-18 niedziela 17:00"));
            showingDaoImpl.save(new Showing(movies.get(3), "2025-05-12 poniedziałek 11:00"));

            showingDaoImpl.save(new Showing(movies.get(4), "2025-05-13 wtorek 19:10"));
            showingDaoImpl.save(new Showing(movies.get(4), "2025-05-14 środa 15:40"));

            showingDaoImpl.save(new Showing(movies.get(5), "2025-05-15 czwartek 16:30"));
            showingDaoImpl.save(new Showing(movies.get(5), "2025-05-16 piątek 21:00"));
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
