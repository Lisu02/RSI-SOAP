package org.example.dao;

import org.example.model.Reservation;
import org.example.wssoapprojekt.util.GlobalUtilities;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ReservationDAO {
    private static final Map<Long, Reservation> reservations = new ConcurrentHashMap<>();
    private static final MovieDAO movieDAO = new MovieDAO();

    public Long createReservation(Reservation reservation) {
        Long reservationId = GlobalUtilities.random.nextLong();
        reservation.setReservationId(reservationId);
        
        // Try to reserve the seats
        if (movieDAO.reserveSeats(
                reservation.getMovieTitle(),
                reservation.getScreeningDate(),
                reservation.getScreeningTime(),
                reservation.getSeats())) {
            reservations.put(reservationId, reservation);
            return reservationId;
        }
        return null;
    }

    public Reservation getReservation(Long reservationId) {
        return reservations.get(reservationId);
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }

    public boolean cancelReservation(String reservationId) {
        Reservation reservation = reservations.remove(reservationId);
        if (reservation != null) {
            // Release the seats back to available seats
            movieDAO.releaseSeats(
                reservation.getMovieTitle(),
                reservation.getScreeningDate(),
                reservation.getScreeningTime(),
                reservation.getSeats()
            );
            return true;
        }
        return false;
    }

    public boolean modifyReservation(String reservationId, List<String> newSeats) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            return false;
        }

        // Release old seats
        movieDAO.releaseSeats(
            reservation.getMovieTitle(),
            reservation.getScreeningDate(),
            reservation.getScreeningTime(),
            reservation.getSeats()
        );

        // Try to reserve new seats
        if (movieDAO.reserveSeats(
                reservation.getMovieTitle(),
                reservation.getScreeningDate(),
                reservation.getScreeningTime(),
                newSeats)) {
            reservation.setSeats(newSeats);
            return true;
        }

        // If new seats couldn't be reserved, try to reserve the old seats again
        movieDAO.reserveSeats(
            reservation.getMovieTitle(),
            reservation.getScreeningDate(),
            reservation.getScreeningTime(),
            reservation.getSeats()
        );
        return false;
    }
} 