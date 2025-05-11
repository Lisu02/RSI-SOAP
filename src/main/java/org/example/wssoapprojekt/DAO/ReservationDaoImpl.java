package org.example.wssoapprojekt.DAO;

import org.example.wssoapprojekt.model.Movie;
import org.example.wssoapprojekt.model.Reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationDaoImpl implements ReservationDao{

    private ReservationDaoImpl(){}

    public static ReservationDaoImpl getReservationDaoInstance(){
        if(reservationDao == null){
            reservationDao = new ReservationDaoImpl();
        }
        return reservationDao;
    }

    private static ReservationDaoImpl reservationDao;
    private HashMap<Long, Reservation> database = new HashMap<>();
    private Long counter = 0L;


    @Override
    public Reservation save(Reservation reservation) {
        if(reservation.getShowing() == null || reservation.getShowing().getMovie() == null){
            return null;
        }
        counter++;
        reservation.setReservationId(counter);
        return database.put(counter,reservation);
    }

    @Override
    public Reservation update(Reservation newReservation) {
        Long reservationId = newReservation.getReservationId();
        if(reservationId == null){
            throw new RuntimeException("Updated movie id is null");
        }
        Reservation databaseReservation = database.get(reservationId);
        if(databaseReservation == null){
            throw new RuntimeException("Movie for update does not exist");
        }
        database.replace(reservationId, newReservation);
        return newReservation;
    }

    @Override
    public void delete(Reservation reservation) {
        database.remove(reservation.getReservationId(),reservation);
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Reservation> findAll() {
        return database.values().stream().toList();
    }

    @Override
    public List<Reservation> findByMovieId(Long movieId) {
        return database.values().stream().toList().stream()
                .filter(reservation -> Objects.equals(reservation.getShowing().getMovie().getId(), movieId))
                .toList();
    }
}
