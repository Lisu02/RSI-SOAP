package org.example.dao;

import org.example.model.Movie;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MovieDAO {
    private static final Map<String, Movie> movies = new ConcurrentHashMap<>();
    private static final Map<String, Set<String>> availableSeats = new ConcurrentHashMap<>();

    public void addMovie(Movie movie) {
        String key = generateKey(movie);
        movies.put(key, movie);
        initializeSeats(key);
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies.values());
    }

    public Movie getMovie(String title, String date, String time) {
        return movies.get(generateKey(title, date, time));
    }

    public Set<String> getAvailableSeats(String title, String date, String time) {
        String key = generateKey(title, date, time);
        Set<String> seats = availableSeats.get(key);
        return seats != null ? new HashSet<>(seats) : new HashSet<>();
    }

    public boolean reserveSeats(String title, String date, String time, List<String> seats) {
        String key = generateKey(title, date, time);
        Set<String> available = availableSeats.get(key);
        
        if (available == null) {
            return false;
        }

        // Check if all requested seats are available
        if (!available.containsAll(seats)) {
            return false;
        }

        // Reserve the seats
        available.removeAll(seats);
        return true;
    }

    public void releaseSeats(String title, String date, String time, List<String> seats) {
        String key = generateKey(title, date, time);
        Set<String> available = availableSeats.get(key);
        if (available != null) {
            available.addAll(seats);
        }
    }

    private void initializeSeats(String key) {
        Set<String> seats = new HashSet<>();
        // Initialize with 50 seats (A1-A10, B1-B10, C1-C10, D1-D10, E1-E10)
        for (char row = 'A'; row <= 'E'; row++) {
            for (int num = 1; num <= 10; num++) {
                seats.add(row + String.valueOf(num));
            }
        }
        availableSeats.put(key, seats);
    }

    private String generateKey(Movie movie) {
        return generateKey(movie.getTitle(), movie.getScreeningDate(), movie.getScreeningTime());
    }

    private String generateKey(String title, String date, String time) {
        return title + "_" + date + "_" + time;
    }
} 