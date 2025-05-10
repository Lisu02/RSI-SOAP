package org.example.model;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "reservation")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation", propOrder = {
    "reservationId",
    "movieTitle",
    "screeningDate",
    "screeningTime",
    "seats",
    "customerName",
    "customerEmail"
})
public class Reservation {

    public Reservation() {
    }

    @XmlElement(required = true)
    private Long reservationId;
    
    @XmlElement(required = true)
    private String movieTitle;
    
    @XmlElement(required = true)
    private String screeningDate;
    
    @XmlElement(required = true)
    private String screeningTime;
    
    @XmlElementWrapper(name = "seats", required = true)
    @XmlElement(name = "seat", required = true)
    private List<String> seats = new ArrayList<>();
    
    @XmlElement(required = true)
    private String customerName;
    
    @XmlElement(required = true)
    private String customerEmail;

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(String screeningDate) {
        this.screeningDate = screeningDate;
    }

    public String getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(String screeningTime) {
        this.screeningTime = screeningTime;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats != null ? seats : new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
} 